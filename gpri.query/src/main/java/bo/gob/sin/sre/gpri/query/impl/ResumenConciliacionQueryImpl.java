package bo.gob.sin.sre.gpri.query.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.ParametrosConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;
import bo.gob.sin.sre.gpri.query.IResumenConciliacionQuery;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
public class ResumenConciliacionQueryImpl implements IResumenConciliacionQuery {

	private static final Logger LOG = LoggerFactory.getLogger(ResumenConciliacionQueryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoListaDto<ResumenConciliacionDto> obtenerResumenPorTipoyGestionPeriodo(
			ParametrosConciliacionDto pParametros) {
		ResultadoGenericoListaDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<ResumenConciliacionDto>();

		NamedParameterJdbcTemplate jdbcTemplateNamed = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		String vQuery = "WITH cte AS (\n" + "   SELECT seq_resumen_conciliacion_nb as resumenConciliacionId,\n"
				+ "				gestion_nb as gestion,periodo_nb as periodo,\n"
				+ "				monto_total_reintegros_nb  as montoTotalReintegros,\n"
				+ "				monto_total_reintegros_nb as montoTotalReparos,\n"
				+ "				monto_total_a_pagar_nb  as montoTotalPagar,\n"
				+ "				monto_total_facturas_nb  as montoTotalFacturas,\n"
				+ "				monto_total_pagado_nb  as montoTotalPagado,\n"
				+ "				(select descripcion from str_transversales.str_cps_clasificadores\n"
				+ "				where tipo_clasificador = 'tipo_conciliacion_id' and estado_id = 'AC'\n"
				+ "				and abreviatura = tipo_conciliacion_id_vc)  as tipoConciliacionId,\n"
				+ "				cantidad_beneficiarios_nb  as cantidadBeneficiarios,\n"
				+ "				(select descripcion from str_transversales.str_cps_clasificadores\n"
				+ "				where tipo_clasificador = 'estado_conciliacion_id' and estado_id = 'AC'\n"
				+ "				and abreviatura = estado_conciliacion_id_vc)   as estadoConciliacionId,\n"
				+ "				fecha_registro_ts\n" + "	from sfe_facturacion.tsfe_riv_resumen_conciliacion\n"
				+ "	where estado_id_vc ='AC' and estado_conciliacion_id_vc != 'ANU' ";

		List<ResumenConciliacionDto> vLista = new ArrayList<ResumenConciliacionDto>();
		Map<String, Object> vNamedParameters = new HashMap<String, Object>();

		try {
			RowMapper<ResumenConciliacionDto> vRowMapper = new BeanPropertyRowMapper<ResumenConciliacionDto>(
					ResumenConciliacionDto.class);

			if (pParametros.getGestion() != null) {
				if (pParametros.getPeriodo() != null) {
					vNamedParameters.put("pGestion", pParametros.getGestion());
					vNamedParameters.put("pPeriodo", pParametros.getPeriodo());
					vQuery = vQuery + "	and gestion_nb =:pGestion and periodo_nb =:pPeriodo \n";
				} else {
					vNamedParameters.put("pGestion", pParametros.getGestion());
					vQuery = vQuery + "	and gestion_nb = :pGestion \n";
				}
			}

			if (pParametros.getTipoConciliacion() != null) {
				vNamedParameters.put("pTipoConciliacion", pParametros.getTipoConciliacion());
				vQuery = vQuery + "	and tipo_conciliacion_id_vc  =:pTipoConciliacion \n";
			}

			vQuery = vQuery + " )\n" + "SELECT *\n" + "FROM  (\n" + "   TABLE  cte\n"
					+ "   ORDER  BY fecha_registro_ts desc\n" + "   LIMIT  :pLimit\n" + "   OFFSET :pOffset\n"
					+ "   ) sub\n" + "RIGHT  JOIN (SELECT count(*) FROM cte) c(totalRegistros) ON true;";
			vNamedParameters.put("pLimit", pParametros.getTamanoPagina());
			vNamedParameters.put("pOffset", pParametros.getPagina());

			vLista = jdbcTemplateNamed.query(vQuery, vNamedParameters, vRowMapper);

			if (vLista.size() > 0 && vLista.get(0).getTotalRegistros() > 0) {
				vRespuesta.setResultadoLista(vLista);
				vRespuesta.setTotalRegistros(vLista.get(0).getTotalRegistros());
				vRespuesta.setOk(true);
			} else {
				vRespuesta.setResultadoLista(null);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
						.set("Obtener Movimientos Por Gestion y Periodo"));
				vRespuesta.setOk(false);
			}
		} catch (Exception e) {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoLista(null);
		}

		vRespuesta.setOk(true);
		return vRespuesta;
	}

}

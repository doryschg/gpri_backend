package bo.gob.sin.sre.gpri.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.query.IMovimientosBeneficiarioConsultaQuery;
import bo.gob.sin.sre.gpri.query.IPersonaBeneficiariosQuery;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
@Transactional("transactionManager")
public class MovimientosBeneficiarioConsultaServiceImpl implements IMovimientosBeneficiarioConsultaQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorBeneficiario(Long pBeneficiarioId) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoListaDto<MovimientoBeneficiarioDto>();

		List<MovimientoBeneficiarioDto> vDetalleConciliacion = new ArrayList<>();
		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vDetalleConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorGestionPeriodo(Integer pGestion,
			Integer pPeriodo, long pLimit, long pOffset) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoListaDto<MovimientoBeneficiarioDto>();

		List<MovimientoBeneficiarioDto> vLista = new ArrayList<MovimientoBeneficiarioDto>();
		String vQuery = " WITH cte AS (\n" + 
				"select m.seq_movimiento_nb as movimientoBeneficiarioId,\n" + 
				"							m.seq_detalle_conciliacion_nb  as detalleConciliacionId,\n" + 
				"							m.seq_beneficiario_nb  as beneficiarioId,\n" + 
				"							m.tipo_movimiento_riv_id_vc as tipoMovimientoRivId,\n" + 
				"							m.tipo_transaccion_riv_id_vc  as tipoTransaccionRivId,\n" + 
				"							m.fecha_transaccion_ts  as fechaTransaccion, m.monto_nb  as monto,\n" + 
				"							m.estado_movimiento_id_vc  as estadoMovimientoId, m.glosa_vc as glosa,\n" + 
				"							m.gestion_origen_nb  as gestionOrigen,m.periodo_origen_nb  as periodoOrigen,\n" + 
				"							b.id_beneficiario_sigep_nb as idBeneficiarioSigep,b.nua_cua_nb nuaCua,\n" + 
				"							m.fecha_transaccion_ts \n" + 
				"							from sfe_facturacion.tsfe_riv_detalle_conciliacion d,\n" + 
				"							sfe_facturacion.tsfe_riv_movimientos m,\n" + 
				"							sfe_facturacion.tsfe_riv_beneficiarios b\n" + 
				"							where d.seq_detalle_conciliacion_nb=m.seq_detalle_conciliacion_nb \n" + 
				"							and d.periodo_nb = m.periodo_origen_nb \n" + 
				"							and d.gestion_nb = m.gestion_origen_nb \n" + 
				"							and d.seq_beneficiario_nb = m.seq_beneficiario_nb \n" + 
				"							and m.seq_beneficiario_nb =  b.seq_beneficiario_nb ";

		try {
			RowMapper<MovimientoBeneficiarioDto> vRowMapper = new BeanPropertyRowMapper<MovimientoBeneficiarioDto>(
					MovimientoBeneficiarioDto.class);
			if (pGestion != null) {
				if (pPeriodo != null) {
					vQuery = vQuery
							+ " and m.gestion_origen_nb = ? and m.periodo_origen_nb =?   and m.estado_id_vc = 'AC'";
				} else {
					vQuery = vQuery + " and m.periodo_origen_nb = ?  and m.estado_id_vc = 'AC'";
				}
			} else {
				vQuery = vQuery + " and m.periodo_origen_nb = ?   and m.estado_id_vc = 'AC'";
			}

			vQuery = vQuery + " )\n" + 
					"SELECT *\n" + 
					"FROM  (\n" + 
					"   TABLE  cte\n" + 
					"   ORDER  BY fecha_transaccion_ts  \n" + 
					"   LIMIT  ?\n" + 
					"   OFFSET ?\n" + 
					"   ) sub\n" + 
					"RIGHT  JOIN (SELECT count(*) FROM cte) c(totalRegistros) ON true;";

			vLista = jdbcTemplate.query(vQuery, vRowMapper, pGestion, pPeriodo, pLimit, pOffset);

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

	@Override
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorDetalleConciliacionId(
			Long pDetalleConciliacionId, long pLimit, long pOffset) {
		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoListaDto<MovimientoBeneficiarioDto>();

		List<MovimientoBeneficiarioDto> vLista = new ArrayList<MovimientoBeneficiarioDto>();
		String vQuery = "WITH cte AS (\n" + 
				"select m.seq_movimiento_nb as movimientoBeneficiarioId,\n" + 
				"				m.seq_detalle_conciliacion_nb  as detalleConciliacionId,\n" + 
				"				m.seq_beneficiario_nb  as beneficiarioId,\n" + 
				"				(select descripcion from str_transversales.str_cps_clasificadores\n" + 
				"				where tipo_clasificador = 'tipo_movimiento_riv_id' and estado_id = 'AC' \n" + 
				"				and abreviatura = m.tipo_movimiento_riv_id_vc)  as tipoMovimientoRivId,\n" + 
				"				(select descripcion from str_transversales.str_cps_clasificadores\n" + 
				"				where tipo_clasificador = 'tipo_transaccion_riv_id' and estado_id = 'AC' \n" + 
				"				and abreviatura = m.tipo_transaccion_riv_id_vc)  as tipoTransaccionRivId,\n" + 
				"				m.fecha_transaccion_ts  as fechaTransaccion, m.monto_nb  as monto,\n" + 
				"				(select descripcion from str_transversales.str_cps_clasificadores\n" + 
				"				where tipo_clasificador = 'estado_movimiento_id' and estado_id = 'AC' \n" + 
				"				and abreviatura = m.estado_movimiento_id_vc)  as estadoMovimientoId,\n" + 
				"				m.glosa_vc as glosa,\n" + 
				"				m.gestion_origen_nb  as gestionOrigen,m.periodo_origen_nb  as periodoOrigen,\n" + 
				"				b.id_beneficiario_sigep_nb as idBeneficiarioSigep,b.nua_cua_nb nuaCua,\n" + 
				"				m.fecha_transaccion_ts \n" + 
				"				from sfe_facturacion.tsfe_riv_detalle_conciliacion d,\n" + 
				"				sfe_facturacion.tsfe_riv_movimientos m,\n" + 
				"				sfe_facturacion.tsfe_riv_beneficiarios b\n" + 
				"				where d.seq_detalle_conciliacion_nb=m.seq_detalle_conciliacion_nb \n" + 
				"				and d.periodo_nb = m.periodo_origen_nb \n" + 
				"				and d.gestion_nb = m.gestion_origen_nb \n" + 
				"				and d.seq_beneficiario_nb = m.seq_beneficiario_nb \n" + 
				"				and m.seq_beneficiario_nb =  b.seq_beneficiario_nb\n" + 
				"				and m.seq_detalle_conciliacion_nb  = ? and m.estado_id_vc = 'AC' \n" + 
				"				and m.estado_movimiento_id_vc != 'ANU'\n" + 
				"   )\n" + 
				"SELECT *\n" + 
				"FROM  (\n" + 
				"   TABLE  cte\n" + 
				"   ORDER  BY fecha_transaccion_ts  \n" + 
				"   LIMIT  ?\n" + 
				"   OFFSET ?\n" + 
				"   ) sub\n" + 
				"RIGHT  JOIN (SELECT count(*) FROM cte) c(totalRegistros) ON true; ";

		try {
			RowMapper<MovimientoBeneficiarioDto> vRowMapper = new BeanPropertyRowMapper<MovimientoBeneficiarioDto>(
					MovimientoBeneficiarioDto.class);

			vLista = jdbcTemplate.query(vQuery, vRowMapper, pDetalleConciliacionId, pLimit, pOffset);

			if (vLista.size() > 0 && vLista.get(0).getTotalRegistros() > 0) {
				vRespuesta.setResultadoLista(vLista);
				vRespuesta.setTotalRegistros(vLista.get(0).getTotalRegistros());
				vRespuesta.setOk(true);
			} else {
				vRespuesta.setResultadoLista(null);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
						.set("Obtener Movimientos Por Detalle Conciliacion"));
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

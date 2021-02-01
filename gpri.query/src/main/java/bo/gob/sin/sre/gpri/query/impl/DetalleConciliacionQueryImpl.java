package bo.gob.sin.sre.gpri.query.impl;

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
import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.query.IDetalleConciliacionQuery;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
public class DetalleConciliacionQueryImpl implements IDetalleConciliacionQuery {

	private static final Logger LOG = LoggerFactory.getLogger(DetalleConciliacionQueryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetalleConciliacionPaginado(long pPersonaId,
			int pGestion, int pPeriodo, int pPagina, int pTamanioPagina) {
		ResultadoGenericoListaDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<DetalleConciliacionDto>();

		NamedParameterJdbcTemplate jdbcTemplateNamed = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

		if (pPersonaId > 0) {

			Map<String, Object> vNamedParameters = new HashMap<String, Object>();

			String vQuery = "WITH cte AS (\n" + "  select det.seq_detalle_conciliacion_nb as detalleConciliacionId,\n"
					+ "					det.seq_resumen_conciliacion_nb as resumenConciliacionId,\n"
					+ "					det.tipo_conciliacion_id_vc as tipoConciliacionId,\n"
					+ "					det.seq_beneficiario_nb as beneficiarioId,\n"
					+ "					det.seq_formulario_vc as formularioId,\n"
					+ "					det.seq_salario_nb as salarioId,\n"
					+ "					det.gestion_nb as gestion,\n"
					+ "					det.periodo_nb as periodo,\n"
					+ "					det.cantidad_facturas_nb as cantidadFacturas,\n"
					+ "					det.monto_total_facturas_nb as montoTotalFacturas,\n"
					+ "					det.monto_total_reintegros_nb as montoTotalReintegros,\n"
					+ "					det.monto_total_reparos_nb as montoTotalReparos,\n"
					+ "					det.monto_total_a_pagar_nb as montoTotalPagar,\n"
					+ "					det.monto_total_pagado_nb as montoTotalPagado,\n"
					+ "					det.estado_proceso_pago_id_vc as estadoProcesoPagoId,\n"
					+ "					det.id_pago_bono_nb  as pagoBonoId,\n"
					+ "					det.usuario_registro_nb as usuarioRegistroId,\n"
					+ "					det.usuario_ultima_modificacion_nb as usuarioUltimaModificacionId,\n"
					+ "					det.fecha_registro_ts as fechaRegistro,\n"
					+ "					det.fecha_ultima_modificacion_ts as fechaUltimaModificacion,\n"
					+ "					det.estado_id_vc as estadoId,\n" + "					det.fecha_registro_ts\n"
					+ "					from sfe_facturacion.tsfe_riv_detalle_conciliacion det, sfe_facturacion.tsfe_riv_beneficiarios trb\n"
					+ "					where trb.seq_beneficiario_nb = det.seq_beneficiario_nb \n"
					+ "					and	trb.persona_id_nb = :pPersonaId and trb.estado_beneficiario_id_vc = 'H'";
			/*
			 * +
			 * "and det.estado_id_vc = 'AC' and det.gestion_nb = ? and det.periodo_nb = ?\n"
			 * + "order by det.fecha_registro_ts desc\n" + "limit ? offset ?";
			 */
			try {
				RowMapper<DetalleConciliacionDto> vRowMapper = new BeanPropertyRowMapper<DetalleConciliacionDto>(
						DetalleConciliacionDto.class);
				// List<DetalleConciliacionDto> vLista = jdbcTemplate.query(vQuery, vRowMapper,
				// pPersonaId, pGestion, pPeriodo, pTamanioPagina, pPagina);
				vNamedParameters.put("pPersonaId", pPersonaId);

				if (pGestion > 0) {
					vNamedParameters.put("pGestion", pGestion);
					if (pPeriodo > 0) {
						vNamedParameters.put("pPeriodo", pPeriodo);
						vQuery = vQuery + "and gestion_nb =:pGestion and periodo_nb =:pPeriodo \n";
					} else {
						vQuery = vQuery + "and gestion_nb = :pGestion \n";
					}
				}

//				if (pModalidad != null) {
//					vNamedParameters.put("pTipoConciliacion", pModalidad);
//					vQuery = vQuery + "	and tipo_conciliacion_id_vc  =:pTipoConciliacion \n";
//				}

				vNamedParameters.put("pLimit", pTamanioPagina);
				vNamedParameters.put("pOffset", pPagina);
				vQuery = vQuery + "   )\n" + "SELECT *\n" + "FROM  (\n" + "   TABLE  cte\n"
						+ "   ORDER  BY fecha_registro_ts \n" + "   LIMIT  :pLimit\n" + "   OFFSET :pOffset\n"
						+ "   ) sub\n" + "RIGHT  JOIN (SELECT count(*) FROM cte) c(totalRegistros) ON true;";

				List<DetalleConciliacionDto> vLista = jdbcTemplateNamed.query(vQuery, vNamedParameters, vRowMapper);

				if (vLista.size() > 0 && vLista.get(0).getTotalRegistros() > 0) {

					vRespuesta.setResultadoLista(vLista);
					vRespuesta.setTotalRegistros(vLista.get(0).getTotalRegistros());
					vRespuesta.setOk(true);
				} else {

					vRespuesta.setResultadoLista(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoLista(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener obtenerDetalleConciliacionPaginado"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("obtenerDetalleConciliacionPaginado"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoLista(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetalleConciliacionPorResumenPaginado(
			long pResumenConciliacionId, int pPagina, int pTamanioPagina, String pDocumentoBeneficiario) {
		ResultadoGenericoListaDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<DetalleConciliacionDto>();

		if (pResumenConciliacionId != 0) {
			String vQuery = " WITH cte AS (\n"
					+ "select distinct	  	per.numero_documento  ||' - '||	per.nombres || ' ' || per.primer_apellido  ||' ' || per.segundo_apellido as beneficiario, \n" + 
					"					det.id_pago_bono_nb bonoSigepId,\n" + 
					"					det.seq_detalle_conciliacion_nb as detalleConciliacionId,\n" + 
					"					det.seq_resumen_conciliacion_nb as resumenConciliacionId,\n" + 
					"					(select descripcion from str_transversales.str_cps_clasificadores\n" + 
					"					where tipo_clasificador = 'tipo_conciliacion_id' and estado_id = 'AC'\n" + 
					"					and abreviatura = det.tipo_conciliacion_id_vc)  as tipoConciliacionId,\n" + 
					"					det.seq_beneficiario_nb as beneficiarioId, det.seq_formulario_vc as formularioId,\n" + 
					"					det.seq_salario_nb as salarioId, det.gestion_nb as gestion,\n" + 
					"					det.periodo_nb as periodo, det.cantidad_facturas_nb as cantidadFacturas,\n" + 
					"					det.monto_total_facturas_nb as montoTotalFacturas,\n" + 
					"					det.monto_total_reintegros_nb as montoTotalReintegros,\n" + 
					"					det.monto_total_reparos_nb as montoTotalReparos,\n" + 
					"					det.monto_total_a_pagar_nb as montoTotalPagar,\n" + 
					"					det.monto_total_pagado_nb as montoTotalPagado,\n" + 
					"					(select descripcion from str_transversales.str_cps_clasificadores\n" + 
					"					where tipo_clasificador = 'estado_proceso_pago_id' and estado_id = 'AC'\n" + 
					"					and abreviatura = det.estado_proceso_pago_id_vc)  as estadoProcesoPagoId,\n" + 
					"					det.id_pago_bono_nb  as pagoBonoId, det.usuario_registro_nb as usuarioRegistroId,\n" + 
					"					det.usuario_ultima_modificacion_nb as usuarioUltimaModificacionId,\n" + 
					"					det.fecha_registro_ts as fechaRegistro,\n" + 
					"					det.fecha_ultima_modificacion_ts as fechaUltimaModificacion,\n" + 
					"					det.estado_id_vc as estadoId from sfe_facturacion.tsfe_riv_detalle_conciliacion det, sfe_facturacion.tsfe_riv_personas per, \n" + 
					"					sfe_facturacion.tsfe_riv_beneficiarios ben\n" + 
					"					where det.seq_beneficiario_nb = ben.seq_beneficiario_nb \n" + 
					"					and ben.persona_id_nb = per.persona_id \n" + 
					"					and per.estado_id ='AC'\n" + 
					"					and ben.estado_id_vc = 'AC' \n" + 
					"					and ben.estado_beneficiario_id_vc = 'H'\n" + 
					"					and	det.seq_resumen_conciliacion_nb = ?\n" + 
					"					and det.estado_id_vc = 'AC'\n" + 
					"					and det.estado_proceso_pago_id_vc != 'ANU'";

			if (pDocumentoBeneficiario != null && pDocumentoBeneficiario.trim() != "" && !pDocumentoBeneficiario.trim().equals("0")) {
				vQuery = vQuery + "  and per.numero_documento  = ? ";
			}

			vQuery = vQuery + "  )\n" + "SELECT *\n" + "FROM  (\n" + "   TABLE  cte\n" + "   ORDER  BY fechaRegistro \n"
					+ "   LIMIT  ?\n" + "   OFFSET ?\n" + "   ) sub\n"
					+ "RIGHT  JOIN (SELECT count(*) FROM cte) c(totalRegistros) ON true;";
			try {
				RowMapper<DetalleConciliacionDto> vRowMapper = new BeanPropertyRowMapper<DetalleConciliacionDto>(
						DetalleConciliacionDto.class);

				List<DetalleConciliacionDto> vLista;
				if (pDocumentoBeneficiario != null && !pDocumentoBeneficiario.trim().equals("0")){
					vLista = jdbcTemplate.query(vQuery, vRowMapper, pResumenConciliacionId, pDocumentoBeneficiario,
							pTamanioPagina, pPagina);
				} else {
					vLista = jdbcTemplate.query(vQuery, vRowMapper, pResumenConciliacionId, pTamanioPagina, pPagina);
				}

				if (vLista.size() > 0 && vLista.get(0).getTotalRegistros() > 0) {

					vRespuesta.setResultadoLista(vLista);
					vRespuesta.setTotalRegistros(vLista.get(0).getTotalRegistros());
					vRespuesta.setOk(true);
				} else {

					vRespuesta.setResultadoLista(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoLista(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener obtenerDetalleConciliacionPaginado"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("obtenerDetalleConciliacionPaginado"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoLista(null);
		}

		return vRespuesta;
	}

}

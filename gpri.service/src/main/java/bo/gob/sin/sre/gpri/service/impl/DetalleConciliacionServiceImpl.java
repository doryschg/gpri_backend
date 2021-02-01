package bo.gob.sin.sre.gpri.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.client.IBeneficiarioRestClient;
import bo.gob.sin.sre.gpri.domain.IDetalleConciliacionDomain;
import bo.gob.sin.sre.gpri.domain.IMovimientoBeneficiarioDomain;
import bo.gob.sin.sre.gpri.domain.IResumenConciliacionDomain;
import bo.gob.sin.sre.gpri.domain.ISalarioBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.RespuestaConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.SolicitudConciliacionDto;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoMovimiento;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoConciliacion;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoMovimiento;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoTransaccion;
import bo.gob.sin.sre.gpri.model.SreTsfeRivDetalleConciliacion;
import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;
import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;
import bo.gob.sin.sre.gpri.service.IDetalleConciliacionService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
@Transactional("transactionManager")
public class DetalleConciliacionServiceImpl implements IDetalleConciliacionService {

	private static final Logger LOG = LoggerFactory.getLogger(DetalleConciliacionServiceImpl.class);

	@Autowired
	private IDetalleConciliacionDomain detalleConciliacionDomain;

	@Autowired
	private IResumenConciliacionDomain resumenConciliacionDomain;

	@Autowired
	private IMovimientoBeneficiarioDomain movimientoBeneficiarioDomain;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private ISalarioBeneficiarioHistoricoDomain salarioBeneficiarioHistoricoDomain;

	@Autowired
	private IBeneficiarioRestClient beneficiarioRestClient;

	@Override
	public ResultadoGenericoDto<RespuestaConciliacionDto> recepionFormulario(
			SolicitudConciliacionDto pSolicitudConciliacion, Long pUsuarioId) {

		ResultadoGenericoDto<RespuestaConciliacionDto> vRespuesta = new ResultadoGenericoDto<RespuestaConciliacionDto>();

		vRespuesta.setOk(false);
		DetalleConciliacionDto vDetalleResumenConciliacion = new DetalleConciliacionDto();
		vDetalleResumenConciliacion.setGestion(pSolicitudConciliacion.getAnio());
		vDetalleResumenConciliacion.setPeriodo(pSolicitudConciliacion.getPeriodo());
		vDetalleResumenConciliacion.setMontoTotalReintegros(pSolicitudConciliacion.getMontoDeterminacionPago());
		vDetalleResumenConciliacion.setMontoTotalPagar(vDetalleResumenConciliacion.getMontoTotalReintegros());
		vDetalleResumenConciliacion.setMontoTotalReparos(new BigDecimal(0));

		if (pSolicitudConciliacion.getAnio() <= 2020) {
			vRespuesta.setResultadoObjeto(null);
			vRespuesta.setOk(false);
			StrMensajeAplicacionDto vMensaje = new StrMensajeAplicacionDto();
			vMensaje.setDescripcion("La gestión para el pago de reintegro es válida a partir de Enero / 2021.");
			vMensaje.setMensajeAplicacionId((long) EnumSubsistema.RECAUDACIONES.getId());
			vMensaje.setDescripcionUi("La gestión para el pago de reintegro es válida a partir de Enero / 2021.");
			vRespuesta.addMensaje(vMensaje);
			return vRespuesta;
		}

		var vRespuestaBeneficiario = beneficiarioRestClient
				.obtenerBeneficiarioPorCodigoPersona(pSolicitudConciliacion.getCodigoPersona());
		if (vRespuestaBeneficiario.getResultadoObjeto() == null) {
			vRespuesta.setResultadoObjeto(null);
			vRespuesta.setOk(false);
			StrMensajeAplicacionDto vMensaje = new StrMensajeAplicacionDto();
			vMensaje.setDescripcion("No se encontro registro como beneficiario.");
			vMensaje.setMensajeAplicacionId((long) EnumSubsistema.RECAUDACIONES.getId());
			vMensaje.setDescripcionUi("No se encontro registro como beneficiario.");
			vRespuesta.addMensaje(vMensaje);
			return vRespuesta;
		}

		vDetalleResumenConciliacion.setBeneficiarioId(vRespuestaBeneficiario.getResultadoObjeto().getBeneficiarioId());
		vDetalleResumenConciliacion
				.setIdBeneficiarioSigep(vRespuestaBeneficiario.getResultadoObjeto().getIdBeneficiarioSigep());

		var vSalariosHistorico = salarioBeneficiarioHistoricoDomain
				.obtenerUltimoRegistroHistoricoPorBeneficiarioId(vDetalleResumenConciliacion.getBeneficiarioId());

		if (vSalariosHistorico != null) {
			vDetalleResumenConciliacion.setSalarioId(vSalariosHistorico.getSalarioId());
			vDetalleResumenConciliacion.setUsuarioRegistroId(pUsuarioId);
			vDetalleResumenConciliacion.setMontoTotalFacturas(pSolicitudConciliacion.getMontoTotalFacturas());
			vDetalleResumenConciliacion.setCantidadFacturas(pSolicitudConciliacion.getCantidadFacturas());
			vDetalleResumenConciliacion.setFormularioId(pSolicitudConciliacion.getFormulario());
		} else {
			vRespuesta.setResultadoObjeto(null);
			vRespuesta.setOk(false);

			StrMensajeAplicacionDto vMensaje = new StrMensajeAplicacionDto();
			vMensaje.setDescripcion("No se encontraron sus registros de salario.");
			vMensaje.setMensajeAplicacionId((long) EnumSubsistema.RECAUDACIONES.getId());
			vMensaje.setDescripcionUi("No se encontraron sus registros de salario.");
			vRespuesta.addMensaje(vMensaje);
			return vRespuesta;
		}

		var vRespuestaRegistro = this.registrar(vDetalleResumenConciliacion);

		if (vRespuestaRegistro.isOk()) {
			vRespuesta.setOk(true);
			vRespuesta.setResultadoObjeto(new RespuestaConciliacionDto());
			vRespuesta.getResultadoObjeto().setDetalleResumenConciliacionId(
					vRespuestaRegistro.getResultadoObjeto().getDetalleConciliacionId());
		} else {
			vRespuesta.setMensajes(vRespuestaRegistro.getMensajes());
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<DetalleConciliacionDto> registrar(DetalleConciliacionDto pDetalleConciliacion) {
		ResultadoGenericoDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoDto<DetalleConciliacionDto>();
		vRespuesta.setOk(false);
		try {

			SreTsfeRivDetalleConciliacion vDetallesRecuperadosBeneficiario = detalleConciliacionDomain
					.obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
							pDetalleConciliacion.getBeneficiarioId(), pDetalleConciliacion.getGestion(),
							pDetalleConciliacion.getPeriodo(), EnumTipoConciliacion.MANUAL.getId());

			if (vDetallesRecuperadosBeneficiario != null) {
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_CONCILIACION_DEL_MES_YA_REGISTRADA)
						.set("guardar Detalle Conciliacion"));
				return vRespuesta;
			}

			ModelMapper vEntityDtoMapper = new ModelMapper();
			vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
			SreTsfeRivDetalleConciliacion vDetalleConciliacionEntidad = vEntityDtoMapper.map(pDetalleConciliacion,
					SreTsfeRivDetalleConciliacion.class);

			vDetalleConciliacionEntidad.setMontoTotalPagado(new BigDecimal(0L));

			List<SreTsfeRivResumenConciliacion> vDatosResumenConciliacion = resumenConciliacionDomain
					.obtenerResumenConciliacionPorTipoGestionyPeriodo(EnumTipoConciliacion.MANUAL.getId(),
							vDetalleConciliacionEntidad.getGestion(), vDetalleConciliacionEntidad.getPeriodo());

			SreTsfeRivResumenConciliacion vResumenConciliacion = new SreTsfeRivResumenConciliacion();
			if (vDatosResumenConciliacion.isEmpty()) {
				vResumenConciliacion.setGestion(vDetalleConciliacionEntidad.getGestion());
				vResumenConciliacion.setPeriodo(vDetalleConciliacionEntidad.getPeriodo());

				vResumenConciliacion.setMontoTotalFacturas(vDetalleConciliacionEntidad.getMontoTotalFacturas());

				vResumenConciliacion.setMontoTotalPagar(vDetalleConciliacionEntidad.getMontoTotalPagar());
				vResumenConciliacion.setMontoTotalPagado(new BigDecimal(0L));
				vResumenConciliacion.setMontoTotalReintegros(vDetalleConciliacionEntidad.getMontoTotalReintegros());
				vResumenConciliacion.setMontoTotalReparos(vDetalleConciliacionEntidad.getMontoTotalReparos());
				vResumenConciliacion.setUsuarioRegistroId(vDetalleConciliacionEntidad.getUsuarioRegistroId());
				resumenConciliacionDomain.insertar(vResumenConciliacion);
			} else {
				vResumenConciliacion = vDatosResumenConciliacion.get(0);
				vResumenConciliacion.calcularMontoFacturas(vDetalleConciliacionEntidad.getMontoTotalFacturas());
				vResumenConciliacion.calcularMontoPagar(vDetalleConciliacionEntidad.getMontoTotalPagar());
				vResumenConciliacion.calcularMontoPagado(vResumenConciliacion.getMontoTotalPagar(),
						pDetalleConciliacion.getMontoTotalReparos());
				vResumenConciliacion.calcularMontoReintegro(vDetalleConciliacionEntidad.getMontoTotalReintegros());
				vResumenConciliacion.calcularMontoReparo(vDetalleConciliacionEntidad.getMontoTotalReparos());
				vResumenConciliacion.incrementarBeneficiario();
				vResumenConciliacion.setUsuarioRegistroId(vDetalleConciliacionEntidad.getUsuarioRegistroId());
				resumenConciliacionDomain.modificar(vResumenConciliacion);
			}

			vDetalleConciliacionEntidad.setResumenConciliacionId(vResumenConciliacion.getResumenConciliacionId());
			detalleConciliacionDomain.insertar(vDetalleConciliacionEntidad);
			pDetalleConciliacion = vEntityDtoMapper.map(vDetalleConciliacionEntidad, DetalleConciliacionDto.class);

			SreTsfeRivMovimientos vMovimiento = new SreTsfeRivMovimientos();
			vMovimiento.setBeneficiarioId(vDetalleConciliacionEntidad.getBeneficiarioId());
			vMovimiento.setDetalleConciliacionId(vDetalleConciliacionEntidad.getDetalleConciliacionId());
			vMovimiento.setTipoMovimientoRivId(EnumTipoMovimiento.CREDITO.getId());
			vMovimiento.setTipoTransaccionRivId(EnumTipoTransaccion.REINTEGRO.getId());
			vMovimiento.setEstadoMovimientoId(EnumEstadoMovimiento.PENDIENTE.getId());
			vMovimiento.setMonto(vDetalleConciliacionEntidad.getMontoTotalPagar());
			vMovimiento.setGestionOrigen(vDetalleConciliacionEntidad.getGestion());
			vMovimiento.setPeriodoOrigen(vDetalleConciliacionEntidad.getPeriodo());
			vMovimiento.setUsuarioRegistroId(vDetalleConciliacionEntidad.getUsuarioRegistroId());
			vMovimiento.setMovimientoBeneficiarioRefId(null);
			movimientoBeneficiarioDomain.insertar(vMovimiento);
			vRespuesta.setOk(true);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogExcepcion.registrar(e, LOG, MethodSign.build(pDetalleConciliacion));
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
					.set("guardar Detalle Conciliacion"));
		}

		vRespuesta.setResultadoObjeto(pDetalleConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<DetalleConciliacionDto> actualizar(DetalleConciliacionDto pDetalleConciliacion) {
		ResultadoGenericoDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoDto<DetalleConciliacionDto>();
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		SreTsfeRivDetalleConciliacion vDetalleConciliacionEntidad = vEntityDtoMapper.map(pDetalleConciliacion,
				SreTsfeRivDetalleConciliacion.class);

		detalleConciliacionDomain.modificar(vDetalleConciliacionEntidad);
		vRespuesta.setOk(true);
		vRespuesta.setResultadoObjeto(pDetalleConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetallePorBeneficiario(Long pBeneficiarioId) {
		ResultadoGenericoListaDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<DetalleConciliacionDto>();
		List<SreTsfeRivDetalleConciliacion> vDetalleConciliacionEntidadLista = detalleConciliacionDomain
				.obtenerDetalleConciliacionPorBeneficiarioId(pBeneficiarioId);

		List<DetalleConciliacionDto> vDetalleConciliacion = mapEntitytoDtoList(vDetalleConciliacionEntidadLista);
		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vDetalleConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetallePorGestionPeriodo(Integer pGestion,
			Integer pPeriodo) {
		ResultadoGenericoListaDto<DetalleConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<DetalleConciliacionDto>();

		List<SreTsfeRivDetalleConciliacion> vDetalleConciliacionEntidadLista = new ArrayList<>();
		if (pGestion != null) {
			if (pPeriodo != null) {
				vDetalleConciliacionEntidadLista = detalleConciliacionDomain
						.obtenerDetalleConciliacionPorGestionyPeriodo(pGestion, pPeriodo);
			} else {
				vDetalleConciliacionEntidadLista = detalleConciliacionDomain
						.obtenerDetalleConciliacionPorGestion(pGestion);
			}
		} else {
			vDetalleConciliacionEntidadLista = detalleConciliacionDomain.obtenerDetalleConciliacionPorPeriodo(pPeriodo);
		}

		List<DetalleConciliacionDto> vDetalleConciliacion = mapEntitytoDtoList(vDetalleConciliacionEntidadLista);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vDetalleConciliacion);
		return vRespuesta;
	}

	private List<DetalleConciliacionDto> mapEntitytoDtoList(List<SreTsfeRivDetalleConciliacion> vEntidadLista) {
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
		List<DetalleConciliacionDto> vListaDto = new ArrayList<>();
		vListaDto = vEntityDtoMapper.map(vEntidadLista, new TypeToken<List<DetalleConciliacionDto>>() {
		}.getType());
		return vListaDto;
	}

}

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
import bo.gob.sin.sre.gpri.domain.IDetalleConciliacionDomain;
import bo.gob.sin.sre.gpri.domain.IResumenConciliacionDomain;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;
import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;
import bo.gob.sin.sre.gpri.service.IResumenConciliacionService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
@Transactional("transactionManager")
public class ResumenConciliacionServiceImpl implements IResumenConciliacionService {

	private static final Logger LOG = LoggerFactory.getLogger(ResumenConciliacionServiceImpl.class);

	@Autowired
	private IResumenConciliacionDomain resumenConciliacionDomain;
	
	@Autowired
	private IDetalleConciliacionDomain detalleConciliacionDomain;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoDto<ResumenConciliacionDto> registrar(ResumenConciliacionDto pResumenConciliacion) {
		ResultadoGenericoDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoDto<ResumenConciliacionDto>();

		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		var vDatosResumen = resumenConciliacionDomain.obtenerResumenConciliacionPorGestionyPeriodo(
				pResumenConciliacion.getGestion(), pResumenConciliacion.getPeriodo());

		try {
			if (!vDatosResumen.isEmpty()) {
				SreTsfeRivResumenConciliacion vRegistroResumenActualVigente = vDatosResumen.get(0);

				vRegistroResumenActualVigente.calcularMontoFacturas(pResumenConciliacion.getMontoTotalFacturas());
				vRegistroResumenActualVigente.calcularMontoPagado(pResumenConciliacion.getMontoTotalPagar(),
						pResumenConciliacion.getMontoTotalReparos());
				vRegistroResumenActualVigente.calcularMontoPagar(pResumenConciliacion.getMontoTotalPagar());
				vRegistroResumenActualVigente.calcularMontoReintegro(pResumenConciliacion.getMontoTotalReintegros());
				vRegistroResumenActualVigente.calcularMontoReparo(pResumenConciliacion.getMontoTotalReparos());
				vRegistroResumenActualVigente.incrementarBeneficiario();

				vRegistroResumenActualVigente.setUsuarioRegistroId(pResumenConciliacion.getUsuarioRegistroId());
				resumenConciliacionDomain.modificar(vRegistroResumenActualVigente);
				pResumenConciliacion = vEntityDtoMapper.map(vRegistroResumenActualVigente,
						ResumenConciliacionDto.class);
			} else {

				SreTsfeRivResumenConciliacion vResumenConciliacionEntidad = vEntityDtoMapper.map(pResumenConciliacion,
						SreTsfeRivResumenConciliacion.class);
				vResumenConciliacionEntidad.setCantidadBeneficiarios(1L);
				resumenConciliacionDomain.insertar(vResumenConciliacionEntidad);
				pResumenConciliacion = vEntityDtoMapper.map(vResumenConciliacionEntidad, ResumenConciliacionDto.class);
			}
			vRespuesta.setOk(true);

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogExcepcion.registrar(e, LOG, MethodSign.build(pResumenConciliacion));
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
					.set("guardar Resumen Conciliacion"));
		}

		vRespuesta.setResultadoObjeto(pResumenConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<ResumenConciliacionDto> actualizar(ResumenConciliacionDto pResumenConciliacion) {
		ResultadoGenericoDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoDto<ResumenConciliacionDto>();
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		SreTsfeRivResumenConciliacion vResumenConciliacionEntidad = vEntityDtoMapper.map(pResumenConciliacion,
				SreTsfeRivResumenConciliacion.class);

		resumenConciliacionDomain.modificar(vResumenConciliacionEntidad);
		vRespuesta.setOk(true);
		vRespuesta.setResultadoObjeto(pResumenConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<ResumenConciliacionDto> obtenerResumenConciliacionPorId(Long pResumenConciliacionId) {
		ResultadoGenericoDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoDto<ResumenConciliacionDto>();

		SreTsfeRivResumenConciliacion vResumenConciliacionEntidad = new SreTsfeRivResumenConciliacion();

		vResumenConciliacionEntidad = resumenConciliacionDomain.obtenerPorResumenConciliacionId(pResumenConciliacionId);

		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		ResumenConciliacionDto vResumenConciliacionDto = vEntityDtoMapper.map(vResumenConciliacionEntidad,
				ResumenConciliacionDto.class);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoObjeto(vResumenConciliacionDto);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<ResumenConciliacionDto> obtenerResumenConciliacionPorGestionPeriodo(
			Integer pGestion, Integer pPeriodo) {

		ResultadoGenericoListaDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoListaDto<ResumenConciliacionDto>();

		List<SreTsfeRivResumenConciliacion> vResumenConciliacionEntidadLista = new ArrayList<>();
		if (pGestion != null) {
			if (pPeriodo != null) {
				vResumenConciliacionEntidadLista = resumenConciliacionDomain
						.obtenerResumenConciliacionPorGestionyPeriodo(pGestion, pPeriodo);
			} else {
				vResumenConciliacionEntidadLista = resumenConciliacionDomain
						.obtenerResumenConciliacionPorGestion(pGestion);
			}
		} else {
			vResumenConciliacionEntidadLista = resumenConciliacionDomain.obtenerResumenConciliacionPorPeriodo(pPeriodo);
		}

		List<ResumenConciliacionDto> vResumenConciliacionEntidadDto = mapEntitytoDtoList(
				vResumenConciliacionEntidadLista);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vResumenConciliacionEntidadDto);
		return vRespuesta;
	}

	private List<ResumenConciliacionDto> mapEntitytoDtoList(List<SreTsfeRivResumenConciliacion> vEntidadLista) {
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
		List<ResumenConciliacionDto> vListaDto = new ArrayList<>();
		vListaDto = vEntityDtoMapper.map(vEntidadLista, new TypeToken<List<ResumenConciliacionDto>>() {
		}.getType());
		return vListaDto;
	}

	@Override
	public ResultadoGenericoDto<ResumenConciliacionDto> conciliar(ResumenConciliacionDto pResumenConciliacion) {
		ResultadoGenericoDto<ResumenConciliacionDto> vRespuesta = new ResultadoGenericoDto<ResumenConciliacionDto>();
		vRespuesta.setOk(false);
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
		try {

			SreTsfeRivResumenConciliacion vResumenConciliacionEntidad = vEntityDtoMapper.map(pResumenConciliacion,
					SreTsfeRivResumenConciliacion.class);

			resumenConciliacionDomain.conciliar(vResumenConciliacionEntidad);
			vRespuesta.setOk(true);
			vRespuesta.setResultadoObjeto(pResumenConciliacion);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogExcepcion.registrar(e, LOG, MethodSign.build(pResumenConciliacion));
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
					.set("guardar Detalle Conciliacion"));
		}
		return vRespuesta;
	}

	@Override	
	public ResultadoGenericoDto<Boolean> actualizarMontoTotalPagadoResumenConciliacion(Long pResumenConciliacionId,
			Long pUsuarioModificacionId) {
		ResultadoGenericoDto<Boolean> vRespuesta = new ResultadoGenericoDto<Boolean>();
		vRespuesta.setOk(false);
		vRespuesta.setResultadoObjeto(false);
		BigDecimal vMontoTotalPagado = detalleConciliacionDomain
				.obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(pResumenConciliacionId);

		var vResumenConciliacionEntidad = resumenConciliacionDomain
				.obtenerPorResumenConciliacionId(pResumenConciliacionId);		

		if (vResumenConciliacionEntidad != null) {
			vResumenConciliacionEntidad.setMontoTotalPagado(vMontoTotalPagado);
			vResumenConciliacionEntidad.setUsuarioUltimaModificacionId(pUsuarioModificacionId);
			vResumenConciliacionEntidad = resumenConciliacionDomain.actualizarMontoTotalPagado(pResumenConciliacionId,
					vMontoTotalPagado, pUsuarioModificacionId);
			vRespuesta.setOk(true);
			vRespuesta.setResultadoObjeto(vResumenConciliacionEntidad.getMontoTotalPagar().equals(vMontoTotalPagado));
		}
		
		return vRespuesta;
	}


}

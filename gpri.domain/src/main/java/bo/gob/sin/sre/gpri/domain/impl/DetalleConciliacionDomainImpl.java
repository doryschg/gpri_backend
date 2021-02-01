package bo.gob.sin.sre.gpri.domain.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IDetalleConciliacionDao;
import bo.gob.sin.sre.gpri.domain.IDetalleConciliacionDomain;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoProcesoPago;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoConciliacion;
import bo.gob.sin.sre.gpri.model.SreTsfeRivDetalleConciliacion;

@Service
@Transactional("transactionManager")
public class DetalleConciliacionDomainImpl implements IDetalleConciliacionDomain {
	private static final Logger LOG = LoggerFactory.getLogger(DetalleConciliacionDomainImpl.class);

	@Autowired
	private IDetalleConciliacionDao detalleConciliacionDao;

	@Override
	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorId(long pDetalleConciliacionId) {
		return detalleConciliacionDao.findById(pDetalleConciliacionId);
	}

	@Override
	public SreTsfeRivDetalleConciliacion insertar(SreTsfeRivDetalleConciliacion pDetalleConciliacion) {
		pDetalleConciliacion.setFechaRegistro(LocalDateTime.now());
		pDetalleConciliacion.setUsuarioUltimaModificacionId(pDetalleConciliacion.getUsuarioRegistroId());
		pDetalleConciliacion.setFechaUltimaModificacion(LocalDateTime.now());
		pDetalleConciliacion.setEstadoProcesoPagoId(EnumEstadoProcesoPago.PENDIENTE.getId());
		pDetalleConciliacion.setTipoConciliacionId(EnumTipoConciliacion.MANUAL.getId());
		pDetalleConciliacion.setEstadoId("AC");
		detalleConciliacionDao.save(pDetalleConciliacion);
		return pDetalleConciliacion;
	}

	@Override
	public SreTsfeRivDetalleConciliacion modificar(SreTsfeRivDetalleConciliacion pDetalleConciliacion) {
		if (detalleConciliacionDao.findById(pDetalleConciliacion.getBeneficiarioId()) != null) {
			detalleConciliacionDao.save(pDetalleConciliacion);
		} else {
			return null;
		}

		return pDetalleConciliacion;
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorBeneficiarioId(Long pBeneficiarioId) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorBeneficiarioId(pBeneficiarioId);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestion(Integer pGestion) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorGestion(pGestion);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorPeriodo(Integer pPeriodo) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorPeriodo(pPeriodo);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorGestionyPeriodo(pGestion, pPeriodo);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorResumenId(long pResumenConciliacionId) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorResumenId(pResumenConciliacionId);
	}

	@Override
	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodo(Long pBeneficiarioId,
			Integer pGestion, Integer pPeriodo) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodo(pBeneficiarioId,
				pGestion, pPeriodo);
	}

	@Override
	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
			Long pBeneficiarioId, Integer pGestion, Integer pPeriodo, String pTipoConciliacion) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
				pBeneficiarioId, pGestion, pPeriodo, pTipoConciliacion);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodoEstadoPago(Integer pGestion,
			Integer pPeriodo, String pEstadoProcesoPago) {
		return detalleConciliacionDao.obtenerDetalleConciliacionPorGestionyPeriodoEstadoPago(pGestion, pPeriodo,
				pEstadoProcesoPago);
	}

	@Override
	public BigDecimal obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(Long pResumenConciliacionId) {
		return detalleConciliacionDao.obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(
				pResumenConciliacionId);
	}

}

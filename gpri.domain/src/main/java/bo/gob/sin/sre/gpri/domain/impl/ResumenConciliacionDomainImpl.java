package bo.gob.sin.sre.gpri.domain.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IResumenConciliacionDao;
import bo.gob.sin.sre.gpri.domain.IResumenConciliacionDomain;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoConciliacion;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoConciliacion;
import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;

@Service
@Transactional("transactionManager")
public class ResumenConciliacionDomainImpl implements IResumenConciliacionDomain {

	private static final Logger LOG = LoggerFactory.getLogger(ResumenConciliacionDomainImpl.class);

	@Autowired
	private IResumenConciliacionDao resumenConciliacionDao;

	@Override
	public SreTsfeRivResumenConciliacion insertar(SreTsfeRivResumenConciliacion pResumenConciliacion) {
		pResumenConciliacion.setCantidadBeneficiarios(1L);
		pResumenConciliacion.setFechaRegistro(LocalDateTime.now());
		pResumenConciliacion.setUsuarioUltimaModificacionId(pResumenConciliacion.getUsuarioRegistroId());
		pResumenConciliacion.setFechaUltimaModificacion(LocalDateTime.now());
		pResumenConciliacion.setEstadoConciliacionId(EnumEstadoConciliacion.GENERADA.getId());
		pResumenConciliacion.setTipoConciliacionId(EnumTipoConciliacion.MANUAL.getId());
		pResumenConciliacion.setEstadoId("AC");
		resumenConciliacionDao.save(pResumenConciliacion);
		return pResumenConciliacion;
	}

	@Override
	public SreTsfeRivResumenConciliacion obtenerPorResumenConciliacionId(Long pResumenConciliacionId) {
		return resumenConciliacionDao.findById(pResumenConciliacionId);
	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestion(Integer pGestion) {
		return resumenConciliacionDao.obtenerResumenConciliacionPorGestion(pGestion);
	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodo(Integer pPeriodo) {
		return resumenConciliacionDao.obtenerResumenConciliacionPorPeriodo(pPeriodo);
	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo) {
		return resumenConciliacionDao.obtenerResumenConciliacionPorPeriodoyGestion(pGestion, pPeriodo);
	}

	@Override
	public SreTsfeRivResumenConciliacion modificar(SreTsfeRivResumenConciliacion pResumenConciliacion) {
		if (resumenConciliacionDao.findById(pResumenConciliacion.getResumenConciliacionId()) != null) {
			pResumenConciliacion.setUsuarioUltimaModificacionId(pResumenConciliacion.getUsuarioRegistroId());
			pResumenConciliacion.setFechaUltimaModificacion(LocalDateTime.now());
			resumenConciliacionDao.save(pResumenConciliacion);
		} else {
			return null;
		}

		return pResumenConciliacion;
	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorTipoGestionyPeriodo(
			String pTipoConciliacion, Integer pGestion, Integer pPeriodo) {
		return resumenConciliacionDao.obtenerResumenConciliacionPorTipoGestionyPeriodo(pTipoConciliacion, pGestion,
				pPeriodo);
	}

	@Override
	public SreTsfeRivResumenConciliacion conciliar(SreTsfeRivResumenConciliacion pResumenConciliacion) {
		if (resumenConciliacionDao.findById(pResumenConciliacion.getResumenConciliacionId()) != null) {
			pResumenConciliacion.setUsuarioUltimaModificacionId(pResumenConciliacion.getUsuarioRegistroId());
			pResumenConciliacion.setFechaUltimaModificacion(LocalDateTime.now());
			pResumenConciliacion.setEstadoConciliacionId(EnumEstadoConciliacion.CONCILIADO.getId());
			resumenConciliacionDao.save(pResumenConciliacion);
		} else {
			return null;
		}

		return pResumenConciliacion;
	}

	@Override
	public SreTsfeRivResumenConciliacion actualizarMontoTotalPagado(Long pResumenConciliacioId,
			BigDecimal pMontoTotalPagado, Long pUsuarioModificacacionId) {
		var vResumenConciliacion = resumenConciliacionDao.findById(pResumenConciliacioId);

		if (vResumenConciliacion != null) {
			vResumenConciliacion.setUsuarioUltimaModificacionId(pUsuarioModificacacionId);
			vResumenConciliacion.setFechaUltimaModificacion(LocalDateTime.now());
			vResumenConciliacion.setMontoTotalPagado(pMontoTotalPagado);
			resumenConciliacionDao.save(vResumenConciliacion);
		} else {
			return null;
		}

		return vResumenConciliacion;
	}
}

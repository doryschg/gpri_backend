package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IMovimientoBeneficiarioDao;
import bo.gob.sin.sre.gpri.domain.IMovimientoBeneficiarioDomain;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoConciliacion;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoConciliacion;
import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;

@Service
@Transactional("transactionManager")
public class MovimientoBeneficiarioDomainImpl implements IMovimientoBeneficiarioDomain {

	private static final Logger LOG = LoggerFactory.getLogger(MovimientoBeneficiarioDomainImpl.class);

	@Autowired
	private IMovimientoBeneficiarioDao movimientoBeneficiarioDao;

	@Override
	public SreTsfeRivMovimientos insertar(SreTsfeRivMovimientos pMovimientoBeneficiario) {
		pMovimientoBeneficiario.setFechaRegistro(LocalDateTime.now());
		pMovimientoBeneficiario.setUsuarioUltimaModificacionId(pMovimientoBeneficiario.getUsuarioRegistroId());
		pMovimientoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
		pMovimientoBeneficiario.setFechaTransaccion(LocalDateTime.now());
		pMovimientoBeneficiario.setEstadoId("AC");
		movimientoBeneficiarioDao.save(pMovimientoBeneficiario);
		return pMovimientoBeneficiario;
	}

	@Override
	public SreTsfeRivMovimientos obtenerMovimientosPorMoviemientoBeneficiarioId(Long pMovimientoBeneficiarioId) {

		return movimientoBeneficiarioDao.findById(pMovimientoBeneficiarioId);

	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerMovimientosPorBeneficiarioId(Long pBeneficiarioId) {
		return movimientoBeneficiarioDao.obtenerMovimientosPorBeneficiarioId(pBeneficiarioId);
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorFechaTransaccion(LocalDate pFechaTransaccion) {
		return movimientoBeneficiarioDao.obtenerPorFechaTransaccion(pFechaTransaccion);
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorGestion(Integer pGestion) {
		return movimientoBeneficiarioDao.obtenerPorGestion(pGestion);
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorPeriodo(Integer pPeriodo) {
		return movimientoBeneficiarioDao.obtenerPorGestion(pPeriodo);
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorPeriodoyGestion(Integer pPeriodo, Integer pGestion) {
		return movimientoBeneficiarioDao.obtenerPorPeriodoyGestion(pPeriodo, pGestion);
	}

	@Override
	public SreTsfeRivMovimientos modificar(SreTsfeRivMovimientos pMovimientoBeneficiario) {
		if (movimientoBeneficiarioDao.findById(pMovimientoBeneficiario.getMovimientoBeneficiarioId()) != null) {
			movimientoBeneficiarioDao.save(pMovimientoBeneficiario);
		} else {
			return null;
		}

		return pMovimientoBeneficiario;
	}

}

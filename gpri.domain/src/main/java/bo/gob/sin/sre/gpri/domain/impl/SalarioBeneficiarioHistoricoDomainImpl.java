package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ISalarioBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.domain.ISalarioBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHSalarioBeneficiario;

@Service
@Transactional("transactionManager")
public class SalarioBeneficiarioHistoricoDomainImpl implements ISalarioBeneficiarioHistoricoDomain {

	@Autowired
	private ISalarioBeneficiarioHistoricoDao salarioBeneficiarioHistoricoDao;

	@Override
	public List<SreTsfeRivHSalarioBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId) {
		return salarioBeneficiarioHistoricoDao.obtenerHistoricoSalarioPorBeneficiarioId(pBeneficiarioId);
	}

	@Override
	public SreTsfeRivHSalarioBeneficiario insertarHistoricoSalarioBeneficiario(
			SreTsfeRivHSalarioBeneficiario pHistoricoSalarioBeneficiario) {

		SreTsfeRivHSalarioBeneficiario vHistoricoSalarioBeneficiario = salarioBeneficiarioHistoricoDao
				.obtenerUltimoRegistroHistoricoPorBeneficiarioId(pHistoricoSalarioBeneficiario.getBeneficiarioId());

		if (vHistoricoSalarioBeneficiario != null) {
			vHistoricoSalarioBeneficiario.setFechaHasta(LocalDate.now());
			vHistoricoSalarioBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
			salarioBeneficiarioHistoricoDao.saveOrUpdate(vHistoricoSalarioBeneficiario);
		}
		
		pHistoricoSalarioBeneficiario.setFechaRegistro(LocalDateTime.now());
		pHistoricoSalarioBeneficiario
				.setUsuarioUltimaModificacionId(pHistoricoSalarioBeneficiario.getUsuarioRegistroId());
		pHistoricoSalarioBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
		pHistoricoSalarioBeneficiario.setEstadoId("AC");
		pHistoricoSalarioBeneficiario.setFechaHasta(null);
		salarioBeneficiarioHistoricoDao.save(pHistoricoSalarioBeneficiario);
		return pHistoricoSalarioBeneficiario;
	}

	@Override
	public SreTsfeRivHSalarioBeneficiario updateHistoricoSalarioBeneficiario(
			SreTsfeRivHSalarioBeneficiario pHistoricoSalarioBeneficiario) {
		if (salarioBeneficiarioHistoricoDao.findById(pHistoricoSalarioBeneficiario.getSalarioId()) != null) {
			pHistoricoSalarioBeneficiario
					.setUsuarioUltimaModificacionId(pHistoricoSalarioBeneficiario.getUsuarioRegistroId());
			pHistoricoSalarioBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
			salarioBeneficiarioHistoricoDao.save(pHistoricoSalarioBeneficiario);
		} else {
			return null;
		}

		return pHistoricoSalarioBeneficiario;
	}

	@Override
	public SreTsfeRivHSalarioBeneficiario guardarHistoricoSalario(SreTsfeRivHSalarioBeneficiario pSalarioBeneficiario) {
		salarioBeneficiarioHistoricoDao.save(pSalarioBeneficiario);
		return pSalarioBeneficiario;
	}

	@Override
	public SreTsfeRivHSalarioBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(long pBeneficiarioId) {
		return salarioBeneficiarioHistoricoDao.obtenerUltimoRegistroHistoricoPorBeneficiarioId(pBeneficiarioId);
	}

}

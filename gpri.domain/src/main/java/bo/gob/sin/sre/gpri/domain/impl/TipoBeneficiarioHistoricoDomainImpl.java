package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ITipoBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.domain.ITipoBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHTipoBeneficiario;

@Service
@Transactional("transactionManager")
public class TipoBeneficiarioHistoricoDomainImpl implements ITipoBeneficiarioHistoricoDomain {

	@Autowired
	private ITipoBeneficiarioHistoricoDao tipoBeneficiarioHistoricoDao;

	@Override
	public List<SreTsfeRivHTipoBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId) {
		return tipoBeneficiarioHistoricoDao.obtenerHistoricoPorBeneficiarioId(pBeneficiarioId);
	}

	@Override
	public SreTsfeRivHTipoBeneficiario insertarHistoricoSalarioBeneficiario(
			SreTsfeRivHTipoBeneficiario pHistoricoBeneficiario) {
		SreTsfeRivHTipoBeneficiario vHistoricoTipoBeneficiario = tipoBeneficiarioHistoricoDao
				.obtenerUltimoRegistroHistoricoPorBeneficiarioId(pHistoricoBeneficiario.getBeneficiarioId());
		vHistoricoTipoBeneficiario.setFechaHasta(LocalDate.now());
		tipoBeneficiarioHistoricoDao.save(vHistoricoTipoBeneficiario);

		pHistoricoBeneficiario.setFechaRegistro(LocalDateTime.now());
		pHistoricoBeneficiario.setUsuarioUltimaModificacionId(pHistoricoBeneficiario.getUsuarioRegistroId());
		pHistoricoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
		pHistoricoBeneficiario.setEstadoId("AC");
		pHistoricoBeneficiario.setFechaHasta(null);
		tipoBeneficiarioHistoricoDao.save(pHistoricoBeneficiario);
		return pHistoricoBeneficiario;
	}

	@Override
	public SreTsfeRivHTipoBeneficiario updateHistoricoSalarioBeneficiario(
			SreTsfeRivHTipoBeneficiario pHistoricoTipoBeneficiario) {
		if (tipoBeneficiarioHistoricoDao.findById(pHistoricoTipoBeneficiario.getTipoBeneficiarioId()) != null) {
			pHistoricoTipoBeneficiario
					.setUsuarioUltimaModificacionId(pHistoricoTipoBeneficiario.getUsuarioRegistroId());
			pHistoricoTipoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
			tipoBeneficiarioHistoricoDao.save(pHistoricoTipoBeneficiario);
		} else {
			return null;
		}

		return pHistoricoTipoBeneficiario;
	}

	@Override
	public SreTsfeRivHTipoBeneficiario guardartipoBeneficiarioHistorico(SreTsfeRivHTipoBeneficiario pTipoBeneficiarioHistorico) {

		tipoBeneficiarioHistoricoDao.guardarHistoricoTipoBeneficiario(pTipoBeneficiarioHistorico);
		return pTipoBeneficiarioHistorico;
	}

}

package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.domain.IBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHBeneficiarios;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;

@Service
@Transactional("transactionManager")
public class BeneficiarioHistoricoDomainImpl implements IBeneficiarioHistoricoDomain {

	@Autowired
	private IBeneficiarioHistoricoDao beneficiarioHistoricoDao;

	@Override
	public List<SreTsfeRivHBeneficiarios> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId) {
		List<SreTsfeRivHBeneficiarios> vRespuesta = beneficiarioHistoricoDao
				.obtenerHistoricoPorBeneficiarioId(pBeneficiarioId);
		return vRespuesta;
	}

//	@Override
//	public SreTsfeRivHBeneficiarios insertarHistoricoBeneficiario(SreTsfeRivHBeneficiarios pHistoricoBeneficiario) {
//		pHistoricoBeneficiario.setFechaRegistro(LocalDateTime.now());
//		pHistoricoBeneficiario.setUsuarioUltimaModificacionId(pHistoricoBeneficiario.getUsuarioRegistroId());
//		pHistoricoBeneficiario.setUsuarioRegistroId(pHistoricoBeneficiario.getUsuarioRegistroId());
//		pHistoricoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
//		pHistoricoBeneficiario.setEstadoId("AC");
//		beneficiarioHistoricoDao.save(pHistoricoBeneficiario);
//		return pHistoricoBeneficiario;
//	}

	@Override
	public SreTsfeRivHBeneficiarios updateHistoricoBeneficiario(SreTsfeRivHBeneficiarios pHistoricoBeneficiario) {
		if (beneficiarioHistoricoDao.findById(pHistoricoBeneficiario.gethBeneficiarioId()) != null) {
			beneficiarioHistoricoDao.save(pHistoricoBeneficiario);
		} else {
			return null;
		}

		return pHistoricoBeneficiario;
	}

	@Override
	public SreTsfeRivHBeneficiarios insertarHistoricoBeneficiario(SreTsfeRivHBeneficiarios pHistoricoBeneficiario) {	
		pHistoricoBeneficiario.setFechaRegistro(LocalDateTime.now());
		pHistoricoBeneficiario.setUsuarioUltimaModificacionId(pHistoricoBeneficiario.getUsuarioRegistroId());
		pHistoricoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
		pHistoricoBeneficiario.setTipoTransaccionId("ACT");
		pHistoricoBeneficiario.setEstadoId("AC");
		beneficiarioHistoricoDao.save(pHistoricoBeneficiario);
		return pHistoricoBeneficiario;
	}
}

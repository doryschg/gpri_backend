package bo.gob.sin.sre.gpri.domain.impl;

import bo.gob.sin.sre.gpri.dao.IPerBeneficiarioDao;
import bo.gob.sin.sre.gpri.domain.IPersonaBeneficiarioDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class PersonaBeneficiarioDomainImpl implements IPersonaBeneficiarioDomain {
	@Autowired
	private IPerBeneficiarioDao perBeneficiarioDao;

	@Override
	public SreTsfeRivPersonas insertarPersonaBeneficiario(SreTsfeRivPersonas pBeneficiario) {
		perBeneficiarioDao.save(pBeneficiario);
		return pBeneficiario;
	}

	@Override
	public SreTsfeRivPersonas updatePersonaBeneficiario(SreTsfeRivPersonas pBeneficiario) {
		pBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
		perBeneficiarioDao.saveOrUpdate(pBeneficiario);
		return pBeneficiario;
	}

	@Override
	public Boolean bajaPersonaBeneficiario(Long pPersonaId) {
		return perBeneficiarioDao.bajaPersonaBeneficiario(pPersonaId);
	}

	@Override
	public SreTsfeRivPersonas obtenerPorPersonaId(Long pPersonaId) {
		return perBeneficiarioDao.obtenerPorPersonaId(pPersonaId);
	}
}

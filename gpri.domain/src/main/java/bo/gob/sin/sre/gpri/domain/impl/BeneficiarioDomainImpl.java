package bo.gob.sin.sre.gpri.domain.impl;

import bo.gob.sin.sre.gpri.dao.IBeneficiarioDao;
import bo.gob.sin.sre.gpri.domain.IBeneficiarioDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivBeneficiarios;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class BeneficiarioDomainImpl implements IBeneficiarioDomain {
    @Autowired
    private IBeneficiarioDao beneficiarioDao;

    @Override
    public SreTsfeRivBeneficiarios obtenerBeneficiarioPorId(long pBeneficiarioId) {
        return beneficiarioDao.obtenerBeneficiarioPorPersonaId(pBeneficiarioId);
    }

    @Override
    public SreTsfeRivBeneficiarios insertarBeneficiario(SreTsfeRivBeneficiarios pBeneficiario) {
        beneficiarioDao.save(pBeneficiario);
        return pBeneficiario;
    }
    
    @Override
    public SreTsfeRivBeneficiarios updateBeneficiario(SreTsfeRivBeneficiarios pBeneficiario) {
    	pBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
        beneficiarioDao.saveOrUpdate(pBeneficiario);
        return pBeneficiario;
    }
    
    @Override
    public SreTsfeRivBeneficiarios buscarBeneficiarioId(long beneficiarioId) {
        return beneficiarioDao.findById(beneficiarioId);
    }

	@Override
	public SreTsfeRivBeneficiarios buscarUltimoRegistroVigenteBeneficiarioId(long beneficiarioId) {
		   return beneficiarioDao.buscarUltimoRegistroVigenteBeneficiarioId(beneficiarioId);
	}
}

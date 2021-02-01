package bo.gob.sin.sre.gpri.domain.impl;

import bo.gob.sin.sre.gpri.dao.ICuentaBeneficiarioDao;
import bo.gob.sin.sre.gpri.domain.ICuentaBeneficiarioDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class CuentaBeneficiarioDomainImpl implements ICuentaBeneficiarioDomain {
    @Autowired
    private ICuentaBeneficiarioDao cuentaBeneficiarioDao;

    @Override
    public SreTsfeRivHCuentasBeneficiario insertarCuentaBeneficiario(SreTsfeRivHCuentasBeneficiario pBeneficiario) {
        cuentaBeneficiarioDao.save(pBeneficiario);
        return pBeneficiario;
    }
    
    @Override
    public SreTsfeRivHCuentasBeneficiario updateCuentaBeneficiario(SreTsfeRivHCuentasBeneficiario pBeneficiario) {
        cuentaBeneficiarioDao.save(pBeneficiario);
        return pBeneficiario;
    }


}

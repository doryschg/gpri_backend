package bo.gob.sin.sre.gpri.dao.impl;

import bo.gob.sin.sre.gpri.dao.ICuentaBeneficiarioDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("transactionManager")
public class CuentaBeneficiarioDaoImpl extends AbstractGenericDao<SreTsfeRivHCuentasBeneficiario> implements ICuentaBeneficiarioDao {

}

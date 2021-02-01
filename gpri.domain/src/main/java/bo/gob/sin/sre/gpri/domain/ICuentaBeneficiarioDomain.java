package bo.gob.sin.sre.gpri.domain;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;

public interface ICuentaBeneficiarioDomain {

    public SreTsfeRivHCuentasBeneficiario insertarCuentaBeneficiario(SreTsfeRivHCuentasBeneficiario pBeneficiario);
    
    public SreTsfeRivHCuentasBeneficiario updateCuentaBeneficiario(SreTsfeRivHCuentasBeneficiario pBeneficiario);
    

}

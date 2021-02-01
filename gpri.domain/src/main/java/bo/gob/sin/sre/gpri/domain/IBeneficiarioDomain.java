package bo.gob.sin.sre.gpri.domain;

import bo.gob.sin.sre.gpri.model.SreTsfeRivBeneficiarios;

public interface IBeneficiarioDomain {
    public SreTsfeRivBeneficiarios obtenerBeneficiarioPorId(long pBeneficiarioId);

    public SreTsfeRivBeneficiarios insertarBeneficiario(SreTsfeRivBeneficiarios pBeneficiario);
    
    public SreTsfeRivBeneficiarios updateBeneficiario(SreTsfeRivBeneficiarios pBeneficiario);
    
    public SreTsfeRivBeneficiarios buscarBeneficiarioId(long beneficiarioId);
    
    public SreTsfeRivBeneficiarios buscarUltimoRegistroVigenteBeneficiarioId(long beneficiarioId);
}

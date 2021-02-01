package bo.gob.sin.sre.gpri.domain;

import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;

public interface IPersonaBeneficiarioDomain {

    public SreTsfeRivPersonas insertarPersonaBeneficiario(SreTsfeRivPersonas pPersonaBeneficiario);
    
    public SreTsfeRivPersonas updatePersonaBeneficiario(SreTsfeRivPersonas pPersonaBeneficiario);
    
    public Boolean bajaPersonaBeneficiario(Long pPersonaId);
    
    public SreTsfeRivPersonas obtenerPorPersonaId(Long pPersonaId);
    

}

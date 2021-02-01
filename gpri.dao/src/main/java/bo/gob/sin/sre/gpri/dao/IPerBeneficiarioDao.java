package bo.gob.sin.sre.gpri.dao;

import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IPerBeneficiarioDao extends GenericDao<SreTsfeRivPersonas> {
    public Boolean bajaPersonaBeneficiario(Long pPersonaId);
    
    public SreTsfeRivPersonas obtenerPorPersonaId(Long pPersonaId);


}

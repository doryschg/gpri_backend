package bo.gob.sin.sre.gpri.dao;

import bo.gob.sin.sre.gpri.model.SreTsfeRivBeneficiarios;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IBeneficiarioDao extends GenericDao<SreTsfeRivBeneficiarios> {

    public SreTsfeRivBeneficiarios obtenerBeneficiarioPorPersonaId(long pPersonaId);
    
    public SreTsfeRivBeneficiarios buscarUltimoRegistroVigenteBeneficiarioId(long pBeneficiarioId);
}

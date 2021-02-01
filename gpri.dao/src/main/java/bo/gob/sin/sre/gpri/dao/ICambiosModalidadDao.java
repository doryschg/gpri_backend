package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivCambiosModalidad;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ICambiosModalidadDao extends GenericDao<SreTsfeRivCambiosModalidad>{
	List<SreTsfeRivCambiosModalidad> obtenerPorBeneficiarioId(Long pBeneficiarioId);

}

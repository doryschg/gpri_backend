package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHSalarioBeneficiario;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ISalarioBeneficiarioHistoricoDao extends GenericDao<SreTsfeRivHSalarioBeneficiario> {

	public List<SreTsfeRivHSalarioBeneficiario> obtenerHistoricoSalarioPorBeneficiarioId(Long pBeneficiarioId);
	
	public SreTsfeRivHSalarioBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(Long pBeneficiarioId);

}

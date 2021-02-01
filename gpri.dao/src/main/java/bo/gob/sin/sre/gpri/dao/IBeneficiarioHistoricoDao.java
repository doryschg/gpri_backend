package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHBeneficiarios;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IBeneficiarioHistoricoDao extends GenericDao<SreTsfeRivHBeneficiarios> {
	public List<SreTsfeRivHBeneficiarios> obtenerHistoricoPorBeneficiarioId(Long pBeneficiarioId);

	public SreTsfeRivHBeneficiarios obtenerUltimoRegistroHistoricoBeneficiarioId(Long pBeneficiarioId);

}

package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHTipoBeneficiario;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ITipoBeneficiarioHistoricoDao extends GenericDao<SreTsfeRivHTipoBeneficiario> {
	public List<SreTsfeRivHTipoBeneficiario> obtenerHistoricoPorBeneficiarioId(Long pBeneficiarioId);

	public SreTsfeRivHTipoBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(Long pBeneficiarioId);

	public SreTsfeRivHTipoBeneficiario guardarHistoricoTipoBeneficiario(SreTsfeRivHTipoBeneficiario pHistorico);

}

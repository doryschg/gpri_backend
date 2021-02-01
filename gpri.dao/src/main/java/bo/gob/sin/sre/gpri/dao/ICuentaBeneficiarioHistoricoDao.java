package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface ICuentaBeneficiarioHistoricoDao extends GenericDao<SreTsfeRivHCuentasBeneficiario> {
	public List<SreTsfeRivHCuentasBeneficiario> obtenerHistoricoBeneficiarioPorBeneficiarioId(Long pBeneficiarioId);

	public SreTsfeRivHCuentasBeneficiario obtenerUltimoRegistroHistoricoCuentaPorBeneficiarioId(Long pBeneficiarioId);
}

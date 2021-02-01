package bo.gob.sin.sre.gpri.domain;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;

import java.util.List;

public interface ICuentaBeneficiarioHistoricoDomain {
	public List<SreTsfeRivHCuentasBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId);

	public SreTsfeRivHCuentasBeneficiario insertarHistoricoBeneficiario(
			SreTsfeRivHCuentasBeneficiario pHistoricoCuentaBeneficiario);

	public SreTsfeRivHCuentasBeneficiario updateHistoricoBeneficiario(
			SreTsfeRivHCuentasBeneficiario pHistoricoCuentaBeneficiario);
}

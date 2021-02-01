package bo.gob.sin.sre.gpri.domain;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHSalarioBeneficiario;

public interface ISalarioBeneficiarioHistoricoDomain {
	public List<SreTsfeRivHSalarioBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId);

	public SreTsfeRivHSalarioBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(long pBeneficiarioId);

	public SreTsfeRivHSalarioBeneficiario insertarHistoricoSalarioBeneficiario(
			SreTsfeRivHSalarioBeneficiario pHistoricoSalarioBeneficiario);

	public SreTsfeRivHSalarioBeneficiario updateHistoricoSalarioBeneficiario(
			SreTsfeRivHSalarioBeneficiario pHistoricoSalarioBeneficiario);

	public SreTsfeRivHSalarioBeneficiario guardarHistoricoSalario(SreTsfeRivHSalarioBeneficiario pSalarioBeneficiario);
}

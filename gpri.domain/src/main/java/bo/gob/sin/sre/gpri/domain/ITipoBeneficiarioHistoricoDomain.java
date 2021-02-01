package bo.gob.sin.sre.gpri.domain;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHTipoBeneficiario;

public interface ITipoBeneficiarioHistoricoDomain {
	public List<SreTsfeRivHTipoBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId);

	public SreTsfeRivHTipoBeneficiario insertarHistoricoSalarioBeneficiario(
			SreTsfeRivHTipoBeneficiario pHistoricoTipoBeneficiario);

	public SreTsfeRivHTipoBeneficiario updateHistoricoSalarioBeneficiario(
			SreTsfeRivHTipoBeneficiario pHistoricoTipoBeneficiario);

	public SreTsfeRivHTipoBeneficiario guardartipoBeneficiarioHistorico(SreTsfeRivHTipoBeneficiario pTipoBeneficiarioHistorico);
}

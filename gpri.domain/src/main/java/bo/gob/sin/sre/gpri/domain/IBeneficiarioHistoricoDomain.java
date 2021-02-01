package bo.gob.sin.sre.gpri.domain;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHBeneficiarios;

public interface IBeneficiarioHistoricoDomain {
	public List<SreTsfeRivHBeneficiarios> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId);

	public SreTsfeRivHBeneficiarios insertarHistoricoBeneficiario(SreTsfeRivHBeneficiarios pHistoricoBeneficiario);

	public SreTsfeRivHBeneficiarios updateHistoricoBeneficiario(SreTsfeRivHBeneficiarios pHistoricoBeneficiario);
}

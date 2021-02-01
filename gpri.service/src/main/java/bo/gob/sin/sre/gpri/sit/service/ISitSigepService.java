package bo.gob.sin.sre.gpri.sit.service;

import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface ISitSigepService {

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> registroBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> registroCuentaBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<Boolean> registroDatosAdicionalesBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<Boolean> actualizarDatosAdicionalesBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> actualizarBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarCuentaBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);
	
	public ResultadoGenericoDto<String> consultarEstadoCuentaBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken);
}

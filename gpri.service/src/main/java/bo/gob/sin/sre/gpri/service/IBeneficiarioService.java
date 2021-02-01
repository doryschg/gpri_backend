package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.RegistroCuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IBeneficiarioService {


	ResultadoGenericoDto<BeneficiarioPersonaRivDto> insertarBeneficiario(BeneficiarioPersonaRivDto pBeneficiario);

	public ResultadoGenericoDto<String> updateBeneficiario(BeneficiarioDto pBeneficiario);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> guardarCuentaBeneficiario(RegistroCuentaBeneficiarioDto pRegistroCuenta);
	
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarBeneficiario(BeneficiarioPersonaRivDto pBeneficiario);
	
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarCuentaBeneficiario(RegistroCuentaBeneficiarioDto pRegistroCuenta);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> habilitarBeneficiario(Long pBeneficiarioId);



}

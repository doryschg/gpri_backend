package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IPersonaBeneficiariosQuery {
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPersonaPorDocuemntoComplemento(String pNroDocumento, String pCodComplementario);

	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorPersonaId(long pPersonaId);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorPersonaId(long pPersonaId);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorNroDocumentoyComplemento(String nroDocumento,String complementoDocumento);
	
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorId(long pId);
	
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorCodigoPersona(long pCodigoPersona);
	//public ResultadoGenericoDto<Boolean> verificarSalario(long pNuaCua, String pNumeroDocumentos);

	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorNroDocumentoComplementoyNumeroCuenta(String nroDocumento,String complementoDocumento,String pNroCuenta);
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorBeneficiarioId(long pBeneficiarioId);

}

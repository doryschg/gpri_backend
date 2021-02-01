package bo.gob.sin.sre.gpri.client;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IBeneficiarioRestClient {
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorCodigoPersona(long pCodigoPersona);
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPorBeneficiarioId(Long pBeneficiarioId);
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPorNroDocumentoComplemento(String pNroDocumento,String pComplemento);


}

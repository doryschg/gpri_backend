package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IApsBeneficiariosQuery {
	ResultadoGenericoDto<AseguradoAPSDto> verificarSalarioBeneficiario(String pNuaCua, long pNumeroDocumento,
			String pTipoBeneficiario);

	ResultadoGenericoDto<AseguradoAPSDto> obtenerDatosBeneficiario(String pNuaCua, long pNumeroDocumento,
			String pTipoBeneficiario);
}

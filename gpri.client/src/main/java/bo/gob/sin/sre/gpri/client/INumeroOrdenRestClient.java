package bo.gob.sin.sre.gpri.client;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface INumeroOrdenRestClient {
	public ResultadoGenericoDto<Long> obtenerNumeroOrdenDeclaracion();

}

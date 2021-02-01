package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface INumerosOrdenQuery {

	ResultadoGenericoDto<Long> obtenerNumeroOrdenDeclaracionBeneficiario();
}

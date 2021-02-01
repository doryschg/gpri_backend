package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.ParametrosConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;

public interface IResumenConciliacionQuery {

	public ResultadoGenericoListaDto<ResumenConciliacionDto> obtenerResumenPorTipoyGestionPeriodo(
			ParametrosConciliacionDto pParametros);
}

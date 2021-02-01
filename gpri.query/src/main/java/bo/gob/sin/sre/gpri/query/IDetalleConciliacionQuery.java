package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;

public interface IDetalleConciliacionQuery {
	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetalleConciliacionPaginado(long pPersonaId,int pGestion, int pPeriodo, int pPagina, int pTamanioPagina);

	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetalleConciliacionPorResumenPaginado(long pResumenConciliacionId, int pPagina, int pTamanioPagina, String pDcumentoBeneficiario);
}

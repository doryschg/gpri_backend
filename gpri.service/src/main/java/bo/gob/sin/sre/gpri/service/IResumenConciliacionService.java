package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;

public interface IResumenConciliacionService {

	public ResultadoGenericoDto<ResumenConciliacionDto> registrar(ResumenConciliacionDto pResumenConciliacion);

	public ResultadoGenericoDto<ResumenConciliacionDto> actualizar(ResumenConciliacionDto pResumenConciliacion);
	
	public ResultadoGenericoDto<ResumenConciliacionDto> conciliar(ResumenConciliacionDto pResumenConciliacion);

	public ResultadoGenericoDto<ResumenConciliacionDto> obtenerResumenConciliacionPorId(Long pResumenConciliacionId);

	public ResultadoGenericoListaDto<ResumenConciliacionDto> obtenerResumenConciliacionPorGestionPeriodo(
			Integer pGestion, Integer pPeriodo);
	
	public ResultadoGenericoDto<Boolean> actualizarMontoTotalPagadoResumenConciliacion(Long pResumenConciliacionId,
			Long pUsuarioModificacionId);
}

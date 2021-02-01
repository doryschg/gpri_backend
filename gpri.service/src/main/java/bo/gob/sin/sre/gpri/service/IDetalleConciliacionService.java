package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.RespuestaConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.SolicitudConciliacionDto;

public interface IDetalleConciliacionService {

	public ResultadoGenericoDto<DetalleConciliacionDto> registrar(DetalleConciliacionDto pDetalleConciliacion);

	public ResultadoGenericoDto<DetalleConciliacionDto> actualizar(DetalleConciliacionDto pDetalleConciliacion);

	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetallePorBeneficiario(Long pBeneficiarioId);

	public ResultadoGenericoListaDto<DetalleConciliacionDto> obtenerDetallePorGestionPeriodo(Integer pGestion,
			Integer pPeriodo);

	public ResultadoGenericoDto<RespuestaConciliacionDto> recepionFormulario(
			SolicitudConciliacionDto pSolicitudConciliacion, Long pUsuarioId);

}

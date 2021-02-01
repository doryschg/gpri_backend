package bo.gob.sin.sre.gpri.query;

import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;

public interface IMovimientosBeneficiarioConsultaQuery {
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorBeneficiario(Long pBeneficiarioId);

	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorGestionPeriodo(Integer pGestion,
			Integer pPeriodo, long pLimit, long pOffset);

	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorDetalleConciliacionId(
			Long pDetalleConciliacionId, long pLimit, long pOffset);
}

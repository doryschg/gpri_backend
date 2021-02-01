package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;

public interface IMovimientoBeneficiarioService {

	public ResultadoGenericoDto<MovimientoBeneficiarioDto> registrar(MovimientoBeneficiarioDto pMovimientoBeneficiario);

	public ResultadoGenericoDto<MovimientoBeneficiarioDto> actualizar(
			MovimientoBeneficiarioDto pMovimientoBeneficiario);

	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorBeneficiario(Long pBeneficiarioId);

	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorGestionPeriodo(Integer pGestion,
			Integer pPeriodo);

}

package bo.gob.sin.sre.gpri.domain;

import java.time.LocalDate;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;

public interface IMovimientoBeneficiarioDomain {
	public SreTsfeRivMovimientos insertar(SreTsfeRivMovimientos pMovimientoBeneficiario);

	public SreTsfeRivMovimientos modificar(SreTsfeRivMovimientos pMovimientoBeneficiario);

	public SreTsfeRivMovimientos obtenerMovimientosPorMoviemientoBeneficiarioId(
			Long pMovimientoBeneficiarioId);

	public List<SreTsfeRivMovimientos> obtenerMovimientosPorBeneficiarioId(Long pBeneficiarioId);

	public List<SreTsfeRivMovimientos> obtenerPorFechaTransaccion(LocalDate pFechaTransaccion);

	public List<SreTsfeRivMovimientos> obtenerPorGestion(Integer pGestion);

	public List<SreTsfeRivMovimientos> obtenerPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivMovimientos> obtenerPorPeriodoyGestion(Integer pPeriodo, Integer pGestion);
}

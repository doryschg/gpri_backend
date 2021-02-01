package bo.gob.sin.sre.gpri.domain;

import java.math.BigDecimal;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivDetalleConciliacion;

public interface IDetalleConciliacionDomain {

	public SreTsfeRivDetalleConciliacion insertar(SreTsfeRivDetalleConciliacion pDetalleConciliacion);

	public SreTsfeRivDetalleConciliacion modificar(SreTsfeRivDetalleConciliacion pDetalleConciliacion);

	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorId(long pDetalleConciliacionId);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorResumenId(long pResumenConciliacionId);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorBeneficiarioId(Long pBeneficiarioId);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestion(Integer pGestion);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodoEstadoPago(Integer pGestion,
			Integer pPeriodo, String pEstadoProcesoPago);

	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodo(Long pBeneficiarioId,
			Integer pGestion, Integer pPeriodo);

	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
			Long pBeneficiarioId, Integer pGestion, Integer pPeriodo, String pTipoConciliacion);
	
	public BigDecimal obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(Long vResumenConciliacionId);
}

package bo.gob.sin.sre.gpri.dao;

import java.math.BigDecimal;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivDetalleConciliacion;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IDetalleConciliacionDao extends GenericDao<SreTsfeRivDetalleConciliacion> {

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorBeneficiarioId(Long pBeneficiarioId);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestion(Integer pGestion);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorResumenId(Long pResumenConciliacionId);

	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodo(Long pBeneficiarioId,
			Integer pGestion, Integer pPeriodo);

	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
			Long pBeneficiarioId, Integer pGestion, Integer pPeriodo, String pTipoConciliacion);

	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodoEstadoPago(Integer pGestion,
			Integer pPeriodo, String pEstadoProcesoPago);

	public BigDecimal obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(Long pResumenConciliacionId);

}

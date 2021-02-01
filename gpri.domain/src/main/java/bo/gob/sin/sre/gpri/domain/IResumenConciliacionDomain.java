package bo.gob.sin.sre.gpri.domain;

import java.math.BigDecimal;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;

public interface IResumenConciliacionDomain {

	public SreTsfeRivResumenConciliacion insertar(SreTsfeRivResumenConciliacion pResumenConciliacion);

	public SreTsfeRivResumenConciliacion modificar(SreTsfeRivResumenConciliacion pResumenConciliacion);

	public SreTsfeRivResumenConciliacion conciliar(SreTsfeRivResumenConciliacion pResumenConciliacion);

	public SreTsfeRivResumenConciliacion obtenerPorResumenConciliacionId(Long pResumenConciliacionId);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestion(Integer pGestion);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorTipoGestionyPeriodo(
			String pTipoConciliacion, Integer pGestion, Integer pPeriodo);

	public SreTsfeRivResumenConciliacion actualizarMontoTotalPagado(Long pResumenConciliacioId,
			BigDecimal pMontoTotalPagado, Long pUsuarioModificacacionId);
}

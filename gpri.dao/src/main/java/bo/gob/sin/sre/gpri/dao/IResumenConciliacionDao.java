package bo.gob.sin.sre.gpri.dao;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IResumenConciliacionDao extends GenericDao<SreTsfeRivResumenConciliacion> {

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestion(Integer pGestion);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodoyGestion(Integer pGestion,
			Integer pPeriodo);

	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorTipoGestionyPeriodo(
			String pTipoConciliacion, Integer pGestion, Integer pPeriodo);
}

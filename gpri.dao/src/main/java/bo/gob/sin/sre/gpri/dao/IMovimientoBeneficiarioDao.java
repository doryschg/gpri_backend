package bo.gob.sin.sre.gpri.dao;

import java.time.LocalDate;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;
import bo.gob.sin.str.ccs.dao.GenericDao;

public interface IMovimientoBeneficiarioDao extends GenericDao<SreTsfeRivMovimientos>  {
	public List<SreTsfeRivMovimientos> obtenerMovimientosPorBeneficiarioId(Long pBeneficiarioId);

	public List<SreTsfeRivMovimientos> obtenerPorFechaTransaccion(LocalDate pFechaTransaccion);

	public List<SreTsfeRivMovimientos> obtenerPorGestion(Integer pGestion);

	public List<SreTsfeRivMovimientos> obtenerPorPeriodo(Integer pPeriodo);

	public List<SreTsfeRivMovimientos> obtenerPorPeriodoyGestion(Integer pPeriodo, Integer pGestion);
}

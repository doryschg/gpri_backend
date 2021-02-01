package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IResumenConciliacionDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivResumenConciliacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class ResumenConciliacionDaoImpl extends AbstractGenericDao<SreTsfeRivResumenConciliacion>
		implements IResumenConciliacionDao {

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorGestion(Integer pGestion) {
		String hql = "FROM SreTsfeRivResumenConciliacion as c" + "  WHERE c.gestion = :pGestion "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivResumenConciliacion> resultado = getSession().createQuery(hql).setParameter("pGestion", pGestion)
				.getResultList();

		return resultado;

	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodo(Integer pPeriodo) {
		String hql = "FROM SreTsfeRivResumenConciliacion as c" + "  WHERE c.periodo = :pPeriodo "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivResumenConciliacion> resultado = getSession().createQuery(hql).setParameter("pPeriodo", pPeriodo)
				.getResultList();
		return resultado;

	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorPeriodoyGestion(Integer pGestion,
			Integer pPeriodo) {
		String hql = "FROM SreTsfeRivResumenConciliacion as c"
				+ "  WHERE c.periodo = :pPeriodo AND c.gestion = :pGestion" + "   AND c.estadoId = 'AC'";

		List<SreTsfeRivResumenConciliacion> resultado = getSession().createQuery(hql).setParameter("pPeriodo", pPeriodo)
				.setParameter("pGestion", pGestion).getResultList();

		return resultado;
	}

	@Override
	public List<SreTsfeRivResumenConciliacion> obtenerResumenConciliacionPorTipoGestionyPeriodo(
			String pTipoConciliacion, Integer pGestion, Integer pPeriodo) {
		String hql = "FROM SreTsfeRivResumenConciliacion as c"
				+ "  WHERE c.tipoConciliacionId = :pTipoConciliacion and c.periodo = :pPeriodo AND c.gestion = :pGestion"
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivResumenConciliacion> resultado = getSession().createQuery(hql).setParameter("pPeriodo", pPeriodo)
				.setParameter("pGestion", pGestion).setParameter("pTipoConciliacion", pTipoConciliacion)
				.getResultList();

		return resultado;
	}

}

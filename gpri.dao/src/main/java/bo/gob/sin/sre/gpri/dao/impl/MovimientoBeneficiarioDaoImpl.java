package bo.gob.sin.sre.gpri.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IMovimientoBeneficiarioDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class MovimientoBeneficiarioDaoImpl extends AbstractGenericDao<SreTsfeRivMovimientos>
		implements IMovimientoBeneficiarioDao {

	@Override
	public List<SreTsfeRivMovimientos> obtenerMovimientosPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivMovimientos as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivMovimientos> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorFechaTransaccion(LocalDate pFechaTransaccion) {
		String hql = "FROM SreTsfeRivMovimientos as c" + "  WHERE c.fechaTransaccion = :pFechaTransaccion "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivMovimientos> resultado = getSession().createQuery(hql)
				.setParameter("pFechaTransaccion", pFechaTransaccion).getResultList();
		return resultado;
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorGestion(Integer pGestion) {
		String hql = "FROM SreTsfeRivMovimientos as c" + "  WHERE c.gestionOrigen = :pGestion "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivMovimientos> resultado = getSession().createQuery(hql)
				.setParameter("pGestion", pGestion).getResultList();
		return resultado;
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorPeriodo(Integer pPeriodo) {
		String hql = "FROM SreTsfeRivMovimientos as c" + "  WHERE c.periodoOrigen = :pPeriodo "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivMovimientos> resultado = getSession().createQuery(hql)
				.setParameter("pPeriodo", pPeriodo).getResultList();
		return resultado;
	}

	@Override
	public List<SreTsfeRivMovimientos> obtenerPorPeriodoyGestion(Integer pPeriodo, Integer pGestion) {
		String hql = "FROM SreTsfeRivMovimientos as c"
				+ "  WHERE c.gestionOrigen = :pGestion AND c.periodoOrigen = :pPeriodo " + "  AND c.estadoId = 'AC'";

		List<SreTsfeRivMovimientos> resultado = getSession().createQuery(hql)
				.setParameter("pGestion", pGestion).setParameter("pPeriodo", pPeriodo).getResultList();
		return resultado;
	}

}

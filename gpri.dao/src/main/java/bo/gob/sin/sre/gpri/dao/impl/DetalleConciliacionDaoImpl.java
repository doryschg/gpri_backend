package bo.gob.sin.sre.gpri.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IDetalleConciliacionDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivDetalleConciliacion;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class DetalleConciliacionDaoImpl extends AbstractGenericDao<SreTsfeRivDetalleConciliacion>
		implements IDetalleConciliacionDao {

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();

		return resultado;
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestion(Integer pGestion) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE c.gestion = :pGestion "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql).setParameter("pGestion", pGestion)
				.getResultList();

		return resultado;
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorPeriodo(Integer pPeriodo) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE c.periodo = :pPeriodo "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql).setParameter("pPeriodo", pPeriodo)
				.getResultList();

		return resultado;

	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodo(Integer pGestion,
			Integer pPeriodo) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c"
				+ "  WHERE c.periodo = :pPeriodo AND c.gestion = :pGestion AND c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql).setParameter("pPeriodo", pPeriodo)
				.setParameter("pGestion", pGestion).getResultList();

		return resultado;
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorResumenId(Long pResumenConciliacionId) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c"
				+ "  WHERE c.resumenConciliacionId = :pResumenConciliacionId  AND c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql)
				.setParameter("pResumenConciliacionId", pResumenConciliacionId).getResultList();

		return resultado;

	}

	@Override
	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodo(Long pBeneficiarioId,
			Integer pGestion, Integer pPeriodo) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.periodo = :pPeriodo AND c.gestion = :pGestion and c.estadoId = 'AC'";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).setParameter("pGestion", pGestion)
				.setParameter("pPeriodo", pPeriodo).getResultList();

		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

	@Override
	public SreTsfeRivDetalleConciliacion obtenerDetalleConciliacionPorBeneficiarioIdGestionPeriodoTipoConciliacion(
			Long pBeneficiarioId, Integer pGestion, Integer pPeriodo, String pTipoConciliacion) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.periodo = :pPeriodo AND c.gestion = :pGestion and c.estadoId = 'AC' and c.tipoConciliacionId = :pTipoConciliacion";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).setParameter("pGestion", pGestion)
				.setParameter("pPeriodo", pPeriodo).setParameter("pTipoConciliacion", pTipoConciliacion)
				.getResultList();

		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

	@Override
	public List<SreTsfeRivDetalleConciliacion> obtenerDetalleConciliacionPorGestionyPeriodoEstadoPago(Integer pGestion,
			Integer pPeriodo, String pEstadoProcesoPago) {
		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.periodo = :pPeriodo AND c.gestion = :pGestion and c.estadoId = 'AC' and c.estadoProcesoPagoId = :pEstadoConciliacion";

		List<SreTsfeRivDetalleConciliacion> resultado = getSession().createQuery(hql)
				.setParameter("pEstadoProcesoPago", pEstadoProcesoPago).setParameter("pGestion", pGestion)
				.setParameter("pPeriodo", pPeriodo).getResultList();

		return resultado;
	}

	@Override
	public BigDecimal obtenerMontoTotalPagadoDetalleConciliacionPorResumenId(Long pResumenConciliacionId) {

		BigDecimal vTotalPagado = new BigDecimal(0L);

		String hql = "FROM SreTsfeRivDetalleConciliacion as c  WHERE "
				+ " c.resumenConciliacionId = :pResumenConciliacionId and c.estadoId = 'AC' and c.estadoProcesoPagoId = 'PAG' and c.pagoBonoId is not null";

		List<SreTsfeRivDetalleConciliacion> vResultado = new ArrayList<SreTsfeRivDetalleConciliacion>();

		vResultado = getSession().createQuery(hql).setParameter("pResumenConciliacionId", pResumenConciliacionId)
				.getResultList();

		for (SreTsfeRivDetalleConciliacion fila : vResultado) {
			vTotalPagado = vTotalPagado.add(fila.getMontoTotalPagado());
		}

		return vTotalPagado;
	}

}

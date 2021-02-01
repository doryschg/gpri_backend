package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHBeneficiarios;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class BeneficiarioHistoricoDaoImpl extends AbstractGenericDao<SreTsfeRivHBeneficiarios>
		implements IBeneficiarioHistoricoDao {

	@Override
	public List<SreTsfeRivHBeneficiarios> obtenerHistoricoPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHBeneficiarios as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivHBeneficiarios> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;
	}

	@Override
	public SreTsfeRivHBeneficiarios obtenerUltimoRegistroHistoricoBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHBeneficiarios as c WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC' order by c.fechaUltimaModificacion ";

		List<SreTsfeRivHBeneficiarios> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

}

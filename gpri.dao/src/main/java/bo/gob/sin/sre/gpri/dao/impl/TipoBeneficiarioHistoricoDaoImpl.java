package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ITipoBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHTipoBeneficiario;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class TipoBeneficiarioHistoricoDaoImpl extends AbstractGenericDao<SreTsfeRivHTipoBeneficiario>
		implements ITipoBeneficiarioHistoricoDao {

	@Override
	public List<SreTsfeRivHTipoBeneficiario> obtenerHistoricoPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHTipoBeneficiario as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivHTipoBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;
	}

	@Override
	public SreTsfeRivHTipoBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHTipoBeneficiario as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC' and c.fechaHasta is null";

		List<SreTsfeRivHTipoBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

	@Override
	public SreTsfeRivHTipoBeneficiario guardarHistoricoTipoBeneficiario(SreTsfeRivHTipoBeneficiario pHistoricoTipoBeneficiario) {
		save(pHistoricoTipoBeneficiario);
		return pHistoricoTipoBeneficiario;
	}

}

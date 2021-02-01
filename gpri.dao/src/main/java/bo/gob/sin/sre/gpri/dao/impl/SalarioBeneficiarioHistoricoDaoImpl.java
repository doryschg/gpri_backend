package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ISalarioBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHSalarioBeneficiario;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class SalarioBeneficiarioHistoricoDaoImpl extends AbstractGenericDao<SreTsfeRivHSalarioBeneficiario>
		implements ISalarioBeneficiarioHistoricoDao {

	@Override
	public List<SreTsfeRivHSalarioBeneficiario> obtenerHistoricoSalarioPorBeneficiarioId(Long pBeneficiarioId) {

		String hql = "FROM SreTsfeRivHSalarioBeneficiario as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivHSalarioBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;

	}

	@Override
	public SreTsfeRivHSalarioBeneficiario obtenerUltimoRegistroHistoricoPorBeneficiarioId(Long pBeneficiarioId) {

		String hql = "FROM SreTsfeRivHSalarioBeneficiario as c WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC' and c.fechaHasta is null";

		List<SreTsfeRivHSalarioBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}
}

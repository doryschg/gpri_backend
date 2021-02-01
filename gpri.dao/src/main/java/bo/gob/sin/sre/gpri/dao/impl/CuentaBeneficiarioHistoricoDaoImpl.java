package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ICuentaBeneficiarioHistoricoDao;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class CuentaBeneficiarioHistoricoDaoImpl extends AbstractGenericDao<SreTsfeRivHCuentasBeneficiario>
		implements ICuentaBeneficiarioHistoricoDao {

	@Override
	public List<SreTsfeRivHCuentasBeneficiario> obtenerHistoricoBeneficiarioPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHCuentasBeneficiario as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivHCuentasBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;
	}

	public SreTsfeRivHCuentasBeneficiario obtenerUltimoRegistroHistoricoCuentaPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivHCuentasBeneficiario as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC' and c.fechaHasta is null";

		List<SreTsfeRivHCuentasBeneficiario> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();

		if (resultado.isEmpty()) {
			return null;
		}

		return resultado.get(0);
	}

}

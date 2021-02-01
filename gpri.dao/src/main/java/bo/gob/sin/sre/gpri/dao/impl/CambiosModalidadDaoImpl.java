package bo.gob.sin.sre.gpri.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ICambiosModalidadDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivCambiosModalidad;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class CambiosModalidadDaoImpl extends AbstractGenericDao<SreTsfeRivCambiosModalidad> implements ICambiosModalidadDao {

	@Override
	public List<SreTsfeRivCambiosModalidad> obtenerPorBeneficiarioId(Long pBeneficiarioId) {
		String hql = "FROM SreTsfeRivCambiosModalidad as c" + "  WHERE c.beneficiarioId = :pBeneficiarioId "
				+ "   AND c.estadoId = 'AC'";

		List<SreTsfeRivCambiosModalidad> resultado = getSession().createQuery(hql)
				.setParameter("pBeneficiarioId", pBeneficiarioId).getResultList();
		return resultado;
	}

}

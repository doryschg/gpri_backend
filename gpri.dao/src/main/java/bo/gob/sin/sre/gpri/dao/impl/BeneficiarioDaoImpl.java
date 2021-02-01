package bo.gob.sin.sre.gpri.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IBeneficiarioDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivBeneficiarios;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class BeneficiarioDaoImpl extends AbstractGenericDao<SreTsfeRivBeneficiarios> implements IBeneficiarioDao {

	@Override
	public SreTsfeRivBeneficiarios obtenerBeneficiarioPorPersonaId(long pBeneficiarioId) {
		SreTsfeRivBeneficiarios vRespuesta = null;
		try {
			String vHqlBeneficiario = "SELECT v FROM SreTsfeRivBeneficiarios v " + "where  "
					+ " v.beneficiarioId =:pBeneficiarioId " + "and v.estadoId = 'AC'";
			vRespuesta = (SreTsfeRivBeneficiarios) getSession().createQuery(vHqlBeneficiario)
					.setParameter("pBeneficiarioId", pBeneficiarioId).getSingleResult();
//            System.out.println("resultado >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + vContactos.size());
		} catch (Exception e) {
			System.out.println("error    dao <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}
		return vRespuesta;
	}

	@Override
	public SreTsfeRivBeneficiarios buscarUltimoRegistroVigenteBeneficiarioId(long pBeneficiarioId) {
		SreTsfeRivBeneficiarios vRespuesta = null;
		try {
			String vHqlBeneficiario = "SELECT v FROM SreTsfeRivBeneficiarios v " + "where  "
					+ " v.beneficiarioId =:pBeneficiarioId " + "and v.estadoId = 'AC' and v.fechaHasta is null";
			vRespuesta = (SreTsfeRivBeneficiarios) getSession().createQuery(vHqlBeneficiario)
					.setParameter("pBeneficiarioId", pBeneficiarioId).getSingleResult();
//	            System.out.println("resultado >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + vContactos.size());
		} catch (Exception e) {
			System.out.println("error    dao <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}
		return vRespuesta;
	}
}

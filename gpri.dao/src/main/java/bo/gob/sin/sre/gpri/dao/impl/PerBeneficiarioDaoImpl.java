package bo.gob.sin.sre.gpri.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.IPerBeneficiarioDao;
import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;
import bo.gob.sin.str.ccs.dao.AbstractGenericDao;

@Repository
@Transactional("transactionManager")
public class PerBeneficiarioDaoImpl extends AbstractGenericDao<SreTsfeRivPersonas> implements IPerBeneficiarioDao {

	@Override
	public Boolean bajaPersonaBeneficiario(Long pPersonaId) {
		boolean respuesta = false;

		String hql = "FROM SreTsfeRivPersonas as c" + "  WHERE c.personaId = :pPersonaId " + "   AND c.estadoId = 'AC'";
		List<SreTsfeRivPersonas> vDatosPersona = getSession().createQuery(hql).setParameter("pPersonaId", pPersonaId)
				.getResultList();
		vDatosPersona.forEach(registro -> {
			registro.setFechaHasta(LocalDateTime.now().toLocalDate());
			registro.setEstadoPerBeneficiarioId("D");
			this.save(registro);
		});

		return respuesta;
	}

	@Override
	public SreTsfeRivPersonas obtenerPorPersonaId(Long pPersonaId) {
		String hql = "FROM SreTsfeRivPersonas as c" + "  WHERE c.personaId = :pPersonaId "
				+ "   AND c.estadoId = 'AC' and c.estadoPerBeneficiarioId = 'H' and c.fechaHasta is null";
		List<SreTsfeRivPersonas> vDatosPersona = getSession().createQuery(hql).setParameter("pPersonaId", pPersonaId)
				.getResultList();
		if (!vDatosPersona.isEmpty()) {
			return vDatosPersona.get(0);
		}

		return null;
	}

}

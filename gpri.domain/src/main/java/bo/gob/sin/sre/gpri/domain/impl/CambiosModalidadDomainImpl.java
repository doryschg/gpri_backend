package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ICambiosModalidadDao;
import bo.gob.sin.sre.gpri.domain.ICambiosModalidadDomain;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoCambioModalidadPago;
import bo.gob.sin.sre.gpri.model.SreTsfeRivCambiosModalidad;

@Service
@Transactional("transactionManager")
public class CambiosModalidadDomainImpl implements ICambiosModalidadDomain {

	@Autowired
	private ICambiosModalidadDao cambiosModalidadDao;

	@Override
	public SreTsfeRivCambiosModalidad insertar(SreTsfeRivCambiosModalidad pCambiosModalidad) {
		List<SreTsfeRivCambiosModalidad> vCambiosRealizados = cambiosModalidadDao
				.obtenerPorBeneficiarioId(pCambiosModalidad.getBeneficiarioId());
		if (vCambiosRealizados.isEmpty() || !vCambiosRealizados.stream()
				.anyMatch(x -> x.getEstadoCambioId().equals(EnumEstadoCambioModalidadPago.PENDIENTE.getId()))) {
			pCambiosModalidad.setUsuarioUltimaModificacionId(pCambiosModalidad.getUsuarioRegistroId());
			pCambiosModalidad.setFechaDesde(LocalDate.now());
			pCambiosModalidad.setFechaRegistro(LocalDateTime.now());
			pCambiosModalidad.setFechaUltimaModificacion(pCambiosModalidad.getFechaRegistro());
			pCambiosModalidad.setEstadoId("AC");
			cambiosModalidadDao.save(pCambiosModalidad);
		} else {
			pCambiosModalidad = vCambiosRealizados.get(0);
		}

		return pCambiosModalidad;
	}

}

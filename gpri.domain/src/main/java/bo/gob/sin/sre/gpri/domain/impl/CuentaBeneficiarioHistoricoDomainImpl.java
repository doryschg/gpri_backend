package bo.gob.sin.sre.gpri.domain.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.dao.ICuentaBeneficiarioHistoricoDao;
import bo.gob.sin.sre.gpri.domain.ICuentaBeneficiarioHistoricoDomain;

@Service
@Transactional("transactionManager")
public class CuentaBeneficiarioHistoricoDomainImpl implements ICuentaBeneficiarioHistoricoDomain {

	@Autowired
	private ICuentaBeneficiarioHistoricoDao cuentaBeneficiarioHistoricoDao;

	@Override
	public List<SreTsfeRivHCuentasBeneficiario> obtenerHistoricoPorBeneficiarioId(long pBeneficiarioId) {
		return cuentaBeneficiarioHistoricoDao.obtenerHistoricoBeneficiarioPorBeneficiarioId(pBeneficiarioId);
	}

	@Override
	public SreTsfeRivHCuentasBeneficiario insertarHistoricoBeneficiario(
			SreTsfeRivHCuentasBeneficiario pHistoricoCuentaBeneficiario) {

		SreTsfeRivHCuentasBeneficiario vUiltimoHistoricoCuentaBeneficiario = cuentaBeneficiarioHistoricoDao
				.obtenerUltimoRegistroHistoricoCuentaPorBeneficiarioId(
						pHistoricoCuentaBeneficiario.getBeneficiarioId());
		if (vUiltimoHistoricoCuentaBeneficiario != null) {
			vUiltimoHistoricoCuentaBeneficiario.setFechaHasta(LocalDate.now());
			cuentaBeneficiarioHistoricoDao.save(vUiltimoHistoricoCuentaBeneficiario);
		}

		pHistoricoCuentaBeneficiario.setFechaRegistro(LocalDateTime.now());
		pHistoricoCuentaBeneficiario
				.setUsuarioUltimaModificacionId(pHistoricoCuentaBeneficiario.getUsuarioRegistroId());
		pHistoricoCuentaBeneficiario.setFechaUltimaModificacionId(LocalDateTime.now());
		pHistoricoCuentaBeneficiario.setFechaDesde(LocalDate.now());
		pHistoricoCuentaBeneficiario.setFechaHasta(null);
		pHistoricoCuentaBeneficiario.setEstadoId("AC");
		cuentaBeneficiarioHistoricoDao.save(pHistoricoCuentaBeneficiario);
		return pHistoricoCuentaBeneficiario;
	}

	@Override
	public SreTsfeRivHCuentasBeneficiario updateHistoricoBeneficiario(
			SreTsfeRivHCuentasBeneficiario pHistoricoCuentaBeneficiario) {
		if (cuentaBeneficiarioHistoricoDao.findById(pHistoricoCuentaBeneficiario.getCuentaId()) != null) {
			cuentaBeneficiarioHistoricoDao.save(pHistoricoCuentaBeneficiario);
		} else {
			return null;
		}

		return pHistoricoCuentaBeneficiario;
	}

}

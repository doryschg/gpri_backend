package bo.gob.sin.sre.gpri.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tsfe_riv_cambios_modalidad", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivCambiosModalidad {

	private long cambioId;
	private long beneficiarioId;
	private String pagoAutomaticoId;
	private String estadoCambioId;
	private LocalDate fechaDesde;

	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_cambio_nb")
	public long getCambioId() {
		return cambioId;
	}

	@Column(name = "seq_beneficiario_nb")
	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "pago_automatico_id_vc")
	public String getPagoAutomaticoId() {
		return pagoAutomaticoId;
	}

	@Column(name = "estado_cambio_id_vc")
	public String getEstadoCambioId() {
		return estadoCambioId;
	}

	@Column(name = "fecha_desde_dt")
	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	@Column(name = "usuario_registro_nb")
	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	@Column(name = "fecha_registro_ts")
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	@Column(name = "usuario_ultima_modificacion_nb")
	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	@Column(name = "fecha_ultima_modificacion_ts")
	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	@Column(name = "estado_id_vc")
	public String getEstadoId() {
		return estadoId;
	}

	public void setCambioId(long cambioId) {
		this.cambioId = cambioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setPagoAutomaticoId(String pagoAutomaticoId) {
		this.pagoAutomaticoId = pagoAutomaticoId;
	}

	public void setEstadoCambioId(String estadoCambioId) {
		this.estadoCambioId = estadoCambioId;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}

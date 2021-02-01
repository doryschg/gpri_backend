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
@Table(name = "tsfe_riv_h_tipos_beneficiario", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivHTipoBeneficiario {

	private long tipoBeneficiarioId;
	private long beneficiarioId;
	private String tipoBeneficiario;
	private String estadoTipoBeneficiarioId;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_tipo_beneficiario_nb")
	public long getTipoBeneficiarioId() {
		return tipoBeneficiarioId;
	}

	@Column(name = "seq_beneficiario_nb")
	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "tipo_beneficiario_id_vc")
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	@Column(name = "estado_tipo_beneficiario_id_vc")
	public String getEstadoTipoBeneficiarioId() {
		return estadoTipoBeneficiarioId;
	}

	@Column(name = "fecha_desde_dt")
	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	@Column(name = "fecha_hasta_dt")
	public LocalDate getFechaHasta() {
		return fechaHasta;
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

	public void setTipoBeneficiarioId(long tipoBeneficiarioId) {
		this.tipoBeneficiarioId = tipoBeneficiarioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	public void setEstadoTipoBeneficiarioId(String estadoTipoBeneficiarioId) {
		this.estadoTipoBeneficiarioId = estadoTipoBeneficiarioId;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (beneficiarioId ^ (beneficiarioId >>> 32));
		result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
		result = prime * result + ((estadoTipoBeneficiarioId == null) ? 0 : estadoTipoBeneficiarioId.hashCode());
		result = prime * result + ((fechaDesde == null) ? 0 : fechaDesde.hashCode());
		result = prime * result + ((fechaHasta == null) ? 0 : fechaHasta.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + ((tipoBeneficiario == null) ? 0 : tipoBeneficiario.hashCode());
		result = prime * result + (int) (tipoBeneficiarioId ^ (tipoBeneficiarioId >>> 32));
		result = prime * result + (int) (usuarioRegistroId ^ (usuarioRegistroId >>> 32));
		result = prime * result + (int) (usuarioUltimaModificacionId ^ (usuarioUltimaModificacionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SreTsfeRivHTipoBeneficiario other = (SreTsfeRivHTipoBeneficiario) obj;
		if (beneficiarioId != other.beneficiarioId)
			return false;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
			return false;
		if (estadoTipoBeneficiarioId == null) {
			if (other.estadoTipoBeneficiarioId != null)
				return false;
		} else if (!estadoTipoBeneficiarioId.equals(other.estadoTipoBeneficiarioId))
			return false;
		if (fechaDesde == null) {
			if (other.fechaDesde != null)
				return false;
		} else if (!fechaDesde.equals(other.fechaDesde))
			return false;
		if (fechaHasta == null) {
			if (other.fechaHasta != null)
				return false;
		} else if (!fechaHasta.equals(other.fechaHasta))
			return false;
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null)
				return false;
		} else if (!fechaRegistro.equals(other.fechaRegistro))
			return false;
		if (fechaUltimaModificacion == null) {
			if (other.fechaUltimaModificacion != null)
				return false;
		} else if (!fechaUltimaModificacion.equals(other.fechaUltimaModificacion))
			return false;
		if (tipoBeneficiario == null) {
			if (other.tipoBeneficiario != null)
				return false;
		} else if (!tipoBeneficiario.equals(other.tipoBeneficiario))
			return false;
		if (tipoBeneficiarioId != other.tipoBeneficiarioId)
			return false;
		if (usuarioRegistroId != other.usuarioRegistroId)
			return false;
		if (usuarioUltimaModificacionId != other.usuarioUltimaModificacionId)
			return false;
		return true;
	}

}

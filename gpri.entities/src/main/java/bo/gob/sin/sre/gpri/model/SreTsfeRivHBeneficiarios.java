package bo.gob.sin.sre.gpri.model;

//import org.hibernate.annotations.TypeDef;
import  com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tsfe_riv_h_beneficiarios", schema = "sfe_facturacion", catalog = "dsin")
//@TypeDef(typeClass = JsonBinaryType.class, defaultForType = JsonNode.class)
@TypeDef(
		name = "jsonb",
		typeClass = JsonBinaryType.class
)
public class SreTsfeRivHBeneficiarios {
	private long hBeneficiarioId;
	private long beneficiarioId;
	private String tipoTransaccionId;
	private String contenidoJs;
	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_h_beneficiario_nb")
	public long gethBeneficiarioId() {
		return hBeneficiarioId;
	}

	@Column(name = "seq_beneficiario_nb")
	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "tipo_h_transaccion_id_vc")
	public String getTipoTransaccionId() {
		return tipoTransaccionId;
	}

	 @Type(type = "jsonb")
	 @Column(columnDefinition = "jsonb", name = "datos_beneficiario_js") public String
	 getContenidoJs() { return contenidoJs; }

	public void setContenidoJs(String contenidoJs) {
		this.contenidoJs = contenidoJs;
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
		return usuarioRegistroId;
	}

	@Column(name = "fecha_ultima_modificacion_ts")
	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	@Column(name = "estado_id_vc")
	public String getEstadoId() {
		return estadoId;
	}

	public void sethBeneficiarioId(long hBeneficiarioId) {
		this.hBeneficiarioId = hBeneficiarioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setTipoTransaccionId(String tipoTransaccionId) {
		this.tipoTransaccionId = tipoTransaccionId;
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
		result = prime * result + ((contenidoJs == null) ? 0 : contenidoJs.hashCode());
		result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + (int) (hBeneficiarioId ^ (hBeneficiarioId >>> 32));
		result = prime * result + ((tipoTransaccionId == null) ? 0 : tipoTransaccionId.hashCode());
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
		SreTsfeRivHBeneficiarios other = (SreTsfeRivHBeneficiarios) obj;
		if (beneficiarioId != other.beneficiarioId)
			return false;
		if (contenidoJs == null) {
			if (other.contenidoJs != null)
				return false;
		} else if (!contenidoJs.equals(other.contenidoJs))
			return false;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
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
		if (hBeneficiarioId != other.hBeneficiarioId)
			return false;
		if (tipoTransaccionId == null) {
			if (other.tipoTransaccionId != null)
				return false;
		} else if (!tipoTransaccionId.equals(other.tipoTransaccionId))
			return false;
		if (usuarioRegistroId != other.usuarioRegistroId)
			return false;
		if (usuarioUltimaModificacionId != other.usuarioUltimaModificacionId)
			return false;
		return true;
	}

}

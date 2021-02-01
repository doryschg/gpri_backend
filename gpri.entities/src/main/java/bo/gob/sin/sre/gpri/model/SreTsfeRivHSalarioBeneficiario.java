package bo.gob.sin.sre.gpri.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tsfe_riv_h_salarios_beneficiario", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivHSalarioBeneficiario {
	private long salarioId;
	private long beneficiarioId;
	private BigDecimal totalSalario;
	private BigDecimal primerSalario;
	private int gestionPrimerSalario;
	private int periodoPrimerSalario;
	private BigDecimal segundoSalario;
	private int gestionSegundoSalario;
	private int periodoSegundoSalario;
	private BigDecimal tercerSalario;
	private int gestionTercerSalario;
	private int periodoTercerSalario;
	private String estadoTotalSalarioId;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;

	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_salario_nb")
	public long getSalarioId() {
		return salarioId;
	}

	@Column(name = "seq_beneficiario_nb")
	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "total_salario_nb")
	public BigDecimal getTotalSalario() {
		return totalSalario;
	}

	@Column(name = "primer_salario_nb")
	public BigDecimal getPrimerSalario() {
		return primerSalario;
	}

	@Column(name = "gestion_primer_salario_nb")
	public int getGestionPrimerSalario() {
		return gestionPrimerSalario;
	}

	@Column(name = "periodo_primer_salario_nb")
	public int getPeriodoPrimerSalario() {
		return periodoPrimerSalario;
	}

	@Column(name = "segundo_salario_nb")
	public BigDecimal getSegundoSalario() {
		return segundoSalario;
	}

	@Column(name = "gestion_segundo_salario_nb")
	public int getGestionSegundoSalario() {
		return gestionSegundoSalario;
	}

	@Column(name = "periodo_segundo_salario_nb")
	public int getPeriodoSegundoSalario() {
		return periodoSegundoSalario;
	}

	@Column(name = "tercer_salario_nb")
	public BigDecimal getTercerSalario() {
		return tercerSalario;
	}

	@Column(name = "gestion_tercer_salario_nb")
	public int getGestionTercerSalario() {
		return gestionTercerSalario;
	}

	@Column(name = "periodo_tercer_salario_nb")
	public int getPeriodoTercerSalario() {
		return periodoTercerSalario;
	}

	@Column(name = "estado_total_salario_id_vc")
	public String getEstadoTotalSalarioId() {
		return estadoTotalSalarioId;
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

	public void setSalarioId(long salarioId) {
		this.salarioId = salarioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setTotalSalario(BigDecimal totalSalario) {
		this.totalSalario = totalSalario;
	}

	public void setPrimerSalario(BigDecimal primerSalario) {
		this.primerSalario = primerSalario;
	}

	public void setGestionPrimerSalario(int gestionPrimerSalario) {
		this.gestionPrimerSalario = gestionPrimerSalario;
	}

	public void setPeriodoPrimerSalario(int periodoPrimerSalario) {
		this.periodoPrimerSalario = periodoPrimerSalario;
	}

	public void setSegundoSalario(BigDecimal segundoSalario) {
		this.segundoSalario = segundoSalario;
	}

	public void setGestionSegundoSalario(int gestionSegundoSalario) {
		this.gestionSegundoSalario = gestionSegundoSalario;
	}

	public void setPeriodoSegundoSalario(int periodoSegundoSalario) {
		this.periodoSegundoSalario = periodoSegundoSalario;
	}

	public void setTercerSalario(BigDecimal tercerSalario) {
		this.tercerSalario = tercerSalario;
	}

	public void setGestionTercerSalario(int gestionTercerSalario) {
		this.gestionTercerSalario = gestionTercerSalario;
	}

	public void setPeriodoTercerSalario(int periodoTercerSalario) {
		this.periodoTercerSalario = periodoTercerSalario;
	}

	public void setEstadoTotalSalarioId(String estadoTotalSalarioId) {
		this.estadoTotalSalarioId = estadoTotalSalarioId;
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
		result = prime * result + ((estadoTotalSalarioId == null) ? 0 : estadoTotalSalarioId.hashCode());
		result = prime * result + ((fechaDesde == null) ? 0 : fechaDesde.hashCode());
		result = prime * result + ((fechaHasta == null) ? 0 : fechaHasta.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + gestionPrimerSalario;
		result = prime * result + gestionSegundoSalario;
		result = prime * result + gestionTercerSalario;
		result = prime * result + periodoPrimerSalario;
		result = prime * result + periodoSegundoSalario;
		result = prime * result + periodoTercerSalario;
		result = prime * result + ((primerSalario == null) ? 0 : primerSalario.hashCode());
		result = prime * result + (int) (salarioId ^ (salarioId >>> 32));
		result = prime * result + ((segundoSalario == null) ? 0 : segundoSalario.hashCode());
		result = prime * result + ((tercerSalario == null) ? 0 : tercerSalario.hashCode());
		result = prime * result + ((totalSalario == null) ? 0 : totalSalario.hashCode());
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
		SreTsfeRivHSalarioBeneficiario other = (SreTsfeRivHSalarioBeneficiario) obj;
		if (beneficiarioId != other.beneficiarioId)
			return false;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
			return false;
		if (estadoTotalSalarioId == null) {
			if (other.estadoTotalSalarioId != null)
				return false;
		} else if (!estadoTotalSalarioId.equals(other.estadoTotalSalarioId))
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
		if (gestionPrimerSalario != other.gestionPrimerSalario)
			return false;
		if (gestionSegundoSalario != other.gestionSegundoSalario)
			return false;
		if (gestionTercerSalario != other.gestionTercerSalario)
			return false;
		if (periodoPrimerSalario != other.periodoPrimerSalario)
			return false;
		if (periodoSegundoSalario != other.periodoSegundoSalario)
			return false;
		if (periodoTercerSalario != other.periodoTercerSalario)
			return false;
		if (primerSalario == null) {
			if (other.primerSalario != null)
				return false;
		} else if (!primerSalario.equals(other.primerSalario))
			return false;
		if (salarioId != other.salarioId)
			return false;
		if (segundoSalario == null) {
			if (other.segundoSalario != null)
				return false;
		} else if (!segundoSalario.equals(other.segundoSalario))
			return false;
		if (tercerSalario == null) {
			if (other.tercerSalario != null)
				return false;
		} else if (!tercerSalario.equals(other.tercerSalario))
			return false;
		if (totalSalario == null) {
			if (other.totalSalario != null)
				return false;
		} else if (!totalSalario.equals(other.totalSalario))
			return false;
		if (usuarioRegistroId != other.usuarioRegistroId)
			return false;
		if (usuarioUltimaModificacionId != other.usuarioUltimaModificacionId)
			return false;
		return true;
	}

}

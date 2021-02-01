package bo.gob.sin.sre.gpri.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tsfe_riv_resumen_conciliacion", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivResumenConciliacion {

	private Long resumenConciliacionId;
	private Integer gestion;
	private Integer periodo;
	private String tipoConciliacionId;
	private BigDecimal montoTotalReintegros;
	private BigDecimal montoTotalFacturas;
	private BigDecimal montoTotalReparos;
	private BigDecimal montoTotalPagar;
	private BigDecimal montoTotalPagado;
	private Long cantidadBeneficiarios;
	private String estadoConciliacionId;
	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_resumen_conciliacion_nb")
	public Long getResumenConciliacionId() {
		return resumenConciliacionId;
	}

	@Column(name = "gestion_nb")
	public Integer getGestion() {
		return gestion;
	}

	@Column(name = "periodo_nb")
	public Integer getPeriodo() {
		return periodo;
	}

	@Column(name = "tipo_conciliacion_id_vc")
	public String getTipoConciliacionId() {
		return tipoConciliacionId;
	}

	@Column(name = "monto_total_facturas_nb")
	public BigDecimal getMontoTotalFacturas() {
		return montoTotalFacturas;
	}

	@Column(name = "monto_total_reintegros_nb")
	public BigDecimal getMontoTotalReintegros() {
		return montoTotalReintegros;
	}

	@Column(name = "monto_total_reparos_nb")
	public BigDecimal getMontoTotalReparos() {
		return montoTotalReparos;
	}

	@Column(name = "monto_total_a_pagar_nb")
	public BigDecimal getMontoTotalPagar() {
		return montoTotalPagar;
	}

	@Column(name = "monto_total_pagado_nb")
	public BigDecimal getMontoTotalPagado() {
		return montoTotalPagado;
	}

	@Column(name = "cantidad_beneficiarios_nb")
	public Long getCantidadBeneficiarios() {
		return cantidadBeneficiarios;
	}

	@Column(name = "estado_conciliacion_id_vc")
	public String getEstadoConciliacionId() {
		return estadoConciliacionId;
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

	public void setResumenConciliacionId(Long resumenConciliacionId) {
		this.resumenConciliacionId = resumenConciliacionId;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public void setTipoConciliacionId(String tipoConciliacionId) {
		this.tipoConciliacionId = tipoConciliacionId;
	}

	public void setMontoTotalFacturas(BigDecimal montoTotalFacturas) {
		this.montoTotalFacturas = montoTotalFacturas;
	}

	public void setMontoTotalReintegros(BigDecimal montoTotalReintegros) {
		this.montoTotalReintegros = montoTotalReintegros;
	}

	public void setMontoTotalReparos(BigDecimal montoTotalReparos) {
		this.montoTotalReparos = montoTotalReparos;
	}

	public void setMontoTotalPagar(BigDecimal montoTotalPagar) {
		this.montoTotalPagar = montoTotalPagar;
	}

	public void setMontoTotalPagado(BigDecimal montoTotalPagado) {
		this.montoTotalPagado = montoTotalPagado;
	}

	public void setCantidadBeneficiarios(Long cantidadBeneficiarios) {
		this.cantidadBeneficiarios = cantidadBeneficiarios;
	}

	public void setEstadoConciliacionId(String estadoConciliacionId) {
		this.estadoConciliacionId = estadoConciliacionId;
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
		result = prime * result + ((cantidadBeneficiarios == null) ? 0 : cantidadBeneficiarios.hashCode());
		result = prime * result + ((estadoConciliacionId == null) ? 0 : estadoConciliacionId.hashCode());
		result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + ((gestion == null) ? 0 : gestion.hashCode());
		result = prime * result + ((montoTotalFacturas == null) ? 0 : montoTotalFacturas.hashCode());
		result = prime * result + ((montoTotalPagado == null) ? 0 : montoTotalPagado.hashCode());
		result = prime * result + ((montoTotalPagar == null) ? 0 : montoTotalPagar.hashCode());
		result = prime * result + ((montoTotalReintegros == null) ? 0 : montoTotalReintegros.hashCode());
		result = prime * result + ((montoTotalReparos == null) ? 0 : montoTotalReparos.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((resumenConciliacionId == null) ? 0 : resumenConciliacionId.hashCode());
		result = prime * result + ((tipoConciliacionId == null) ? 0 : tipoConciliacionId.hashCode());
		result = prime * result + (int) (usuarioRegistroId ^ (usuarioRegistroId >>> 32));
		result = prime * result + (int) (usuarioUltimaModificacionId ^ (usuarioUltimaModificacionId >>> 32));
		return result;
	}

	public void calcularMontoPagar(BigDecimal pMontoIngresado) {
		this.setMontoTotalPagar(this.getMontoTotalPagar().add(pMontoIngresado));
	}

	public void calcularMontoPagado(BigDecimal pMontoPagar, BigDecimal pMontoReparo) {
		this.setMontoTotalPagado(pMontoPagar.subtract(pMontoReparo));
	}

	public void calcularMontoReparo(BigDecimal pMontoIngresado) {
		this.setMontoTotalReparos(this.getMontoTotalReparos().add(pMontoIngresado));
	}

	public void calcularMontoReintegro(BigDecimal pMontoIngresado) {
		this.setMontoTotalReintegros(this.getMontoTotalReintegros().add(pMontoIngresado));
	}

	public void calcularMontoFacturas(BigDecimal pMontoIngresado) {
		this.setMontoTotalFacturas(this.getMontoTotalFacturas().add(pMontoIngresado));
	}
	
	public void incrementarBeneficiario() {
		this.setCantidadBeneficiarios(this.getCantidadBeneficiarios() + 1);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SreTsfeRivResumenConciliacion other = (SreTsfeRivResumenConciliacion) obj;
		if (cantidadBeneficiarios == null) {
			if (other.cantidadBeneficiarios != null)
				return false;
		} else if (!cantidadBeneficiarios.equals(other.cantidadBeneficiarios))
			return false;
		if (estadoConciliacionId == null) {
			if (other.estadoConciliacionId != null)
				return false;
		} else if (!estadoConciliacionId.equals(other.estadoConciliacionId))
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
		if (gestion == null) {
			if (other.gestion != null)
				return false;
		} else if (!gestion.equals(other.gestion))
			return false;
		if (montoTotalFacturas == null) {
			if (other.montoTotalFacturas != null)
				return false;
		} else if (!montoTotalFacturas.equals(other.montoTotalFacturas))
			return false;
		if (montoTotalPagado == null) {
			if (other.montoTotalPagado != null)
				return false;
		} else if (!montoTotalPagado.equals(other.montoTotalPagado))
			return false;
		if (montoTotalPagar == null) {
			if (other.montoTotalPagar != null)
				return false;
		} else if (!montoTotalPagar.equals(other.montoTotalPagar))
			return false;
		if (montoTotalReintegros == null) {
			if (other.montoTotalReintegros != null)
				return false;
		} else if (!montoTotalReintegros.equals(other.montoTotalReintegros))
			return false;
		if (montoTotalReparos == null) {
			if (other.montoTotalReparos != null)
				return false;
		} else if (!montoTotalReparos.equals(other.montoTotalReparos))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (resumenConciliacionId == null) {
			if (other.resumenConciliacionId != null)
				return false;
		} else if (!resumenConciliacionId.equals(other.resumenConciliacionId))
			return false;
		if (tipoConciliacionId == null) {
			if (other.tipoConciliacionId != null)
				return false;
		} else if (!tipoConciliacionId.equals(other.tipoConciliacionId))
			return false;
		if (usuarioRegistroId != other.usuarioRegistroId)
			return false;
		if (usuarioUltimaModificacionId != other.usuarioUltimaModificacionId)
			return false;
		return true;
	}
}

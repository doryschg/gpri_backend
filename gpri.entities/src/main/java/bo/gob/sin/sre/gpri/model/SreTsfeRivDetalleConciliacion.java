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
@Table(name = "tsfe_riv_detalle_conciliacion", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivDetalleConciliacion {

	private Long detalleConciliacionId;
	private Long resumenConciliacionId;
	private Long beneficiarioId;
	private String formularioId;
	private Long salarioId;
	private Integer gestion;
	private Integer periodo;
	private BigDecimal montoTotalFacturas;

	private Long idBeneficiarioSigep;
	private String estadoProcesoPagoId;
	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	private String tipoConciliacionId;
	private Long cantidadFacturas;
	private BigDecimal montoTotalReintegros;
	private BigDecimal montoTotalReparos;
	private BigDecimal montoTotalPagar;
	private BigDecimal montoTotalPagado;
	private Long pagoBonoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_detalle_conciliacion_nb")
	public Long getDetalleConciliacionId() {
		return detalleConciliacionId;
	}

	@Column(name = "seq_resumen_conciliacion_nb")
	public Long getResumenConciliacionId() {
		return resumenConciliacionId;
	}

	@Column(name = "seq_beneficiario_nb")
	public Long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "seq_formulario_vc")
	public String getFormularioId() {
		return formularioId;
	}

	@Column(name = "seq_salario_nb")
	public Long getSalarioId() {
		return salarioId;
	}

	@Column(name = "gestion_nb")
	public Integer getGestion() {
		return gestion;
	}

	@Column(name = "periodo_nb")
	public Integer getPeriodo() {
		return periodo;
	}

	@Column(name = "monto_total_facturas_nb")
	public BigDecimal getMontoTotalFacturas() {
		return montoTotalFacturas;
	}

    @Column(name = "id_beneficiario_sigep_nb")
	public Long getIdBeneficiarioSigep() {
		return idBeneficiarioSigep;
	}

	@Column(name = "estado_proceso_pago_id_vc")
	public String getEstadoProcesoPagoId() {
		return estadoProcesoPagoId;
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

	// ==========================================================

	@Column(name = "tipo_conciliacion_id_vc")
	public String getTipoConciliacionId() {
		return tipoConciliacionId;
	}

	@Column(name = "cantidad_facturas_nb")
	public Long getCantidadFacturas() {
		return cantidadFacturas;
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

	@Column(name = "id_pago_bono_nb")
	public Long getPagoBonoId() {
		return pagoBonoId;
	}

	public void setDetalleConciliacionId(Long detalleConciliacionId) {
		this.detalleConciliacionId = detalleConciliacionId;
	}

	public void setResumenConciliacionId(Long resumenConciliacionId) {
		this.resumenConciliacionId = resumenConciliacionId;
	}

	public void setBeneficiarioId(Long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setFormularioId(String formularioId) {
		this.formularioId = formularioId;
	}

	public void setSalarioId(Long salarioId) {
		this.salarioId = salarioId;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public void setMontoTotalFacturas(BigDecimal montoTotalFacturas) {
		this.montoTotalFacturas = montoTotalFacturas;
	}

	public void setEstadoProcesoPagoId(String estadoProcesoPagoId) {
		this.estadoProcesoPagoId = estadoProcesoPagoId;
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

	public void setMontoTotalPagar(BigDecimal montoTotalPagar) {
		this.montoTotalPagar = montoTotalPagar;
	}
	/* ======================================== */

	public void setTipoConciliacionId(String tipoConciliacionId) {
		this.tipoConciliacionId = tipoConciliacionId;
	}

	public void setCantidadFacturas(Long cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
	}

	public void setMontoTotalReintegros(BigDecimal montoTotalReintegros) {
		this.montoTotalReintegros = montoTotalReintegros;
	}

	public void setMontoTotalReparos(BigDecimal montoTotalReparos) {
		this.montoTotalReparos = montoTotalReparos;
	}

	public void setMontoTotalPagado(BigDecimal montoTotalPagado) {
		this.montoTotalPagado = montoTotalPagado;
	}

	public void setPagoBonoId(Long pagoBonoId) {
		this.pagoBonoId = pagoBonoId;
	}

	public void calcularMontoPagado(BigDecimal pMontoPagar, BigDecimal pMontoReparo) {
		this.setMontoTotalPagado(pMontoPagar.subtract(pMontoReparo));
	}

	public void setIdBeneficiarioSigep(Long idBeneficiarioSigep) {
		this.idBeneficiarioSigep = idBeneficiarioSigep;
	}

}

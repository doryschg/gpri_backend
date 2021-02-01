package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ConciliacionDto implements Serializable {
	private long detalleConciliacionId;
	private long resumenConciliacionId;
	private long beneficiarioId;
	private long gestion;
	private long periodo;
	private BigDecimal montoFacturas;
	private BigDecimal montoReparo;
	private BigDecimal montoPago;
	private String estadoProcesoPago;
	private String formularioId;
	private long salarioId;
	private long usuarioRegistroNb;
	private Timestamp fechaRegistroTs;
	private long usuarioUltimaModificacionNb;
	private Timestamp fechaUltimaModificacionTs;
	private String estadoIdVc;

	private String beneficiario;

	public ConciliacionDto() {
	}

	public long getDetalleConciliacionId() {
		return detalleConciliacionId;
	}

	public void setDetalleConciliacionId(long detalleConciliacionId) {
		this.detalleConciliacionId = detalleConciliacionId;
	}

	public long getResumenConciliacionId() {
		return resumenConciliacionId;
	}

	public void setResumenConciliacionId(long resumenConciliacionId) {
		this.resumenConciliacionId = resumenConciliacionId;
	}

	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public long getGestion() {
		return gestion;
	}

	public void setGestion(long gestion) {
		this.gestion = gestion;
	}

	public long getPeriodo() {
		return periodo;
	}

	public void setPeriodo(long periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getMontoFacturas() {
		return montoFacturas;
	}

	public void setMontoFacturas(BigDecimal montoFacturas) {
		this.montoFacturas = montoFacturas;
	}

	public BigDecimal getMontoReparo() {
		return montoReparo;
	}

	public void setMontoReparo(BigDecimal montoReparo) {
		this.montoReparo = montoReparo;
	}

	public BigDecimal getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(BigDecimal montoPago) {
		this.montoPago = montoPago;
	}

	public String getEstadoProcesoPago() {
		return estadoProcesoPago;
	}

	public void setEstadoProcesoPago(String estadoProcesoPago) {
		this.estadoProcesoPago = estadoProcesoPago;
	}

	public String getFormularioId() {
		return formularioId;
	}

	public void setFormularioId(String formularioId) {
		this.formularioId = formularioId;
	}

	public long getSalarioId() {
		return salarioId;
	}

	public void setSalarioId(long salarioId) {
		this.salarioId = salarioId;
	}

	public long getUsuarioRegistroNb() {
		return usuarioRegistroNb;
	}

	public void setUsuarioRegistroNb(long usuarioRegistroNb) {
		this.usuarioRegistroNb = usuarioRegistroNb;
	}

	public Timestamp getFechaRegistroTs() {
		return fechaRegistroTs;
	}

	public void setFechaRegistroTs(Timestamp fechaRegistroTs) {
		this.fechaRegistroTs = fechaRegistroTs;
	}

	public long getUsuarioUltimaModificacionNb() {
		return usuarioUltimaModificacionNb;
	}

	public void setUsuarioUltimaModificacionNb(long usuarioUltimaModificacionNb) {
		this.usuarioUltimaModificacionNb = usuarioUltimaModificacionNb;
	}

	public Timestamp getFechaUltimaModificacionTs() {
		return fechaUltimaModificacionTs;
	}

	public void setFechaUltimaModificacionTs(Timestamp fechaUltimaModificacionTs) {
		this.fechaUltimaModificacionTs = fechaUltimaModificacionTs;
	}

	public String getEstadoIdVc() {
		return estadoIdVc;
	}

	public void setEstadoIdVc(String estadoIdVc) {
		this.estadoIdVc = estadoIdVc;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	@Override
	public String toString() {
		return "ConciliacionDto [detalleConciliacionId=" + detalleConciliacionId + ", resumenConciliacionId="
				+ resumenConciliacionId + ", beneficiarioId=" + beneficiarioId + ", gestion=" + gestion + ", periodo="
				+ periodo + ", montoFacturas=" + montoFacturas + ", montoReparo=" + montoReparo + ", montoPago="
				+ montoPago + ", estadoProcesoPago=" + estadoProcesoPago + ", formularioId=" + formularioId
				+ ", salarioId=" + salarioId + ", usuarioRegistroNb=" + usuarioRegistroNb + ", fechaRegistroTs="
				+ fechaRegistroTs + ", usuarioUltimaModificacionNb=" + usuarioUltimaModificacionNb
				+ ", fechaUltimaModificacionTs=" + fechaUltimaModificacionTs + ", estadoIdVc=" + estadoIdVc
				+ ", beneficiario=" + beneficiario + "]";
	}

}

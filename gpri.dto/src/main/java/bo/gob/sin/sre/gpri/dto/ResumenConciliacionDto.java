package bo.gob.sin.sre.gpri.dto;

import java.math.BigDecimal;

public class ResumenConciliacionDto extends AuditoriaDto {
	private Long resumenConciliacionId;
	private Integer gestion;
	private Integer periodo;
	private BigDecimal montoTotalReintegros;
	private BigDecimal montoTotalReparos;
	private BigDecimal montoTotalPagar;
	private BigDecimal montoTotalFacturas;
	private BigDecimal montoTotalPagado;
	private String tipoConciliacionId;
	private Long cantidadBeneficiarios;
	private String estadoConciliacionId;
	private Integer totalRegistros;

	public Long getResumenConciliacionId() {
		return resumenConciliacionId;
	}

	public void setResumenConciliacionId(Long resumenConciliacionId) {
		this.resumenConciliacionId = resumenConciliacionId;
	}

	public Integer getGestion() {
		return gestion;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getMontoTotalReintegros() {
		return montoTotalReintegros;
	}

	public void setMontoTotalReintegros(BigDecimal montoTotalReintegros) {
		this.montoTotalReintegros = montoTotalReintegros;
	}

	public BigDecimal getMontoTotalReparos() {
		return montoTotalReparos;
	}

	public void setMontoTotalReparos(BigDecimal montoTotalReparos) {
		this.montoTotalReparos = montoTotalReparos;
	}

	public BigDecimal getMontoTotalPagar() {
		return montoTotalPagar;
	}

	public void setMontoTotalPagar(BigDecimal montoTotalPagar) {
		this.montoTotalPagar = montoTotalPagar;
	}

	public BigDecimal getMontoTotalFacturas() {
		return montoTotalFacturas;
	}

	public void setMontoTotalFacturas(BigDecimal montoTotalFacturas) {
		this.montoTotalFacturas = montoTotalFacturas;
	}

	public BigDecimal getMontoTotalPagado() {
		return montoTotalPagado;
	}

	public void setMontoTotalPagado(BigDecimal montoTotalPagado) {
		this.montoTotalPagado = montoTotalPagado;
	}

	public String getTipoConciliacionId() {
		return tipoConciliacionId;
	}

	public void setTipoConciliacionId(String tipoConciliacionId) {
		this.tipoConciliacionId = tipoConciliacionId;
	}

	public Long getCantidadBeneficiarios() {
		return cantidadBeneficiarios;
	}

	public void setCantidadBeneficiarios(Long cantidadBeneficiarios) {
		this.cantidadBeneficiarios = cantidadBeneficiarios;
	}

	public String getEstadoConciliacionId() {
		return estadoConciliacionId;
	}

	public void setEstadoConciliacionId(String estadoConciliacionId) {
		this.estadoConciliacionId = estadoConciliacionId;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	@Override
	public String toString() {
		return "ResumenConciliacionDto [resumenConciliacionId=" + resumenConciliacionId + ", gestion=" + gestion
				+ ", periodo=" + periodo + ", montoTotalReintegros=" + montoTotalReintegros + ", montoTotalReparos="
				+ montoTotalReparos + ", montoTotalPagar=" + montoTotalPagar + ", montoTotalFacturas="
				+ montoTotalFacturas + ", montoTotalPagado=" + montoTotalPagado + ", tipoConciliacionId="
				+ tipoConciliacionId + ", cantidadBeneficiarios=" + cantidadBeneficiarios + ", estadoConciliacionId="
				+ estadoConciliacionId + "]";
	}

}

package bo.gob.sin.sre.gpri.dto;

import java.math.BigDecimal;

public class DetalleConciliacionDto extends AuditoriaDto {
	private String beneficiario;
	private Long bonoSigepId;
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

	private String tipoConciliacionId;
	private Long cantidadFacturas;
	private BigDecimal montoTotalReintegros;
	private BigDecimal montoTotalReparos;
	private BigDecimal montoTotalPagar;
	private BigDecimal montoTotalPagado;
	private Long pagoBonoId;

	private BeneficiarioDto beneficiarioDto;
	
	private Integer totalRegistros;

	public Long getDetalleConciliacionId() {
		return detalleConciliacionId;
	}

	public void setDetalleConciliacionId(Long detalleConciliacionId) {
		this.detalleConciliacionId = detalleConciliacionId;
	}

	public Long getResumenConciliacionId() {
		return resumenConciliacionId;
	}

	public void setResumenConciliacionId(Long resumenConciliacionId) {
		this.resumenConciliacionId = resumenConciliacionId;
	}

	public Long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(Long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public String getFormularioId() {
		return formularioId;
	}

	public void setFormularioId(String formularioId) {
		this.formularioId = formularioId;
	}

	public Long getSalarioId() {
		return salarioId;
	}

	public void setSalarioId(Long salarioId) {
		this.salarioId = salarioId;
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

	public BigDecimal getMontoTotalFacturas() {
		return montoTotalFacturas;
	}

	public void setMontoTotalFacturas(BigDecimal montoTotalFacturas) {
		this.montoTotalFacturas = montoTotalFacturas;
	}

	public String getEstadoProcesoPagoId() {
		return estadoProcesoPagoId;
	}

	public void setEstadoProcesoPagoId(String estadoProcesoPagoId) {
		this.estadoProcesoPagoId = estadoProcesoPagoId;
	}

	public BeneficiarioDto getBeneficiarioDto() {
		return beneficiarioDto;
	}

	public void setBeneficiarioDto(BeneficiarioDto beneficiarioDto) {
		this.beneficiarioDto = beneficiarioDto;
	}

	public String getTipoConciliacionId() {
		return tipoConciliacionId;
	}

	public void setTipoConciliacionId(String tipoConciliacionId) {
		this.tipoConciliacionId = tipoConciliacionId;
	}

	public Long getCantidadFacturas() {
		return cantidadFacturas;
	}

	public void setCantidadFacturas(Long cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
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

	public BigDecimal getMontoTotalPagado() {
		return montoTotalPagado;
	}

	public void setMontoTotalPagado(BigDecimal montoTotalPagado) {
		this.montoTotalPagado = montoTotalPagado;
	}

	public Long getPagoBonoId() {
		return pagoBonoId;
	}

	public void setPagoBonoId(Long pagoBonoId) {
		this.pagoBonoId = pagoBonoId;
	}

	public Long getIdBeneficiarioSigep() {
		return idBeneficiarioSigep;
	}

	public void setIdBeneficiarioSigep(Long idBeneficiarioSigep) {
		this.idBeneficiarioSigep = idBeneficiarioSigep;
	}


	@Override
	public String toString() {
		return "DetalleConciliacionDto [beneficiario=" + beneficiario + ", detalleConciliacionId="
				+ detalleConciliacionId + ", resumenConciliacionId=" + resumenConciliacionId + ", beneficiarioId="
				+ beneficiarioId + ", formularioId=" + formularioId + ", salarioId=" + salarioId + ", gestion="
				+ gestion + ", periodo=" + periodo + ", montoTotalFacturas=" + montoTotalFacturas
				+ ", idBeneficiarioSigep=" + idBeneficiarioSigep + ", estadoProcesoPagoId=" + estadoProcesoPagoId
				+ ", tipoConciliacionId=" + tipoConciliacionId + ", cantidadFacturas=" + cantidadFacturas
				+ ", montoTotalReintegros=" + montoTotalReintegros + ", montoTotalReparos=" + montoTotalReparos
				+ ", montoTotalPagar=" + montoTotalPagar + ", montoTotalPagado=" + montoTotalPagado + ", pagoBonoId="
				+ pagoBonoId + ", beneficiarioDto=" + beneficiarioDto + ", totalRegistros=" + totalRegistros + "]";
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Long getBonoSigepId() {
		return bonoSigepId;
	}

	public void setBonoSigepId(Long bonoSigepId) {
		this.bonoSigepId = bonoSigepId;
	}

}

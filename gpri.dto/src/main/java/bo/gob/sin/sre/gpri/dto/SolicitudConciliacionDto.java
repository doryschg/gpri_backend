package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SolicitudConciliacionDto implements Serializable {
	private String formulario;
	private Long codigoPersona;
	private Integer periodo;
	private Integer anio;
	private BigDecimal montoDeterminacionPago;
	private Long cantidadFacturas;
	private BigDecimal montoTotalFacturas;

	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public Long getCodigoPersona() {
		return codigoPersona;
	}

	public void setCodigoPersona(Long codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public BigDecimal getMontoDeterminacionPago() {
		return montoDeterminacionPago;
	}

	public void setMontoDeterminacionPago(BigDecimal montoDeterminacionPago) {
		this.montoDeterminacionPago = montoDeterminacionPago;
	}

	public Long getCantidadFacturas() {
		return cantidadFacturas;
	}

	public void setCantidadFacturas(Long cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
	}

	public BigDecimal getMontoTotalFacturas() {
		return montoTotalFacturas;
	}

	public void setMontoTotalFacturas(BigDecimal montoTotalFacturas) {
		this.montoTotalFacturas = montoTotalFacturas;
	}

}

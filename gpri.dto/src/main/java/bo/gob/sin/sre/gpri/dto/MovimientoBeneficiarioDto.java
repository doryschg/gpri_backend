package bo.gob.sin.sre.gpri.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoBeneficiarioDto extends AuditoriaDto {

	private Long movimientoBeneficiarioId;
	private Long detalleConciliacionId;
	private Long beneficiarioId;
	private String tipoMovimientoRivId;
	private String tipoTransaccionRivId;
	private LocalDateTime fechaTransaccion;
	private BigDecimal monto;
	private String estadoMovimientoId;
	private String glosa;
	private Integer gestionOrigen;
	private Integer periodoOrigen;
	private BeneficiarioDto beneficiario;
	private long codigoDependiente;
	private Long idBeneficiarioSigep;
	private String nuaCua;
	
	private Integer totalRegistros;

	public Long getMovimientoBeneficiarioId() {
		return movimientoBeneficiarioId;
	}

	public void setMovimientoBeneficiarioId(Long movimientoBeneficiarioId) {
		this.movimientoBeneficiarioId = movimientoBeneficiarioId;
	}

	public Long getDetalleConciliacionId() {
		return detalleConciliacionId;
	}

	public void setDetalleConciliacionId(Long detalleConciliacionId) {
		this.detalleConciliacionId = detalleConciliacionId;
	}

	public Long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(Long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public String getTipoMovimientoRivId() {
		return tipoMovimientoRivId;
	}

	public void setTipoMovimientoRivId(String tipoMovimientoRivId) {
		this.tipoMovimientoRivId = tipoMovimientoRivId;
	}

	public String getTipoTransaccionRivId() {
		return tipoTransaccionRivId;
	}

	public void setTipoTransaccionRivId(String tipoTransaccionRivId) {
		this.tipoTransaccionRivId = tipoTransaccionRivId;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getEstadoMovimientoId() {
		return estadoMovimientoId;
	}

	public void setEstadoMovimientoId(String estadoMovimientoId) {
		this.estadoMovimientoId = estadoMovimientoId;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public Integer getGestionOrigen() {
		return gestionOrigen;
	}

	public void setGestionOrigen(Integer gestionOrigen) {
		this.gestionOrigen = gestionOrigen;
	}

	public Integer getPeriodoOrigen() {
		return periodoOrigen;
	}

	public void setPeriodoOrigen(Integer periodoOrigen) {
		this.periodoOrigen = periodoOrigen;
	}

	public BeneficiarioDto getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(BeneficiarioDto beneficiario) {
		this.beneficiario = beneficiario;
	}

	public long getCodigoDependiente() {
		return codigoDependiente;
	}

	public void setCodigoDependiente(long codigoDependiente) {
		this.codigoDependiente = codigoDependiente;
	}

	public Long getIdBeneficiarioSigep() {
		return idBeneficiarioSigep;
	}

	public void setIdBeneficiarioSigep(Long idBeneficiarioSigep) {
		this.idBeneficiarioSigep = idBeneficiarioSigep;
	}

	public String getNuaCua() {
		return nuaCua;
	}

	public void setNuaCua(String nuaCua) {
		this.nuaCua = nuaCua;
	}

	@Override
	public String toString() {
		return "MovimientoBeneficiarioDto [movimientoBeneficiarioId=" + movimientoBeneficiarioId
				+ ", detalleConciliacionId=" + detalleConciliacionId + ", beneficiarioId=" + beneficiarioId
				+ ", tipoMovimientoRivId=" + tipoMovimientoRivId + ", tipoTransaccionRivId=" + tipoTransaccionRivId
				+ ", fechaTransaccion=" + fechaTransaccion + ", monto=" + monto + ", estadoMovimientoId="
				+ estadoMovimientoId + ", glosa=" + glosa + ", gestionOrigen=" + gestionOrigen + ", periodoOrigen="
				+ periodoOrigen + ", beneficiario=" + beneficiario + ", codigoDependiente=" + codigoDependiente
				+ ", idBeneficiarioSigep=" + idBeneficiarioSigep + ", nuaCua=" + nuaCua + "]";
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

}

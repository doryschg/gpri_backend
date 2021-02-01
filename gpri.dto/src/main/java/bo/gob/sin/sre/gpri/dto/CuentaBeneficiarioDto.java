package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class CuentaBeneficiarioDto implements Serializable {
	private long cuentaId;
	private long beneficiarioId;
	private String entidadFinancieraId;
	private String tipoCuentaIdVc;
	private String cuentaBanco;
	private String monedaId;
	private String distritoCuentaId;
	private String estadoCuentaId;
	private Date fechaDesde;
	private Date fechaHasta;

	private long usuarioRegistro;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacion;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	public CuentaBeneficiarioDto() {
		super();
	}

	public long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public String getEntidadFinancieraId() {
		return entidadFinancieraId;
	}

	public void setEntidadFinancieraId(String entidadFinancieraId) {
		this.entidadFinancieraId = entidadFinancieraId;
	}

	public String getTipoCuentaIdVc() {
		return tipoCuentaIdVc;
	}

	public void setTipoCuentaIdVc(String tipoCuentaIdVc) {
		this.tipoCuentaIdVc = tipoCuentaIdVc;
	}

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public String getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(String monedaId) {
		this.monedaId = monedaId;
	}

	public String getDistritoCuentaId() {
		return distritoCuentaId;
	}

	public void setDistritoCuentaId(String distritoCuentaId) {
		this.distritoCuentaId = distritoCuentaId;
	}

	public String getEstadoCuentaId() {
		return estadoCuentaId;
	}

	public void setEstadoCuentaId(String estadoCuentaId) {
		this.estadoCuentaId = estadoCuentaId;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public long getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(long usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public long getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(long usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}

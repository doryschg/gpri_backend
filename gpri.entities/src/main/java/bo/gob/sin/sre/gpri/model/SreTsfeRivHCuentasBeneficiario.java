package bo.gob.sin.sre.gpri.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tsfe_riv_h_cuentas_beneficiario", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivHCuentasBeneficiario {
	private long cuentaId;
	private long beneficiarioId;
	private String entidadFinancieraId;
	private String tipoCuentaId;
	private String cuentaBanco;
	private String monedaId;
	private String distritoCuentaId;
	private String estadoCuentaId;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;

	private long usuarioRegistro;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacionId;
	private String estadoId;

	@Id
	@Column(name = "seq_cuenta_nb")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getCuentaId() {
		return cuentaId;
	}

	@Column(name = "seq_beneficiario_nb")
	public long getBeneficiarioId() {
		return beneficiarioId;
	}

	@Column(name = "entidad_financiera_riv_id_vc")
	public String getEntidadFinancieraId() {
		return entidadFinancieraId;
	}

	@Column(name = "tipo_cuenta_bancaria_id_vc")
	public String getTipoCuentaId() {
		return tipoCuentaId;
	}

	@Column(name = "cuenta_banco_vc")
	public String getCuentaBanco() {
		return cuentaBanco;
	}

	@Column(name = "moneda_id_vc")
	public String getMonedaId() {
		return monedaId;
	}

	@Column(name = "distrito_cuenta_id_vc")
	public String getDistritoCuentaId() {
		return distritoCuentaId;
	}

	@Column(name = "estado_cuenta_bancaria_id_vc")
	public String getEstadoCuentaId() {
		return estadoCuentaId;
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
		return usuarioRegistro;
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
	public LocalDateTime getFechaUltimaModificacionId() {
		return fechaUltimaModificacionId;
	}

	@Column(name = "estado_id_vc")
	public String getEstadoId() {
		return estadoId;
	}

	public void setCuentaId(long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public void setBeneficiarioId(long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public void setEntidadFinancieraId(String entidadFinancieraId) {
		this.entidadFinancieraId = entidadFinancieraId;
	}

	public void setTipoCuentaId(String tipoCuentaId) {
		this.tipoCuentaId = tipoCuentaId;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public void setMonedaId(String monedaId) {
		this.monedaId = monedaId;
	}

	public void setDistritoCuentaId(String distritoCuentaId) {
		this.distritoCuentaId = distritoCuentaId;
	}

	public void setEstadoCuentaId(String estadoCuentaId) {
		this.estadoCuentaId = estadoCuentaId;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void setUsuarioRegistroId(long usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacion) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacion;
	}

	public void setFechaUltimaModificacionId(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacionId = fechaUltimaModificacion;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}

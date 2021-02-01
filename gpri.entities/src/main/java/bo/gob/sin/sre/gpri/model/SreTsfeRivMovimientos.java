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
@Table(name = "tsfe_riv_movimientos", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivMovimientos {

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

	private Long movimientoBeneficiarioRefId;

	private long usuarioRegistroId;
	private LocalDateTime fechaRegistro;
	private long usuarioUltimaModificacionId;
	private LocalDateTime fechaUltimaModificacion;
	private String estadoId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_movimiento_nb")
	public Long getMovimientoBeneficiarioId() {
		return movimientoBeneficiarioId;
	}

	public void setMovimientoBeneficiarioId(Long movimientoBeneficiarioId) {
		this.movimientoBeneficiarioId = movimientoBeneficiarioId;
	}

	@Column(name = "seq_detalle_conciliacion_nb")
	public Long getDetalleConciliacionId() {
		return detalleConciliacionId;
	}

	public void setDetalleConciliacionId(Long detalleConciliacionId) {
		this.detalleConciliacionId = detalleConciliacionId;
	}

	@Column(name = "seq_beneficiario_nb")
	public Long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(Long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	@Column(name = "tipo_movimiento_riv_id_vc")
	public String getTipoMovimientoRivId() {
		return tipoMovimientoRivId;
	}

	public void setTipoMovimientoRivId(String tipoMovimientoRivId) {
		this.tipoMovimientoRivId = tipoMovimientoRivId;
	}

	@Column(name = "tipo_transaccion_riv_id_vc")
	public String getTipoTransaccionRivId() {
		return tipoTransaccionRivId;
	}

	public void setTipoTransaccionRivId(String tipoTransaccionRivId) {
		this.tipoTransaccionRivId = tipoTransaccionRivId;
	}

	@Column(name = "fecha_transaccion_ts")
	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	@Column(name = "monto_nb")
	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Column(name = "estado_movimiento_id_vc")
	public String getEstadoMovimientoId() {
		return estadoMovimientoId;
	}

	public void setEstadoMovimientoId(String estadoMovimientoId) {
		this.estadoMovimientoId = estadoMovimientoId;
	}

	@Column(name = "glosa_vc")
	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	@Column(name = "gestion_origen_nb")
	public Integer getGestionOrigen() {
		return gestionOrigen;
	}

	public void setGestionOrigen(Integer gestionOrigen) {
		this.gestionOrigen = gestionOrigen;
	}

	@Column(name = "periodo_origen_nb")
	public Integer getPeriodoOrigen() {
		return periodoOrigen;
	}

	public void setPeriodoOrigen(Integer periodoOrigen) {
		this.periodoOrigen = periodoOrigen;
	}

	@Column(name = "seq_movimiento_ref_nb")
	public Long getMovimientoBeneficiarioRefId() {
		return movimientoBeneficiarioRefId;
	}

	public void setMovimientoBeneficiarioRefId(Long movimientoBeneficiarioRefId) {
		this.movimientoBeneficiarioRefId = movimientoBeneficiarioRefId;
	}
	@Column(name = "usuario_registro_nb")
	public long getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(long usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	@Column(name = "fecha_registro_ts")
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "usuario_ultima_modificacion_nb")
	public long getUsuarioUltimaModificacionId() {
		return usuarioUltimaModificacionId;
	}

	public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionId) {
		this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
	}

	@Column(name = "fecha_ultima_modificacion_ts")
	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	@Column(name = "estado_id_vc")
	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}

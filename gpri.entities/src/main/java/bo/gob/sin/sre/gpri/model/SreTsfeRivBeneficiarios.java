package bo.gob.sin.sre.gpri.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tsfe_riv_beneficiarios", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivBeneficiarios {
    private long beneficiarioId;
    private long personaId;
    private long codigoPersona;
    private String formatoNombreId;
    private String paisId;
    private Integer departamentoId;
    private Integer provinciaId;
    private Integer alcaldiaId;
    private String direccion;
    private String tipoBeneficiarioId;
    private String nuaCua;
    private BigDecimal totalSalario;
    private String entidadFinancieraId;
    private String tipoCuentaBancariaId;
    private String cuentaBanco;
    private String monedaId;
    private int distritoCuentaId;
    private String estadoCuentaBancariaId;
    private String pagoAutomaticoId;
    private String estadoBeneficiarioId;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Long idBeneficiarioSigep;
    private int gestionRegistro;
    private long usuarioRegistroId;
    private LocalDateTime fechaRegistro;
    private long usuarioUltimaModificacionId;
    private LocalDateTime fechaUltimaModificacion;
    private String estadoId;
    private Long numeroOrden;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_beneficiario_nb")
    public long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(long seqBeneficiarioNb) {
        this.beneficiarioId = seqBeneficiarioNb;
    }

    @Basic
    @Column(name = "persona_id_nb")
    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaIdNb) {
        this.personaId = personaIdNb;
    }

    @Basic
    @Column(name = "codigo_persona_nb")
    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long CodigoDependiente) {
        this.codigoPersona = CodigoDependiente;
    }


    @Basic
    @Column(name = "formato_nombre_id_vc")
    public String getFormatoNombreId() {
        return formatoNombreId;
    }

    public void setFormatoNombreId(String formatoNombreIdVc) {
        this.formatoNombreId = formatoNombreIdVc;
    }



    @Basic
    @Column(name = "pais_id_vc")
    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisIdVc) {
        this.paisId = paisIdVc;
    }

    @Basic
    @Column(name = "departamento_id_nb")
    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoIdNb) {
        this.departamentoId = departamentoIdNb;
    }

    @Basic
    @Column(name = "provincia_id_nb")
    public Integer getProvinciaId() {
        return provinciaId;
    }
    public void setProvinciaId(Integer alcaldiaIdNb) {
        this.provinciaId = alcaldiaIdNb;
    }

    @Basic
    @Column(name = "alcaldia_id_nb")
    public Integer getAlcaldiaId() {
        return alcaldiaId;
    }

    public void setAlcaldiaId(Integer alcaldiaIdNb) {
        this.alcaldiaId = alcaldiaIdNb;
    }

    @Basic
    @Column(name = "direccion_vc")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccionVc) {
        this.direccion = direccionVc;
    }

    @Basic
    @Column(name = "tipo_beneficiario_id_vc")
    public String getTipoBeneficiarioId() {
        return tipoBeneficiarioId;
    }

    public void setTipoBeneficiarioId(String tipoBeneficiarioIdVc) {
        this.tipoBeneficiarioId = tipoBeneficiarioIdVc;
    }

    @Basic
    @Column(name = "nua_cua_nb")
    public String getNuaCua() {
        return nuaCua;
    }

    public void setNuaCua(String nuaCuaNb) {
        this.nuaCua = nuaCuaNb;
    }

    @Basic
    @Column(name = "total_salario_nb")
    public BigDecimal getTotalSalario() {
        return totalSalario;
    }

    public void setTotalSalario(BigDecimal totalSalario) {
        this.totalSalario = totalSalario;
    }

    @Basic
    @Column(name = "entidad_financiera_riv_id_vc")
    public String getEntidadFinancieraId() {
        return entidadFinancieraId;
    }

    public void setEntidadFinancieraId(String entidadFinancieraIdVc) {
        this.entidadFinancieraId = entidadFinancieraIdVc;
    }

    @Basic
    @Column(name = "tipo_cuenta_bancaria_id_vc")
    public String getTipoCuentaBancariaId() {
        return tipoCuentaBancariaId;
    }

    public void setTipoCuentaBancariaId(String tipoCuentaBancariaIdVc) {
        this.tipoCuentaBancariaId = tipoCuentaBancariaIdVc;
    }

    @Basic
    @Column(name = "cuenta_banco_vc")
    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBancoVc) {
        this.cuentaBanco = cuentaBancoVc;
    }

    @Basic
    @Column(name = "moneda_id_vc")
    public String getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(String monedaIdVc) {
        this.monedaId = monedaIdVc;
    }

    @Basic
    @Column(name = "distrito_cuenta_id_nb")
    public int getDistritoCuentaId() {
        return distritoCuentaId;
    }

    public void setDistritoCuentaId(int distritoCuentaIdNb) {
        this.distritoCuentaId = distritoCuentaIdNb;
    }

    @Basic
    @Column(name = "estado_cuenta_bancaria_id_vc")
    public String getEstadoCuentaBancariaId() {
        return estadoCuentaBancariaId;
    }

    public void setEstadoCuentaBancariaId(String estadoCuentaBancariaIdVc) {
        this.estadoCuentaBancariaId = estadoCuentaBancariaIdVc;
    }

    @Basic
    @Column(name = "pago_automatico_id_vc")
    public String getPagoAutomaticoId() {
        return pagoAutomaticoId;
    }

    public void setPagoAutomaticoId(String pagoAutomaticoVc) {
        this.pagoAutomaticoId = pagoAutomaticoVc;
    }

    @Basic
    @Column(name = "estado_beneficiario_id_vc")
    public String getEstadoBeneficiarioId() {
        return estadoBeneficiarioId;
    }

    public void setEstadoBeneficiarioId(String estadoBeneficiarioIdVc) {
        this.estadoBeneficiarioId = estadoBeneficiarioIdVc;
    }

    @Basic
    @Column(name = "fecha_desde_dt")
    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesdeDt) {
        this.fechaDesde = fechaDesdeDt;
    }

    @Basic
    @Column(name = "fecha_hasta_dt")
    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHastaDt) {
        this.fechaHasta = fechaHastaDt;
    }

    @Basic
    @Column(name = "id_beneficiario_sigep_nb")
    public Long getIdBeneficiarioSigep() {
        return idBeneficiarioSigep;
    }

    public void setIdBeneficiarioSigep(Long idBeneficiarioSigepNb) {
        this.idBeneficiarioSigep = idBeneficiarioSigepNb;
    }

    @Basic
    @Column(name = "gestion_registro_nb")
    public int getGestionRegistro() {
        return gestionRegistro;
    }

    public void setGestionRegistro(int gestionRegistro) {
        this.gestionRegistro = gestionRegistro;
    }

    @Basic
    @Column(name = "usuario_registro_nb")
    public long getUsuarioRegistroId() {
        return usuarioRegistroId;
    }

    public void setUsuarioRegistroId(long usuarioRegistroNb) {
        this.usuarioRegistroId = usuarioRegistroNb;
    }

    @Basic
    @Column(name = "fecha_registro_ts")
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistroTs) {
        this.fechaRegistro = fechaRegistroTs;
    }

    @Basic
    @Column(name = "usuario_ultima_modificacion_nb")
    public long getUsuarioUltimaModificacionId() {
        return usuarioUltimaModificacionId;
    }

    public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionNb) {
        this.usuarioUltimaModificacionId = usuarioUltimaModificacionNb;
    }

    @Basic
    @Column(name = "fecha_ultima_modificacion_ts")
    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacionTs) {
        this.fechaUltimaModificacion = fechaUltimaModificacionTs;
    }

    @Basic
    @Column(name = "estado_id_vc")
    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoIdVc) {
        this.estadoId = estadoIdVc;
    }

    @Basic
    @Column(name = "nro_orden_nb")
    public Long getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
}

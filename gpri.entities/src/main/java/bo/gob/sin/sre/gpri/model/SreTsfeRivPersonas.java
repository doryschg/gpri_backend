package bo.gob.sin.sre.gpri.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tsfe_riv_personas", schema = "sfe_facturacion", catalog = "dsin")
public class SreTsfeRivPersonas {
    private long perBeneficiarioId;
    private long personaId;
    private int tipoDocumentoIdentidadId;
    private Integer lugarExpedicionId;
    private Integer tipoPersonaId;
    private String correoElectronico;
    private String numeroDocumento;
    private String codigoComplementario;
    private String celular;
    private String telefonoReferencia1;
    private String telefonoReferencia2;
    private int paisOrigenId;
    private int estadoCivilId;
    private int generoId;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String apellidoCasada;
    private LocalDateTime fechaNacimiento;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String modificadoId;
    private String origenId;
    private String estadoPerBeneficiarioId;
    private long usuarioRegistroId;
    private LocalDateTime fechaRegistro;
    private long usuarioUltimaModificacionId;
    private LocalDateTime fechaUltimaModificacion;
    private String estadoId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_per_beneficiario_id")
    public long getPerBeneficiarioId() {
        return perBeneficiarioId;
    }

    public void setPerBeneficiarioId(long seqPerBeneficiarioId) {
        this.perBeneficiarioId = seqPerBeneficiarioId;
    }

    @Basic
    @Column(name = "persona_id")
    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }

    @Basic
    @Column(name = "tipo_documento_identidad_id")
    public int getTipoDocumentoIdentidadId() {
        return tipoDocumentoIdentidadId;
    }

    public void setTipoDocumentoIdentidadId(int tipoDocumentoIdentidadId) {
        this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
    }

    @Basic
    @Column(name = "lugar_expedicion_id")
    public Integer getLugarExpedicionId() {
        return lugarExpedicionId;
    }

    public void setLugarExpedicionId(Integer lugarExpedicionId) {
        this.lugarExpedicionId = lugarExpedicionId;
    }

    @Basic
    @Column(name = "tipo_persona_id")
    public Integer getTipoPersonaId() {
        return tipoPersonaId;
    }

    public void setTipoPersonaId(Integer tipoPersonaId) {
        this.tipoPersonaId = tipoPersonaId;
    }

    @Basic
    @Column(name = "correo_electronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Basic
    @Column(name = "numero_documento")
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Basic
    @Column(name = "codigo_complementario")
    public String getCodigoComplementario() {
        return codigoComplementario;
    }

    public void setCodigoComplementario(String codigoComplementario) {
        this.codigoComplementario = codigoComplementario;
    }

    @Basic
    @Column(name = "celular")
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Basic
    @Column(name = "telefono_referencia_1")
    public String getTelefonoReferencia1() {
        return telefonoReferencia1;
    }

    public void setTelefonoReferencia1(String telefonoReferencia1) {
        this.telefonoReferencia1 = telefonoReferencia1;
    }

    @Basic
    @Column(name = "telefono_referencia_2")
    public String getTelefonoReferencia2() {
        return telefonoReferencia2;
    }

    public void setTelefonoReferencia2(String telefonoReferencia2) {
        this.telefonoReferencia2 = telefonoReferencia2;
    }

    @Basic
    @Column(name = "pais_origen_id")
    public int getPaisOrigenId() {
        return paisOrigenId;
    }

    public void setPaisOrigenId(int paisOrigenId) {
        this.paisOrigenId = paisOrigenId;
    }

    @Basic
    @Column(name = "estado_civil_id")
    public int getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(int estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    @Basic
    @Column(name = "genero_id")
    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    @Basic
    @Column(name = "nombres")
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Basic
    @Column(name = "segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Basic
    @Column(name = "apellido_casada")
    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
    @Column(name = "modificado_id_vc")
    public String getModificadoId() {
        return modificadoId;
    }

    public void setModificadoId(String modificadoId) {
        this.modificadoId = modificadoId;
    }
    @Basic
    @Column(name = "origen_riv_id_vc")
    public String getOrigenId() {
        return origenId;
    }

    public void setOrigenId(String origenId) {
        this.origenId = origenId;
    }

    @Basic
    @Column(name = "estado_per_beneficiario_id_vc")
    public String getEstadoPerBeneficiarioId() {
        return estadoPerBeneficiarioId;
    }

    public void setEstadoPerBeneficiarioId(String estadoPerBeneficiarioIdVc) {
        this.estadoPerBeneficiarioId = estadoPerBeneficiarioIdVc;
    }



    @Basic
    @Column(name = "usuario_registro_id")
    public long getUsuarioRegistroId() {
        return usuarioRegistroId;
    }

    public void setUsuarioRegistroId(long usuarioRegistroNb) {
        this.usuarioRegistroId = usuarioRegistroNb;
    }

    @Basic
    @Column(name = "fecha_registro")
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistroTs) {
        this.fechaRegistro = fechaRegistroTs;
    }

    @Basic
    @Column(name = "usuario_ultima_modificacion_id")
    public long getUsuarioUltimaModificacionId() {
        return usuarioUltimaModificacionId;
    }

    public void setUsuarioUltimaModificacionId(long usuarioUltimaModificacionNb) {
        this.usuarioUltimaModificacionId = usuarioUltimaModificacionNb;
    }

    @Basic
    @Column(name = "fecha_ultima_modificacion")
    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacionTs) {
        this.fechaUltimaModificacion = fechaUltimaModificacionTs;
    }

    @Basic
    @Column(name = "estado_id")
    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }


}

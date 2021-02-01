package bo.gob.sin.sre.gpri.dto;

import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonaDto extends Auditoria2Dto implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private Long personaId;

    private Long tramiteId;

    private Integer dependenciaRegistroId;

    private String dependenciaRegistroDescripcion;

    private Integer tipoDocumentoIdentidadId;

    private String tipoDocumentoIdentidadDescripcion;

    private Integer lugarExpedicionId;

    private String lugarExpedicionDescripcion;

    private Integer tipoPersonaId;

    private String correoElectronico;

    private String numeroDocumento;

    private String codigoComplementario;

    private String celular;

    private String telefonoReferencia1;

    private String telefonoReferencia2;

    private String fax;

    private Boolean estadoCelular;

    private boolean ok;
    private List<StrMensajeAplicacionDto> mensajes = new ArrayList();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public PersonaDto() {
    }

    public PersonaDto(Long personaId, Long tramiteId, Integer dependenciaRegistroId, String dependenciaRegistroDescripcion, Integer tipoDocumentoIdentidadId, String tipoDocumentoIdentidadDescripcion, Integer lugarExpedicionId, String lugarExpedicionDescripcion, Integer tipoPersonaId, String correoElectronico, String numeroDocumento, String codigoComplementario, String celular, String telefonoReferencia1, String telefonoReferencia2, String fax, Boolean estadoCelular) {
        this.personaId = personaId;
        this.tramiteId = tramiteId;
        this.dependenciaRegistroId = dependenciaRegistroId;
        this.dependenciaRegistroDescripcion = dependenciaRegistroDescripcion;
        this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
        this.tipoDocumentoIdentidadDescripcion = tipoDocumentoIdentidadDescripcion;
        this.lugarExpedicionId = lugarExpedicionId;
        this.lugarExpedicionDescripcion = lugarExpedicionDescripcion;
        this.tipoPersonaId = tipoPersonaId;
        this.correoElectronico = correoElectronico;
        this.numeroDocumento = numeroDocumento;
        this.codigoComplementario = codigoComplementario;
        this.celular = celular;
        this.telefonoReferencia1 = telefonoReferencia1;
        this.telefonoReferencia2 = telefonoReferencia2;
        this.fax = fax;
        this.estadoCelular = estadoCelular;
    }

    public Long getPersonaId() {
        return this.personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getTramiteId() {
        return this.tramiteId;
    }

    public void setTramiteId(Long tramiteId) {
        this.tramiteId = tramiteId;
    }

    public Integer getDependenciaRegistroId() {
        return this.dependenciaRegistroId;
    }

    public void setDependenciaRegistroId(Integer dependenciaRegistroId) {
        this.dependenciaRegistroId = dependenciaRegistroId;
    }

    public Integer getTipoDocumentoIdentidadId() {
        return this.tipoDocumentoIdentidadId;
    }

    public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
        this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
    }

    public Integer getLugarExpedicionId() {
        return this.lugarExpedicionId;
    }

    public void setLugarExpedicionId(Integer lugarExpedicionId) {
        this.lugarExpedicionId = lugarExpedicionId;
    }

    public Integer getTipoPersonaId() {
        return this.tipoPersonaId;
    }

    public void setTipoPersonaId(Integer tipoPersonaId) {
        this.tipoPersonaId = tipoPersonaId;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCodigoComplementario() {
        return this.codigoComplementario;
    }

    public void setCodigoComplementario(String codigoComplementario) {
        this.codigoComplementario = codigoComplementario;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefonoReferencia1() {
        return this.telefonoReferencia1;
    }

    public void setTelefonoReferencia1(String telefonoReferencia1) {
        this.telefonoReferencia1 = telefonoReferencia1;
    }

    public String getTelefonoReferencia2() {
        return this.telefonoReferencia2;
    }

    public void setTelefonoReferencia2(String telefonoReferencia2) {
        this.telefonoReferencia2 = telefonoReferencia2;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Boolean getEstadoCelular() {
        return this.estadoCelular;
    }

    public void setEstadoCelular(Boolean estadoCelular) {
        this.estadoCelular = estadoCelular;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoDocumentoIdentidadDescripcion() {
        return this.tipoDocumentoIdentidadDescripcion;
    }

    public void setTipoDocumentoIdentidadDescripcion(String tipoDocumentoIdentidadDescripcion) {
        this.tipoDocumentoIdentidadDescripcion = tipoDocumentoIdentidadDescripcion;
    }

    public String getLugarExpedicionDescripcion() {
        return this.lugarExpedicionDescripcion;
    }

    public void setLugarExpedicionDescripcion(String lugarExpedicionDescripcion) {
        this.lugarExpedicionDescripcion = lugarExpedicionDescripcion;
    }

    public String getDependenciaRegistroDescripcion() {
        return this.dependenciaRegistroDescripcion;
    }

    public void setDependenciaRegistroDescripcion(String dependenciaRegistroDescripcion) {
        this.dependenciaRegistroDescripcion = dependenciaRegistroDescripcion;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<StrMensajeAplicacionDto> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<StrMensajeAplicacionDto> mensajes) {
        this.mensajes = mensajes;
    }
}
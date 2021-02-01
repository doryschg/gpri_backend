package bo.gob.sin.sre.gpri.dto;




import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaNaturalDto extends Auditoria2Dto implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;


    private Long personaId;

    private Long tramiteId;

    private Integer dependenciaRegistroId;

    private String dependenciaRegistroDescripcion;

    private Integer nacionalidadId;

    private String nacionalidadDescripcion;

    private Integer paisOrigenId;

    private String paisOrigenDescripcion;

    private Integer estadoCivilId;

    private String estadoCivilDescripcion;

    private Integer generoId;

    private String generoDescripcion;

    private String informacionReferencial;

    private String nombres;

    private String primerApellido;

    private String segundoApellido;

    private String apellidoCasada;

    private String correoPersonal;

    private Date fechaNacimiento;

    private Boolean estadoCorreoPersonal;

    private Boolean estadoSegip;

    private PersonaDto persona;

    private Integer profesionOcupacionId;

    private Boolean nacionalizado;

    private List<ContactosPersonasDto> contactos;

    private boolean ok;
    private List<StrMensajeAplicacionDto> mensajes = new ArrayList();

    public PersonaNaturalDto() {}

    public PersonaNaturalDto(Long personaId, Long tramiteId, Integer dependenciaRegistroId, String dependenciaRegistroDescripcion, Integer nacionalidadId, String nacionalidadDescripcion, Integer paisOrigenId, String paisOrigenDescripcion, Integer estadoCivilId, String estadoCivilDescripcion, Integer generoId, String generoDescripcion, String informacionReferencial, String nombres, String primerApellido, String segundoApellido, String apellidoCasada, String correoPersonal, Date fechaNacimiento, Boolean estadoCorreoPersonal, Boolean estadoSegip, PersonaDto persona, Integer profesionOcupacionId, Boolean nacionalizado) {
        this.personaId = personaId;
        this.tramiteId = tramiteId;
        this.dependenciaRegistroId = dependenciaRegistroId;
        this.dependenciaRegistroDescripcion = dependenciaRegistroDescripcion;
        this.nacionalidadId = nacionalidadId;
        this.nacionalidadDescripcion = nacionalidadDescripcion;
        this.paisOrigenId = paisOrigenId;
        this.paisOrigenDescripcion = paisOrigenDescripcion;
        this.estadoCivilId = estadoCivilId;
        this.estadoCivilDescripcion = estadoCivilDescripcion;
        this.generoId = generoId;
        this.generoDescripcion = generoDescripcion;
        this.informacionReferencial = informacionReferencial;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.apellidoCasada = apellidoCasada;
        this.correoPersonal = correoPersonal;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCorreoPersonal = estadoCorreoPersonal;
        this.estadoSegip = estadoSegip;
        this.persona = persona;
        this.profesionOcupacionId = profesionOcupacionId;
        this.nacionalizado = nacionalizado;
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

    public String getDependenciaRegistroDescripcion() {
        return this.dependenciaRegistroDescripcion;
    }

    public void setDependenciaRegistroDescripcion(String dependenciaRegistroDescripcion) {
        this.dependenciaRegistroDescripcion = dependenciaRegistroDescripcion;
    }

    public Integer getNacionalidadId() {
        return this.nacionalidadId;
    }

    public void setNacionalidadId(Integer nacionalidadId) {
        this.nacionalidadId = nacionalidadId;
    }

    public String getNacionalidadDescripcion() {
        return this.nacionalidadDescripcion;
    }

    public void setNacionalidadDescripcion(String nacionalidadDescripcion) {
        this.nacionalidadDescripcion = nacionalidadDescripcion;
    }

    public Integer getPaisOrigenId() {
        return this.paisOrigenId;
    }

    public void setPaisOrigenId(Integer paisOrigenId) {
        this.paisOrigenId = paisOrigenId;
    }

    public String getPaisOrigenDescripcion() {
        return this.paisOrigenDescripcion;
    }

    public void setPaisOrigenDescripcion(String paisOrigenDescripcion) {
        this.paisOrigenDescripcion = paisOrigenDescripcion;
    }

    public Integer getEstadoCivilId() {
        return this.estadoCivilId;
    }

    public void setEstadoCivilId(Integer estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public String getEstadoCivilDescripcion() {
        return this.estadoCivilDescripcion;
    }

    public void setEstadoCivilDescripcion(String estadoCivilDescripcion) {
        this.estadoCivilDescripcion = estadoCivilDescripcion;
    }

    public Integer getGeneroId() {
        return this.generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }

    public String getGeneroDescripcion() {
        return this.generoDescripcion;
    }

    public void setGeneroDescripcion(String generoDescripcion) {
        this.generoDescripcion = generoDescripcion;
    }

    public String getInformacionReferencial() {
        return this.informacionReferencial;
    }

    public void setInformacionReferencial(String informacionReferencial) {
        this.informacionReferencial = informacionReferencial;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return this.primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return this.segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return this.apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getCorreoPersonal() {
        return this.correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getEstadoCorreoPersonal() {
        return this.estadoCorreoPersonal;
    }

    public void setEstadoCorreoPersonal(Boolean estadoCorreoPersonal) {
        this.estadoCorreoPersonal = estadoCorreoPersonal;
    }

    public Boolean getEstadoSegip() {
        return this.estadoSegip;
    }

    public void setEstadoSegip(Boolean estadoSegip) {
        this.estadoSegip = estadoSegip;
    }

    public PersonaDto getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

    public Integer getProfesionOcupacionId() {
        return this.profesionOcupacionId;
    }

    public void setProfesionOcupacionId(Integer profesionOcupacionId) {
        this.profesionOcupacionId = profesionOcupacionId;
    }

    public Boolean getNacionalizado() {
        return this.nacionalizado;
    }

    public void setNacionalizado(Boolean nacionalizado) {
        this.nacionalizado = nacionalizado;
    }

    public PersonaNaturalDto convertToPerBeneficiario(PersonaBeneficiarioDto pPersonaBeneficiario)
    {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        PersonaNaturalDto vPersonaNatural=new PersonaNaturalDto();
        PersonaDto vPersona=new PersonaDto();
        vPersonaNatural = new PersonaNaturalDto();
        vPersonaNatural.setPersonaId(pPersonaBeneficiario.getPersonaId());
        vPersonaNatural.setTramiteId(null);
        vPersonaNatural.setDependenciaRegistroId(null);
        vPersonaNatural.setDependenciaRegistroDescripcion("");
        vPersonaNatural.setNacionalidadId(340);
        vPersonaNatural.setNacionalidadDescripcion("");
        vPersonaNatural.setPaisOrigenId(pPersonaBeneficiario.getPaisOrigenId());
        vPersonaNatural.setPaisOrigenDescripcion(pPersonaBeneficiario.getPaisOrigenDescripcion());
        vPersonaNatural.setEstadoCivilId(pPersonaBeneficiario.getEstadoCivilId());
        vPersonaNatural.setEstadoCivilDescripcion(pPersonaBeneficiario.getEstadoCivilDescripcion());
        vPersonaNatural.setGeneroId(pPersonaBeneficiario.getGeneroId());
        vPersonaNatural.setGeneroDescripcion(pPersonaBeneficiario.getGeneroDescripcion());
        vPersonaNatural.setInformacionReferencial("");
        vPersonaNatural.setNombres(pPersonaBeneficiario.getNombres());
        vPersonaNatural.setPrimerApellido(pPersonaBeneficiario.getPrimerApellido());
        vPersonaNatural.setSegundoApellido(pPersonaBeneficiario.getSegundoApellido());
        vPersonaNatural.setApellidoCasada(pPersonaBeneficiario.getApellidoCasada());
        vPersonaNatural.setCorreoPersonal(null);
        vPersonaNatural.setFechaNacimiento(Date.from(pPersonaBeneficiario.getFechaNacimiento().atZone(ZoneId.systemDefault()).toInstant()));
        vPersonaNatural.setEstadoCorreoPersonal(null);
        vPersonaNatural.setEstadoSegip(null);
        vPersonaNatural.setProfesionOcupacionId(3350);
        vPersonaNatural.setNacionalizado(null);


        vPersona=new PersonaDto();
        vPersona.setPersonaId(pPersonaBeneficiario.getPersonaId());
        vPersona.setTramiteId(null);
        vPersona.setDependenciaRegistroId(null);
        vPersona.setDependenciaRegistroDescripcion(null);
        vPersona.setTipoDocumentoIdentidadId(pPersonaBeneficiario.getTipoDocumentoIdentidadId());
        vPersona.setTipoDocumentoIdentidadDescripcion(pPersonaBeneficiario.getTipoDocumentoIdentidadDescripcion());
        vPersona.setLugarExpedicionId(pPersonaBeneficiario.getLugarExpedicionId());
        vPersona.setLugarExpedicionDescripcion(pPersonaBeneficiario.getLugarExpedicionDescripcion());
        vPersona.setTipoPersonaId(584);
        vPersona.setCorreoElectronico(pPersonaBeneficiario.getCorreoElectronico());
        vPersona.setNumeroDocumento(pPersonaBeneficiario.getNumeroDocumento());
        vPersona.setCodigoComplementario(pPersonaBeneficiario.getCodigoComplementario());
        vPersona.setCelular(pPersonaBeneficiario.getCelular());
        vPersona.setTelefonoReferencia1(pPersonaBeneficiario.getTelefonoReferencia1());
        vPersona.setTelefonoReferencia2(pPersonaBeneficiario.getTelefonoReferencia2());
        vPersona.setFax(null);
        vPersona.setEstadoCelular(false);
        vPersonaNatural.setPersona(vPersona);

        return vPersonaNatural;
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

    public List<ContactosPersonasDto> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactosPersonasDto> contactos) {
        this.contactos = contactos;
    }
}
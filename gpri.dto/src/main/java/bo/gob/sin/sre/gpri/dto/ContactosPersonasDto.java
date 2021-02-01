package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.util.Date;

public class ContactosPersonasDto extends Auditoria2Dto implements Serializable{
	private static final long serialVersionUID = 1L;	
	
	private Long contactoPersonaId;	
	private Long tramiteId;
	private Long personaId;
	private Integer tipoContactoId;
	private String descripcionContacto;
	private String datoContacto;	
	private Integer estadoDatoContactoId;
	private boolean contactoValidado;
	private Date fechaDesdeVigencia;
	private Date fechaHastaVigencia;
	
	private String validatePlaceholder;
	
		
	public ContactosPersonasDto() {
		super();
	}

	public Long getContactoPersonaId() {
		return contactoPersonaId;
	}

	public void setContactoPersonaId(Long contactoPersonaId) {
		this.contactoPersonaId = contactoPersonaId;
	}

	public Long getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(Long tramiteId) {
		this.tramiteId = tramiteId;
	}

	public Long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}

	public Integer getTipoContactoId() {
		return tipoContactoId;
	}

	public void setTipoContactoId(Integer tipoContactoId) {
		this.tipoContactoId = tipoContactoId;
	}

	public String getDescripcionContacto() {
		return descripcionContacto;
	}

	public void setDescripcionContacto(String descripcionContacto) {
		this.descripcionContacto = descripcionContacto;
	}

	public String getDatoContacto() {
		return datoContacto;
	}

	public void setDatoContacto(String datoContacto) {
		this.datoContacto = datoContacto;
	}

	public Integer getEstadoDatoContactoId() {
		return estadoDatoContactoId;
	}

	public void setEstadoDatoContactoId(Integer estadoDatoContactoId) {
		this.estadoDatoContactoId = estadoDatoContactoId;
	}

	public boolean isContactoValidado() {
		return contactoValidado;
	}

	public void setContactoValidado(boolean contactoValidado) {
		this.contactoValidado = contactoValidado;
	}

	public Date getFechaDesdeVigencia() {
		return fechaDesdeVigencia;
	}

	public void setFechaDesdeVigencia(Date fechaDesdeVigencia) {
		this.fechaDesdeVigencia = fechaDesdeVigencia;
	}

	public Date getFechaHastaVigencia() {
		return fechaHastaVigencia;
	}

	public void setFechaHastaVigencia(Date fechaHastaVigencia) {
		this.fechaHastaVigencia = fechaHastaVigencia;
	}

	public String getValidatePlaceholder() {
		return validatePlaceholder;
	}

	public void setValidatePlaceholder(String validatePlaceholder) {
		this.validatePlaceholder = validatePlaceholder;
	}


		
}

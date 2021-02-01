package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AseguradoAPSDto implements Serializable {

	private Long beneficiarioId;
	private Long administradoraId;
	private Long tipoRegimenId;
	private String nuaCua;
	private String matricula;
	private Integer tipoDocumentoIdentidadId;
	private Long numeroDocumentoIdentificacion;
	private String complemento;
	private String lugarExpedicion;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String apellidoCasada;
	private String nombres;
	private Short estadoCivilId;
	private String email;
	private Integer telefono;
	private Integer celular;
	private String direccion;
	private Integer tipoDepartamento;
	private String ciudad;
	private Short localidadId;
	private LocalDate fechaNacimiento;
	private BigDecimal totalSalarioPromedio;
	private BigDecimal primerSalario;
	private Integer gestionPrimerSalario;
	private Integer periodoPrimerSalario;
	private BigDecimal segundoSalario;
	private Integer gestionSegundoSalario;
	private Integer periodoSegundoSalario;
	private BigDecimal tercerSalario;
	private Integer gestionTercerSalario;
	private Integer periodoTercerSalario;
	boolean cumpleRequisito;

	public Long getBeneficiarioId() {
		return beneficiarioId;
	}

	public void setBeneficiarioId(Long beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}

	public Long getAdministradoraId() {
		return administradoraId;
	}

	public void setAdministradoraId(Long administradoraId) {
		this.administradoraId = administradoraId;
	}

	public Long getTipoRegimenId() {
		return tipoRegimenId;
	}

	public void setTipoRegimenId(Long tipoRegimenId) {
		this.tipoRegimenId = tipoRegimenId;
	}

	public String getNuaCua() {
		return nuaCua;
	}

	public void setNuaCua(String nuaCua) {
		this.nuaCua = nuaCua;
	}

	public Integer getTipoDocumentoIdentidadId() {
		return tipoDocumentoIdentidadId;
	}

	public void setTipoDocumentoIdentidadId(Integer tipoDocumentoIdentidadId) {
		this.tipoDocumentoIdentidadId = tipoDocumentoIdentidadId;
	}

	public Long getNumeroDocumentoIdentificacion() {
		return numeroDocumentoIdentificacion;
	}

	public void setNumeroDocumentoIdentificacion(Long numeroDocumentoIdentificacion) {
		this.numeroDocumentoIdentificacion = numeroDocumentoIdentificacion;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoCasada() {
		return apellidoCasada;
	}

	public void setApellidoCasada(String apellidoCasada) {
		this.apellidoCasada = apellidoCasada;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Short getEstadoCivilId() {
		return estadoCivilId;
	}

	public void setEstadoCivilId(Short estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTipoDepartamento() {
		return tipoDepartamento;
	}

	public void setTipoDepartamento(Integer tipoDepartamento) {
		this.tipoDepartamento = tipoDepartamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Short getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(Short localidadId) {
		this.localidadId = localidadId;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public BigDecimal getTotalSalarioPromedio() {
		return totalSalarioPromedio;
	}

	public void setTotalSalarioPromedio(BigDecimal totalSalarioPromedio) {
		this.totalSalarioPromedio = totalSalarioPromedio;
	}

	public boolean isCumpleRequisito() {
		return cumpleRequisito;
	}

	public void setCumpleRequisito(boolean cumpleRequisito) {
		this.cumpleRequisito = cumpleRequisito;
	}

	public BigDecimal getPrimerSalario() {
		return primerSalario;
	}

	public void setPrimerSalario(BigDecimal primerSalario) {
		this.primerSalario = primerSalario;
	}

	public BigDecimal getSegundoSalario() {
		return segundoSalario;
	}

	public void setSegundoSalario(BigDecimal segundoSalario) {
		this.segundoSalario = segundoSalario;
	}

	public BigDecimal getTercerSalario() {
		return tercerSalario;
	}

	public void setTercerSalario(BigDecimal tercerSalario) {
		this.tercerSalario = tercerSalario;
	}

	public Integer getGestionPrimerSalario() {
		return gestionPrimerSalario;
	}

	public void setGestionPrimerSalario(Integer gestionPrimerSalario) {
		this.gestionPrimerSalario = gestionPrimerSalario;
	}

	public Integer getPeriodoPrimerSalario() {
		return periodoPrimerSalario;
	}

	public void setPeriodoPrimerSalario(Integer periodoPrimerSalario) {
		this.periodoPrimerSalario = periodoPrimerSalario;
	}

	public Integer getGestionSegundoSalario() {
		return gestionSegundoSalario;
	}

	public void setGestionSegundoSalario(Integer gestionSegundoSalario) {
		this.gestionSegundoSalario = gestionSegundoSalario;
	}

	public Integer getPeriodoSegundoSalario() {
		return periodoSegundoSalario;
	}

	public void setPeriodoSegundoSalario(Integer periodoSegundoSalario) {
		this.periodoSegundoSalario = periodoSegundoSalario;
	}

	public Integer getGestionTercerSalario() {
		return gestionTercerSalario;
	}

	public void setGestionTercerSalario(Integer gestionTercerSalario) {
		this.gestionTercerSalario = gestionTercerSalario;
	}

	public Integer getPeriodoTercerSalario() {
		return periodoTercerSalario;
	}

	public void setPeriodoTercerSalario(Integer periodoTercerSalario) {
		this.periodoTercerSalario = periodoTercerSalario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}

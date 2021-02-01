package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BeneficiarioPersonaRivDto extends AuditoriaDto implements Serializable
{
    private long beneficiarioId;
    private long personaId;
    private long codigoPersona;
    private String formatoNombreId;
    private String paisId;
    private Integer departamentoId;
    private String departamentoDescripcion;
    private String localidadDescripcion;
    private Integer provinciaId;
    private Integer alcaldiaId;
    private String direccion;
    private String tipoBeneficiarioId;
    private String nuaCua;
    private BigDecimal totalSalario;
    private String entidadFinancieraId;
    private String entidadFinancieraDescripcion;
    private String tipoCuentaBancariaId;
    private String tipoCuentaDescripcion;
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
    private BigDecimal primerSalario;
    private Integer gestionPrimerSalario;
    private Integer periodoPrimerSalario;
    private BigDecimal segundoSalario;
    private Integer gestionSegundoSalario;
    private Integer periodoSegundoSalario;
    private BigDecimal tercerSalario;
    private Integer gestionTercerSalario;
    private Integer periodoTercerSalario;
    private Long numeroOrden;
    private PersonaBeneficiarioDto personaBeneficiario;
    private boolean tieneSolicitudCambioModalidadAutomatica;

    public BeneficiarioPersonaRivDto() {
        super();
    }

    public long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }

    public String getFormatoNombreId() {
        return formatoNombreId;
    }

    public void setFormatoNombreId(String formatoNombreId) {
        this.formatoNombreId = formatoNombreId;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Integer getAlcaldiaId() {
        return alcaldiaId;
    }

    public void setAlcaldiaId(Integer alcaldiaId) {
        this.alcaldiaId = alcaldiaId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoBeneficiarioId() {
        return tipoBeneficiarioId;
    }

    public void setTipoBeneficiarioId(String tipoBeneficiarioId) {
        this.tipoBeneficiarioId = tipoBeneficiarioId;
    }

    public String getNuaCua() {
        return nuaCua;
    }

    public void setNuaCua(String nuaCua) {
        this.nuaCua = nuaCua;
    }

    public BigDecimal getTotalSalario() {
        return totalSalario;
    }

    public void setTotalSalario(BigDecimal totalSalario) {
        this.totalSalario = totalSalario;
    }

    public String getEntidadFinancieraId() {
        return entidadFinancieraId;
    }

    public void setEntidadFinancieraId(String entidadFinancieraId) {
        this.entidadFinancieraId = entidadFinancieraId;
    }

    public String getTipoCuentaBancariaId() {
        return tipoCuentaBancariaId;
    }

    public void setTipoCuentaBancariaId(String tipoCuentaBancariaId) {
        this.tipoCuentaBancariaId = tipoCuentaBancariaId;
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

    public int getDistritoCuentaId() {
        return distritoCuentaId;
    }

    public void setDistritoCuentaId(int distritoCuentaId) {
        this.distritoCuentaId = distritoCuentaId;
    }

    public String getEstadoCuentaBancariaId() {
        return estadoCuentaBancariaId;
    }

    public void setEstadoCuentaBancariaId(String estadoCuentaBancariaId) {
        this.estadoCuentaBancariaId = estadoCuentaBancariaId;
    }

    public String getPagoAutomaticoId() {
        return pagoAutomaticoId;
    }

    public void setPagoAutomaticoId(String pagoAutomaticoId) {
        this.pagoAutomaticoId = pagoAutomaticoId;
    }

    public String getEstadoBeneficiarioId() {
        return estadoBeneficiarioId;
    }

    public void setEstadoBeneficiarioId(String estadoBeneficiarioId) {
        this.estadoBeneficiarioId = estadoBeneficiarioId;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Long getIdBeneficiarioSigep() {
        return idBeneficiarioSigep;
    }

    public void setIdBeneficiarioSigep(Long idBeneficiarioSigep) {
        this.idBeneficiarioSigep = idBeneficiarioSigep;
    }


    public PersonaBeneficiarioDto getPersonaBeneficiario() {
        return personaBeneficiario;
    }

    public void setPersonaBeneficiario(PersonaBeneficiarioDto personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public BigDecimal getPrimerSalario() {
        return primerSalario;
    }

    public void setPrimerSalario(BigDecimal primerSalario) {
        this.primerSalario = primerSalario;
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

    public BigDecimal getSegundoSalario() {
        return segundoSalario;
    }

    public void setSegundoSalario(BigDecimal segundoSalario) {
        this.segundoSalario = segundoSalario;
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

    public BigDecimal getTercerSalario() {
        return tercerSalario;
    }

    public void setTercerSalario(BigDecimal tercerSalario) {
        this.tercerSalario = tercerSalario;
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

    public int getGestionRegistro() {
        return gestionRegistro;
    }

    public void setGestionRegistro(int gestionRegistro) {
        this.gestionRegistro = gestionRegistro;
    }

    public String getDepartamentoDescripcion() {
        return departamentoDescripcion;
    }

    public void setDepartamentoDescripcion(String departamentoDescripcion) {
        this.departamentoDescripcion = departamentoDescripcion;
    }

    public String getLocalidadDescripcion() {
        return localidadDescripcion;
    }

    public void setLocalidadDescripcion(String localidadDescripcion) {
        this.localidadDescripcion = localidadDescripcion;
    }

    public Integer getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Integer provinciaId) {
        this.provinciaId = provinciaId;
    }

    public Long getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getEntidadFinancieraDescripcion() {
        return entidadFinancieraDescripcion;
    }

    public void setEntidadFinancieraDescripcion(String entidadFinancieraDescripcion) {
        this.entidadFinancieraDescripcion = entidadFinancieraDescripcion;
    }

    public String getTipoCuentaDescripcion() {
        return tipoCuentaDescripcion;
    }

    public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
        this.tipoCuentaDescripcion = tipoCuentaDescripcion;
    }

	public boolean isTieneSolicitudCambioModalidadAutomatica() {
		return tieneSolicitudCambioModalidadAutomatica;
	}

	public void setTieneSolicitudCambioModalidadAutomatica(boolean tieneSolicitudCambioModalidadAutomatica) {
		this.tieneSolicitudCambioModalidadAutomatica = tieneSolicitudCambioModalidadAutomatica;
	}
}

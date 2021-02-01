package bo.gob.sin.sre.gpri.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BeneficiarioDto extends AuditoriaDto implements Serializable {

    private long beneficiarioId;
    private long personaId;
    private long codigoPersona;
    private String formatoNombreId;
    private String paisId;
    private Integer departamentoId;
    private String departamentoDescripcion;
    private String localidadDescripcion;
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
    private BigDecimal salarioUno;
    private BigDecimal salarioDos;
    private BigDecimal salarioTres;
    private PersonaNaturalDto personaNatural;

    public BeneficiarioDto() {
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


    public PersonaNaturalDto getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNaturalDto personaNatural) {
        this.personaNatural = personaNatural;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public BigDecimal getSalarioUno() {
        return salarioUno;
    }

    public void setSalarioUno(BigDecimal salarioUno) {
        this.salarioUno = salarioUno;
    }

    public BigDecimal getSalarioDos() {
        return salarioDos;
    }

    public void setSalarioDos(BigDecimal salarioDos) {
        this.salarioDos = salarioDos;
    }

    public BigDecimal getSalarioTres() {
        return salarioTres;
    }

    public void setSalarioTres(BigDecimal salarioTres) {
        this.salarioTres = salarioTres;
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
}

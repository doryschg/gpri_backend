package bo.gob.sin.sre.gpri.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroCuentaBeneficiarioDto implements Serializable {

    private long beneficiarioId;
    private String nuaCua;
    private String entidadFinancieraId;
    private String tipoCuentaBancariaId;
    private String cuentaBanco;
    private String monedaId;
    private int distritoCuentaId;
    private String estadoCuentaBancariaId;
    private String pagoAutomaticoId;


    public RegistroCuentaBeneficiarioDto() {
        super();
    }

    public long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

    public String getNuaCua() {
        return nuaCua;
    }

    public void setNuaCua(String nuaCua) {
        this.nuaCua = nuaCua;
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
}

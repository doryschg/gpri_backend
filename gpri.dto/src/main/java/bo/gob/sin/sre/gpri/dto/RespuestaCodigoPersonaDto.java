package bo.gob.sin.sre.gpri.dto;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

import java.io.Serializable;

public class RespuestaCodigoPersonaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigoPersonaId;
    private Long codigoPersona;

    public RespuestaCodigoPersonaDto()
    {

    }
    public Long getCodigoPersonaId() {
        return codigoPersonaId;
    }
    public void setCodigoPersonaId(Long codigoPersonaId) {
        this.codigoPersonaId = codigoPersonaId;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }
    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
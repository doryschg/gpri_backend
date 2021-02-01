package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Auditoria2Dto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioRegistroId = null;

    private Long usuarioUltimaModificacionId = null;

    private Date fechaRegistro = null;

    private Date fechaUltimaModificacion = null;

    private String estadoId = null;

    public Long getUsuarioRegistroId() {
        return this.usuarioRegistroId;
    }

    public void setUsuarioRegistroId(Long usuarioRegistroId) {
        this.usuarioRegistroId = usuarioRegistroId;
    }

    public Long getUsuarioUltimaModificacionId() {
        return this.usuarioUltimaModificacionId;
    }

    public void setUsuarioUltimaModificacionId(Long usuarioUltimaModificacionId) {
        this.usuarioUltimaModificacionId = usuarioUltimaModificacionId;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getEstadoId() {
        return this.estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }
}

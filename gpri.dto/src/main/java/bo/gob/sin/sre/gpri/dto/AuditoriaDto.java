package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class AuditoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioRegistroId = null;

    private Long usuarioUltimaModificacionId = null;

    private LocalDateTime fechaRegistro = null;

    private LocalDateTime fechaUltimaModificacion = null;

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

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

//    public void setFechaRegistro(Long fechaRegistro) {
//        this.fechaRegistro = LocalDateTime.ofInstant(Instant.ofEpochMilli(fechaRegistro),
//                TimeZone.getDefault().toZoneId());;
//    }


    public LocalDateTime getFechaUltimaModificacion() {
        return this.fechaUltimaModificacion;
    }

//    public void setFechaUltimaModificacion(Long fechaUltimaModificacion) {
//        this.fechaUltimaModificacion = LocalDateTime.ofInstant(Instant.ofEpochMilli(fechaUltimaModificacion),
//                TimeZone.getDefault().toZoneId());;
//    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getEstadoId() {
        return this.estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }
}

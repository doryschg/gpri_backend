package bo.gob.sin.sre.gpri.client;

import bo.gob.sin.sre.gpri.dto.PersonaNaturalDto;
import bo.gob.sin.sre.gpri.dto.RespuestaCodigoPersonaDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;

public interface IPersonaNaturalRestclient {

    public ResultadoGenericoListaDto<PersonaNaturalDto> insertarPersonaNatural(PersonaNaturalDto pPersonaNatural);

    public ResultadoGenericoDto<RespuestaCodigoPersonaDto> generarCodigoPersona(PersonaNaturalDto pPersonaNatural);
}

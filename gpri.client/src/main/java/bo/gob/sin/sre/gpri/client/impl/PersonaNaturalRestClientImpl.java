package bo.gob.sin.sre.gpri.client.impl;

import bo.gob.sin.sre.gpri.client.IPersonaNaturalRestclient;
import bo.gob.sin.sre.gpri.dto.*;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import javax.transaction.Transactional;

@Repository
//@Transactional
public class PersonaNaturalRestClientImpl implements IPersonaNaturalRestclient {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaNaturalRestClientImpl.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Value("${scn.empa.capd.apnd.rest}")
    private String restPersonaNaturalUrl;
    private RestTemplate restTemplate;

    public PersonaNaturalRestClientImpl() {
        restTemplate = new RestTemplate();
    }
    @Override
    public ResultadoGenericoListaDto<PersonaNaturalDto> insertarPersonaNatural(PersonaNaturalDto pPersonaNatural) {
        ResultadoGenericoListaDto<PersonaNaturalDto> vRespuesta=new ResultadoGenericoListaDto<>();
        List<PersonaNaturalDto> VListaPersonas = new ArrayList<PersonaNaturalDto>();
        String vUrlServicio = restPersonaNaturalUrl + ConstMetodosRest.PATH_INSERTAR_PERSONA_NATURAL;
        HttpHeaders headers = new HttpHeaders();
        ContextoSeguridad contexto = new ContextoSeguridad();


        try {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
            HttpEntity<PersonaNaturalDto> httpEntity = new HttpEntity<PersonaNaturalDto>(pPersonaNatural, headers);
            ResponseEntity<ResultadoGenericoListaDto<PersonaNaturalDto>> vResponse= restTemplate.exchange(vUrlServicio, HttpMethod.POST, httpEntity,new ParameterizedTypeReference<ResultadoGenericoListaDto<PersonaNaturalDto>>() {
            });
            vRespuesta=vResponse.getBody();
            if(vRespuesta==null)
            {
                vRespuesta=new ResultadoGenericoListaDto<>();
            }
   //         if (vResponse.getBody().isOk())
 //           {
//                mapper.canDeserialize(LocalDateTime);
//                mapper.constructType();
    //            var  VListaPersonas2= mapper.convertValue(vResponse.getBody().getResultadoLista(), new TypeReference<List<PersonaNaturalDto>>() { });

  //              vRespuesta= vResponse.getBody();

//                vRespuesta.setOk(true);
//                VListaPersonas.add(new PersonaNaturalDto());
//                VListaPersonas.get(0).setPersonaId(VListaPersonas2.get(0).getPersonaId());
 //            vRespuesta.setResultadoLista(VListaPersonas2);
//                vRespuesta.setResultadoLista(VListaPersonas);
//            }
        }
        catch (HttpClientErrorException e){
            vRespuesta = new ResultadoGenericoListaDto<>();
            LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
            LOG.info("Servicio no disponible {}", vUrlServicio);
        }
        catch (HttpServerErrorException e){
            vRespuesta = new ResultadoGenericoListaDto<>();
            LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
            LOG.info("Servicio no disponible {}", vUrlServicio);
        }
        return vRespuesta;
    }

    @Override
    public ResultadoGenericoDto<RespuestaCodigoPersonaDto> generarCodigoPersona(PersonaNaturalDto pPersonaNatural) {

        ResultadoGenericoDto<RespuestaCodigoPersonaDto> vRespuesta = new ResultadoGenericoDto<>();
        RespuestaCodigoPersonaDto vCodigoPersona=new RespuestaCodigoPersonaDto();
        String vUrlServicio = restPersonaNaturalUrl + ConstMetodosRest.PATH_GENERAR_CODIGO_PERSONA+"/"+pPersonaNatural.getPersonaId()
                +"/"+pPersonaNatural.getPersona().getNumeroDocumento()+"/"+pPersonaNatural.getPersona().getTipoDocumentoIdentidadId();
        HttpHeaders headers = new HttpHeaders();
        ContextoSeguridad contexto = new ContextoSeguridad();
        try {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
            HttpEntity httpEntity = new HttpEntity(headers);

            ResponseEntity<ResultadoGenericoDto> vResponse=restTemplate.exchange(vUrlServicio, HttpMethod.GET, httpEntity, ResultadoGenericoDto.class);
            if (vResponse.getBody().isOk())
            {
                vCodigoPersona = mapper.convertValue(vResponse.getBody().getResultadoObjeto(), new TypeReference<RespuestaCodigoPersonaDto>() { });
                vRespuesta= vResponse.getBody();
                vRespuesta.setResultadoObjeto(vCodigoPersona);
            }


//
        }
        catch (HttpClientErrorException e){
            vRespuesta = new ResultadoGenericoDto<>();
            LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
            LOG.info("Servicio no disponible {}", vUrlServicio);
        }
        catch (HttpServerErrorException e){
            vRespuesta = new ResultadoGenericoDto<>();
            LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
            LOG.info("Servicio no disponible {}", vUrlServicio);
        }
        return vRespuesta;
    }
}

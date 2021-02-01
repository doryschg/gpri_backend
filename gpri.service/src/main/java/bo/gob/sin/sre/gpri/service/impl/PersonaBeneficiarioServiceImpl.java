package bo.gob.sin.sre.gpri.service.impl;

import bo.gob.sin.sre.gpri.ContextoSeguridad;
import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.client.IPersonaNaturalRestclient;
import bo.gob.sin.sre.gpri.domain.IBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.domain.IPersonaBeneficiarioDomain;
import bo.gob.sin.sre.gpri.dto.PersonaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;
import bo.gob.sin.sre.gpri.service.IPersonaBeneficiarioService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional("transactionManager")
public class PersonaBeneficiarioServiceImpl implements IPersonaBeneficiarioService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonaBeneficiarioServiceImpl.class);
    @Autowired
    private IBeneficiarioHistoricoDomain beneficiarioHistoricoDomain;

    @Autowired
    private IMensajeAplicacionDomain mensajesDomain;

    @Autowired
    private IPersonaNaturalRestclient personaNaturalRestclient;

    @Autowired
    private IPersonaBeneficiarioDomain personaBeneficiarioDomain;


    @Override
    public ResultadoGenericoDto<PersonaBeneficiarioDto> insertarPersonaBeneficiario(PersonaBeneficiarioDto pPersonaBeneficiario) {

        ContextoSeguridad contexto = new ContextoSeguridad();
        ResultadoGenericoDto<PersonaBeneficiarioDto> vResultado = new ResultadoGenericoDto<>();
        vResultado.setOk(false);
        if(pPersonaBeneficiario!=null)
        {
            pPersonaBeneficiario.setUsuarioRegistroId(contexto.obtener().getId());
            pPersonaBeneficiario.setUsuarioUltimaModificacionId(contexto.obtener().getId());
            pPersonaBeneficiario.setFechaRegistro(LocalDateTime.now());
            pPersonaBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
            pPersonaBeneficiario.setEstadoPerBeneficiarioId("H");
            pPersonaBeneficiario.setFechaDesde(LocalDate.now());
            pPersonaBeneficiario.setEstadoId("AC");
            ModelMapper vBeneficiarioPersonaMapper = new ModelMapper();
            vBeneficiarioPersonaMapper.getConfiguration().setAmbiguityIgnored(true);
            SreTsfeRivPersonas vBeneficiarioEntidad = vBeneficiarioPersonaMapper.map(pPersonaBeneficiario, SreTsfeRivPersonas.class);
            SreTsfeRivPersonas vBeneficiarioRegistrado  = personaBeneficiarioDomain.insertarPersonaBeneficiario(vBeneficiarioEntidad);
            PersonaBeneficiarioDto vBeneficiarioPersonaRegistradoDto = vBeneficiarioPersonaMapper.map(vBeneficiarioRegistrado, PersonaBeneficiarioDto.class);

            vResultado.setOk(true);
            vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
                    EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO).set("Persona Beneficiario"));
            vResultado.setResultadoObjeto(vBeneficiarioPersonaRegistradoDto);

        }else
        {
            vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
                    EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS).set(" Sin datos de PersonaBeneficiario "));
            vResultado.setResultadoObjeto(null);
        }

        return vResultado;
    }

    @Override
    public ResultadoGenericoDto<PersonaBeneficiarioDto> modificarPErsonaBeneficiario(PersonaBeneficiarioDto pPersonaBeneficiario) {
        return null;
    }
}

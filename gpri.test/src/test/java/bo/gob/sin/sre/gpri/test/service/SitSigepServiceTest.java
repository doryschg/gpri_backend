package bo.gob.sin.sre.gpri.test.service;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.config.HibernateConfig;
import bo.gob.sin.config.RedisConfigTest;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroNuevoBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.dtos.ResultadoGenericoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaConsultaBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.PersonaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.PersonaDto;
import bo.gob.sin.sre.gpri.dto.PersonaNaturalDto;
import bo.gob.sin.sre.gpri.sit.service.ISitSigepService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { AppConfig.class, HibernateConfig.class, RedisConfigTest.class })
//@Transactional
//@Rollback
public class SitSigepServiceTest {

	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJQYWluYTkzMDYiLCJvZmljaW5hIjoiSDRzSUFBQUFBQUFBQURNQUFDSGYyX1FCQUFBQSIsIm5pdCI6Ikg0c0lBQUFBQUFBQUFMTTBOakF6TkRVd01MUUFBR1dfVnJJS0FBQUEiLCJpZCI6NzEwNjUyLCJleHAiOjE2MDg1NzU0MjEsImlhdCI6MTYwODU2MDk2MSwiZGVwZW5kZW5jaWEiOiJINHNJQUFBQUFBQUFBRE0wTUFBQTZsQjNJd01BQUFBPSJ9.l1L58OW8UOWeI99MFDIXSLY3mfxchgEPtaUx3-6obLVekgO_G2n3Y1ZSRa_Y2RKFryCXC3Z5FGSNrh4At246Kw";

	@Autowired
	private ISitSigepService sitSigepService;

	// @Test
	public void registrarBeneficiario() {
		try {
			BeneficiarioPersonaRivDto vBeneficiario = new BeneficiarioPersonaRivDto();
			vBeneficiario.setDireccion("PRUEBA DIRECCION");
			vBeneficiario.setFormatoNombreId("U1AC");
			PersonaBeneficiarioDto vPersonaNatural = new PersonaBeneficiarioDto();
			vPersonaNatural.setNombres("VLADIMIR");
			vPersonaNatural.setPrimerApellido("ZAPATA");
			vPersonaNatural.setSegundoApellido("FERNANDEZ");
			//vPersonaNatural.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("22/11/1991"));
			//vPersonaNatural.setCorreoElectronico("sergio902@hotmail.com");
			PersonaDto vPersona = new PersonaDto();
			vPersona.setNumeroDocumento("4332566936");
			vPersona.setCelular("77548556");
			vPersona.setTelefonoReferencia1("2415233");

			//vPersonaNatural.setPersona(vPersona);

			vBeneficiario.setPersonaBeneficiario(vPersonaNatural);
			var vRespuesta = sitSigepService.registroBeneficiarioSIGEP(vBeneficiario, token);
			Assert.isTrue(vRespuesta.getResultadoObjeto().getIdBeneficiarioSigep() > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// @Test
	public void modificarBeneficiario() {
		try {

			BeneficiarioPersonaRivDto vBeneficiario = new BeneficiarioPersonaRivDto();
			vBeneficiario.setIdBeneficiarioSigep(1011782L);
			vBeneficiario.setDireccion("PRUEBA DIRECCION");
			vBeneficiario.setFormatoNombreId("U1AC");
			PersonaNaturalDto vPersonaNatural = new PersonaNaturalDto();
			vPersonaNatural.setNombres("VLADIMIR");
			vPersonaNatural.setPrimerApellido("ZAPATA");
			vPersonaNatural.setSegundoApellido("FERNANDEZ2");
			vPersonaNatural.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("22/11/1991"));
			vPersonaNatural.setCorreoPersonal("sergio902@hotmail.com");
			PersonaDto vPersona = new PersonaDto();
			vPersona.setCelular("77560403");
			vPersona.setTelefonoReferencia1("2415233");

			vPersonaNatural.setPersona(vPersona);

			//vBeneficiario.setPersonaNatural(vPersonaNatural);
			var vRespuesta = sitSigepService.actualizarBeneficiarioSIGEP(vBeneficiario, token);
			Assert.isTrue(vRespuesta.getResultadoObjeto().getIdBeneficiarioSigep() > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// @Test
	public void registroCuentaBeneficiario() {
		try {
			BeneficiarioPersonaRivDto vBeneficiario = new BeneficiarioPersonaRivDto();
			vBeneficiario.setCuentaBanco("10000025952410");
			vBeneficiario.setIdBeneficiarioSigep(1011782L);
			vBeneficiario.setMonedaId("69");
			vBeneficiario.setEntidadFinancieraId("1014");
			vBeneficiario.setDistritoCuentaId(2);
			vBeneficiario.setTipoCuentaBancariaId("A");

			var vRespuesta = sitSigepService.registroCuentaBeneficiarioSIGEP(vBeneficiario, token);
			Assert.isTrue(vRespuesta.getResultadoObjeto() != null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// @Test
	public void registroDatosAdicionalesBeneficiario() {
		try {
			BeneficiarioPersonaRivDto vBeneficiario = new BeneficiarioPersonaRivDto();
			vBeneficiario.setIdBeneficiarioSigep(1011782L);
			vBeneficiario.setCuentaBanco("10000025952410");

			var vRespuesta = sitSigepService.registroDatosAdicionalesBeneficiarioSIGEP(vBeneficiario, token);
			Assert.isTrue(vRespuesta.getResultadoObjeto());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void actualizarDatosAdicionalesBeneficiario() {
		try {
			BeneficiarioPersonaRivDto vBeneficiario = new BeneficiarioPersonaRivDto();
			vBeneficiario.setIdBeneficiarioSigep(1011782L);
			vBeneficiario.setCuentaBanco("10000025952410");

			var vRespuesta = sitSigepService.actualizarDatosAdicionalesBeneficiarioSIGEP(vBeneficiario, token);
			Assert.isTrue(vRespuesta.getResultadoObjeto());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//	@Test
//	public void consultarYregistrarBeneficiarioTest() {
//		try {
//
//			SolicitudConsultaBeneficiarioDto vSolicitudConsulta = ConsultaBeneficiarioBuilder.builder()
//					.numeroDocumento("43385966").primerApellido("CERFF").segundoApellido("MIRANDA").nombres("PEDRO")
//					.fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("22/11/1985")).build();
//
//			ResultadoGenericoDto<RespuestaConsultaBeneficiarioDto> vRespuestaConsulta = client
//					.consultaBeneficiario(vSolicitudConsulta, token);
//
//			if (vRespuestaConsulta.isEstado()) {
//				ModelMapper vEntityDtoMapper = new ModelMapper();
//				vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
//				RespuestaConsultaBeneficiarioDto vRespuestaConsultaBeneficiarioDto = vEntityDtoMapper.map(
//						vRespuestaConsulta.getResultadoObjeto(), new TypeToken<RespuestaConsultaBeneficiarioDto>() {
//						}.getType());
//
//				System.out.println(vRespuestaConsultaBeneficiarioDto);
//				if (!vRespuestaConsultaBeneficiarioDto.getErrores().isEmpty()) {
//					SolicitudRegistroNuevoBeneficiarioDto vSolicitud = RegistroNuevoBeneficiarioBuilder.builder()
//							.numeroDocumento(vSolicitudConsulta.getNumeroDocumento()).expDepartamento(2).expPais("BO")
//							.primerApellido(vSolicitudConsulta.getPrimerApellido())
//							.segundoApellido(vSolicitudConsulta.getSegundoApellido()).apellidoCasada("LOPEZ")
//							.nombres(vSolicitudConsulta.getNombres()).email("ptest.a@gmail.com")
//							.fechaNacimiento(vSolicitudConsulta.getFechaNacimiento()).estadoCivil("C")
//							.formatoInf("U1AC").pais("BO").ciudad("LA PAZ").casillaPostal(133233).localidad("LA PAZ")
//							.direccion("CALLE INDABURO ESQ. COLON NRO 567").telefono("2345678").celular("70654312")
//							.fax("").origen("SIN").build();
//					ResultadoGenericoDto<RespuestaRegistroNuevoBeneficiarioDto> vRespuesta = client
//							.registrarNuevoBeneficiario(vSolicitud, token);
//					System.out.println(
//							"===============================registrarBeneficiarioTest===============================");
//					System.out.println(vRespuesta);
//					Assert.notNull(vRespuesta);
//				} else {
//					System.out.println(
//							"===============================Beneficiario ya existe===============================");
//					System.out.println(vRespuestaConsulta);
//					Assert.notNull(vRespuestaConsulta);
//				}
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
}
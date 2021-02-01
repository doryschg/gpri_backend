package bo.gob.sin.sre.gpri.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.config.HibernateConfig;
import bo.gob.sin.config.RedisConfigTest;
import bo.gob.sin.sit.api.sigep.models.builders.ActualizacionDatosAdicionalesBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaIndentificadorPagoBonoBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.EliminacionPagoBonoBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.InactivacionCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ModificacionCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ModificarBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroDatosAdicionalesBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroDatosPagoBonoBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroNuevoBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.dtos.ErrorDto;
import bo.gob.sin.sit.api.sigep.models.dtos.RespuestaSolicitudComunicacionDto;
import bo.gob.sin.sit.api.sigep.models.dtos.ResultadoGenericoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaActualizacionDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaConsultaBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaConsultaIdentificadorPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaEliminacionPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaInactivacionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaInsercionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaModificacionBenefeciarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaModificacionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaRegistroDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaRegistroDatosPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudActualizacionDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaIdentificadorPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudEliminacionPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudInactivacionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudInsercionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudModificacionBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudModificacionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroDatosPagoBonoDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sit.client.sigep.SigepRestClient;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppConfig.class, HibernateConfig.class, RedisConfigTest.class })
@Transactional
@Rollback
public class SigepServiceTest {

	private SigepRestClient client = new SigepRestClient();

	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaWF0LmZhY3R1cmFjaW9uIiwiaWQiOjk5OSwiZXhwIjoxNjExNzYyNjg1LCJpYXQiOjE2MTE3NDc5NTV9.NpY922qlq85xRbdrWJw9pi3kG5laxVBJqXoRqgzEM0WhgswaNZ9IItouUzq2ZdBB56OehPPaOhvE2PLJ-hYNcg";

	@Test
	public void verificarComunicacionTest() {
		RespuestaSolicitudComunicacionDto vRespuesta = client.verificarComunicacion(token);
		System.out.println("===============================verificarComunicacionTest===============================");
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void consultaBeneficiarioTest() {
		try {
			SolicitudConsultaBeneficiarioDto vSolicitud = ConsultaBeneficiarioBuilder.builder()
					.numeroDocumento("426883312").primerApellido("TAPIA").segundoApellido("APAZA").nombres("EFRAIN LUIS")
					.fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("22/11/1975")).build();
			ResultadoGenericoDto<RespuestaConsultaBeneficiarioDto> vRespuesta = client.consultaBeneficiario(vSolicitud,
					token);
			if (vRespuesta != null && vRespuesta.getResultadoObjeto() != null) {
				if (vRespuesta.getResultadoObjeto().getBeneficiario() == null) {
					System.out
					.println("===============================inserta nuevo===============================");
				}else {
					System.out
					.println("===============================inserta existente SIGEP===============================");
				}
			}
			System.out.println(vRespuesta);
			System.out
					.println("===============================consultaBeneficiarioTest===============================");
			Assert.notNull(vRespuesta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void registrarBeneficiarioTest() {
		try {
			SolicitudRegistroNuevoBeneficiarioDto vSolicitud = RegistroNuevoBeneficiarioBuilder.builder()
					.numeroDocumento("433234495").expDepartamento(2).expPais("BO").primerApellido("PEREZ")
					.segundoApellido("AGUIRRE").apellidoCasada("LOPEZ").nombres("MARIA").email("pMARITA.a@gmail.com")
					.fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("24/04/2000")).estadoCivil("C")
					.formatoInf("U1AC").pais("BO").ciudad("LA PAZ").casillaPostal(133233).localidad("LA PAZ")
					.direccion("CALLE INDABURO ESQ. COLON NRO 567").telefono("2345678").celular("70654312").fax("")
					.origen("SIN").build();
			ResultadoGenericoDto<RespuestaRegistroNuevoBeneficiarioDto> vRespuesta = client
					.registrarNuevoBeneficiario(vSolicitud, token);
			System.out
					.println("===============================registrarBeneficiarioTest===============================");
			System.out.println(vRespuesta);

			Assert.notNull(vRespuesta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void modificacionBeneficiarioTest() {
		try {
			SolicitudModificacionBeneficiarioDto vSolicitud = ModificarBeneficiarioBuilder.builder()
					.beneficiario(1011784L).expDepartamento(2).expPais("BO").primerApellido("OJEDA")
					.segundoApellido("MAMANI").apellidoCasada("").nombres("ESPERANCITO POCHOLO")
					.email("esperancito@gmail.com")
					.fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("16/09/1989")).estadoCivil("S")
					.formatoInf("NUAC").pais("BO").ciudad("LA PAZ").casillaPostal(133233).localidad("LA PAZ")
					.direccion("CALLE MURILLO ESQ COLOMBIA N 123").telefono("2323485").celular("73596251").fax("")
					.build();
			ResultadoGenericoDto<RespuestaModificacionBenefeciarioDto> vRespuesta = client
					.modificarBeneficiario(vSolicitud, token);
			System.out.println(vRespuesta);
			if (vRespuesta.getResultadoObjeto().getErrores() != null) {
				System.out.println("Errores");
				vRespuesta.getResultadoObjeto().getErrores().forEach(x -> {
					System.out.println("Mensaje: " + x.getMensaje());
					System.out.println("Causa: " + x.getCausa());
					System.out.println("Accion: " + x.getAccion());
				});
			}
			Assert.notNull(vRespuesta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void insercionCuentaBancariaTest() {
		SolicitudInsercionCuentaBancariaDto vSolicitud = RegistroCuentaBancariaBuilder.builder().beneficiario(405079L)
				.banco(1014).cuenta("10000025952503").moneda(69).distrito(2).tipocuenta("C").build();
		ResultadoGenericoDto<RespuestaInsercionCuentaBancariaDto> vRespuesta = client.registroCuentaBancaria(vSolicitud,
				token);
		System.out.println(vRespuesta);

		var vErrores = mapErrorsSigep(vRespuesta.getResultadoObjeto().getErrores());
		Assert.notNull(vRespuesta);
	}

	@Test
	public void inactivacionCuentaBancariaTest() {
		SolicitudInactivacionCuentaBancariaDto vSolicitud = InactivacionCuentaBancariaBuilder.builder()
				.beneficiario(1011766L).banco(1014).cuenta("10000002957740").build();
		ResultadoGenericoDto<RespuestaInactivacionCuentaBancariaDto> vRespuesta = client
				.inactivacionCuentaBancaria(vSolicitud, token);
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void modificacionCuentaBancariaTest() {
		SolicitudModificacionCuentaBancariaDto vSolicitud = ModificacionCuentaBancariaBuilder.builder()
				.beneficiario(405079L).banco(1014).cuenta("10000029129322").moneda(69).distrito(2).tipocuenta("C")
				.build();
		ResultadoGenericoDto<RespuestaModificacionCuentaBancariaDto> vRespuesta = client
				.modificarCuentaBancaria(vSolicitud, token);
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void registroDatosAcionalesTest() {
		SolicitudRegistroDatosAdicionalesDto vSolicitud = RegistroDatosAdicionalesBuilder.builder()
				.beneficiario(1011766L).bono(2).idUbigeo(3).idEntidadPago(46).banco(1014).cuenta("10000005582666")
				.build();
		ResultadoGenericoDto<RespuestaRegistroDatosAdicionalesDto> vRespuesta = client
				.registroDatosAdicionales(vSolicitud, token);
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void actualizacionDatosAdicionalesTest() {
		SolicitudActualizacionDatosAdicionalesDto vSolicitud = ActualizacionDatosAdicionalesBuilder.builder()
				.beneficiario(1011766L).bono(3).idUbigeo(3).idEntidadPago(46).banco(1014).cuenta("10000005582666")
				.build();
		ResultadoGenericoDto<RespuestaActualizacionDatosAdicionalesDto> vRespuesta = client
				.actualizacionDatosAdicionales(vSolicitud, token);
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void eliminacionDatosPagoBonoTest() {
		SolicitudEliminacionPagoBonoDto vSolicitud = EliminacionPagoBonoBuilder.builder().identificadorPagoBono(1111L)
				.build();
		ResultadoGenericoDto<RespuestaEliminacionPagoBonoDto> vRespuesta = client.eliminacionDatosPagoBono(vSolicitud,
				token);
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void registroDatosPagoBonoTest() {
		SolicitudRegistroDatosPagoBonoDto vSolicitud = RegistroDatosPagoBonoBuilder.builder().beneficiario(1011771L)
				.bono(3).gestion(2020).mes(12).monto(500.00).origen("SIN").build();
		ResultadoGenericoDto<RespuestaRegistroDatosPagoBonoDto> vRespuesta = client.registroDatosPagoBono(vSolicitud,
				token);
		System.out.println("===============================registroDatosPagoBonoTest===============================");
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void consultaCuentaBancaria() {
		SolicitudConsultaCuentaBancariaDto vSolicitudConsultaCuentaBancario = ConsultaCuentaBancariaBuilder.builder()
				.beneficiario(1011771L).origen("SIN").banco(Integer.valueOf(1014)).cuenta("10000025952503").build();

		var vRespuesta = client.consultaCuentaBancaria(vSolicitudConsultaCuentaBancario, token);
		System.out.println("===============================consultaCuentaBancariaTest===============================");
		System.out.println(vRespuesta);
		Assert.notNull(vRespuesta);
	}

	@Test
	public void consultaIdentificadorRegistroPagoBonoTest() {
		SolicitudConsultaIdentificadorPagoBonoDto vSolicitud = ConsultaIndentificadorPagoBonoBuilder.builder()
				.beneficiario(1011766L).bono(3).gestion(2020).mes(11).build();
		ResultadoGenericoDto<RespuestaConsultaIdentificadorPagoBonoDto> vRespuesta = client
				.consultaIdentificadorPagoBono(vSolicitud, token);
		System.out.println(vRespuesta);
		System.out.println("Identificador:" + vRespuesta.getResultadoObjeto().getIdentificadorPagoBono());
		if (vRespuesta.getResultadoObjeto().getErrores() != null) {
			System.out.println("Errores");
			vRespuesta.getResultadoObjeto().getErrores().forEach(x -> {
				System.out.println("Mensaje: " + x.getMensaje());
				System.out.println("Causa: " + x.getCausa());
				System.out.println("Accion: " + x.getAccion());
			});
		}
		Assert.notNull(vRespuesta);
	}

	private List<StrMensajeAplicacionDto> mapErrorsSigep(List<ErrorDto> vErrores) {
		List<StrMensajeAplicacionDto> vMensajes = new ArrayList<>();

		vErrores.forEach(fila -> {
			StrMensajeAplicacionDto vMensaje = new StrMensajeAplicacionDto();
			vMensaje.setCodigo(0);
			vMensaje.setDescripcion(fila.getMensaje());
			vMensaje.setDescripcionUi(fila.getCausa());
			vMensajes.add(vMensaje);
		});

		return vMensajes;
	}
}
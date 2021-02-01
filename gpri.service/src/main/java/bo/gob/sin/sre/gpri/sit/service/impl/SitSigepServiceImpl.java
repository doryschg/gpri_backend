package bo.gob.sin.sre.gpri.sit.service.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sit.api.sigep.models.builders.ActualizacionDatosAdicionalesBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ModificacionCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.ModificarBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroCuentaBancariaBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroDatosAdicionalesBuilder;
import bo.gob.sin.sit.api.sigep.models.builders.RegistroNuevoBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.dtos.ErrorDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudActualizacionDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudInsercionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudModificacionBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudModificacionCuentaBancariaDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroDatosAdicionalesDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sit.client.sigep.SigepRestClient;
import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.enumerador.sigep.ConstantesSigep;
import bo.gob.sin.sre.gpri.sit.service.ISitSigepService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
@Transactional("transactionManager")
public class SitSigepServiceImpl implements ISitSigepService {
	private static final Logger LOG = LoggerFactory.getLogger(SitSigepServiceImpl.class);
	private SigepRestClient client = new SigepRestClient();

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> registroBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		Long vCodigoBeneficiario = 0L;
		try {
			Date vFechaaEnviar = Date.from(pBeneficiario.getPersonaBeneficiario().getFechaNacimiento()
					.atZone(ZoneId.systemDefault()).toInstant());
			SolicitudConsultaBeneficiarioDto vSolicitudConsulta = ConsultaBeneficiarioBuilder.builder()
					.numeroDocumento(pBeneficiario.getPersonaBeneficiario().getNumeroDocumento())
					.primerApellido(pBeneficiario.getPersonaBeneficiario().getPrimerApellido().toUpperCase())
					.segundoApellido(pBeneficiario.getPersonaBeneficiario().getSegundoApellido().toUpperCase())
					.nombres(pBeneficiario.getPersonaBeneficiario().getNombres().toUpperCase())
					.fechaNacimiento(vFechaaEnviar).build();

			var vRespuestaConsulta = client.consultaBeneficiario(vSolicitudConsulta, pToken);

			if (vRespuestaConsulta != null && vRespuestaConsulta.getResultadoObjeto() != null) {
				// TODO: Ajustar
				Integer vCasillaPostal = 133233;
				if (vRespuestaConsulta.getResultadoObjeto().getBeneficiario() == null) {

					SolicitudRegistroNuevoBeneficiarioDto vSolicitud = RegistroNuevoBeneficiarioBuilder.builder()
							.numeroDocumento(vSolicitudConsulta.getNumeroDocumento())
							.expDepartamento(pBeneficiario.getPersonaBeneficiario().getLugarExpedicionId())
							.expPais(pBeneficiario.getPaisId()).primerApellido(vSolicitudConsulta.getPrimerApellido())
							.segundoApellido(vSolicitudConsulta.getSegundoApellido())
							.apellidoCasada(pBeneficiario.getPersonaBeneficiario().getApellidoCasada())
							.nombres(vSolicitudConsulta.getNombres())
							.email(pBeneficiario.getPersonaBeneficiario().getCorreoElectronico())
							.fechaNacimiento(vFechaaEnviar)
							.estadoCivil(pBeneficiario.getPersonaBeneficiario().getEstadoCivilDescripcion())
							.formatoInf(pBeneficiario.getFormatoNombreId()).pais(pBeneficiario.getPaisId())
							.ciudad(pBeneficiario.getDepartamentoDescripcion()).casillaPostal(vCasillaPostal)
							.localidad(pBeneficiario.getLocalidadDescripcion()).direccion(pBeneficiario.getDireccion())
							.telefono(pBeneficiario.getPersonaBeneficiario().getTelefonoReferencia1())
							.celular(pBeneficiario.getPersonaBeneficiario().getCelular()).fax("").origen("SIN").build();
					var vRespuestaInsercionBeneficiario = client.registrarNuevoBeneficiario(vSolicitud, pToken);

					if (vRespuestaInsercionBeneficiario.isEstado()) {
						vCodigoBeneficiario = vRespuestaInsercionBeneficiario.getResultadoObjeto().getBeneficiario();
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setOk(false);
						vRespuesta.setMensajes(
								mapErrorsSigep(vRespuestaInsercionBeneficiario.getResultadoObjeto().getErrores()));
					}
				} else {
					vCodigoBeneficiario = vRespuestaConsulta.getResultadoObjeto().getBeneficiario();
					vRespuesta.setOk(true);
				}
			}

		} catch (Exception ex) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO).set("Sigep"));
			ex.printStackTrace();
		}

		pBeneficiario.setIdBeneficiarioSigep(vCodigoBeneficiario);
		vRespuesta.setResultadoObjeto(pBeneficiario);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> registroCuentaBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		SolicitudConsultaCuentaBancariaDto vSolicitudConsultaCuentaBancario = ConsultaCuentaBancariaBuilder.builder()
				.beneficiario(pBeneficiario.getIdBeneficiarioSigep()).origen("SIN")
				.banco(Integer.valueOf(pBeneficiario.getEntidadFinancieraId())).cuenta(pBeneficiario.getCuentaBanco())
				.build();

		var vRespuestaConsultaCuenta = client.consultaCuentaBancaria(vSolicitudConsultaCuentaBancario, pToken);
		if (vRespuestaConsultaCuenta.isEstado()) {
			if (vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("VAL")
					&& vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoRegistro().equals("ACTIVO")) {

				vRespuesta.setOk(true);
				pBeneficiario
						.setEstadoCuentaBancariaId(vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta());
				pBeneficiario.setMonedaId(String.valueOf(ConstantesSigep.MONEDA_EN_BOLIVIANOS));
				pBeneficiario.setBeneficiarioId(vRespuestaConsultaCuenta.getResultadoObjeto().getBeneficiario());
				vRespuesta.setResultadoObjeto(pBeneficiario);
				return vRespuesta;
			}
			if(vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("CER")||
					vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("INX")||
					vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("BLO")||
					vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("CMP")||
					vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta().equals("CYT"))
			{
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
						EnumMensajesAplicacionBeneficiario.ERROR_CUENTA_REGISTRO).set("Beneficiario"));
				return vRespuesta;
			}

			SolicitudInsercionCuentaBancariaDto vSolicitud = RegistroCuentaBancariaBuilder.builder()
					.beneficiario(pBeneficiario.getIdBeneficiarioSigep())
					.banco(Integer.valueOf(pBeneficiario.getEntidadFinancieraId()))
					.cuenta(pBeneficiario.getCuentaBanco()).moneda(ConstantesSigep.MONEDA_EN_BOLIVIANOS)
					.distrito(pBeneficiario.getDistritoCuentaId()).tipocuenta(pBeneficiario.getTipoCuentaBancariaId())
					.build();
			var vRespuestaInsercionCuenta = client.registroCuentaBancaria(vSolicitud, pToken);
			if (vRespuestaInsercionCuenta.getResultadoObjeto().getErrores() == null) {

				if(vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta().equals("CER")||
						vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta().equals("INX")||
						vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta().equals("BLO")||
						vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta().equals("CMP")||
						vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta().equals("CYT"))
				{
					vRespuesta.setOk(false);
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_CUENTA_REGISTRO).set("Beneficiario"));
				}
				else
				{
					vRespuesta.setOk(true);
					pBeneficiario.setEstadoCuentaBancariaId(vRespuestaInsercionCuenta.getResultadoObjeto().getEstadoCuenta());
					pBeneficiario.setMonedaId(vRespuestaInsercionCuenta.getResultadoObjeto().getMoneda().toString());
					pBeneficiario.setBeneficiarioId(vRespuestaInsercionCuenta.getResultadoObjeto().getBeneficiario());
					vRespuesta.setResultadoObjeto(pBeneficiario);
				}



			} else {
				vRespuesta.setOk(false);
				vRespuesta.setMensajes(mapErrorsSigep(vRespuestaInsercionCuenta.getResultadoObjeto().getErrores()));

			}
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<Boolean> registroDatosAdicionalesBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ResultadoGenericoDto<Boolean> vRespuesta = new ResultadoGenericoDto<Boolean>();
		vRespuesta.setOk(false);
		vRespuesta.setResultadoObjeto(false);

		Integer vIdUbigeo = ConstantesSigep.UBI_GEO.get(pBeneficiario.getDepartamentoId());
		Integer vEntidadPago = ConstantesSigep.ENTIDAD_PAGO;
		Integer vBancoId = Integer.valueOf(pBeneficiario.getEntidadFinancieraId());

		SolicitudRegistroDatosAdicionalesDto vSolicitud = RegistroDatosAdicionalesBuilder.builder()
				.beneficiario(pBeneficiario.getIdBeneficiarioSigep()).bono(ConstantesSigep.BONO_REINTEGRO)
				.idUbigeo(vIdUbigeo).idEntidadPago(vEntidadPago).banco(vBancoId).cuenta(pBeneficiario.getCuentaBanco())
				.build();

		var vRespuestaRegistroDatosAdicionales = client.registroDatosAdicionales(vSolicitud, pToken);

		if (vRespuestaRegistroDatosAdicionales.isEstado()) {
			vRespuesta.setResultadoObjeto(true);

		} else {
			vRespuesta.setOk(false);
			vRespuesta
					.setMensajes(mapErrorsSigep(vRespuestaRegistroDatosAdicionales.getResultadoObjeto().getErrores()));
		}

		vRespuesta.setOk(true);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<Boolean> actualizarDatosAdicionalesBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ResultadoGenericoDto<Boolean> vRespuesta = new ResultadoGenericoDto<Boolean>();
		vRespuesta.setOk(false);
		vRespuesta.setResultadoObjeto(false);

		Integer vIdUbigeo = ConstantesSigep.UBI_GEO.get(pBeneficiario.getDepartamentoId());
		Integer vEntidadPago = ConstantesSigep.ENTIDAD_PAGO;
		Integer vBancoId = Integer.valueOf(pBeneficiario.getEntidadFinancieraId());

		SolicitudActualizacionDatosAdicionalesDto vSolicitud = ActualizacionDatosAdicionalesBuilder.builder()
				.beneficiario(pBeneficiario.getIdBeneficiarioSigep()).bono(ConstantesSigep.BONO_REINTEGRO)
				.idUbigeo(vIdUbigeo).idEntidadPago(vEntidadPago).banco(vBancoId).cuenta(pBeneficiario.getCuentaBanco())
				.build();

		try {
			var vRespuestaRegistroDatosAdicionales = client.actualizacionDatosAdicionales(vSolicitud, pToken);
			if (vRespuestaRegistroDatosAdicionales.isEstado()) {
				vRespuesta.setOk(true);
				vRespuesta.setResultadoObjeto(true);

			} else {
				vRespuesta.setOk(false);
				vRespuesta.setMensajes(
						mapErrorsSigep(vRespuestaRegistroDatosAdicionales.getResultadoObjeto().getErrores()));
			}

		} catch (Exception ex) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO).set("Sigep"));
			ex.printStackTrace();
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> actualizarBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		Long vCodigoBeneficiario = 0L;
		try {

			// TODO: Ajustar
			Integer vCasillaPostal = 133233;

			SolicitudModificacionBeneficiarioDto vSolicitud = ModificarBeneficiarioBuilder.builder()
					.beneficiario(pBeneficiario.getIdBeneficiarioSigep())
					.expDepartamento(pBeneficiario.getPersonaBeneficiario().getLugarExpedicionId())
					.expPais(pBeneficiario.getPaisId())
					.primerApellido(pBeneficiario.getPersonaBeneficiario().getPrimerApellido())
					.segundoApellido(pBeneficiario.getPersonaBeneficiario().getSegundoApellido())
					.apellidoCasada(pBeneficiario.getPersonaBeneficiario().getApellidoCasada())
					.nombres(pBeneficiario.getPersonaBeneficiario().getNombres())
					.email(pBeneficiario.getPersonaBeneficiario().getCorreoElectronico())
					.fechaNacimiento(Date.from(pBeneficiario.getPersonaBeneficiario().getFechaNacimiento()
							.atZone(ZoneId.systemDefault()).toInstant()))
					.estadoCivil(pBeneficiario.getPersonaBeneficiario().getApellidoCasada() == null ? "S" : "C")
					.formatoInf(pBeneficiario.getFormatoNombreId()).pais(pBeneficiario.getPaisId())
					.ciudad(pBeneficiario.getDepartamentoDescripcion()).casillaPostal(vCasillaPostal)
					.localidad(pBeneficiario.getLocalidadDescripcion()).direccion(pBeneficiario.getDireccion())
					.telefono(pBeneficiario.getPersonaBeneficiario().getTelefonoReferencia1())
					.celular(pBeneficiario.getPersonaBeneficiario().getCelular()).fax("").build();

			var vRespuestaModificacionBeneficiario = client.modificarBeneficiario(vSolicitud, pToken);

			if (vRespuestaModificacionBeneficiario.isEstado()) {
				vCodigoBeneficiario = vRespuestaModificacionBeneficiario.getResultadoObjeto().getBeneficiario();
				vRespuesta.setOk(true);
			} else {
				vRespuesta.setOk(false);
				// TODO MENSAJE NO PUEDO MODIFICAR EL BENEFICIARIO
			}

		} catch (Exception ex) {
			// TODO MENSAJE EXEPCION
			ex.printStackTrace();
		}

		pBeneficiario.setIdBeneficiarioSigep(vCodigoBeneficiario);
		vRespuesta.setResultadoObjeto(pBeneficiario);

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		Long vCodigoBeneficiario = 0L;
		try {

			// TODO: Ajustar
			Integer vCasillaPostal = 133233;

			SolicitudModificacionBeneficiarioDto vSolicitud = ModificarBeneficiarioBuilder.builder()
					.beneficiario(pBeneficiario.getIdBeneficiarioSigep())
					.expDepartamento(pBeneficiario.getPersonaBeneficiario().getLugarExpedicionId())
					.expPais(pBeneficiario.getPaisId())
					.primerApellido(pBeneficiario.getPersonaBeneficiario().getPrimerApellido())
					.segundoApellido(pBeneficiario.getPersonaBeneficiario().getSegundoApellido())
					.apellidoCasada(pBeneficiario.getPersonaBeneficiario().getApellidoCasada())
					.nombres(pBeneficiario.getPersonaBeneficiario().getNombres())
					.email(pBeneficiario.getPersonaBeneficiario().getCorreoElectronico())
					.fechaNacimiento(Date.from(pBeneficiario.getPersonaBeneficiario().getFechaNacimiento()
							.atZone(ZoneId.systemDefault()).toInstant()))
					.estadoCivil(pBeneficiario.getPersonaBeneficiario().getApellidoCasada() == null ? "S" : "C")
					.formatoInf(pBeneficiario.getFormatoNombreId()).pais(pBeneficiario.getPaisId())
					.ciudad(pBeneficiario.getDepartamentoDescripcion()).casillaPostal(vCasillaPostal)
					.localidad(pBeneficiario.getLocalidadDescripcion()).direccion(pBeneficiario.getDireccion())
					.telefono(pBeneficiario.getPersonaBeneficiario().getTelefonoReferencia1())
					.celular(pBeneficiario.getPersonaBeneficiario().getCelular()).fax("").build();

			var vRespuestaInsercionBeneficiario = client.modificarBeneficiario(vSolicitud, pToken);
			if (vRespuestaInsercionBeneficiario.isEstado()) {
				vCodigoBeneficiario = vRespuestaInsercionBeneficiario.getResultadoObjeto().getBeneficiario();
				vRespuesta.setOk(true);
			} else {
				vRespuesta.setOk(false);
				vRespuesta
						.setMensajes(mapErrorsSigep(vRespuestaInsercionBeneficiario.getResultadoObjeto().getErrores()));
			}

		} catch (Exception ex) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO).set("Sigep"));
			ex.printStackTrace();
		}

		pBeneficiario.setIdBeneficiarioSigep(vCodigoBeneficiario);
		vRespuesta.setResultadoObjeto(pBeneficiario);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarCuentaBeneficiarioSIGEP(
			BeneficiarioPersonaRivDto pBeneficiario, String pToken) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		try {
			SolicitudConsultaCuentaBancariaDto vSolicitudConsultaCuentaBancario = ConsultaCuentaBancariaBuilder
					.builder().beneficiario(pBeneficiario.getIdBeneficiarioSigep()).origen("SIN")
					.banco(Integer.valueOf(pBeneficiario.getEntidadFinancieraId()))
					.cuenta(pBeneficiario.getCuentaBanco()).build();

			var vRespuestaConsultaCuenta = client.consultaCuentaBancaria(vSolicitudConsultaCuentaBancario, pToken);
			if (vRespuestaConsultaCuenta.isEstado()) {
				SolicitudModificacionCuentaBancariaDto vSolicitud = ModificacionCuentaBancariaBuilder.builder()
						.beneficiario(pBeneficiario.getIdBeneficiarioSigep())
						.banco(Integer.valueOf(pBeneficiario.getEntidadFinancieraId()))
						.cuenta(pBeneficiario.getCuentaBanco()).moneda(ConstantesSigep.MONEDA_EN_BOLIVIANOS)
						.distrito(pBeneficiario.getDistritoCuentaId())
						.tipocuenta(pBeneficiario.getTipoCuentaBancariaId()).build();
				var vRespuestaInsercionCuenta = client.modificarCuentaBancaria(vSolicitud, pToken);
				if (vRespuestaInsercionCuenta.getResultadoObjeto() != null
						&& vRespuestaInsercionCuenta.getResultadoObjeto().getErrores() == null) {
					vRespuesta.setOk(true);
					pBeneficiario
							.setEstadoCuentaBancariaId(vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta());
					vRespuesta.setResultadoObjeto(pBeneficiario);

				} else {
					vRespuesta.setOk(false);
					vRespuesta.setMensajes(mapErrorsSigep(vRespuestaInsercionCuenta.getResultadoObjeto().getErrores()));
				}
			}

		} catch (Exception ex) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO).set("Sigep"));
			ex.printStackTrace();
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<String> consultarEstadoCuentaBeneficiarioSIGEP(BeneficiarioPersonaRivDto pBeneficiario,
			String pToken) {
		ResultadoGenericoDto<String> vRespuesta = new ResultadoGenericoDto<String>();
		try {
			SolicitudConsultaCuentaBancariaDto vSolicitudConsultaCuentaBancario = ConsultaCuentaBancariaBuilder
					.builder().beneficiario(pBeneficiario.getIdBeneficiarioSigep()).origen("SIN")
					.banco(Integer.valueOf(pBeneficiario.getEntidadFinancieraId()))
					.cuenta(pBeneficiario.getCuentaBanco()).build();

			String vEstadoCuenta = "";
			var vRespuestaConsultaCuenta = client.consultaCuentaBancaria(vSolicitudConsultaCuentaBancario, pToken);
			if (vRespuestaConsultaCuenta.isEstado()) {
				vEstadoCuenta = vRespuestaConsultaCuenta.getResultadoObjeto().getEstadoCuenta();
				vRespuesta.setResultadoObjeto(vEstadoCuenta);
				vRespuesta.setOk(true);
			} else {
				vRespuesta.setOk(false);
				vRespuesta.setResultadoObjeto(vEstadoCuenta);
				vRespuesta.setMensajes(mapErrorsSigep(vRespuestaConsultaCuenta.getResultadoObjeto().getErrores()));
			}

		} catch (Exception ex) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
					EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO).set("Sigep"));
			ex.printStackTrace();
		}

		return vRespuesta;

	}

	private List<StrMensajeAplicacionDto> mapErrorsSigep(List<ErrorDto> vErrores) {
		List<StrMensajeAplicacionDto> vMensajes = new ArrayList<>();

		vErrores.forEach(fila -> {
			StrMensajeAplicacionDto vMensaje = new StrMensajeAplicacionDto();
			vMensaje.setCodigo(0);
			vMensaje.setDescripcion("Error Servicio SIGEP - " + fila.getMensaje());
			vMensaje.setDescripcionUi("Error Servicio SIGEP - " + fila.getCausa());
			vMensajes.add(vMensaje);
		});

		return vMensajes;
	}
}

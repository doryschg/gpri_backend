package bo.gob.sin.sre.gpri.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.gob.sin.sit.api.sigep.models.dtos.SitMensajeServiciosDto;
import bo.gob.sin.sre.gpri.ContextoSeguridad;
import bo.gob.sin.sre.gpri.client.IBeneficiarioRestClient;
import bo.gob.sin.sre.gpri.client.INumeroOrdenRestClient;
import bo.gob.sin.sre.gpri.client.IPersonaNaturalRestclient;
import bo.gob.sin.sre.gpri.domain.IBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.domain.ICambiosModalidadDomain;
import bo.gob.sin.sre.gpri.domain.ICuentaBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.domain.IPersonaBeneficiarioDomain;
import bo.gob.sin.sre.gpri.domain.ISalarioBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.dto.*;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumEstadoCambioModalidadPago;
import bo.gob.sin.sre.gpri.dto.enumerador.EnumTipoModalidadPagoBeneficiario;
import bo.gob.sin.sre.gpri.dto.enumerador.sigep.ConstantesSigep;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHBeneficiarios;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHCuentasBeneficiario;
import bo.gob.sin.sre.gpri.model.SreTsfeRivHSalarioBeneficiario;
import bo.gob.sin.sre.gpri.model.SreTsfeRivPersonas;
import bo.gob.sin.sre.gpri.service.IPersonaBeneficiarioService;
import bo.gob.sin.sre.gpri.sit.service.ISitSigepService;
import bo.gob.sin.str.cmsj.mapl.dto.StrMensajeAplicacionDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sit.api.sigep.models.builders.ConsultaBeneficiarioBuilder;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudConsultaBeneficiarioDto;
import bo.gob.sin.sit.client.sigep.SigepRestClient;
import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.domain.IBeneficiarioDomain;
import bo.gob.sin.sre.gpri.model.SreTsfeRivBeneficiarios;
import bo.gob.sin.sre.gpri.model.SreTsfeRivCambiosModalidad;
import bo.gob.sin.sre.gpri.service.IBeneficiarioService;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
@Transactional("transactionManager")
public class BeneficiarioServiceImpl implements IBeneficiarioService {
	private static final Logger LOG = LoggerFactory.getLogger(BeneficiarioServiceImpl.class);
	@Autowired
	private IBeneficiarioDomain beneficiarioDomain;

	@Autowired
	private IBeneficiarioHistoricoDomain beneficiarioHistoricoDomain;

	@Autowired
	private ICuentaBeneficiarioHistoricoDomain cuentaBeneficiarioHistoricoDomain;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Autowired
	private IPersonaNaturalRestclient personaNaturalRestclient;

	@Autowired
	private ISitSigepService serviceSegip;

	@Autowired
	private IPersonaBeneficiarioDomain personaBenDomain;

	@Autowired
	private ISalarioBeneficiarioHistoricoDomain salarioHDomain;

	@Autowired
	private INumeroOrdenRestClient numeroOrdenRestclient;

	@Autowired
	private IBeneficiarioRestClient beneficiarioRestclient;

	@Autowired
	private ICambiosModalidadDomain cambiosModalidadDomain;

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> insertarBeneficiario(
			BeneficiarioPersonaRivDto pBeneficiario) {

		ContextoSeguridad contexto = new ContextoSeguridad();

		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = new ResultadoGenericoDto<>();
		vResultado.setOk(false);
		try {
			if (pBeneficiario != null) {
				pBeneficiario.setFormatoNombreId(pBeneficiario.getFormatoNombreId()==null?"NUAC":pBeneficiario.getFormatoNombreId());
				if (pBeneficiario.getBeneficiarioId() == 0) {
					pBeneficiario.setEntidadFinancieraId("");
					pBeneficiario.setTipoCuentaBancariaId("A");
					pBeneficiario.setCuentaBanco("");
					pBeneficiario.setMonedaId("");
					pBeneficiario.setDistritoCuentaId(0);
					pBeneficiario.setEstadoCuentaBancariaId("INX");
					pBeneficiario.setPagoAutomaticoId("N");

					pBeneficiario.setEstadoBeneficiarioId("P");
					pBeneficiario.setFechaDesde(LocalDate.now());
					pBeneficiario.setFechaHasta(null);
					pBeneficiario.setFechaRegistro(LocalDateTime.now());
					pBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
					pBeneficiario.setUsuarioRegistroId(contexto.obtener().getId());
					pBeneficiario.setUsuarioUltimaModificacionId(contexto.obtener().getId());
					pBeneficiario.setEstadoId("AC");
					if (pBeneficiario.getPersonaBeneficiario().getCodigoComplementario() != null)
						if (pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals("")
								|| pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals(" ")
								|| pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals("null")) {
							pBeneficiario.getPersonaBeneficiario().setCodigoComplementario(null);
						}
					ResultadoGenericoDto<BeneficiarioPersonaRivDto> vConsultaBeneficiario = beneficiarioRestclient
							.obtenerBeneficiarioPorNroDocumentoComplemento(
									pBeneficiario.getPersonaBeneficiario().getNumeroDocumento(),
									pBeneficiario.getPersonaBeneficiario().getCodigoComplementario());
					if (!vConsultaBeneficiario.isOk() && vConsultaBeneficiario.getResultadoObjeto() == null)

					{
						pBeneficiario.getPersonaBeneficiario().setFechaRegistro(LocalDateTime.now());
						pBeneficiario.getPersonaBeneficiario().setFechaUltimaModificacion(LocalDateTime.now());
						pBeneficiario.getPersonaBeneficiario().setUsuarioRegistroId(contexto.obtener().getId());
						pBeneficiario.getPersonaBeneficiario()
								.setUsuarioUltimaModificacionId(contexto.obtener().getId());
						pBeneficiario.getPersonaBeneficiario().setEstadoId("AC");
						ModelMapper vBeneficiarioMapper = new ModelMapper();
						vBeneficiarioMapper.getConfiguration().setAmbiguityIgnored(true);
						PersonaNaturalDto vNuevaPErsonaNatural = new PersonaNaturalDto();
						vNuevaPErsonaNatural = vNuevaPErsonaNatural
								.convertToPerBeneficiario(pBeneficiario.getPersonaBeneficiario());

						if (pBeneficiario.getPersonaBeneficiario().getPersonaId() == 0
								&& pBeneficiario.getPersonaBeneficiario().getOrigenId().equals("BEN")) {
							// MEFP
							ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultadoSigep = serviceSegip
									.registroBeneficiarioSIGEP(pBeneficiario, contexto.obtener().getToken());
							LOG.info("beneficiario {}", vResultadoSigep.toString());
							if (vResultadoSigep.isOk()) {
								// CODIGO PERSONAS (insertar persona,insertar persona beneficiario, generar su
								// código)

								PersonaNaturalDto vPersonaNaturalAjustada=new PersonaNaturalDto();
								vPersonaNaturalAjustada=vNuevaPErsonaNatural;
								if(vPersonaNaturalAjustada.getPrimerApellido()==null||vPersonaNaturalAjustada.getPrimerApellido().equals("")
								||vPersonaNaturalAjustada.getPrimerApellido().equals(" "))
								{
									vPersonaNaturalAjustada.setPrimerApellido(".");
								}
								var vRespuestaPersona = personaNaturalRestclient
										.insertarPersonaNatural(vPersonaNaturalAjustada);

								if (vRespuestaPersona.isOk()) {
									// TODO insertar persona beneficiaria
									SreTsfeRivPersonas vPersonaBeneficiariaEntidad = vBeneficiarioMapper
											.map(pBeneficiario.getPersonaBeneficiario(), SreTsfeRivPersonas.class);
									vPersonaBeneficiariaEntidad
											.setPersonaId(vRespuestaPersona.getResultadoLista().get(0).getPersonaId());
									vPersonaBeneficiariaEntidad.setEstadoPerBeneficiarioId("H");
									vPersonaBeneficiariaEntidad.setFechaDesde(LocalDate.now());
									SreTsfeRivPersonas vPersonaBeneficiariaEntidadRespuesta = personaBenDomain
											.insertarPersonaBeneficiario(vPersonaBeneficiariaEntidad);
									// TODO generar codigo beneficiario
									vNuevaPErsonaNatural
											.setPersonaId(vRespuestaPersona.getResultadoLista().get(0).getPersonaId());
									ResultadoGenericoDto<RespuestaCodigoPersonaDto> vResultadoCodigo = personaNaturalRestclient
											.generarCodigoPersona(vRespuestaPersona.getResultadoLista().get(0));
									LOG.info("vResultadoCodigo {}", vResultadoCodigo.toString());
									if (vResultadoCodigo.isOk()) {
										pBeneficiario.setCodigoPersona(
												vResultadoCodigo.getResultadoObjeto().getCodigoPersona());
										// INICIO INSERCION BENEFICIARIO
										SreTsfeRivBeneficiarios vBeneficiarioEntidad = vBeneficiarioMapper
												.map(pBeneficiario, SreTsfeRivBeneficiarios.class);
										vBeneficiarioEntidad.setPersonaId(vPersonaBeneficiariaEntidad.getPersonaId());
										SreTsfeRivBeneficiarios vBeneficiarioRegistrado = beneficiarioDomain
												.insertarBeneficiario(vBeneficiarioEntidad);
										BeneficiarioPersonaRivDto vBeneficiarioRegistradoDto = vBeneficiarioMapper
												.map(vBeneficiarioRegistrado, BeneficiarioPersonaRivDto.class);

										SreTsfeRivHSalarioBeneficiario vSalarios = new SreTsfeRivHSalarioBeneficiario();
										vSalarios.setBeneficiarioId(vBeneficiarioRegistradoDto.getBeneficiarioId());
										vSalarios.setFechaDesde(LocalDate.now());
										vSalarios.setFechaRegistro(LocalDateTime.now());
										vSalarios.setFechaUltimaModificacion(LocalDateTime.now());
										vSalarios
												.setGestionPrimerSalario(pBeneficiario.getGestionPrimerSalario() != null
														? pBeneficiario.getGestionPrimerSalario()
														: 0);
										vSalarios
												.setPeriodoPrimerSalario(pBeneficiario.getPeriodoPrimerSalario() != null
														? pBeneficiario.getPeriodoPrimerSalario()
														: 0);
										vSalarios.setPrimerSalario(pBeneficiario.getPrimerSalario());
										vSalarios.setGestionSegundoSalario(
												pBeneficiario.getGestionSegundoSalario() != null
														? pBeneficiario.getGestionSegundoSalario()
														: 0);
										vSalarios.setPeriodoSegundoSalario(
												pBeneficiario.getPeriodoSegundoSalario() != null
														? pBeneficiario.getPeriodoSegundoSalario()
														: 0);
										vSalarios.setSegundoSalario(pBeneficiario.getSegundoSalario());
										vSalarios
												.setGestionTercerSalario(pBeneficiario.getGestionTercerSalario() != null
														? pBeneficiario.getGestionTercerSalario()
														: 0);
										vSalarios
												.setPeriodoTercerSalario(pBeneficiario.getPeriodoTercerSalario() != null
														? pBeneficiario.getPeriodoTercerSalario()
														: 0);
										vSalarios.setTercerSalario(pBeneficiario.getTercerSalario());
										vSalarios.setUsuarioRegistroId(pBeneficiario.getUsuarioRegistroId());
										vSalarios.setUsuarioUltimaModificacionId(
												pBeneficiario.getUsuarioUltimaModificacionId());
										vSalarios.setEstadoTotalSalarioId("H");
										vSalarios.setTotalSalario(pBeneficiario.getTotalSalario());
										vSalarios.setEstadoId("AC");

										salarioHDomain.guardarHistoricoSalario(vSalarios);

										vResultado.setOk(true);
										vResultado.addMensaje(mensajesDomain
												.getMensaje(EnumSubsistema.RECAUDACIONES,
														EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
												.set("Beneficiario"));
										vResultado.setResultadoObjeto(vBeneficiarioRegistradoDto);
									} else {
										vResultado.setOk(false);
										vResultado.setMensajes(vResultadoCodigo.getMensajes());
										vResultado.setResultadoObjeto(null);
									}
								} else {
									vResultado.setMensajes(vRespuestaPersona.getMensajes());
									vResultado.setResultadoObjeto(null);
								}
							} else {
								vResultado.setOk(false);
								vResultado.setMensajes(vResultadoSigep.getMensajes());
							}
						} else {
							// MEFP
							ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultadoSigep = serviceSegip
									.registroBeneficiarioSIGEP(pBeneficiario, contexto.obtener().getToken());
							if (vResultadoSigep.isOk()) {
								// CODIGO PERSONAS (editar persona,generar su código si no tiene)
								// TODO editar persona y generar codigo beneficiario
								SreTsfeRivPersonas vPersonaBeneficiariaEntidad = vBeneficiarioMapper
										.map(pBeneficiario.getPersonaBeneficiario(), SreTsfeRivPersonas.class);
								vPersonaBeneficiariaEntidad.setFechaDesde(LocalDate.now());
								vPersonaBeneficiariaEntidad.setEstadoPerBeneficiarioId("H");
								SreTsfeRivPersonas vPersonaBeneficiariaEntidadRespuesta = personaBenDomain
										.insertarPersonaBeneficiario(vPersonaBeneficiariaEntidad);
								// TODO generar codigo beneficiario

								ResultadoGenericoDto<RespuestaCodigoPersonaDto> vResultadoCodigo = personaNaturalRestclient
										.generarCodigoPersona(vNuevaPErsonaNatural);

								if (vResultadoCodigo.isOk()) {
									Long vCodPersona = vResultadoCodigo.getResultadoObjeto().getCodigoPersona()
											.longValue();
									pBeneficiario.setCodigoPersona(vCodPersona);
									// INICIO INSERCION BENEFICIARIO
									SreTsfeRivBeneficiarios vBeneficiarioEntidad = vBeneficiarioMapper
											.map(pBeneficiario, SreTsfeRivBeneficiarios.class);
									SreTsfeRivBeneficiarios vBeneficiarioRegistrado = beneficiarioDomain
											.insertarBeneficiario(vBeneficiarioEntidad);
									BeneficiarioPersonaRivDto vBeneficiarioRegistradoDto = vBeneficiarioMapper
											.map(vBeneficiarioRegistrado, BeneficiarioPersonaRivDto.class);

									SreTsfeRivHSalarioBeneficiario vSalarios = new SreTsfeRivHSalarioBeneficiario();
									vSalarios.setBeneficiarioId(vBeneficiarioRegistradoDto.getBeneficiarioId());
									vSalarios.setFechaDesde(LocalDate.now());
									vSalarios.setFechaRegistro(LocalDateTime.now());
									vSalarios.setFechaUltimaModificacion(LocalDateTime.now());
									vSalarios.setGestionPrimerSalario(pBeneficiario.getGestionPrimerSalario() != null
											? pBeneficiario.getGestionPrimerSalario()
											: 0);
									vSalarios.setPeriodoPrimerSalario(pBeneficiario.getPeriodoPrimerSalario() != null
											? pBeneficiario.getPeriodoPrimerSalario()
											: 0);
									vSalarios.setPrimerSalario(pBeneficiario.getPrimerSalario());
									vSalarios.setGestionSegundoSalario(pBeneficiario.getGestionSegundoSalario() != null
											? pBeneficiario.getGestionSegundoSalario()
											: 0);
									vSalarios.setPeriodoSegundoSalario(pBeneficiario.getPeriodoSegundoSalario() != null
											? pBeneficiario.getPeriodoSegundoSalario()
											: 0);
									vSalarios.setSegundoSalario(pBeneficiario.getSegundoSalario());
									vSalarios.setGestionTercerSalario(pBeneficiario.getGestionTercerSalario() != null
											? pBeneficiario.getGestionTercerSalario()
											: 0);
									vSalarios.setPeriodoTercerSalario(pBeneficiario.getPeriodoTercerSalario() != null
											? pBeneficiario.getPeriodoTercerSalario()
											: 0);
									vSalarios.setTercerSalario(pBeneficiario.getTercerSalario());
									vSalarios.setUsuarioRegistroId(pBeneficiario.getUsuarioRegistroId());
									vSalarios.setUsuarioUltimaModificacionId(
											pBeneficiario.getUsuarioUltimaModificacionId());
									vSalarios.setEstadoTotalSalarioId("H");
									vSalarios.setTotalSalario(pBeneficiario.getTotalSalario());
									vSalarios.setEstadoId("AC");

									salarioHDomain.guardarHistoricoSalario(vSalarios);

									vResultado.setOk(true);
									vResultado.addMensaje(mensajesDomain
											.getMensaje(EnumSubsistema.RECAUDACIONES,
													EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
											.set("Beneficiario"));
									vResultado.setResultadoObjeto(vBeneficiarioRegistradoDto);
								}

							} else {
								vResultado.setOk(false);
								vResultado.setMensajes(vResultadoSigep.getMensajes());
							}
						}
					} else {
						vResultado.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_BENEFICIARIO_YA_REGISTRADO)
								.set(" ya cuenta con beneficiario registrado "));
						vResultado.setResultadoObjeto(null);
					}
				} else {
					vResultado.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
							.set(" Sin datos de Beneficiario "));
					vResultado.setResultadoObjeto(null);
				}

			} else {
				vResultado.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_BENEFICIARIO_YA_REGISTRADO)
						.set(" Beneficiario ya Registrado "));
				vResultado.setResultadoObjeto(null);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogExcepcion.registrar(e, LOG, MethodSign.build(pBeneficiario));
			LOG.info("Excepcion {}", e.toString());
			vResultado.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
					.set("guardar Beneficiario"));
		}

		return vResultado;
	}

	// TODO Update beneficiario - SreTsfeRivBeneficiarios
	public ResultadoGenericoDto<String> updateBeneficiario(BeneficiarioDto pBeneficiario) {
		SreTsfeRivBeneficiarios entityBeneficiario = beneficiarioDomain
				.buscarBeneficiarioId(pBeneficiario.getBeneficiarioId());

		// entityBeneficiario.setBeneficiarioId(pBeneficiario.seqBeneficiarioNb);
		// entityBeneficiario.setPersonaId(personaIdNb);
		entityBeneficiario.setFormatoNombreId(pBeneficiario.getFormatoNombreId());
		entityBeneficiario.setPaisId(pBeneficiario.getPaisId());
		entityBeneficiario.setDepartamentoId(pBeneficiario.getDepartamentoId());
		entityBeneficiario.setAlcaldiaId(pBeneficiario.getAlcaldiaId());
		entityBeneficiario.setDireccion(pBeneficiario.getDireccion());
		entityBeneficiario.setTipoBeneficiarioId(pBeneficiario.getTipoBeneficiarioId());
		entityBeneficiario.setNuaCua(pBeneficiario.getNuaCua());
		entityBeneficiario.setTotalSalario(pBeneficiario.getTotalSalario());
		entityBeneficiario.setEntidadFinancieraId(pBeneficiario.getEntidadFinancieraId());
		entityBeneficiario.setTipoCuentaBancariaId(pBeneficiario.getTipoCuentaBancariaId());
		entityBeneficiario.setCuentaBanco(pBeneficiario.getCuentaBanco());
		entityBeneficiario.setMonedaId(pBeneficiario.getMonedaId());
		entityBeneficiario.setDistritoCuentaId(pBeneficiario.getDistritoCuentaId());
		entityBeneficiario.setEstadoCuentaBancariaId(pBeneficiario.getEstadoCuentaBancariaId());
		entityBeneficiario.setPagoAutomaticoId(pBeneficiario.getPagoAutomaticoId());
		entityBeneficiario.setEstadoBeneficiarioId(pBeneficiario.getEstadoBeneficiarioId());
		entityBeneficiario.setFechaDesde(pBeneficiario.getFechaDesde());
		entityBeneficiario.setFechaHasta(pBeneficiario.getFechaHasta());
		entityBeneficiario.setIdBeneficiarioSigep(pBeneficiario.getIdBeneficiarioSigep());
		entityBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());

		ResultadoGenericoDto<String> vResultado = new ResultadoGenericoDto<>();

		SreTsfeRivBeneficiarios respuesta = beneficiarioDomain.updateBeneficiario(entityBeneficiario);
		if (respuesta == null) {
			vResultado.setOk(false);
			vResultado
					.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_REGISTRO_CONSOLIDACION)
							.set("updateBeneficiario"));
		} else {
			vResultado.setOk(true);
			vResultado.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO_CONSOLIDACION)
					.set("updateBeneficiario"));
		}

		return vResultado;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> guardarCuentaBeneficiario(
			RegistroCuentaBeneficiarioDto pRegistroCuenta) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<>();
		try {
			SreTsfeRivBeneficiarios vBeneficiarioRecuperadoEntidad = beneficiarioDomain
					.obtenerBeneficiarioPorId(pRegistroCuenta.getBeneficiarioId());
			ContextoSeguridad contexto = new ContextoSeguridad();
			if (vBeneficiarioRecuperadoEntidad != null) {
				vBeneficiarioRecuperadoEntidad.setNuaCua(pRegistroCuenta.getNuaCua());
				vBeneficiarioRecuperadoEntidad.setEntidadFinancieraId(pRegistroCuenta.getEntidadFinancieraId());
				vBeneficiarioRecuperadoEntidad.setTipoCuentaBancariaId(pRegistroCuenta.getTipoCuentaBancariaId());
				vBeneficiarioRecuperadoEntidad.setCuentaBanco(pRegistroCuenta.getCuentaBanco());
				vBeneficiarioRecuperadoEntidad.setDistritoCuentaId(pRegistroCuenta.getDistritoCuentaId());
				// vBeneficiarioRecuperadoEntidad.setEstadoCuentaBancariaId(pRegistroCuenta.getEstadoCuentaBancariaId());
				vBeneficiarioRecuperadoEntidad.setPagoAutomaticoId(pRegistroCuenta.getPagoAutomaticoId());
				ModelMapper vBeneficiarioMapper = new ModelMapper();
				BeneficiarioPersonaRivDto vBeneficiario = vBeneficiarioMapper.map(vBeneficiarioRecuperadoEntidad,
						BeneficiarioPersonaRivDto.class);
				// TODO adicionar consumo servicio mefp y acciones pertinentes
				ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultadoSigep = serviceSegip
						.registroCuentaBeneficiarioSIGEP(vBeneficiario, contexto.obtener().getToken());
				if (vResultadoSigep.isOk()) {
					vRespuesta.setOk(true);
					// TODO ajustar modificacion beneficiario
					vBeneficiarioRecuperadoEntidad
							.setNumeroOrden(numeroOrdenRestclient.obtenerNumeroOrdenDeclaracion().getResultadoObjeto());
					vBeneficiarioRecuperadoEntidad.setEstadoCuentaBancariaId(
							vResultadoSigep.getResultadoObjeto().getEstadoCuentaBancariaId());
					vBeneficiarioRecuperadoEntidad.setMonedaId(vResultadoSigep.getResultadoObjeto().getMonedaId());
					vBeneficiarioRecuperadoEntidad.setGestionRegistro(LocalDate.now().getYear());
					beneficiarioDomain.updateBeneficiario(vBeneficiarioRecuperadoEntidad);
					vBeneficiario.setNumeroOrden(vBeneficiarioRecuperadoEntidad.getNumeroOrden());
					vBeneficiario.setGestionRegistro(LocalDate.now().getYear());

					ResultadoGenericoDto<Boolean> vResultadoDatosAdi = serviceSegip
							.registroDatosAdicionalesBeneficiarioSIGEP(vBeneficiario, contexto.obtener().getToken());
					if (vResultadoDatosAdi.isOk()) {
						// TODO CAMBIAR MENSAJE DE OK
						vRespuesta
								.addMensaje(mensajesDomain
										.getMensaje(EnumSubsistema.RECAUDACIONES,
												EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
										.set("Beneficiario"));
						vRespuesta.setResultadoObjeto(vBeneficiario);
					} else {
						vRespuesta.setOk(false);
						vRespuesta.setMensajes(vResultadoDatosAdi.getMensajes());
					}

				} else {
					vRespuesta.setOk(false);
					vRespuesta.setMensajes(vResultadoSigep.getMensajes());
				}

			} else {
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
						.set("Beneficiario"));
			}

		} catch (Exception e) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
					.set("Beneficiario"));
			LOG.info("Excepcion {}", e.toString());
		}
		return vRespuesta;
	}

	private List<StrMensajeAplicacionDto> mapErrorsSigep(List<SitMensajeServiciosDto> vEntidadLista) {
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
		List<StrMensajeAplicacionDto> vMensajes = new ArrayList<>();
		vMensajes = vEntityDtoMapper.map(vEntidadLista, new TypeToken<List<StrMensajeAplicacionDto>>() {
		}.getType());
		return vMensajes;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarBeneficiario(
			BeneficiarioPersonaRivDto pBeneficiario) {
		ContextoSeguridad contexto = new ContextoSeguridad();

		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = new ResultadoGenericoDto<>();
		vResultado.setOk(false);
		try {
			if (pBeneficiario != null) {
				pBeneficiario.setFechaRegistro(LocalDateTime.now());
				pBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
				pBeneficiario.setUsuarioRegistroId(contexto.obtener().getId());
				pBeneficiario.setUsuarioUltimaModificacionId(contexto.obtener().getId());
				pBeneficiario.setEstadoId("AC");
				pBeneficiario.getPersonaBeneficiario().setFechaRegistro(LocalDateTime.now());
				pBeneficiario.getPersonaBeneficiario().setFechaUltimaModificacion(LocalDateTime.now());
				pBeneficiario.getPersonaBeneficiario().setUsuarioRegistroId(contexto.obtener().getId());
				pBeneficiario.getPersonaBeneficiario().setUsuarioUltimaModificacionId(contexto.obtener().getId());
				pBeneficiario.getPersonaBeneficiario().setEstadoId("AC");

				// MEFP
				ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultadoSigep = serviceSegip
						.modificarBeneficiarioSIGEP(pBeneficiario, contexto.obtener().getToken());

				LOG.info("beneficiario {}", vResultadoSigep.toString());
				if (vResultadoSigep.isOk()) {
					ModelMapper vBeneficiarioMapper = new ModelMapper();
					vBeneficiarioMapper.getConfiguration().setAmbiguityIgnored(true);
					SreTsfeRivPersonas vPersonaBeneficiariaEntidad = personaBenDomain
							.obtenerPorPersonaId(pBeneficiario.getPersonaId());

					vPersonaBeneficiariaEntidad.setCelular(pBeneficiario.getPersonaBeneficiario().getCelular());
					vPersonaBeneficiariaEntidad.setGeneroId(pBeneficiario.getPersonaBeneficiario().getGeneroId());
					vPersonaBeneficiariaEntidad
							.setApellidoCasada(pBeneficiario.getPersonaBeneficiario().getApellidoCasada());
					vPersonaBeneficiariaEntidad
							.setEstadoCivilId(pBeneficiario.getPersonaBeneficiario().getEstadoCivilId());
					vPersonaBeneficiariaEntidad
							.setCorreoElectronico(pBeneficiario.getPersonaBeneficiario().getCorreoElectronico());
					vPersonaBeneficiariaEntidad.setCelular(pBeneficiario.getPersonaBeneficiario().getCelular());
					vPersonaBeneficiariaEntidad
							.setTelefonoReferencia1(pBeneficiario.getPersonaBeneficiario().getTelefonoReferencia1());
					vPersonaBeneficiariaEntidad
							.setLugarExpedicionId(pBeneficiario.getPersonaBeneficiario().getLugarExpedicionId());
					if (pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals("")
							|| pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals("null")
							|| pBeneficiario.getPersonaBeneficiario().getCodigoComplementario().equals(" ")) {
						pBeneficiario.getPersonaBeneficiario().setCodigoComplementario(null);
					}

					vPersonaBeneficiariaEntidad
							.setCodigoComplementario(pBeneficiario.getPersonaBeneficiario().getCodigoComplementario());
					personaBenDomain.updatePersonaBeneficiario(vPersonaBeneficiariaEntidad);

					// ACTUALIZACION BENEFICIARIO - HISTORICO
					SreTsfeRivBeneficiarios vBeneficiarioRegistroActual = beneficiarioDomain
							.buscarUltimoRegistroVigenteBeneficiarioId(pBeneficiario.getBeneficiarioId());
					vBeneficiarioRegistroActual.setDireccion(pBeneficiario.getDireccion());
					vBeneficiarioRegistroActual.setDepartamentoId(pBeneficiario.getDepartamentoId());
					vBeneficiarioRegistroActual.setAlcaldiaId(pBeneficiario.getAlcaldiaId());
					vBeneficiarioRegistroActual.setFormatoNombreId(pBeneficiario.getFormatoNombreId());
					vBeneficiarioRegistroActual.setUsuarioUltimaModificacionId(contexto.obtener().getId());
					if (pBeneficiario.getNumeroOrden() != null) {
						vBeneficiarioRegistroActual.setNumeroOrden(pBeneficiario.getNumeroOrden());
					}

					SreTsfeRivBeneficiarios vBeneficiarioRegistrado = beneficiarioDomain
							.updateBeneficiario(vBeneficiarioRegistroActual);

					if (vBeneficiarioRegistroActual.getPagoAutomaticoId()
							.equals(EnumTipoModalidadPagoBeneficiario.MANUAL.getId())
							&& pBeneficiario.getPagoAutomaticoId()
									.equals(EnumTipoModalidadPagoBeneficiario.AUTOMATICO.getId())) {
						SreTsfeRivCambiosModalidad vCambioModalidad = new SreTsfeRivCambiosModalidad();
						vCambioModalidad.setPagoAutomaticoId(pBeneficiario.getPagoAutomaticoId());
						vCambioModalidad.setBeneficiarioId(pBeneficiario.getBeneficiarioId());
						vCambioModalidad.setEstadoCambioId(EnumEstadoCambioModalidadPago.PENDIENTE.getId());
						vCambioModalidad.setUsuarioRegistroId(contexto.obtener().getId());
						vCambioModalidad = cambiosModalidadDomain.insertar(vCambioModalidad);
					}

					String vJsonBeneficiario = "";
					ObjectMapper mapper = new ObjectMapper();
					vJsonBeneficiario = mapper.writeValueAsString(vBeneficiarioRegistroActual);
					SreTsfeRivHBeneficiarios vHistoricoBeneficiario = new SreTsfeRivHBeneficiarios();
					vHistoricoBeneficiario.setContenidoJs(vJsonBeneficiario);
					vHistoricoBeneficiario.setBeneficiarioId(pBeneficiario.getBeneficiarioId());
					vHistoricoBeneficiario.setUsuarioRegistroId(pBeneficiario.getUsuarioRegistroId());
					vHistoricoBeneficiario
							.setUsuarioUltimaModificacionId(pBeneficiario.getUsuarioUltimaModificacionId());
					vHistoricoBeneficiario.setFechaRegistro(LocalDateTime.now());
					vHistoricoBeneficiario.setFechaUltimaModificacion(LocalDateTime.now());
					vHistoricoBeneficiario.setTipoTransaccionId("ACT");
					vHistoricoBeneficiario.setEstadoId("AC");
					beneficiarioHistoricoDomain.insertarHistoricoBeneficiario(vHistoricoBeneficiario);

					// SALARIOS - HISTORICOS
					SreTsfeRivHSalarioBeneficiario vSalarios = new SreTsfeRivHSalarioBeneficiario();
					vSalarios.setBeneficiarioId(pBeneficiario.getBeneficiarioId());
					vSalarios.setFechaDesde(LocalDate.now());
					vSalarios.setFechaRegistro(LocalDateTime.now());
					vSalarios.setFechaUltimaModificacion(LocalDateTime.now());
					vSalarios.setGestionPrimerSalario(
							pBeneficiario.getGestionPrimerSalario() != null ? pBeneficiario.getGestionPrimerSalario()
									: 0);
					vSalarios.setPeriodoPrimerSalario(
							pBeneficiario.getPeriodoPrimerSalario() != null ? pBeneficiario.getPeriodoPrimerSalario()
									: 0);
					vSalarios.setPrimerSalario(pBeneficiario.getPrimerSalario());
					vSalarios.setGestionSegundoSalario(
							pBeneficiario.getGestionSegundoSalario() != null ? pBeneficiario.getGestionSegundoSalario()
									: 0);
					vSalarios.setPeriodoSegundoSalario(
							pBeneficiario.getPeriodoSegundoSalario() != null ? pBeneficiario.getPeriodoSegundoSalario()
									: 0);
					vSalarios.setSegundoSalario(pBeneficiario.getSegundoSalario());
					vSalarios.setGestionTercerSalario(
							pBeneficiario.getGestionTercerSalario() != null ? pBeneficiario.getGestionTercerSalario()
									: 0);
					vSalarios.setPeriodoTercerSalario(
							pBeneficiario.getPeriodoTercerSalario() != null ? pBeneficiario.getPeriodoTercerSalario()
									: 0);
					vSalarios.setTercerSalario(pBeneficiario.getTercerSalario());
					vSalarios.setUsuarioRegistroId(pBeneficiario.getUsuarioRegistroId());
					vSalarios.setUsuarioUltimaModificacionId(pBeneficiario.getUsuarioUltimaModificacionId());
					vSalarios.setEstadoTotalSalarioId("H");
					vSalarios.setTotalSalario(pBeneficiario.getTotalSalario());
					vSalarios.setEstadoId("AC");
					salarioHDomain.insertarHistoricoSalarioBeneficiario(vSalarios);

					vResultado.setOk(true);
					vResultado.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO).set("Beneficiario"));

					BeneficiarioPersonaRivDto vBeneficiarioRegistradoDto = vBeneficiarioMapper
							.map(vBeneficiarioRegistrado, BeneficiarioPersonaRivDto.class);
					vResultado.setResultadoObjeto(vBeneficiarioRegistradoDto);
				} else {
					vResultado.setOk(false);
					vResultado.setMensajes(vResultadoSigep.getMensajes());
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogExcepcion.registrar(e, LOG, MethodSign.build(pBeneficiario));
			LOG.info("Excepcion {}", e.toString());
			vResultado.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
					.set("guardar Beneficiario"));
		}
		return vResultado;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> modificarCuentaBeneficiario(
			RegistroCuentaBeneficiarioDto pRegistroCuenta) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<>();
		try {

			ContextoSeguridad contexto = new ContextoSeguridad();
			SreTsfeRivBeneficiarios vBeneficiarioRecuperadoEntidad = beneficiarioDomain
					.obtenerBeneficiarioPorId(pRegistroCuenta.getBeneficiarioId());
			BeneficiarioPersonaRivDto vDatosBeneficiario = new BeneficiarioPersonaRivDto();
			vDatosBeneficiario.setBeneficiarioId(pRegistroCuenta.getBeneficiarioId());
			vDatosBeneficiario.setCuentaBanco(pRegistroCuenta.getCuentaBanco());
			vDatosBeneficiario.setIdBeneficiarioSigep(vBeneficiarioRecuperadoEntidad.getIdBeneficiarioSigep());
			vDatosBeneficiario.setEntidadFinancieraId(pRegistroCuenta.getEntidadFinancieraId());
			vDatosBeneficiario.setDistritoCuentaId(pRegistroCuenta.getDistritoCuentaId());
			vDatosBeneficiario.setTipoCuentaBancariaId(pRegistroCuenta.getTipoCuentaBancariaId());

			vDatosBeneficiario.setDepartamentoId(vBeneficiarioRecuperadoEntidad.getDepartamentoId());
			ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultadoSigep = serviceSegip
					.modificarCuentaBeneficiarioSIGEP(vDatosBeneficiario, contexto.obtener().getToken());
			if (vResultadoSigep.isOk()) {
				var vRespuestaDatosAdionales = serviceSegip
						.actualizarDatosAdicionalesBeneficiarioSIGEP(vDatosBeneficiario, contexto.obtener().getToken());
				if (vRespuestaDatosAdionales.isOk()) {
					vBeneficiarioRecuperadoEntidad.setNuaCua(pRegistroCuenta.getNuaCua());
					vBeneficiarioRecuperadoEntidad.setEntidadFinancieraId(pRegistroCuenta.getEntidadFinancieraId());
					vBeneficiarioRecuperadoEntidad.setTipoCuentaBancariaId(pRegistroCuenta.getTipoCuentaBancariaId());
					vBeneficiarioRecuperadoEntidad.setCuentaBanco(pRegistroCuenta.getCuentaBanco());
					vBeneficiarioRecuperadoEntidad.setDistritoCuentaId(pRegistroCuenta.getDistritoCuentaId());
					vBeneficiarioRecuperadoEntidad.setPagoAutomaticoId(pRegistroCuenta.getPagoAutomaticoId());
					ModelMapper vBeneficiarioMapper = new ModelMapper();
					BeneficiarioPersonaRivDto vBeneficiario = vBeneficiarioMapper.map(vBeneficiarioRecuperadoEntidad,
							BeneficiarioPersonaRivDto.class);

					// TODO ajustar modificacion beneficiario
					vBeneficiarioRecuperadoEntidad
							.setNumeroOrden(numeroOrdenRestclient.obtenerNumeroOrdenDeclaracion().getResultadoObjeto());

					vBeneficiarioRecuperadoEntidad.setEstadoCuentaBancariaId(
							vResultadoSigep.getResultadoObjeto().getEstadoCuentaBancariaId());
					vBeneficiarioRecuperadoEntidad.setMonedaId(String.valueOf(ConstantesSigep.MONEDA_EN_BOLIVIANOS));

					vBeneficiarioRecuperadoEntidad.setGestionRegistro(LocalDate.now().getYear());
					beneficiarioDomain.updateBeneficiario(vBeneficiarioRecuperadoEntidad);
					vBeneficiario.setNumeroOrden(vBeneficiarioRecuperadoEntidad.getNumeroOrden());
					vBeneficiario.setGestionRegistro(LocalDate.now().getYear());

					SreTsfeRivHCuentasBeneficiario vHistoricoCuentaBeneficiario = new SreTsfeRivHCuentasBeneficiario();
					vHistoricoCuentaBeneficiario.setCuentaBanco(vBeneficiarioRecuperadoEntidad.getCuentaBanco());
					vHistoricoCuentaBeneficiario
							.setDistritoCuentaId(String.valueOf(vBeneficiarioRecuperadoEntidad.getDistritoCuentaId()));
					vHistoricoCuentaBeneficiario
							.setEntidadFinancieraId(vBeneficiarioRecuperadoEntidad.getEntidadFinancieraId());
					vHistoricoCuentaBeneficiario
							.setEstadoCuentaId(vBeneficiarioRecuperadoEntidad.getEstadoCuentaBancariaId());
					vHistoricoCuentaBeneficiario.setMonedaId(vBeneficiarioRecuperadoEntidad.getMonedaId());
					vHistoricoCuentaBeneficiario.setBeneficiarioId(vBeneficiarioRecuperadoEntidad.getBeneficiarioId());
					vHistoricoCuentaBeneficiario
							.setTipoCuentaId(vBeneficiarioRecuperadoEntidad.getTipoCuentaBancariaId());
					vHistoricoCuentaBeneficiario.setUsuarioRegistroId(contexto.obtener().getId());
					vHistoricoCuentaBeneficiario.setUsuarioUltimaModificacionId(contexto.obtener().getId());
					cuentaBeneficiarioHistoricoDomain.insertarHistoricoBeneficiario(vHistoricoCuentaBeneficiario);
					vRespuesta.setOk(true);
					// TODO CAMBIAR MENSAJE DE OK
					vRespuesta.addMensaje(mensajesDomain.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO).set("Beneficiario"));
					vRespuesta.setResultadoObjeto(vBeneficiario);
				} else {
					vRespuesta.setOk(false);
					vRespuesta.setMensajes(vResultadoSigep.getMensajes());
				}

			} else {
				vRespuesta.setOk(false);
				vRespuesta.setMensajes(vResultadoSigep.getMensajes());
			}

		} catch (Exception e) {
			vRespuesta.setOk(false);
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
					.set("Beneficiario"));
		}
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> habilitarBeneficiario(Long pBeneficiarioId) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<>();
		try {
			SreTsfeRivBeneficiarios vBeneficiarioRecuperadoEntidad = beneficiarioDomain
					.obtenerBeneficiarioPorId(pBeneficiarioId);
			ContextoSeguridad contexto = new ContextoSeguridad();
			if (vBeneficiarioRecuperadoEntidad != null) {
				vBeneficiarioRecuperadoEntidad.setEstadoBeneficiarioId("H");
				ModelMapper vBeneficiarioMapper = new ModelMapper();

				beneficiarioDomain.updateBeneficiario(vBeneficiarioRecuperadoEntidad);
				BeneficiarioPersonaRivDto vBeneficiario = vBeneficiarioMapper.map(vBeneficiarioRecuperadoEntidad,
						BeneficiarioPersonaRivDto.class);
				// TODO CAMBIAR MENSAJE DE OK
				vRespuesta.setOk(true);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.REGISTRO_CORRECTO)
						.set("Beneficiario"));
				vRespuesta.setResultadoObjeto(vBeneficiario);
			} else {
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
						.set("Beneficiario"));
			}

		} catch (Exception e) {
			vRespuesta.setOk(false);
			vRespuesta
					.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_DE_SERVICIO)
							.set("Beneficiario"));
			LOG.info("Excepcion {}", e.toString());
		}
		return vRespuesta;
	}

}

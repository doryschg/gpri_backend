package bo.gob.sin.sre.gpri.query.impl;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.IPersonaBeneficiariosQuery;
import bo.gob.sin.sre.gpri.query.config.RepositoryConfig;
import bo.gob.sin.sre.gpri.query.extractor.BeneficiarioPersonaNaturalExtractor;
import bo.gob.sin.sre.gpri.query.extractor.BeneficiarioPersonaRivExtractor;
import bo.gob.sin.sre.gpri.query.extractor.BeneficiarioPersonaRivExtractorReporte;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaBeneficiariosQueryImpl implements IPersonaBeneficiariosQuery {
	private static final Logger LOG = LoggerFactory.getLogger(PersonaBeneficiariosQueryImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	@Override
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPersonaPorDocuemntoComplemento(String pNuaCua,
			String pNumeroDocumento) {

		ResultadoGenericoDto<BeneficiarioDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioDto>();
		Sql2o sql2o = RepositoryConfig.getSql2o("org.postgresql.Driver",
				env.getRequiredProperty("spring.datasource.url"), env.getRequiredProperty("spring.datasource.username"),
				env.getRequiredProperty("spring.datasource.password"));

		String vQuery = "select \n" + "dependientes_id as \"dependienteId\",\n" + "nua_cua as \"NuaCua\",\n"
				+ "empleador_id as \"empleadorId\",\n" + "primer_nombre  as \"primerNombre\",\n"
				+ "segundo_nombre as \"segundoNombre\",\n" + "apellido_casada as \"apellidoCasada\",\n"
				+ "primer_apellido  as \"apellidoPaterno\",\n" + "segundo_apellido  as \"apellidoMaterno\",\n"
				+ "total_salario as \"totalSalario\",\n"
				+ "departamento_lugar_trabajo as \"departamentoLugarTrabajo\",\n"
				+ "tipo_departamento_id as \"tipoDepartamento\",\n"
				+ "numero_documento_identificacion  as \"numeroDocumentoIdentificacion\"\n"
				+ "from sfi_fiscalizacion.sfi_ain_afps_dependientes \n"
				+ "where nua_cua =:pNua and numero_documento_identificacion =:pNumeroDocumento and estado_id = 'AC'";
		try (Connection vConnection = sql2o.open()) {
			Query query = vConnection.createQuery(vQuery);
			query.addParameter("pNua", pNuaCua).addParameter("pNumeroDocumento", pNumeroDocumento);
			var vLista = query.executeAndFetch(BeneficiarioDto.class);
			if (!vLista.isEmpty()) {
				vRespuesta.setResultadoObjeto(vLista.get(0));
			}
		}

		vRespuesta.setOk(true);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorPersonaId(long pPersonaId) {
		ResultadoGenericoDto<BeneficiarioDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioDto>();

		if (pPersonaId != 0) {
			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,  " + "ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,  " + "ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,  " + "ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,  " + "pn.persona_id as pnPersonaId,  "
					+ "pn.tramite_id as pnTramiteId,  " + "pn.dependencia_registro_id as dependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as dependenciaRegistroDescripcion,  "
					+ "pn.nacionalidad_id as nacionalidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.nacionalidad_id) as nacionalidadDescripcion,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.informacion_referencial as informacionReferencial,  " + "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,  " + "pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,  " + "pn.correo_personal as correoPersonal,  "
					+ "pn.fecha_nacimiento as fechaNacimiento,  "
					+ "pn.estado_correo_personal as estadoCorreoPersonal,  " + "pn.estado_segip as estadoSegip,  "
					+ "pn.profesion_ocupacion_id as profesionOcupacionId,  " + "pn.nacionalizado as nacionalizado,  "
					+ "p.persona_id as pPersonaId,  " + "p.tramite_id as pTramiteId,  "
					+ "p.dependencia_registro_id as pdependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as pdependenciaRegistroDescripcion,  "
					+ "p.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "p.lugar_expedicion_id as plugarExpedicionId,  " + "p.tipo_persona_id as ptipoPersonaId,  "
					+ "p.correo_electronico as pcorreoElectronico,  " + "p.numero_documento as pnumeroDocumento,  "
					+ "p.codigo_complementario as pcodigoComplementario,  " + "p.celular as pcelular,  "
					+ "p.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "p.telefono_referencia_2 as ptelefonoReferencia2,  " + "p.fax as pfax,  "
					+ "p.estado_celular as pestadoCelular  "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, scn_contribuyentes.scn_per_personas_naturales as pn,  "
					+ "scn_contribuyentes.scn_per_personas as p  "
					+ "where ben.persona_id_nb =? and estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id and pn.persona_id =p.persona_id;";
			try {

				List<BeneficiarioDto> vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaNaturalExtractor(),
						pPersonaId);
				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));

						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorPersonaId(long pPersonaId) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		if (pPersonaId > 0) {

			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.provincia_id_nb as provinciaId, "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,   ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where  tipo_clasificador = 'entidad_financiera_riv_id' and codigo_clasificador=ben.entidad_financiera_riv_id_vc) as entidadFinancieraDescripcion, "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,   ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,   ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,    pn.persona_id as pnPersonaId,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,   pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,    pn.fecha_nacimiento as fechaNacimiento,    "
					+ "pn.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.tipo_documento_identidad_id) as ptipoDocumentoIdentidadDescripcion,"
					+ "pn.lugar_expedicion_id as plugarExpedicionId,"
					+ "(select de.nombre from str_transversales.str_ccs_departamentos de where de.departamento_id = pn.lugar_expedicion_id) as plugarExpedicionDescripcion,  "
					+ "pn.tipo_persona_id as ptipoPersonaId,  "
					+ "pn.correo_electronico as pcorreoElectronico,   pn.numero_documento as pnumeroDocumento,  "
					+ "pn.codigo_complementario as pcodigoComplementario,   pn.celular as pcelular,  "
					+ "pn.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "pn.telefono_referencia_2 as ptelefonoReferencia2,   pn.modificado_id_vc as pmodificadoId, "
					+ "pn.origen_riv_id_vc as porigenId ,"
					+ "sa.primer_salario_nb as primerSalario,sa.gestion_primer_salario_nb as gestionPrimerSalario, sa.periodo_primer_salario_nb as periodoPrimerSalario, "
					+ "sa.segundo_salario_nb as segundoSalario,sa.gestion_segundo_salario_nb as gestionSegundoSalario, sa.periodo_segundo_salario_nb as periodoSegundoSalario, "
					+ "sa.tercer_salario_nb as tercerSalario,sa.gestion_tercer_salario_nb as gestionTercerSalario, sa.periodo_tercer_salario_nb as periodoTercerSalario "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, sfe_facturacion.tsfe_riv_personas as pn, sfe_facturacion.tsfe_riv_h_salarios_beneficiario as sa  "
					+ "where ben.persona_id_nb =? and ben.estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id and pn.estado_id = 'AC' "
					+ "and pn.estado_per_beneficiario_id_vc='H' and sa.seq_beneficiario_nb=ben.seq_beneficiario_nb and sa.fecha_hasta_dt isnull and sa.estado_id_vc='AC' and sa.estado_total_salario_id_vc='H';";

			try {

				List<BeneficiarioPersonaRivDto> vLista = jdbcTemplate.query(vQuery,
						new BeneficiarioPersonaRivExtractor(), pPersonaId);
				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vQuery = "select * from sfe_facturacion.tsfe_riv_cambios_modalidad\n"
								+ "where seq_beneficiario_nb = ? and estado_cambio_id_vc = 'PEN' and estado_id_vc = 'AC'";

						RowMapper<Object> vRowMapper = new BeanPropertyRowMapper<Object>(Object.class);

						var vCambiosModalidad = jdbcTemplate.query(vQuery, vRowMapper,
								vLista.get(0).getBeneficiarioId());
						vLista.get(0).setTieneSolicitudCambioModalidadAutomatica(!vCambiosModalidad.isEmpty());
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorNroDocumentoyComplemento(
			String pNroDocumento, String pComplementoDocumento) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		if (pNroDocumento != null) {

			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.provincia_id_nb as provinciaId, "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,   ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where  tipo_clasificador = 'entidad_financiera_riv_id' and codigo_clasificador=ben.entidad_financiera_riv_id_vc) as entidadFinancieraDescripcion, "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,   ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,   ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,    pn.persona_id as pnPersonaId,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,   pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,    pn.fecha_nacimiento as fechaNacimiento,    "
					+ "pn.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.tipo_documento_identidad_id) as ptipoDocumentoIdentidadDescripcion,"
					+ "pn.lugar_expedicion_id as plugarExpedicionId,"
					+ "(select de.nombre from str_transversales.str_ccs_departamentos de where de.departamento_id = pn.lugar_expedicion_id) as plugarExpedicionDescripcion,  "
					+ "pn.tipo_persona_id as ptipoPersonaId,  "
					+ "pn.correo_electronico as pcorreoElectronico,   pn.numero_documento as pnumeroDocumento,  "
					+ "pn.codigo_complementario as pcodigoComplementario,   pn.celular as pcelular,  "
					+ "pn.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "pn.telefono_referencia_2 as ptelefonoReferencia2,   pn.modificado_id_vc as pmodificadoId, "
					+ "pn.origen_riv_id_vc as porigenId , "
					+ "sa.primer_salario_nb as primerSalario,sa.gestion_primer_salario_nb as gestionPrimerSalario, sa.periodo_primer_salario_nb as periodoPrimerSalario, "
					+ "sa.segundo_salario_nb as segundoSalario,sa.gestion_segundo_salario_nb as gestionSegundoSalario, sa.periodo_segundo_salario_nb as periodoSegundoSalario, "
					+ "sa.tercer_salario_nb as tercerSalario,sa.gestion_tercer_salario_nb as gestionTercerSalario, sa.periodo_tercer_salario_nb as periodoTercerSalario "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, sfe_facturacion.tsfe_riv_personas as pn, sfe_facturacion.tsfe_riv_h_salarios_beneficiario as sa  "
					+ "where pn.numero_documento=? and ben.estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id "
					+ "and pn.estado_per_beneficiario_id_vc='H' and pn.fecha_hasta_dt is null and sa.seq_beneficiario_nb=ben.seq_beneficiario_nb and sa.estado_id_vc='AC' and sa.estado_total_salario_id_vc='H'  and sa.fecha_hasta_dt is null ";

			if (pComplementoDocumento == null) {

				vQuery = vQuery + "and pn.codigo_complementario is null;";
			} else {
				vQuery = vQuery + "and pn.codigo_complementario = ? ;";
			}

			try {
				List<BeneficiarioPersonaRivDto> vLista = new ArrayList<>();
				if (pComplementoDocumento != null) {
					vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaRivExtractor(), pNroDocumento,
							pComplementoDocumento);
				}

				else {
					vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaRivExtractor(), pNroDocumento);
				}

				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorId(long pId) {
		ResultadoGenericoDto<BeneficiarioDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioDto>();

		if (pId != 0) {
			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,  " + "ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,  " + "ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,  " + "ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,  " + "pn.persona_id as pnPersonaId,  "
					+ "pn.tramite_id as pnTramiteId,  " + "pn.dependencia_registro_id as dependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as dependenciaRegistroDescripcion,  "
					+ "pn.nacionalidad_id as nacionalidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.nacionalidad_id) as nacionalidadDescripcion,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.informacion_referencial as informacionReferencial,  " + "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,  " + "pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,  " + "pn.correo_personal as correoPersonal,  "
					+ "pn.fecha_nacimiento as fechaNacimiento,  "
					+ "pn.estado_correo_personal as estadoCorreoPersonal,  " + "pn.estado_segip as estadoSegip,  "
					+ "pn.profesion_ocupacion_id as profesionOcupacionId,  " + "pn.nacionalizado as nacionalizado,  "
					+ "p.persona_id as pPersonaId,  " + "p.tramite_id as pTramiteId,  "
					+ "p.dependencia_registro_id as pdependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as pdependenciaRegistroDescripcion,  "
					+ "p.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "p.lugar_expedicion_id as plugarExpedicionId,  " + "p.tipo_persona_id as ptipoPersonaId,  "
					+ "p.correo_electronico as pcorreoElectronico,  " + "p.numero_documento as pnumeroDocumento,  "
					+ "p.codigo_complementario as pcodigoComplementario,  " + "p.celular as pcelular,  "
					+ "p.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "p.telefono_referencia_2 as ptelefonoReferencia2,  " + "p.fax as pfax,  "
					+ "p.estado_celular as pestadoCelular  "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, scn_contribuyentes.scn_per_personas_naturales as pn,  "
					+ "scn_contribuyentes.scn_per_personas as p  "
					+ "where ben.seq_beneficiario_nb =? and estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id and pn.persona_id =p.persona_id;";
			try {

				List<BeneficiarioDto> vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaNaturalExtractor(),
						pId);
				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorCodigoPersona(long pCodigoPersona) {
		ResultadoGenericoDto<BeneficiarioDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioDto>();

		if (pCodigoPersona != 0) {
			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,  " + "ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,  " + "ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,  " + "ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,  " + "pn.persona_id as pnPersonaId,  "
					+ "pn.tramite_id as pnTramiteId,  " + "pn.dependencia_registro_id as dependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as dependenciaRegistroDescripcion,  "
					+ "pn.nacionalidad_id as nacionalidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.nacionalidad_id) as nacionalidadDescripcion,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.informacion_referencial as informacionReferencial,  " + "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,  " + "pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,  " + "pn.correo_personal as correoPersonal,  "
					+ "pn.fecha_nacimiento as fechaNacimiento,  "
					+ "pn.estado_correo_personal as estadoCorreoPersonal,  " + "pn.estado_segip as estadoSegip,  "
					+ "pn.profesion_ocupacion_id as profesionOcupacionId,  " + "pn.nacionalizado as nacionalizado,  "
					+ "p.persona_id as pPersonaId,  " + "p.tramite_id as pTramiteId,  "
					+ "p.dependencia_registro_id as pdependenciaRegistroId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.dependencia_registro_id) as pdependenciaRegistroDescripcion,  "
					+ "p.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "p.lugar_expedicion_id as plugarExpedicionId,  " + "p.tipo_persona_id as ptipoPersonaId,  "
					+ "p.correo_electronico as pcorreoElectronico,  " + "p.numero_documento as pnumeroDocumento,  "
					+ "p.codigo_complementario as pcodigoComplementario,  " + "p.celular as pcelular,  "
					+ "p.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "p.telefono_referencia_2 as ptelefonoReferencia2,  " + "p.fax as pfax,  "
					+ "p.estado_celular as pestadoCelular  "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, scn_contribuyentes.scn_per_personas_naturales as pn,  "
					+ "scn_contribuyentes.scn_per_personas as p  "
					+ "where ben.codigo_persona_nb =? and estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id and pn.persona_id =p.persona_id;";
			try {

				List<BeneficiarioDto> vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaNaturalExtractor(),
						pCodigoPersona);
				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario Codigo Persona"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioCodigoPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorNroDocumentoComplementoyNumeroCuenta(
			String pNroDocumento, String pComplementoDocumento, String pNroCuenta) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		if (pNroDocumento != null) {

			String vQuery = "SELECT ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.provincia_id_nb as provinciaId, "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,   ben.nua_cua_nb as nuaCua,  "
					+ "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where  tipo_clasificador = 'entidad_financiera_riv_id' and codigo_clasificador=ben.entidad_financiera_riv_id_vc) as entidadFinancieraDescripcion, "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "ben.cuenta_banco_vc  as cuentaBanco,   ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,   ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,    pn.persona_id as pnPersonaId,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,   pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,    pn.fecha_nacimiento as fechaNacimiento,    "
					+ "pn.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.tipo_documento_identidad_id) as ptipoDocumentoIdentidadDescripcion, "
					+ "pn.lugar_expedicion_id as plugarExpedicionId, "
					+ "(select de.nombre from str_transversales.str_ccs_departamentos de where de.departamento_id = pn.lugar_expedicion_id) as plugarExpedicionDescripcion,  "
					+ "pn.tipo_persona_id as ptipoPersonaId,  "
					+ "pn.correo_electronico as pcorreoElectronico,   pn.numero_documento as pnumeroDocumento,  "
					+ "pn.codigo_complementario as pcodigoComplementario,   pn.celular as pcelular,  "
					+ "pn.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "pn.telefono_referencia_2 as ptelefonoReferencia2,   pn.modificado_id_vc as pmodificadoId, "
					+ "pn.origen_riv_id_vc as porigenId , "
					+ "sa.primer_salario_nb as primerSalario,sa.gestion_primer_salario_nb as gestionPrimerSalario, sa.periodo_primer_salario_nb as periodoPrimerSalario, "
					+ "sa.segundo_salario_nb as segundoSalario,sa.gestion_segundo_salario_nb as gestionSegundoSalario, sa.periodo_segundo_salario_nb as periodoSegundoSalario, "
					+ "sa.tercer_salario_nb as tercerSalario,sa.gestion_tercer_salario_nb as gestionTercerSalario, sa.periodo_tercer_salario_nb as periodoTercerSalario "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, sfe_facturacion.tsfe_riv_personas as pn, sfe_facturacion.tsfe_riv_h_salarios_beneficiario as sa  "
					+ "where pn.numero_documento=? and ben.cuenta_banco_vc=? and ben.estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id "
					+ "and pn.estado_per_beneficiario_id_vc='H' and pn.fecha_hasta_dt is null and sa.seq_beneficiario_nb=ben.seq_beneficiario_nb "
					+ "and sa.estado_id_vc='AC' and sa.estado_total_salario_id_vc='H'  and sa.fecha_hasta_dt is null";

			if (pComplementoDocumento == null) {

				vQuery = vQuery + " and pn.codigo_complementario is null";
			} else {
				vQuery = vQuery + " and pn.codigo_complementario = ?";
			}

			try {
				List<BeneficiarioPersonaRivDto> vLista = new ArrayList<>();
				if (pComplementoDocumento != null) {
					vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaRivExtractor(), pNroDocumento,
							pNroCuenta, pComplementoDocumento);
				}

				else {
					vLista = jdbcTemplate.query(vQuery, new BeneficiarioPersonaRivExtractor(), pNroDocumento,
							pNroCuenta);
				}

				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario PersonaId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

//	@Override
//	public ResultadoGenericoDto<Boolean> verificarSalario(long pNuaCua, String pNumeroDocumento) {
//		ResultadoGenericoDto<Boolean> vRespuesta = new ResultadoGenericoDto<Boolean>();
//		vRespuesta.setResultadoObjeto(false);
//		ResultadoGenericoDto<AseguradoAFPDto> vAsegurado = this.obtenerAseguradoAFP(pNuaCua, pNumeroDocumento);
//		if (vAsegurado.getResultadoObjeto().getDependienteId() > 0) {
//			vRespuesta.setResultadoObjeto(
//					vAsegurado.getResultadoObjeto().getTotalSalario().compareTo(new BigDecimal(9000)) <= 0);
//		}
//
//		vRespuesta.setOk(true);
//		return vRespuesta;
//	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPersonaPorBeneficiarioId(
			long pBeneficiarioId) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();

		if (pBeneficiarioId > 0) {

			String vQuery = "select ben.seq_beneficiario_nb as beneficiarioId , ben.persona_id_nb as personaId,  "
					+ "ben.codigo_persona_nb as codigoPersona, ben.formato_nombre_id_vc  as formatoNombreId,  "
					+ "ben.pais_id_vc as paisId, ben.departamento_id_nb as departamentoId,  "
					+ "ben.provincia_id_nb as provinciaId, "
					+ "ben.alcaldia_id_nb  as alcaldiaId, ben.direccion_vc  as direccion,  "
					+ "ben.tipo_beneficiario_id_vc as tipoBeneficiarioId,   ben.nua_cua_nb as nuaCua,  "
					+ "ben.nro_orden_nb as numeroOrden, " + "ben.total_salario_nb as totalSalario,  "
					+ "ben.entidad_financiera_riv_id_vc  as entidadFinancieraId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where  tipo_clasificador = 'entidad_financiera_riv_id' and codigo_clasificador=ben.entidad_financiera_riv_id_vc) as entidadFinancieraDescripcion, "
					+ "ben.tipo_cuenta_bancaria_id_vc  as tipoCuentaBancariaId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where  tipo_clasificador = 'tipo_cuenta_bancaria_id' and codigo_clasificador=ben.tipo_cuenta_bancaria_id_vc) as tipoCuentaDescripcion, "
					+ "ben.cuenta_banco_vc  as cuentaBanco,   ben.moneda_id_vc as monedaId,  "
					+ "ben.distrito_cuenta_id_nb as distritoCuentaId,  "
					+ "ben.estado_cuenta_bancaria_id_vc as estadoCuentaBancariaId,  "
					+ "ben.pago_automatico_id_vc  as pagoAutomaticoId,  "
					+ "ben.estado_beneficiario_id_vc  as estadoBeneficiarioId,  "
					+ "ben.fecha_desde_dt as fechaDesde,   ben.fecha_hasta_dt  as fechaHasta,  "
					+ "ben.id_beneficiario_sigep_nb  as idBeneficiarioSigep,  "
					+ "ben.gestion_registro_nb  as gestionRegistro,    pn.persona_id as pnPersonaId,  "
					+ "pn.pais_origen_id as paisOrigenId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.pais_origen_id) as paisOrigenDescripcion,  "
					+ "pn.estado_civil_id as estadoCivilId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.estado_civil_id) as estadoCivilDescripcion,  "
					+ "pn.genero_id as generoId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.genero_id) as generoDescripcion,  "
					+ "pn.nombres as nombres,  "
					+ "pn.primer_apellido as primerApellido,   pn.segundo_apellido as segundoApellido,  "
					+ "pn.apellido_casada as apellidoCasada,    pn.fecha_nacimiento as fechaNacimiento,    "
					+ "pn.tipo_documento_identidad_id as ptipoDocumentoIdentidadId,  "
					+ "(select za.descripcion from str_transversales.str_cps_clasificadores za where za.clasificador_id = pn.tipo_documento_identidad_id) as ptipoDocumentoIdentidadDescripcion,"
					+ "pn.lugar_expedicion_id as plugarExpedicionId,"
					+ "(select de.nombre from str_transversales.str_ccs_departamentos de where de.departamento_id = pn.lugar_expedicion_id) as plugarExpedicionDescripcion,  "
					+ "pn.tipo_persona_id as ptipoPersonaId,  "
					+ "pn.correo_electronico as pcorreoElectronico,   pn.numero_documento as pnumeroDocumento,  "
					+ "pn.codigo_complementario as pcodigoComplementario,   pn.celular as pcelular,  "
					+ "pn.telefono_referencia_1 as ptelefonoReferencia1,  "
					+ "pn.telefono_referencia_2 as ptelefonoReferencia2,   pn.modificado_id_vc as pmodificadoId, "
					+ "pn.origen_riv_id_vc as porigenId ," + "usu.login as nombreUsuario , "
					+ "sa.primer_salario_nb as primerSalario,sa.gestion_primer_salario_nb as gestionPrimerSalario, sa.periodo_primer_salario_nb as periodoPrimerSalario, "
					+ "sa.segundo_salario_nb as segundoSalario,sa.gestion_segundo_salario_nb as gestionSegundoSalario, sa.periodo_segundo_salario_nb as periodoSegundoSalario, "
					+ "sa.tercer_salario_nb as tercerSalario,sa.gestion_tercer_salario_nb as gestionTercerSalario, sa.periodo_tercer_salario_nb as periodoTercerSalario "
					+ "from sfe_facturacion.tsfe_riv_beneficiarios as ben, sfe_facturacion.tsfe_riv_personas as pn, sfe_facturacion.tsfe_riv_h_salarios_beneficiario as sa, sau_usuarios.sau_adm_usuarios as usu "
					+ "where ben.seq_beneficiario_nb =? and ben.estado_id_vc = 'AC' and ben.persona_id_nb =pn.persona_id "
					+ "and pn.persona_id=usu.persona_id and usu.tipo_usuario_id=4689 "
					+ "and pn.estado_per_beneficiario_id_vc='H' and sa.seq_beneficiario_nb=ben.seq_beneficiario_nb and sa.fecha_hasta_dt isnull and sa.estado_id_vc='AC' and sa.estado_total_salario_id_vc='H';";

			try {

				List<BeneficiarioPersonaRivDto> vLista = jdbcTemplate.query(vQuery,
						new BeneficiarioPersonaRivExtractorReporte(), pBeneficiarioId);
				if (vLista.size() > 0) {
					if (vLista.size() == 1) {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.EXITO_CONSULTA)
								.set("Obtener Beneficiario beneficiarioId"));
						vRespuesta.setOk(true);
					} else {
						vRespuesta.setResultadoObjeto(vLista.get(0));
						vRespuesta.addMensaje(mensajesDomain
								.getMensaje(EnumSubsistema.RECAUDACIONES,
										EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_MAS_DE_UN_REGISTRO)
								.set("Obtener Beneficiario beneficiarioId"));
						vRespuesta.setOk(false);
					}

				} else {

					vRespuesta.setResultadoObjeto(null);
					vRespuesta.addMensaje(mensajesDomain
							.getMensaje(EnumSubsistema.RECAUDACIONES,
									EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
							.set("Obtener Beneficiario PersonaId"));
					vRespuesta.setOk(false);
				}

			} catch (Exception e) {
				LogExcepcion.registrar(e, LOG, MethodSign.build(vQuery));
				vRespuesta.setResultadoObjeto(null);
				vRespuesta.setOk(false);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_EJECUCION_CONSULTA)
						.set("Obtener Beneficiario PersonaId"));
				LOG.info("Consulta ejecutada {} error {}", vQuery, e);
			}
		} else {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("ObtenerBeneficiarioPersonaId"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

}

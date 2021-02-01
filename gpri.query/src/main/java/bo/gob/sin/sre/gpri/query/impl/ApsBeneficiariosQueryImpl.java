package bo.gob.sin.sre.gpri.query.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.AseguradoAPSIngresoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.IApsBeneficiariosQuery;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;

@Service
public class ApsBeneficiariosQueryImpl implements IApsBeneficiariosQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	private final int LIMITE_INGRESOS = 9000;

	private static final String TIPO_BENEFICIARIO_DEPENDIENTE = "D";
	private static final String TIPO_BENEFICIARIO_JUBILADO = "J";

	@Override
	public ResultadoGenericoDto<AseguradoAPSDto> verificarSalarioBeneficiario(String pNuaCua, long pNumeroDocumento,
			String pTipoBeneficiario) {
		ResultadoGenericoDto<AseguradoAPSDto> vRespuesta = new ResultadoGenericoDto<AseguradoAPSDto>();
		vRespuesta.setOk(false);

		var vBeneficiario = this.obtenerDatosBeneficiario(pNuaCua, pNumeroDocumento, pTipoBeneficiario);
		if (vBeneficiario.isOk() == false) {
			vRespuesta.setResultadoObjeto(null);
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES,
							EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
					.set("Obtener obtenerDatosBeneficiario APS"));
			vRespuesta.setOk(false);
			return vRespuesta;
		}

		String vQuery = "select beneficiario_id as beneficiario,gestion, mes, sum(total_ingresos) as totalIngresos\n" + 
				"				from sfi_fiscalizacion.sfi_ain_aps_ingresos_beneficiarios				\n" + 
				"				where beneficiario_id = ? and estado_id = 'AC' \n" + 
				"				group by beneficiario_id,gestion,mes\n" + 
				"				order by gestion,mes desc";
		try {
			RowMapper<AseguradoAPSIngresoDto> vRowMapper = new BeanPropertyRowMapper<AseguradoAPSIngresoDto>(
					AseguradoAPSIngresoDto.class);
			BigDecimal vPromedioSalarios = new BigDecimal(0);
			var vIngresosBeneficiariosLista = jdbcTemplate.query(vQuery, vRowMapper,
					vBeneficiario.getResultadoObjeto().getBeneficiarioId());

			BigDecimal vSumaIngresos = new BigDecimal(0);
			vBeneficiario.getResultadoObjeto().setTercerSalario(BigDecimal.ZERO.stripTrailingZeros());
			vBeneficiario.getResultadoObjeto().setSegundoSalario(BigDecimal.ZERO.stripTrailingZeros());
			vBeneficiario.getResultadoObjeto().setPrimerSalario(BigDecimal.ZERO.stripTrailingZeros());
			int vParametroCalculoPromedio = 0;
			if (!vIngresosBeneficiariosLista.isEmpty()) {
				int vCantidadRegistrosSalarios = 0;
				for (AseguradoAPSIngresoDto aseguradoAPSIngresoDto : vIngresosBeneficiariosLista) {
					if (vCantidadRegistrosSalarios < 3) {

						vCantidadRegistrosSalarios++;
						switch (vCantidadRegistrosSalarios) {
						case 1:
							vSumaIngresos = vSumaIngresos
									.add(new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
							vParametroCalculoPromedio++;
							vBeneficiario.getResultadoObjeto().setTercerSalario(
									new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
							vBeneficiario.getResultadoObjeto()
									.setGestionTercerSalario(aseguradoAPSIngresoDto.getGestion());
							vBeneficiario.getResultadoObjeto().setPeriodoTercerSalario(aseguradoAPSIngresoDto.getMes());
							break;
						case 2:
							int vMesActual=aseguradoAPSIngresoDto.getMes();
							int vMesTercero=vBeneficiario.getResultadoObjeto().getPeriodoTercerSalario()-1;
							int vAnioActual=aseguradoAPSIngresoDto.getGestion();
							int vAnioTercero=vBeneficiario.getResultadoObjeto().getGestionTercerSalario();
							if((vMesActual==vMesTercero &&
									vAnioActual==vAnioTercero)
							||(vAnioActual<vAnioTercero
							&& vMesActual==12))
							{
								vBeneficiario.getResultadoObjeto().setSegundoSalario(
										new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
								vBeneficiario.getResultadoObjeto()
										.setGestionSegundoSalario(aseguradoAPSIngresoDto.getGestion());
								vBeneficiario.getResultadoObjeto()
										.setPeriodoSegundoSalario(aseguradoAPSIngresoDto.getMes());
								vSumaIngresos = vSumaIngresos
										.add(new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
								vParametroCalculoPromedio++;
							}

							break;
						case 3:
							int vMesActual2=aseguradoAPSIngresoDto.getMes();
							int vMesTercero2=vBeneficiario.getResultadoObjeto().getPeriodoTercerSalario()-2;
							int vAnioActual2=aseguradoAPSIngresoDto.getGestion();
							int vAnioTercero2=vBeneficiario.getResultadoObjeto().getGestionTercerSalario();
							if((vMesActual2==vMesTercero2 &&
									vAnioActual2==vAnioTercero2)
									||(vAnioActual2<vAnioTercero2
									&& vMesActual2==11))
							{
								vBeneficiario.getResultadoObjeto().setPrimerSalario(
										new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
								vBeneficiario.getResultadoObjeto()
										.setGestionPrimerSalario(aseguradoAPSIngresoDto.getGestion());
								vBeneficiario.getResultadoObjeto().setPeriodoPrimerSalario(aseguradoAPSIngresoDto.getMes());
								vSumaIngresos = vSumaIngresos
										.add(new BigDecimal(aseguradoAPSIngresoDto.getTotalIngresos().doubleValue()));
								vParametroCalculoPromedio++;
							}

							break;
						}
						continue;
					}
				}
			}

			vPromedioSalarios = vSumaIngresos.divide(new BigDecimal(vParametroCalculoPromedio), RoundingMode.HALF_UP);
			vBeneficiario.getResultadoObjeto().setCumpleRequisito(vPromedioSalarios.doubleValue() <= LIMITE_INGRESOS);
			vBeneficiario.getResultadoObjeto().setTotalSalarioPromedio(vPromedioSalarios);
			vRespuesta.setResultadoObjeto(vBeneficiario.getResultadoObjeto());
			vRespuesta.setOk(true);
		} catch (Exception e) {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("Obtener obtenerDatosBeneficiario APS"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<AseguradoAPSDto> obtenerDatosBeneficiario(String pNuaCua, long pNumeroDocumento,
			String pTipoBeneficiario) {
		ResultadoGenericoDto<AseguradoAPSDto> vRespuesta = new ResultadoGenericoDto<AseguradoAPSDto>();

		vRespuesta.setOk(false);
		String vQuery = "";
		try {
			List<AseguradoAPSDto> vLista = new ArrayList<AseguradoAPSDto>();
			if (pTipoBeneficiario.equals(TIPO_BENEFICIARIO_DEPENDIENTE)) {
				vQuery = "select beneficiario_id as beneficiarioId,\n" + "administradora_id as administradoraId,\n"
						+ "tipo_regimen_id as tipoRegimenId,\n" + "tipo_prestacion_id as tipoPrestacionId,\n"
						+ "tipo_documento_identidad_id as tipoDocumentoIdentidadId,\n"
						+ "numero_documento_identificacion  as numeroDocumentoIdentificacion,\n" + "complemento,\n"
						+ "lugar_expedicion as lugarExpedicion,\n" + "primer_apellido as apellidoPaterno,\n"
						+ "segundo_apellido  as apellidoMaterno,\n" + "apellido_casada  as apellidoCasada,\n"
						+ "nombres,\n" + "estado_civil_id as estadoCivilId,\n" + "e_mail as email,\n" + "telefono,\n"
						+ "celular,\n" + "direccion,\n" + "tipo_departamento_id as tipoDepartamentoId,\n" + "ciudad,\n"
						+ "localidad_id as localidadId,\n" + "fecha_nacimiento as fechaNacimiento,\n"
						+ "CAST (nua_cua AS VARCHAR) as nuaCua, matricula\n"
						+ "from sfi_fiscalizacion.sfi_ain_aps_beneficiarios\n" + "where nua_cua  = ? \n"
						+ "and numero_documento_identificacion = ? and estado_id = 'AC'";

				RowMapper<AseguradoAPSDto> vRowMapper = new BeanPropertyRowMapper<AseguradoAPSDto>(
						AseguradoAPSDto.class);
				vLista = jdbcTemplate.query(vQuery, vRowMapper, TryParseLong(pNuaCua), pNumeroDocumento);
			}

			if (pTipoBeneficiario.equals(TIPO_BENEFICIARIO_JUBILADO)) {
				vQuery = "select beneficiario_id as beneficiarioId,\n" + "administradora_id as administradoraId,\n"
						+ "tipo_regimen_id as tipoRegimenId,\n" + "tipo_prestacion_id as tipoPrestacionId,\n"
						+ "tipo_documento_identidad_id as tipoDocumentoIdentidadId,\n"
						+ "numero_documento_identificacion  as numeroDocumentoIdentificacion,\n" + "complemento,\n"
						+ "lugar_expedicion as lugarExpedicion,\n" + "primer_apellido as apellidoPaterno,\n"
						+ "segundo_apellido  as apellidoMaterno,\n" + "apellido_casada  as apellidoCasada,\n"
						+ "nombres,\n" + "estado_civil_id as estadoCivilId,\n" + "e_mail as email,\n" + "telefono,\n"
						+ "celular,\n" + "direccion,\n" + "tipo_departamento_id as tipoDepartamentoId,\n" + "ciudad,\n"
						+ "localidad_id as localidadId,\n" + "fecha_nacimiento as fechaNacimiento,\n"
						+ "CAST (nua_cua AS VARCHAR) as nuaCua, matricula\n"
						+ "from sfi_fiscalizacion.sfi_ain_aps_beneficiarios\n" + "where matricula = ? \n"
						+ "and numero_documento_identificacion = ? and estado_id = 'AC'";

				vLista = new ArrayList<AseguradoAPSDto>();
				RowMapper<AseguradoAPSDto> vRowMapper = new BeanPropertyRowMapper<AseguradoAPSDto>(
						AseguradoAPSDto.class);
				vLista = jdbcTemplate.query(vQuery, vRowMapper, pNuaCua, pNumeroDocumento);
			}

			if (!vLista.isEmpty()) {
				AseguradoAPSDto vDatosBeneficiario = vLista.get(0);
				vRespuesta.setResultadoObjeto(vDatosBeneficiario);
				vRespuesta.setOk(true);
			} else {

				vRespuesta.setResultadoObjeto(null);
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES,
								EnumMensajesAplicacionBeneficiario.ERROR_CONSULTA_SIN_RESULTADOS)
						.set("Obtener obtenerDatosBeneficiario APS"));
				vRespuesta.setOk(false);
			}

		} catch (Exception e) {
			vRespuesta.addMensaje(mensajesDomain
					.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
					.set("Obtener obtenerDatosBeneficiario APS"));
			vRespuesta.setOk(false);
			vRespuesta.setResultadoObjeto(null);
		}

		return vRespuesta;
	}

	public static Long TryParseLong(String pValor) {
		try {
			return Long.parseLong(pValor);
		} catch (NumberFormatException ex) {
			return -100000L;
		}
	}
}

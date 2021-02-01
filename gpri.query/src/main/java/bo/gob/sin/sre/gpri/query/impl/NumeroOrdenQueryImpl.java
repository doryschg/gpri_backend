package bo.gob.sin.sre.gpri.query.impl;

import bo.gob.sin.sre.gpri.EnumMensajesAplicacionBeneficiario;
import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.AseguradoAPSIngresoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.IApsBeneficiariosQuery;
import bo.gob.sin.sre.gpri.query.INumerosOrdenQuery;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import bo.gob.sin.str.enumeradores.entorno.EnumSubsistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class NumeroOrdenQueryImpl implements INumerosOrdenQuery {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMensajeAplicacionDomain mensajesDomain;

	private final int LIMITE_INGRESOS = 9000;




	@Override
	public ResultadoGenericoDto<Long> obtenerNumeroOrdenDeclaracionBeneficiario() {
		ResultadoGenericoDto<Long> vRespuesta=new ResultadoGenericoDto<>();
		Long vSecuencia;
		String vQuery="SELECT nextval('sfe_facturacion.tsfe_riv_beneficiarios_nro_orden_nb_seq')";

			try{
				RowMapper<Long> vRowMapper = new BeanPropertyRowMapper<Long>(Long.class);

				vSecuencia = jdbcTemplate.queryForObject(vQuery, Long.class);

				vRespuesta.setResultadoObjeto(vSecuencia);
				vRespuesta.setOk(true);
			}
			catch (Exception e){
				vRespuesta.addMensaje(mensajesDomain
						.getMensaje(EnumSubsistema.RECAUDACIONES, EnumMensajesAplicacionBeneficiario.ERROR_PARAMETROS)
						.set("Obtener obtenerDatosBeneficiario APS"));
				vRespuesta.setOk(false);
				vRespuesta.setResultadoObjeto(null);
			}

		return vRespuesta;
	}
}

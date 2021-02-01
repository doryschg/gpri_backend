package bo.gob.sin.sre.gpri.client.impl;

import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.sin.sre.gpri.client.IBeneficiarioRestClient;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;

@Repository
public class BeneficiarioRestClientImpl implements IBeneficiarioRestClient {

	private static final Logger LOG = LoggerFactory.getLogger(BeneficiarioRestClientImpl.class);
	private ObjectMapper mapper = new ObjectMapper();
	@Value("${sre.sfe.gpri.query}")
	private String restBeneficiariosUrl;
	private RestTemplate restTemplate;

	public BeneficiarioRestClientImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioDto> obtenerBeneficiarioPorCodigoPersona(long pCodigoPersona) {
		ResultadoGenericoDto<BeneficiarioDto> vRespuesta = new ResultadoGenericoDto<>();

		String vUrlServicio = restBeneficiariosUrl + ConstMetodosRest.PATH_OBTENER_BENEFICIARIO_CODIGO_PERSONA + "/"
				+ pCodigoPersona;
		HttpHeaders headers = new HttpHeaders();
		ContextoSeguridad contexto = new ContextoSeguridad();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
			//HttpEntity<ResultadoGenericoDto<BeneficiarioDto>> vEntidadRespuesta = new HttpEntity<>(null, headers);
			
		    HttpEntity httpEntity = new HttpEntity(headers);
			ResponseEntity<ResultadoGenericoDto<BeneficiarioDto>> response = restTemplate.exchange(vUrlServicio,
					HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<ResultadoGenericoDto<BeneficiarioDto>>() {
					});
			vRespuesta = response.getBody();
		} catch (HttpClientErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		} catch (HttpServerErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		}
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPorBeneficiarioId(Long pBeneficiarioId) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<>();

		String vUrlServicio = restBeneficiariosUrl + ConstMetodosRest.PATH_OBTENER_BENEFICIARIO_BENEFICIARIO_ID + "/"
				+ pBeneficiarioId;
		HttpHeaders headers = new HttpHeaders();
		ContextoSeguridad contexto = new ContextoSeguridad();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
			HttpEntity httpEntity = new HttpEntity(headers);
			ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> response = restTemplate.exchange(vUrlServicio,
					HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>() {
					});
			vRespuesta = response.getBody();
		} catch (HttpClientErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		} catch (HttpServerErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		}
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<BeneficiarioPersonaRivDto> obtenerBeneficiarioPorNroDocumentoComplemento(String pNroDocumento, String pComplemento) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vRespuesta = new ResultadoGenericoDto<>();
		pComplemento=pComplemento==null?"null":pComplemento;
		String vUrlServicio = restBeneficiariosUrl + ConstMetodosRest.PATH_OBTENER_BENEFICIARIO_NRDOCUMENTO_COMPLEMENTO + "/"
				+ pNroDocumento+"/"+pComplemento;
		HttpHeaders headers = new HttpHeaders();
		ContextoSeguridad contexto = new ContextoSeguridad();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
			//HttpEntity<ResultadoGenericoDto<BeneficiarioDto>> vEntidadRespuesta = new HttpEntity<>(null, headers);

			HttpEntity httpEntity = new HttpEntity(headers);
			ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> response = restTemplate.exchange(vUrlServicio,
					HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>() {
					});
			vRespuesta = response.getBody();
		} catch (HttpClientErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		} catch (HttpServerErrorException e) {
			vRespuesta = new ResultadoGenericoDto<>();
			LogExcepcion.registrar(e, LOG, MethodSign.build(vUrlServicio));
			LOG.info("Servicio no disponible {}", vUrlServicio);
		}
		return vRespuesta;
	}


}

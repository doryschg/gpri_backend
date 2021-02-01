package bo.gob.sin.sre.gpri.client.impl;

import bo.gob.sin.sre.gpri.client.IBeneficiarioRestClient;
import bo.gob.sin.sre.gpri.client.INumeroOrdenRestClient;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.str.cexc.LogExcepcion;
import bo.gob.sin.str.cexc.MethodSign;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class NumeroOrdenRestClientImpl implements INumeroOrdenRestClient {

	private static final Logger LOG = LoggerFactory.getLogger(PersonaNaturalRestClientImpl.class);
	private ObjectMapper mapper = new ObjectMapper();
	@Value("${sre.sfe.gpri.query}")
	private String restBeneficiariosUrl;
	private RestTemplate restTemplate;

	public NumeroOrdenRestClientImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public ResultadoGenericoDto<Long> obtenerNumeroOrdenDeclaracion() {
		ResultadoGenericoDto<Long> vRespuesta = new ResultadoGenericoDto<>();

		String vUrlServicio = restBeneficiariosUrl + ConstMetodosRest.PATH_OBTENER_NUMERO_ORDEN_DECLARACION;
		HttpHeaders headers = new HttpHeaders();
		ContextoSeguridad contexto = new ContextoSeguridad();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(HttpHeaders.AUTHORIZATION, "Token " + contexto.obtener().getToken());
			//HttpEntity<ResultadoGenericoDto<BeneficiarioDto>> vEntidadRespuesta = new HttpEntity<>(null, headers);

			HttpEntity httpEntity = new HttpEntity(headers);
			ResponseEntity<ResultadoGenericoDto<Long>> response = restTemplate.exchange(vUrlServicio,
					HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<ResultadoGenericoDto<Long>>() {
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

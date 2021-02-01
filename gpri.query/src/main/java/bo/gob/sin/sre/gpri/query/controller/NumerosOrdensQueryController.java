package bo.gob.sin.sre.gpri.query.controller;

import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.INumerosOrdenQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/BeneficiariosNumeroOrden")
public class NumerosOrdensQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(NumerosOrdensQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private INumerosOrdenQuery numeroOrdenQuery;

	@RequestMapping(value = "/obtenerNumeroDeclaracion/", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<Long>> obtenerAseguradoAFP() {

		ResultadoGenericoDto<Long> vRespuesta = new ResultadoGenericoDto<Long>();
		vRespuesta = numeroOrdenQuery.obtenerNumeroOrdenDeclaracionBeneficiario();

		return new ResponseEntity<ResultadoGenericoDto<Long>>(vRespuesta, HttpStatus.OK);
	}

}

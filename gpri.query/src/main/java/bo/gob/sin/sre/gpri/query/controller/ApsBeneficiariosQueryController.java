package bo.gob.sin.sre.gpri.query.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.gpri.dto.AseguradoAPSDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.IApsBeneficiariosQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/BeneficiariosAps")
public class ApsBeneficiariosQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(ApsBeneficiariosQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IApsBeneficiariosQuery apsBeneficiariosQuery;

	@RequestMapping(value = "/obtenerAseguradoAFP/{pNuaCua}/{pNumeroDocumento}/{pTipoBeneficiario}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<AseguradoAPSDto>> obtenerAseguradoAFP(@PathVariable String pNuaCua,
			@PathVariable Long pNumeroDocumento, @PathVariable String pTipoBeneficiario) {

		ResultadoGenericoDto<AseguradoAPSDto> vRespuesta = new ResultadoGenericoDto<AseguradoAPSDto>();
		vRespuesta = apsBeneficiariosQuery.obtenerDatosBeneficiario(pNuaCua, pNumeroDocumento, pTipoBeneficiario);

		return new ResponseEntity<ResultadoGenericoDto<AseguradoAPSDto>>(vRespuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/verificarSalario/{pNuaCua}/{pNumeroDocumento}/{pTipoBeneficiario}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<AseguradoAPSDto>> verificarSalario(@PathVariable String pNuaCua,
			@PathVariable Long pNumeroDocumento, @PathVariable String pTipoBeneficiario) {

		ResultadoGenericoDto<AseguradoAPSDto> vRespuesta = new ResultadoGenericoDto<AseguradoAPSDto>();
		vRespuesta = apsBeneficiariosQuery.verificarSalarioBeneficiario(pNuaCua, pNumeroDocumento, pTipoBeneficiario);
		return new ResponseEntity<ResultadoGenericoDto<AseguradoAPSDto>>(vRespuesta, HttpStatus.OK);
	}
}

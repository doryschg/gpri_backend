package bo.gob.sin.sre.gpri.query.controller;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.query.IPersonaBeneficiariosQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/consultaBeneficiario")
public class BeneficiarioQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(BeneficiarioQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IPersonaBeneficiariosQuery personaBeneficiariosQuery;

	@RequestMapping(value = "/obtenerBeneficiarioPorPersonaId/{pPersonaId}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioDto>> obtenerBeneficiarioPorPersonaId(
			@PathVariable Long pPersonaId) {

		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPorPersonaId(pPersonaId);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioDto>>(vRespuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerBeneficiarioPersonaPorPersonaId/{pPersonaId}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> obtenerBeneficiarioPersonaPorPersonaId(
			@PathVariable Long pPersonaId) {

		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPersonaPorPersonaId(pPersonaId);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>(vRespuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerBeneficiarioPersonaPorNroDocumentoComplemento/{pNroDocumento}/{pComplemento}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> obtenerBeneficiarioPersonaPorNroDocumentoComplemento(
			@PathVariable String pNroDocumento, @PathVariable String pComplemento) {
    	if(pComplemento.equals("") ||pComplemento.equals("null") ||pComplemento.equals(" "))
		{
			pComplemento=null;
		}
		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPersonaPorNroDocumentoyComplemento(pNroDocumento,pComplemento);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>(vRespuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerBeneficiarioPersonaPorCodigoPersona/{pCodigoPersona}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> obtenerBeneficiarioPersonaPorCodigoPersona(
			@PathVariable Long pCodigoPersona) {

		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPorCodigoPersona(pCodigoPersona);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>(vRespuesta, HttpStatus.OK);
	}
	@RequestMapping(value = "/obtenerBeneficiarioPersonaPorNroDocumentoComplementoNroCuenta/{pNroDocumento}/{pComplemento}/{pNroCuenta}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> obtenerBeneficiarioPersonaPorNroDocumentoComplementoNrocuenta(
			@PathVariable String pNroDocumento, @PathVariable String pComplemento,@PathVariable String pNroCuenta) {
		if(pComplemento.equals("") ||pComplemento.equals("null") ||pComplemento.equals(" "))
		{
			pComplemento=null;
		}
		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPersonaPorNroDocumentoComplementoyNumeroCuenta(pNroDocumento,pComplemento,pNroCuenta);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>(vRespuesta, HttpStatus.OK);
	}
	@RequestMapping(value = "/obtenerBeneficiarioPersonaPorBeneficiarioId/{pBeneficiarioId}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> obtenerBeneficiarioPersonaPorBeneficiarioId(
			@PathVariable Long pBeneficiarioId) {

		ResultadoGenericoDto vRespuesta = new ResultadoGenericoDto<BeneficiarioPersonaRivDto>();
		vRespuesta = personaBeneficiariosQuery.obtenerBeneficiarioPersonaPorBeneficiarioId(pBeneficiarioId);

		return new ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>>(vRespuesta, HttpStatus.OK);
	}
}

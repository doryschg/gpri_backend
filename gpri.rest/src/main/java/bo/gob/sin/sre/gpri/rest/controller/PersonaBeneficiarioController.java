package bo.gob.sin.sre.gpri.rest.controller;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.PersonaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.RegistroCuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.service.IBeneficiarioService;
import bo.gob.sin.sre.gpri.service.IPersonaBeneficiarioService;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/PersonasBeneficiarios")
public class PersonaBeneficiarioController extends ApiController {

	private final static Logger LOG = LoggerFactory.getLogger(PersonaBeneficiarioController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;
	@Autowired
	IPersonaBeneficiarioService serviceBeneficiario;

	@RequestMapping(value = "/insertarPersonaBeneficiario", method = RequestMethod.POST, headers = "Accept=application/json", produces = { API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<PersonaBeneficiarioDto>> insertarDomicilio(
			@RequestBody PersonaBeneficiarioDto pBeneficiarioDto) {
		ResultadoGenericoDto<PersonaBeneficiarioDto> vResultado = serviceBeneficiario.insertarPersonaBeneficiario(pBeneficiarioDto);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

}

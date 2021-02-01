package bo.gob.sin.sre.gpri.rest.controller;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.RegistroCuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.service.IBeneficiarioService;
import bo.gob.sin.sre.gpri.service.IReporteDeclaracionJuradaService;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/Beneficiarios")
public class BeneficiarioController extends ApiController {

	private final static Logger LOG = LoggerFactory.getLogger(BeneficiarioController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;
	@Autowired
	IBeneficiarioService serviceBeneficiario;

	@Autowired
	IReporteDeclaracionJuradaService serviceReporte;

	@RequestMapping(value = "/verificarConexion/", method = RequestMethod.GET, produces = { API_V1_0 })
	public ResponseEntity<Object> verificarConexion() {
		ResultadoGenericoDto<Object> vRespuesta = new ResultadoGenericoDto<Object>();
		vRespuesta.setOk(true);
		return new ResponseEntity<Object>(vRespuesta, HttpStatus.OK);

	}

	@RequestMapping(value = "/insertarBeneficiario", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> insertarBeneficiario(
			@RequestBody BeneficiarioPersonaRivDto pBeneficiarioDto) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = serviceBeneficiario
				.insertarBeneficiario(pBeneficiarioDto);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

	@RequestMapping(value = "/modificarBeneficiario", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> modificarBeneficiario(
			@RequestBody BeneficiarioPersonaRivDto pBeneficiarioDto) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = serviceBeneficiario
				.modificarBeneficiario(pBeneficiarioDto);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}

	@PutMapping(value = "/actualizar", produces = { API_V1_0 })
	public ResponseEntity<Object> actualizarBeneficiario(@RequestBody BeneficiarioDto pBeneficiarioDto) {
		ResultadoGenericoDto<String> vResultado = serviceBeneficiario.updateBeneficiario(pBeneficiarioDto);
		ResponseEntity<Object> res = new ResponseEntity<Object>(vResultado, HttpStatus.OK);
		return res;
	}

	@PutMapping(value = "/insertarCuentaBancaria", produces = { API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> guardarCuentaBeneficiario(
			@RequestBody RegistroCuentaBeneficiarioDto pCuentaBeneficiario) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = serviceBeneficiario
				.guardarCuentaBeneficiario(pCuentaBeneficiario);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);

	}

	@PutMapping(value = "/modificarCuentaBancaria", produces = { API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> modificarCuentaBeneficiario(
			@RequestBody RegistroCuentaBeneficiarioDto pCuentaBeneficiario) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = serviceBeneficiario
				.modificarCuentaBeneficiario(pCuentaBeneficiario);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);

	}

	@RequestMapping(value = "/habilitarBeneficiario/{pBeneficiarioId}", method = RequestMethod.GET, produces = { API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<BeneficiarioPersonaRivDto>> habilitarBeneficiario(
			@PathVariable Long pBeneficiarioId) {
		ResultadoGenericoDto<BeneficiarioPersonaRivDto> vResultado = serviceBeneficiario.habilitarBeneficiario(pBeneficiarioId);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}
	@RequestMapping(value = "/reporteDeclaracionJurada/{pBeneficiarioId}", method = RequestMethod.GET, produces = { API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<String>> reporteDeclaracionJurada(
			@PathVariable Long pBeneficiarioId) {
		ResultadoGenericoDto<String> vResultado = serviceReporte.obtenerReporteDeclaracionJurada(pBeneficiarioId);
		return new ResponseEntity<>(vResultado, HttpStatus.OK);
	}
	}

package bo.gob.sin.sre.gpri.rest.controller;

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

import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.service.IMovimientoBeneficiarioService;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/MovimientoBeneficiario")
public class MovimientoBeneficiarioController extends ApiController {
	private final static Logger LOG = LoggerFactory.getLogger(MovimientoBeneficiarioController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IMovimientoBeneficiarioService movimientoBeneficiarioService;

	@RequestMapping(value = "/obtenerMovimientos/{pBeneficiarioId}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>> obtenerDetalleConciliacionPorId(
			@PathVariable Long pBeneficiarioId) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vResultado = movimientoBeneficiarioService
				.obtenerMovimientoPorBeneficiario(pBeneficiarioId);

		return new ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>>(vResultado, HttpStatus.OK);
	}

}

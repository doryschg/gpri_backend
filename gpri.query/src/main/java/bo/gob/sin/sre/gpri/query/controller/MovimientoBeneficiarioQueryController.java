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

import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.query.IMovimientosBeneficiarioConsultaQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/MovimientoBeneficiario")
public class MovimientoBeneficiarioQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(MovimientoBeneficiarioQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IMovimientosBeneficiarioConsultaQuery movimientosBeneficiarioConsultaService;

	@RequestMapping(value = "/obtenerMovimientos/{pGestion}/{pPeriodo}/{pTamanioPagina}/{pPagina}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>> obtenerResumenesConciliacion(
			@PathVariable Integer pGestion, @PathVariable Integer pPeriodo, @PathVariable Integer pTamanioPagina,
			@PathVariable Integer pPagina) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vResultado = movimientosBeneficiarioConsultaService
				.obtenerMovimientoPorGestionPeriodo(pGestion, pPeriodo, pTamanioPagina, pPagina);

		return new ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>>(vResultado, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerMovimientosPorDetalleConciliacion/{pDetalleConciliacionId}/{pTamanioPagina}/{pPagina}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>> obtenerResumenesConciliacion(
			@PathVariable Long pDetalleConciliacionId, @PathVariable Integer pTamanioPagina,
			@PathVariable Integer pPagina) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vResultado = movimientosBeneficiarioConsultaService
				.obtenerMovimientoPorDetalleConciliacionId(pDetalleConciliacionId, pTamanioPagina, pPagina);

		return new ResponseEntity<ResultadoGenericoListaDto<MovimientoBeneficiarioDto>>(vResultado, HttpStatus.OK);
	}
}

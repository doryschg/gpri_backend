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

import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.query.IDetalleConciliacionQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/DetalleConciliacion")
public class DetalleConciliacionQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(MovimientoBeneficiarioQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IDetalleConciliacionQuery detalleConciliacionQuery;

	// {{url}}:39211/rest/DetalleConciliacion/beneficiarios/1062/page/0/10/2020/10
	@RequestMapping(value = "/beneficiarios/{pPersonaId}/page/{pPagina}/{pTamanioPagina}/{pGestion}/{pPeriodo}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<DetalleConciliacionDto>> obtenerResumenesConciliacion(
			@PathVariable long pPersonaId, @PathVariable Integer pGestion, @PathVariable Integer pPeriodo,
			@PathVariable Integer pTamanioPagina, @PathVariable Integer pPagina) {

		ResultadoGenericoListaDto<DetalleConciliacionDto> vResultado = detalleConciliacionQuery
				.obtenerDetalleConciliacionPaginado(pPersonaId, pGestion, pPeriodo, pPagina, pTamanioPagina);

		return new ResponseEntity<ResultadoGenericoListaDto<DetalleConciliacionDto>>(vResultado, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerDetallePorResumenIdPaginado/{pResumenConciliacionId}/{pTamanioPagina}/{pPagina}/{pDocumentoBeneficiario}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<DetalleConciliacionDto>> obtenerDetalleConciliacionPorResumenTipoConciliacionPaginado(
			@PathVariable Long pResumenConciliacionId, @PathVariable Integer pTamanioPagina,
			@PathVariable Integer pPagina, @PathVariable String pDocumentoBeneficiario) {

		ResultadoGenericoListaDto<DetalleConciliacionDto> vResultado = detalleConciliacionQuery
				.obtenerDetalleConciliacionPorResumenPaginado(pResumenConciliacionId, pPagina, pTamanioPagina,
						pDocumentoBeneficiario);

		return new ResponseEntity<ResultadoGenericoListaDto<DetalleConciliacionDto>>(vResultado, HttpStatus.OK);
	}
}

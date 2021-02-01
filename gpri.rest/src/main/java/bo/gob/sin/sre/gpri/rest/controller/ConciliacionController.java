package bo.gob.sin.sre.gpri.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.gpri.dto.DetalleConciliacionDto;
import bo.gob.sin.sre.gpri.dto.RespuestaConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;
import bo.gob.sin.sre.gpri.dto.SolicitudConciliacionDto;
import bo.gob.sin.sre.gpri.service.IDetalleConciliacionService;
import bo.gob.sin.sre.gpri.service.IResumenConciliacionService;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/Conciliacion")
public class ConciliacionController extends ApiController {

	private final static Logger LOG = LoggerFactory.getLogger(ConciliacionController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;
	@Autowired
	private IResumenConciliacionService resumenConciliacionService;

	@Autowired
	private IDetalleConciliacionService detalleConciliacionService;

	@RequestMapping(value = "/recepionFormulario", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<RespuestaConciliacionDto>> recepionFormulario(
			@RequestBody SolicitudConciliacionDto pResumenConciliacion) {

		ResultadoGenericoDto<RespuestaConciliacionDto> vResultado = detalleConciliacionService
				.recepionFormulario(pResumenConciliacion, usuarioId());
		return new ResponseEntity<ResultadoGenericoDto<RespuestaConciliacionDto>>(vResultado, HttpStatus.OK);
	}

//	@RequestMapping(value = "/insertarResumenConciliacion", method = RequestMethod.POST, headers = "Accept=application/json")
//	public ResponseEntity<ResultadoGenericoDto<ResumenConciliacionDto>> insertarResumenConciliacion(
//			@RequestBody ResumenConciliacionDto pResumenConciliacion) {
//
//		pResumenConciliacion.setUsuarioRegistroId(usuarioId());
//
//		ResultadoGenericoDto<ResumenConciliacionDto> vResultado = resumenConciliacionService
//				.registrar(pResumenConciliacion);
//		return new ResponseEntity<ResultadoGenericoDto<ResumenConciliacionDto>>(vResultado, HttpStatus.OK);
//
//	}

	// TODO: sergio.ichas Revisar, si se publica este endpoint
	@RequestMapping(value = "/conciliar", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<ResumenConciliacionDto>> conciliar(
			@RequestBody ResumenConciliacionDto pResumenConciliacion) {

		pResumenConciliacion.setUsuarioRegistroId(usuarioId());

		ResultadoGenericoDto<ResumenConciliacionDto> vResultado = resumenConciliacionService
				.conciliar(pResumenConciliacion);
		return new ResponseEntity<ResultadoGenericoDto<ResumenConciliacionDto>>(vResultado, HttpStatus.OK);

	}

	@RequestMapping(value = "/obtenerResumenesConciliacion/{pGestion}/{pPeriodo}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoListaDto<ResumenConciliacionDto>> obtenerResumenesConciliacion(
			@PathVariable Integer pGestion, @PathVariable Integer pPeriodo) {

		ResultadoGenericoListaDto<ResumenConciliacionDto> vResultado = resumenConciliacionService
				.obtenerResumenConciliacionPorGestionPeriodo(pGestion, pPeriodo);

		return new ResponseEntity<ResultadoGenericoListaDto<ResumenConciliacionDto>>(vResultado, HttpStatus.OK);
	}

	// DETALLE CONCILIACION
	@RequestMapping(value = "/insertarDetalleConciliacion", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<DetalleConciliacionDto>> insertarDetalleConciliacion(
			@RequestBody DetalleConciliacionDto pDetalleConciliacion) {

		pDetalleConciliacion.setUsuarioRegistroId(usuarioId());

		ResultadoGenericoDto<DetalleConciliacionDto> vResultado = detalleConciliacionService
				.registrar(pDetalleConciliacion);
		return new ResponseEntity<ResultadoGenericoDto<DetalleConciliacionDto>>(vResultado, HttpStatus.OK);
	}

	@RequestMapping(value = "/actualizarMontoResumenConciliacion", method = RequestMethod.PUT, headers = "Accept=application/json", produces = {
			API_V1_0 })
	public ResponseEntity<ResultadoGenericoDto<Boolean>> actualizarMontoResumenConciliacion(
			@RequestBody ResumenConciliacionDto pResumenConciliacion) {

		ResultadoGenericoDto<Boolean> vResultado = resumenConciliacionService
				.actualizarMontoTotalPagadoResumenConciliacion(pResumenConciliacion.getResumenConciliacionId(),
						usuarioId());

		return new ResponseEntity<ResultadoGenericoDto<Boolean>>(vResultado, HttpStatus.OK);

	}
	
	
}

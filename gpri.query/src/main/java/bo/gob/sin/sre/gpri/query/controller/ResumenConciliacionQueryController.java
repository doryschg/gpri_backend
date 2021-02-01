package bo.gob.sin.sre.gpri.query.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.gpri.dto.ParametrosConciliacionDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.dto.ResumenConciliacionDto;
import bo.gob.sin.sre.gpri.query.IResumenConciliacionQuery;
import bo.gob.sin.str.constantes.swagger.ConstSwagger;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/rest/ResumenConciliacion")
public class ResumenConciliacionQueryController {
	private final static Logger LOG = LoggerFactory.getLogger(ResumenConciliacionQueryController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;

	@Autowired
	private IResumenConciliacionQuery resumenConciliacionQuery;

	@RequestMapping(value = "/obtenerResumenPaginado", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<ResultadoGenericoListaDto<ResumenConciliacionDto>> obtenerDetalleConciliacionPorResumenTipoConciliacionPaginado(
			@RequestBody ParametrosConciliacionDto pParametros) {

		ResultadoGenericoListaDto<ResumenConciliacionDto> vResultado = resumenConciliacionQuery
				.obtenerResumenPorTipoyGestionPeriodo(pParametros);
		return new ResponseEntity<ResultadoGenericoListaDto<ResumenConciliacionDto>>(vResultado, HttpStatus.OK);
	}
}

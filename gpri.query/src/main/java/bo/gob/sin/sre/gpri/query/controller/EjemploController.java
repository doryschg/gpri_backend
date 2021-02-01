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

import bo.gob.sin.str.constantes.swagger.ConstSwagger;
import bo.gob.sin.sre.gpri.query.IEjemploQuery;

//@CrossOrigin(maxAge = 3600)
//@RestController
//
//@RequestMapping("/rest/ejemplo")
public class EjemploController {

	private final static Logger LOG = LoggerFactory.getLogger(EjemploController.class);
	private static final String API_V1_0 = ConstSwagger.API_HEADER_VERSION + "1.0" + ConstSwagger.API_HEADER_JSON;	
	
	@Autowired
	IEjemploQuery ejemploQuery;

	@RequestMapping(value = "/dummy/{pDummy}", method = RequestMethod.GET, produces = {
			API_V1_0 })
	public ResponseEntity<String> dummy(@PathVariable Integer pDummy) {

		String vResultado = ejemploQuery.dummy(pDummy);
		return new ResponseEntity<String>(vResultado, HttpStatus.OK);

	}	
}

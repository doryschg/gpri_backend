package bo.gob.sin.sre.gpri.camunda.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.gob.sin.sre.gpri.camunda.sagas.DummySaga;

@RestController
@RequestMapping(value = "/rest/sagas")
public class SagasController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SagasController.class);

	@Autowired
	private DummySaga dummySaga;

	@RequestMapping(path = "/dummy/{idUsuario}", method = RequestMethod.PUT)
	public boolean ejemploDummy(HttpServletResponse response, @PathVariable Integer idUsuario) {

		dummySaga.procesoDummy(idUsuario);
		// we do it asynchronously, so send a 202 (see
		// https://github.com/berndruecker/flowing-retail/tree/master/rest/ for more
		// details on this)
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		System.out.println("saliendo saga");

		return true;
	}
}
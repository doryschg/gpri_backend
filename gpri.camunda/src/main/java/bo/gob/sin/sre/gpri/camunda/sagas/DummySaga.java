package bo.gob.sin.sre.gpri.camunda.sagas;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bo.gob.sin.sre.gpri.camunda.adapters.DummyTx1Adapter;
import bo.gob.sin.sre.gpri.camunda.adapters.DummyTx2Adapter;
import bo.gob.sin.sre.gpri.camunda.adapters.DummyTx1CompensatorioAdapter;
import bo.gob.sin.sre.gpri.camunda.adapters.DummyTx2CompensatorioAdapter;
import bo.gob.sin.util.camunda.SagaBuilder;

@Component
public class DummySaga {

	@Autowired
	private ProcessEngine camunda;	

	@PostConstruct
	public void defineSaga() {
		SagaBuilder saga = SagaBuilder.newSaga("dummySaga") //
				.activity("Primera transaccion", DummyTx1Adapter.class) //
				.compensationActivity("Compensa primera transaccion", DummyTx1CompensatorioAdapter.class) //				
				.activity("Segunda transaccion", DummyTx2Adapter.class) //
				.compensationActivity("Compensa segunda transaccion", DummyTx2CompensatorioAdapter.class) //
				.end() //
				.triggerCompensationOnAnyError();

		camunda.getRepositoryService().createDeployment() //
				.addModelInstance("dummySaga.bpmn", saga.getModel()) //
				.deploy();
	}

	//Metodo que sera consumido desde el controlador rest
	public void procesoDummy(Object pParam) {
		HashMap<String, Object> vContextVariables = new HashMap<>();		
		vContextVariables.put("pParam", pParam);
		
		camunda.getRuntimeService().startProcessInstanceByKey("dummySaga", vContextVariables);
	}

}
package bo.gob.sin.sre.gpri.camunda.adapters;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DummyTx1Adapter implements JavaDelegate {

//	@Autowired
//	private IClienteRestDummy clienteService;
	
	@Override
	public void execute(DelegateExecution ctx) throws Exception{
		
		//obtenemos el parametro del contexto de la saga
		//Integer usuarioId = (int) ctx.getVariable("usuarioId");
		
		//consumo del servicio y verificacion de la respuesta
		//ResServicio res = clienteService.dummyServicio(usuarioId);
		
//		if (!res.isOk()) {
//			throw new RuntimeException("No se pudo realizar la transaccion");
//		}
		
		//seteamos el objeto de respuesta para una posible compensacion
		//ctx.setVariable("dummyRespuesta", res);
	}
}

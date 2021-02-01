package bo.gob.sin.sre.gpri.camunda.adapters;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyTx2CompensatorioAdapter implements JavaDelegate {

//	@Autowired
//	private IClienteRestDummy clienteService;
	
	@Override
	public void execute(DelegateExecution ctx) throws Exception {
		//obtenemos el parametro del contexto de la saga seteado en la transaccion ejecutada correctamente
		//ObjectDto objDummy = (ObjectDto) ctx.getVariable("dummyRespuestaTx2");
				
		//consumo del servicio compensatorio y verificacion de la respuesta
		//ResServicio res = clienteService.dummyServicioCompensacionTx2(objDummy);
				
//		if (!res.isOk()) {
//			throw new RuntimeException("No se pudo realizar la transaccion");
//		}
	}

}

import bo.gob.sin.ent.repositories.BeneficiarioCuentaRepository
import bo.gob.sin.ent.repositories.impl.BeneficiarioCuentaRepositoryImpl
import bo.gob.sin.ent.repositories.model.BeneficiarioPago
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaEstadoPagoBonoBuilder
import bo.gob.sin.sit.client.sigep.SigepRestClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.OutputStream
import java.io.PrintStream
import kotlin.system.exitProcess

fun main() {
    /*System.setOut(PrintStream(object : OutputStream() {
        override fun write(b: Int) {}
    }))*/
    val procesarBeneficiariosPagosChannel = Channel<BeneficiarioPago>()
    val jobProcesarPagosRegistrados = procesarEstadoPagos(procesarBeneficiariosPagosChannel)
    var offset = 0L
    val limit = 1000L
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    while (true) {
        val results = repository.findBeneficiariosPagosConsulta(limit, offset)
        if (results.isEmpty()) {
            break
        }
        for (beneficiario in results) {
            println(beneficiario.pagoBonoId)
            runBlocking {
                val client = SigepRestClient()
                val reqConsulta = ConsultaEstadoPagoBonoBuilder.builder()
                    .identificadorPagoBono(beneficiario.pagoBonoId).build()
                val resConsulta = client.consultaEstadoPagoBono(reqConsulta, obtenerToken())
                when {
                    (resConsulta != null && resConsulta.isEstado && resConsulta.resultadoObjeto.codigo == 1L) -> { // El pago se ha realizado
                        procesarBeneficiariosPagosChannel.send(beneficiario)
                    }
                    (resConsulta != null && resConsulta.isEstado && (resConsulta.resultadoObjeto.codigo == 1000L || resConsulta.resultadoObjeto.codigo == 1001L)) -> {
                        // No hacer nada en un estado pendiente o c31 generado
                    }
                    else -> {
                        // TODO: Verificar casos especiales como cuentas bloquedadas o cuentas desabilitadas
                        LOG.warn("Error al consultar el pago bono: {}", resConsulta)
                    }
                }
            }
        }
        offset += limit
    }
    runBlocking {
        procesarBeneficiariosPagosChannel.close()
        jobProcesarPagosRegistrados.join()
    }
    exitProcess(0)
}

fun procesarEstadoPagos(beneficiarios: Channel<BeneficiarioPago>) = GlobalScope.launch(Dispatchers.IO) {
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    val lst = mutableListOf<BeneficiarioPago>()
    var total = 0
    for (beneficiario in beneficiarios) {
        lst += beneficiario
        if (lst.size >= resourceBundle.getString("cuenta.validas.size").toIntOrNull()?:10) {
            total += lst.size
            repository.updateEstadoPagoBono(lst.toMutableList())
            lst.clear()
        }
    }
    total += lst.size
    repository.updateEstadoPagoBono(lst.toMutableList())
	// TODO: Eliminar la siguiente linea
    System.err.println("Se han registrado $total pagos ${lst.size}")
    LOG.info("Se han registrado $total pagos")
}

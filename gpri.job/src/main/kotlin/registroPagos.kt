import bo.gob.sin.ent.repositories.BeneficiarioCuentaRepository
import bo.gob.sin.ent.repositories.impl.BeneficiarioCuentaRepositoryImpl
import bo.gob.sin.ent.repositories.model.BeneficiarioPago
import bo.gob.sin.sit.api.sigep.models.builders.RegistroDatosPagoBonoBuilder
import bo.gob.sin.sit.client.sigep.SigepRestClient
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.io.OutputStream
import java.io.PrintStream
import kotlin.system.exitProcess

fun main() {
    // Evita visualizar las salidas por System.out.println
    /*System.setOut(PrintStream(object : OutputStream() {
        override fun write(b: Int) {}
    }))*/
    val beneficiariosPagosChannel = Channel<BeneficiarioPago>()
    val procesarBeneficiariosPagosChannel = Channel<BeneficiarioPago>()
    val jobProcesarPagosRegistrados = procesarPagosRegistrados(procesarBeneficiariosPagosChannel)
    val jobs = mutableListOf<Job>()
    repeat(10) {
        jobs += registrarPago(beneficiariosPagosChannel, procesarBeneficiariosPagosChannel)
    }
    var offset = 0L
    val limit = resourceBundle.getString("consulta.buffer.size").toLongOrNull()?:1000
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    while (true) {
        val results = repository.findBeneficiariosPagos(limit, offset)
        if (results.isEmpty()) {
            break
        }
        val json = Gson()
        for (beneficiario in results) {
            runBlocking {
                beneficiariosPagosChannel.send(beneficiario)
            }
        }
        offset += limit
    }
    runBlocking {
        beneficiariosPagosChannel.close()
        jobs.forEach {
            it.join()
        }
        procesarBeneficiariosPagosChannel.close()
        jobProcesarPagosRegistrados.join()
    }
    LOG.info("Registro de pagos finalizada")
    exitProcess(0)
}

fun procesarPagosRegistrados(beneficiarios: Channel<BeneficiarioPago>) = GlobalScope.launch(Dispatchers.IO) {
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    val lst = mutableListOf<BeneficiarioPago>()
    var total = 0
    for (beneficiario in beneficiarios) {
        lst += beneficiario
        if (lst.size >= resourceBundle.getString("cuenta.validas.size").toIntOrNull()?:10) {
            total += lst.size
            repository.updateIdPagoBonos(lst.toMutableList())
            lst.clear()
        }
    }
    total += lst.size
    repository.updateIdPagoBonos(lst.toMutableList())
    LOG.info("Se han registrado $total pagos")
	// TODO: Eliminar la siguiente linea
    System.err.println("Se han registrado $total pagos")
}

fun registrarPago(beneficiarios: Channel<BeneficiarioPago>, pagosRegistrados: Channel<BeneficiarioPago>) = GlobalScope.launch(Dispatchers.IO) {
    val client = SigepRestClient()
    for (beneficiario in beneficiarios) {
        val reqPago = RegistroDatosPagoBonoBuilder.builder().beneficiario(beneficiario.beneficiarioSigepId)
            .bono(3).gestion(beneficiario.gestion).mes(beneficiario.periodo).monto(beneficiario.monto)
            .origen("SIN").build()
        // Bono y origen son datos por defecto con valores bono=3 y origen=SIN llevar a enumeradores
        val resPago = runBlocking {
            client.registroDatosPagoBono(reqPago, obtenerToken())
        }
        if (resPago != null && resPago.isEstado) {
            beneficiario.pagoBonoId = resPago.resultadoObjeto.identificadorPagoBono
            pagosRegistrados.send(beneficiario)
        } else {
            LOG.warn("Error en el registro de pago bono: {}", resPago)
        }
    }
}

import bo.gob.sin.ent.repositories.BeneficiarioCuentaRepository
import bo.gob.sin.ent.repositories.impl.BeneficiarioCuentaRepositoryImpl
import bo.gob.sin.ent.repositories.model.BeneficiarioCuenta
import bo.gob.sin.ent.repositories.model.Token
import bo.gob.sin.ent.repositories.model.enums.EnumEstadoCuenta
import bo.gob.sin.sit.api.sigep.models.builders.ConsultaCuentaBancariaBuilder
import bo.gob.sin.sit.client.sigep.SigepRestClient
import com.auth0.jwt.JWT
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory
import java.io.OutputStream
import java.io.PrintStream
import java.sql.Connection
import java.util.*
import kotlin.system.exitProcess

val resourceBundle = ResourceBundle.getBundle("job")
val tokenRest = bo.gob.sin.sit.client.sigep.util.Url.obtenerRutaArchivoConfig("str_cau_caut_rest");
val usuariosRest = bo.gob.sin.sit.client.sigep.util.Url.obtenerRutaArchivoConfig("sau_arol_rest");
val LOG = LoggerFactory.getLogger(BeneficiarioCuentaRepositoryImpl::class.java)
fun main() {
    // Evita visualizar las salidas por System.out.println
    /*System.setOut(PrintStream(object : OutputStream() {
        override fun write(b: Int) {}
    }))*/
    LOG.info("Validación de cuentas iniciada")
    val beneficiariosChannel = Channel<BeneficiarioCuenta>()
    val cuentasValidasChannel = Channel<BeneficiarioCuenta>(resourceBundle.getString("cuenta.buffer.size").toIntOrNull()?:500)
    val cuentasErrorChannel = Channel<Pair<Long, String>>(resourceBundle.getString("cuenta.buffer.size").toIntOrNull()?:500)
    val jobCuentasValidas = procesarCuentasValidas(cuentasValidasChannel)
    val jobCuentasInexistentes = procesarCuentasError(cuentasErrorChannel)
    val jobs = mutableListOf<Job>()
    repeat(resourceBundle.getString("hilos.consulta").toIntOrNull()?:2) {
        jobs += consultarEstadoCuenta(beneficiariosChannel, cuentasValidasChannel, cuentasErrorChannel)
    }

    var offset = 0L
    val limit = resourceBundle.getString("consulta.buffer.size").toLongOrNull()?:1000
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    while (true) {
        val results = repository.findBeneficiarios(EnumEstadoCuenta.ENVIADO.estado, limit, offset)
        if (results.isEmpty()) {
            break
        }
        for (beneficiario in results) {
            runBlocking {
                beneficiariosChannel.send(beneficiario)
            }
        }
        offset += limit
    }

    runBlocking { // Cerrar los canales y esperar a que termine todos los jobs
        beneficiariosChannel.close()
        jobs.forEach {
            it.join()
        }
        cuentasValidasChannel.close()
        jobCuentasValidas.join()
        cuentasErrorChannel.close()
        jobCuentasInexistentes.join()
    }
    LOG.info("Validación de cuentas finalizada")
    exitProcess(0)
}

fun procesarCuentasError(beneficiarios: Channel<Pair<Long, String>>) = GlobalScope.launch(Dispatchers.IO) {
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    val lst = mutableListOf<Pair<Long, String>>()
    var total = 0
    for (beneficiarioId in beneficiarios) {
        lst += beneficiarioId
        if (lst.size >= resourceBundle.getString("cuentas.errors.size").toIntOrNull()?:10) {
            total += lst.size
            repository.updateCuentasError(lst.toMutableList())
            lst.clear()
        }
    }
    total += lst.size
    repository.updateCuentasError(lst.toMutableList())
    LOG.info("Se han actualizado $total cuentas con estado de error: INX | BLO | CER | CMP | CYT")
}

fun actualizarRolesUsuario(connection: Connection?, usuarios: List<Long>) {

    runBlocking {
        val client = HttpClient(Apache) {}
        for (usuarioId in usuarios) {
            // TODO: Llevar los ids de los roles a constantes
            val message = client.post<String> {
                url("${usuariosRest}/rest/usuarioRol/registrarAltaRolesUsuario")
                contentType(ContentType.Application.Json)
                header("Authorization", "Token ${obtenerToken()}")
                body = "{\n \"usuarioId\": $usuarioId, \"roles\": [ 1458, 1330]}"
            }
        }
    }
}

fun procesarCuentasValidas(beneficiarios: Channel<BeneficiarioCuenta>) = GlobalScope.launch(Dispatchers.IO) {
    val repository: BeneficiarioCuentaRepository = BeneficiarioCuentaRepositoryImpl()
    val lst = mutableListOf<BeneficiarioCuenta>()
    var total = 0;
    for (beneficiario in beneficiarios) {
        lst += beneficiario
        if (lst.size >= resourceBundle.getString("cuenta.validas.size").toIntOrNull()?:10) {
            total += lst.size
            repository.updateCuentasValidas(lst.toMutableList(), ::actualizarRolesUsuario)
            lst.clear()
        }
    }
    total += lst.size
    repository.updateCuentasValidas(lst.toMutableList(), ::actualizarRolesUsuario)
    LOG.info("Se han actualizado $total cuentas: VAL")
}

fun consultarEstadoCuenta(
    beneficiarios: Channel<BeneficiarioCuenta>,
    cuentasValidas: Channel<BeneficiarioCuenta>,
    jobCuentasError: Channel<Pair<Long, String>>
) = GlobalScope.launch(Dispatchers.IO) {
    val origenSin = "SIN"
    val client = SigepRestClient();
    val mapsEnumEstado = EnumEstadoCuenta.values().associateBy(EnumEstadoCuenta::estado)
    for (beneficiario in beneficiarios) {
        val solicitud = ConsultaCuentaBancariaBuilder.builder().beneficiario(beneficiario.beneficiarioSigepId)
            .banco(beneficiario.banco.toIntOrNull() ?: continue).cuenta(beneficiario.cuenta) // ojo con banco
            .origen(origenSin).build()
        val respuesta = client.consultaCuentaBancaria(solicitud, obtenerToken())
        when {
            (respuesta != null && respuesta.isEstado && respuesta.resultadoObjeto.estadoCuenta == EnumEstadoCuenta.VALIDA.estado) -> {
                cuentasValidas.send(beneficiario)
            }
            (respuesta != null && respuesta.resultadoObjeto.estadoCuenta == EnumEstadoCuenta.ENVIADO.estado) -> {
                // No hacer nada y esperar la siguiente consulta
            }
            (respuesta != null && mapsEnumEstado[respuesta.resultadoObjeto.estadoCuenta] != null) -> {
                jobCuentasError.send(Pair(beneficiario.id, respuesta.resultadoObjeto.estadoCuenta))
            }
            else -> {
                LOG.warn("Estado desconocido beneficiarioId: {}, usuarioId: {}, -> {}", beneficiario.id, beneficiario.usuarioId, respuesta)
            }
        }
    }
}

object TOKEN {
    var token: String = ""
    var expiresAt: Long = 0
    val mu = Mutex()
    suspend fun refrescarToken(): String? {
        val urlToken = "${tokenRest}/token/getGenerico/999"
        var gson = Gson()
        val client = HttpClient(Apache) {}
        // https://ktor.io/docs/examples.html#example-json
        val result = client.get<String> {
            url(urlToken)
        }
        val token = gson.fromJson(result, Token::class.java)
        return token?.token?.apply {
            val jwt = JWT.decode(this)
            TOKEN.expiresAt = jwt.expiresAt.time
            TOKEN.token = this
        }
    }
}

suspend fun obtenerToken(): String {
    return TOKEN.mu.withLock {

        if (TOKEN.token.isEmpty()) {
            TOKEN.refrescarToken()
        } else {
            // Actualizar 5 minutos antes
            val tiempoActual = Calendar.getInstance().timeInMillis - 5 * 60 * 1000
            if (TOKEN.expiresAt < tiempoActual) {
                TOKEN.refrescarToken()
            }
        }
        TOKEN.token
    }
}
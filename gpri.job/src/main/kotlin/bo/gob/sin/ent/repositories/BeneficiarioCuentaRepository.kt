package bo.gob.sin.ent.repositories

import bo.gob.sin.ent.repositories.model.BeneficiarioCuenta
import bo.gob.sin.ent.repositories.model.BeneficiarioPago
import java.sql.Connection

interface BeneficiarioCuentaRepository {
    // TODO: Añadir SQL exception
    fun findBeneficiarios(estado: String, limit: Long, offset: Long): List<BeneficiarioCuenta>
    fun findBeneficiariosPagos(limit: Long, offset: Long): List<BeneficiarioPago>
    fun findBeneficiariosPagosConsulta(limit: Long, offset: Long): List<BeneficiarioPago>
    fun updateCuentasValidas(beneficiarios: List<BeneficiarioCuenta>, actuaizarRoles: (Connection?, List<Long>) -> Unit)
    fun updateCuentasError(beneficiarios: List<Pair<Long, String>>)
    fun updateIdPagoBonos(beneficiariosPagados: List<BeneficiarioPago>)
    fun updateEstadoPagoBono(beneficiarios: List<BeneficiarioPago>)
}
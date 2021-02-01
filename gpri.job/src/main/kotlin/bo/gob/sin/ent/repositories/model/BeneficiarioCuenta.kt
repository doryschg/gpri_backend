package bo.gob.sin.ent.repositories.model

import java.util.*

class BeneficiarioCuenta {
    var id: Long = 0
    var personaId: Long = 0
    var banco: String = ""
    var cuenta: String = ""
    var fechaRegistro: Date? = null
    var beneficiarioSigepId: Long = 0
    var usuarioId: Long = 0
}
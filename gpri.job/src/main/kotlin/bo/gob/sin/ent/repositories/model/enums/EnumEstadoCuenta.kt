package bo.gob.sin.ent.repositories.model.enums

enum class EnumEstadoCuenta(val estado: String) {
    VALIDA("VAL"),
    CERRAR("CER"),
    INEXISTENTE("INX"),
    BLOQUEADA("BLO"),
    ERROR_COMPLEMENTO("CMP"),
    ERROR_CUENTA_TITULAR("CYT"),
    ENVIADO("ENV");

    val agrupador = "estado_cuenta_bancaria_id"
}
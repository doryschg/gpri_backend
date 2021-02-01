package bo.gob.sin.sre.gpri;

import bo.gob.sin.str.parametricas.enumeradores.IMensajeAplicacion;

public enum EnumMensajesAplicacionBeneficiario implements IMensajeAplicacion {
    ERROR_REGISTRO_CONSOLIDACION(4291),
    REGISTRO_CORRECTO_CONSOLIDACION(2145),
    ERROR_PARAMETROS(4295),
    ERROR_EJECUCION_CONSULTA(4296),
    ERROR_CONSULTA_SIN_RESULTADOS(4297),
    ERROR_CONSULTA_MAS_DE_UN_REGISTRO(4298),
    EXITO_CONSULTA(2148),
    ERROR_EJECUCION_DE_SERVICIO(4299),
    ERROR_BENEFICIARIO_YA_REGISTRADO(4300),
    REGISTRO_CORRECTO(2149),	
    ERROR_CONCILIACION_DEL_MES_YA_REGISTRADA(4300),
    ERROR_CUENTA_REGISTRO(4301);
    private final int id;

    EnumMensajesAplicacionBeneficiario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

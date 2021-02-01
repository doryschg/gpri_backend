package bo.gob.sin.sre.gpri.client.impl;

public class ConstMetodosRest {
	private ConstMetodosRest() {

	}

	public static final String PATH_INSERTAR_PERSONA_NATURAL = "/rest/PersonaNatural/guardarPersonaNatural";
	public static final String PATH_GENERAR_CODIGO_PERSONA = "/rest/PersonaNatural/guardarCodigoPersona";

	public static final String PATH_OBTENER_BENEFICIARIO_CODIGO_PERSONA = "/rest/consultaBeneficiario/obtenerBeneficiarioPersonaPorCodigoPersona";
	public static final String PATH_OBTENER_BENEFICIARIO_BENEFICIARIO_ID = "/rest/consultaBeneficiario/obtenerBeneficiarioPersonaPorBeneficiarioId";
	public static final String PATH_OBTENER_NUMERO_ORDEN_DECLARACION="/rest/BeneficiariosNumeroOrden/obtenerNumeroDeclaracion/";
	public static final String PATH_OBTENER_BENEFICIARIO_NRDOCUMENTO_COMPLEMENTO= "/rest/consultaBeneficiario/obtenerBeneficiarioPersonaPorNroDocumentoComplemento";

}

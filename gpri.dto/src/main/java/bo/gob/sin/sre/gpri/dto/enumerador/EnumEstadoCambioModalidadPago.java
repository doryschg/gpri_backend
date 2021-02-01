package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoCambioModalidadPago {

	PENDIENTE("PEN"), PROCESADO("PRO");

	private final String id;

	EnumEstadoCambioModalidadPago(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

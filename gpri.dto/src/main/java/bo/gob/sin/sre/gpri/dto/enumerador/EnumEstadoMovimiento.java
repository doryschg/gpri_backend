package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoMovimiento {
	CONLUIDO("CON"), PENDIENTE("PEN");

	private final String id;

	EnumEstadoMovimiento(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

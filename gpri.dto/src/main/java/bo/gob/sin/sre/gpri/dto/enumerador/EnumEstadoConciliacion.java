package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoConciliacion {
	GENERADA("GEN"), CONCILIADO("CON"), PAGADO("PAG"), ANULADA("ANU"), OBSERVADA("OBS");

	private final String id;

	EnumEstadoConciliacion(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoTransaccion {
	INTERES("INT"), MANTENIMIENTO_VALOR("MVA"), REPARO("REP"), REINTEGRO("REI");

	private final String id;

	EnumTipoTransaccion(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}

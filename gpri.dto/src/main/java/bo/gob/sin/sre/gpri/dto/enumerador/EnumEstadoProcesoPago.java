package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoProcesoPago {
	OBSERVADO("OBS"), ANULADO("ANU"), PAGADO("PAG"), PENDIENTE("PEN");
	
	private final String id;

	EnumEstadoProcesoPago(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

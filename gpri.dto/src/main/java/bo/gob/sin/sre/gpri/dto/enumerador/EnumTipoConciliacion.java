package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoConciliacion {
	MANUAL("MAN"), AUTOMATICO("AUT");

	private final String id;

	EnumTipoConciliacion(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

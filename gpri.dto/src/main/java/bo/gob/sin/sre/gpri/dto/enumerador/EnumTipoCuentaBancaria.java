package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoCuentaBancaria {

	CUENTA_CORRIENTE("C"), CAJA_DE_AHORRO("A");

	private final String id;

	EnumTipoCuentaBancaria(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

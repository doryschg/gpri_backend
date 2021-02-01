package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoMovimiento {

	CREDITO("CRE"), DEBITO("DEB");

	private final String id;

	EnumTipoMovimiento(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}

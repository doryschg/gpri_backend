package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoModalidadPagoBeneficiario {
	AUTOMATICO("S"), MANUAL("N");

	private final String id;

	EnumTipoModalidadPagoBeneficiario(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

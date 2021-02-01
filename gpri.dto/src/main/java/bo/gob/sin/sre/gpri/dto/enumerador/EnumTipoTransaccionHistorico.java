package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoTransaccionHistorico {
	ACTUALIZACION("ACT"), INSERCION("INS");

	private final String id;

	EnumTipoTransaccionHistorico(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

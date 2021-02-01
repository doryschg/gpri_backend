package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoSalario {

	SALARIO_DE_BENEFICIARIO_NO_VIGENTE("DES"), SALARIO_DE_BENEFICIARIO_VIGENTE("HAB");

	private final String id;

	EnumEstadoSalario(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

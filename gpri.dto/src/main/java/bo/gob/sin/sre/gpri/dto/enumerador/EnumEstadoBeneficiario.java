package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumEstadoBeneficiario {

	BENEFICIARIO_DESHABILITADO_RRIVA("DES"), BENEFICIARIO_HABILITADO_RRIVA("HAB");

	private final String id;

	EnumEstadoBeneficiario(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

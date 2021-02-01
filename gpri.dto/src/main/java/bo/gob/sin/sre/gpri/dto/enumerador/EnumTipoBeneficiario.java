package bo.gob.sin.sre.gpri.dto.enumerador;

public enum EnumTipoBeneficiario {

	BENEFICIARIO_DEPENDIENTE("D"),BENEFICIARIO_INDEPENDIENTE("I"), BENEFICIARIO_JUBILADO("J");

	private final String id;

	EnumTipoBeneficiario(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

package bo.gob.sin.sre.gpri.dto.enumerador.sigep;

import java.util.HashMap;

public class ConstantesSigep {
	public static final int BONO_REINTEGRO = 3;
	public static final String ENTIDAD_ORIGEN_PAGO = "SIN";
	public static final int ENTIDAD_PAGO = 408;

	public static final int MONEDA_EN_BOLIVIANOS = 69;

	// Mapeo Codigo clasificador Departamentos SIN - Codigos Ubicacion Geografica MEFP Beneficiarios, ver manual tecnico servicios MEFP.
	public static final HashMap<Integer, Integer> UBI_GEO = new HashMap<>();
	static {
		UBI_GEO.put(1, 3);
		UBI_GEO.put(2, 55);
		UBI_GEO.put(3, 183);
		UBI_GEO.put(4, 265);
		UBI_GEO.put(5, 335);
		UBI_GEO.put(6, 410);
		UBI_GEO.put(7, 436);
		UBI_GEO.put(8, 525);
		UBI_GEO.put(9, 563);
	};

}

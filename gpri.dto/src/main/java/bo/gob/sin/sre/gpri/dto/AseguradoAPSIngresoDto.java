package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AseguradoAPSIngresoDto implements Serializable {
	Long beneficiarioid;
	BigDecimal totalIngresos;
	Integer mes;
	Integer gestion;

	public Long getBeneficiarioid() {
		return beneficiarioid;
	}

	public void setBeneficiarioid(Long beneficiarioid) {
		this.beneficiarioid = beneficiarioid;
	}

	public BigDecimal getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(BigDecimal totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getGestion() {
		return gestion;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

}

package bo.gob.sin.sre.gpri.dto;

import java.io.Serializable;

public class ParametrosConciliacionDto implements Serializable {

	private Integer periodo;
	private Integer gestion;
	private String tipoConciliacion;
	private Integer tamanoPagina;
	private Integer pagina;

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getGestion() {
		return gestion;
	}

	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}

	public String getTipoConciliacion() {
		return tipoConciliacion;
	}

	public void setTipoConciliacion(String tipoConciliacion) {
		this.tipoConciliacion = tipoConciliacion;
	}

	public Integer getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(Integer tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

}

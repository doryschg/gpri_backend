package bo.gob.sin.sre.gpri.dto;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

import java.io.Serializable;
import java.util.List;

public class ResultadoGenericoListaDto<T> extends ListaMensajesAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> resultadoLista;
	private Integer totalRegistros;

	public List<T> getResultadoLista() {
		return this.resultadoLista;
	}

	public void setResultadoLista(List<T> resultadoLista) {
		this.resultadoLista = resultadoLista;
	}

	@Override
	public String toString() {
		return "ResultadoGenericoListaDto [resultadoLista=" + resultadoLista + ", getResultadoLista()="
				+ getResultadoLista() + ", isOk()=" + isOk() + ", getMensajes()=" + getMensajes() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

}

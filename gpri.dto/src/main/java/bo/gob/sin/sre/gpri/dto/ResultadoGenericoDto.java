package bo.gob.sin.sre.gpri.dto;

import bo.gob.sin.str.cmsj.mapl.dto.ListaMensajesAplicacion;

import java.io.Serializable;

public class ResultadoGenericoDto<T> extends ListaMensajesAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private T resultadoObjeto;

	public T getResultadoObjeto() {
		return this.resultadoObjeto;
	}

	public void setResultadoObjeto(T resultadoObjeto) {
		this.resultadoObjeto = resultadoObjeto;
	}

	@Override
	public String toString() {
		return "ResultadoGenericoDto [resultadoObjeto=" + resultadoObjeto + ", getResultadoObjeto()="
				+ getResultadoObjeto() + ", isOk()=" + isOk() + ", getMensajes()=" + getMensajes() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}

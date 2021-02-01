package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sit.api.sigep.models.dtos.respuesta.RespuestaRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroNuevoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.CuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface ICuentaBeneficiarioService {


	public ResultadoGenericoDto<BeneficiarioDto> insertarCuentaBeneficiario(CuentaBeneficiarioDto pBeneficiario);

	public ResultadoGenericoDto<String> updateCuentaBeneficiario(CuentaBeneficiarioDto pBeneficiario);


}

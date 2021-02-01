package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.CuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.PersonaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IPersonaBeneficiarioService {


	public ResultadoGenericoDto<PersonaBeneficiarioDto> insertarPersonaBeneficiario(PersonaBeneficiarioDto pPersonaBeneficiario);

	public ResultadoGenericoDto<PersonaBeneficiarioDto> modificarPErsonaBeneficiario(PersonaBeneficiarioDto pPersonaBeneficiario);


}

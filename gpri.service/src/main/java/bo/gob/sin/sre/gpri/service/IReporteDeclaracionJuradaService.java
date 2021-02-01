package bo.gob.sin.sre.gpri.service;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;

public interface IReporteDeclaracionJuradaService {
    public ResultadoGenericoDto<String> obtenerReporteDeclaracionJurada(Long pBeneficiarioId);



}

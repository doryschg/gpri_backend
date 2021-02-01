package bo.gob.sin.sre.gpri.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.domain.IMovimientoBeneficiarioDomain;
import bo.gob.sin.sre.gpri.dto.MovimientoBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoListaDto;
import bo.gob.sin.sre.gpri.model.SreTsfeRivMovimientos;
import bo.gob.sin.sre.gpri.service.IMovimientoBeneficiarioService;

@Service
@Transactional("transactionManager")
public class MovimientoBeneficiarioServiceImpl implements IMovimientoBeneficiarioService {

	private static final Logger LOG = LoggerFactory.getLogger(MovimientoBeneficiarioServiceImpl.class);
	 
	@Autowired
	private IMovimientoBeneficiarioDomain movimientoBeneficiarioDomain;

	@Override
	public ResultadoGenericoDto<MovimientoBeneficiarioDto> registrar(
			MovimientoBeneficiarioDto pMovimientoBeneficiario) {
		ResultadoGenericoDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoDto<MovimientoBeneficiarioDto>();

		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		SreTsfeRivMovimientos vMovimientoBeneficiario = vEntityDtoMapper.map(pMovimientoBeneficiario,
				SreTsfeRivMovimientos.class);

		movimientoBeneficiarioDomain.insertar(vMovimientoBeneficiario);
		pMovimientoBeneficiario = vEntityDtoMapper.map(vMovimientoBeneficiario, MovimientoBeneficiarioDto.class);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoObjeto(pMovimientoBeneficiario);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoDto<MovimientoBeneficiarioDto> actualizar(
			MovimientoBeneficiarioDto pMovimientoBeneficiario) {

		ResultadoGenericoDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoDto<MovimientoBeneficiarioDto>();
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);

		SreTsfeRivMovimientos vDetalleConciliacionEntidad = vEntityDtoMapper.map(pMovimientoBeneficiario,
				SreTsfeRivMovimientos.class);

		movimientoBeneficiarioDomain.modificar(vDetalleConciliacionEntidad);
		vRespuesta.setOk(true);
		vRespuesta.setResultadoObjeto(pMovimientoBeneficiario);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorBeneficiario(Long pBeneficiarioId) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoListaDto<MovimientoBeneficiarioDto>();

		List<SreTsfeRivMovimientos> vMovimientosBeneficiarioEntidadLista = new ArrayList<>();

		vMovimientosBeneficiarioEntidadLista = movimientoBeneficiarioDomain
				.obtenerMovimientosPorBeneficiarioId(pBeneficiarioId);

		List<MovimientoBeneficiarioDto> vDetalleConciliacion = mapEntitytoDtoList(vMovimientosBeneficiarioEntidadLista);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vDetalleConciliacion);
		return vRespuesta;
	}

	@Override
	public ResultadoGenericoListaDto<MovimientoBeneficiarioDto> obtenerMovimientoPorGestionPeriodo(Integer pGestion,
			Integer pPeriodo) {

		ResultadoGenericoListaDto<MovimientoBeneficiarioDto> vRespuesta = new ResultadoGenericoListaDto<MovimientoBeneficiarioDto>();

		List<SreTsfeRivMovimientos> vMovimientosBeneficiariosEntidadLista = new ArrayList<>();
		if (pGestion != null) {
			if (pPeriodo != null) {
				vMovimientosBeneficiariosEntidadLista = movimientoBeneficiarioDomain.obtenerPorPeriodoyGestion(pGestion,
						pPeriodo);
			} else {
				vMovimientosBeneficiariosEntidadLista = movimientoBeneficiarioDomain.obtenerPorGestion(pGestion);
			}
		} else {
			vMovimientosBeneficiariosEntidadLista = movimientoBeneficiarioDomain.obtenerPorPeriodo(pPeriodo);
		}

		List<MovimientoBeneficiarioDto> vDetalleConciliacion = mapEntitytoDtoList(
				vMovimientosBeneficiariosEntidadLista);

		vRespuesta.setOk(true);
		vRespuesta.setResultadoLista(vDetalleConciliacion);
		return vRespuesta;
	}

	private List<MovimientoBeneficiarioDto> mapEntitytoDtoList(List<SreTsfeRivMovimientos> vEntidadLista) {
		ModelMapper vEntityDtoMapper = new ModelMapper();
		vEntityDtoMapper.getConfiguration().setAmbiguityIgnored(true);
		List<MovimientoBeneficiarioDto> vListaDto = new ArrayList<>();
		vListaDto = vEntityDtoMapper.map(vEntidadLista, new TypeToken<List<MovimientoBeneficiarioDto>>() {
		}.getType());
		return vListaDto;
	}
}

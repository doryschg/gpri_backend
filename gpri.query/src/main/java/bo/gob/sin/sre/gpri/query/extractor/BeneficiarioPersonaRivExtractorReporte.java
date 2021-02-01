package bo.gob.sin.sre.gpri.query.extractor;

import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.PersonaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.PersonaDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeneficiarioPersonaRivExtractorReporte implements ResultSetExtractor<List<BeneficiarioPersonaRivDto>> {
    @Override
    public List<BeneficiarioPersonaRivDto> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Long, BeneficiarioPersonaRivDto> map = new HashMap<Long, BeneficiarioPersonaRivDto>();
        Long vPosicion = 0L;
        BeneficiarioPersonaRivDto vBeneficiario = null;
        PersonaBeneficiarioDto vPersonaRiv = new PersonaBeneficiarioDto();
        PersonaDto vPersona=new PersonaDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



        while(rs.next()) {
            Long vBeneficiarioId = rs.getLong("beneficiarioId");
            vBeneficiario = map.get(vBeneficiarioId);

            if ( vBeneficiario == null ) {
                vPosicion++;

                vBeneficiario = new BeneficiarioPersonaRivDto();

                vBeneficiario.setBeneficiarioId(rs.getLong("beneficiarioId"));
                vBeneficiario.setPersonaId(rs.getLong("personaId"));
                vBeneficiario.setCodigoPersona(rs.getLong("codigoPersona"));
                vBeneficiario.setFormatoNombreId(rs.getString("formatoNombreId"));
                vBeneficiario.setPaisId(rs.getString("paisId"));
                vBeneficiario.setDepartamentoId(rs.getInt("departamentoId"));
                vBeneficiario.setProvinciaId(rs.getInt("provinciaId"));
                vBeneficiario.setAlcaldiaId(rs.getInt("alcaldiaId"));
                vBeneficiario.setDireccion(rs.getString("direccion"));
                vBeneficiario.setTipoBeneficiarioId(rs.getString("tipoBeneficiarioId"));
                vBeneficiario.setNuaCua(rs.getString("nuaCua"));
                vBeneficiario.setNumeroOrden(rs.getLong("numeroOrden"));
                vBeneficiario.setTotalSalario(rs.getBigDecimal("totalSalario"));
                vBeneficiario.setEntidadFinancieraId(rs.getString("entidadFinancieraId"));
                vBeneficiario.setEntidadFinancieraDescripcion(rs.getString("entidadFinancieraDescripcion"));
                vBeneficiario.setTipoCuentaBancariaId(rs.getString("tipoCuentaBancariaId"));
                vBeneficiario.setTipoCuentaDescripcion(rs.getString("tipoCuentaDescripcion"));
                vBeneficiario.setCuentaBanco(rs.getString("cuentaBanco"));
                vBeneficiario.setMonedaId(rs.getString("monedaId"));
                vBeneficiario.setDistritoCuentaId(rs.getInt("distritoCuentaId"));
                vBeneficiario.setEstadoCuentaBancariaId(rs.getString("estadoCuentaBancariaId"));
                vBeneficiario.setPagoAutomaticoId(rs.getString("pagoAutomaticoId"));
                vBeneficiario.setEstadoBeneficiarioId(rs.getString("estadoBeneficiarioId"));
                vBeneficiario.setFechaDesde(LocalDate.parse(rs.getString("fechaDesde")));
//                vBeneficiario.setFechaHasta(LocalDate.parse(rs.get rs.getString("fechaHasta")));
                vBeneficiario.setIdBeneficiarioSigep(rs.getLong("idBeneficiarioSigep"));
                vBeneficiario.setGestionRegistro(rs.getInt("gestionRegistro"));
                vBeneficiario.setPrimerSalario(rs.getBigDecimal("primerSalario"));
                vBeneficiario.setGestionPrimerSalario(rs.getInt("gestionPrimerSalario"));
                vBeneficiario.setPeriodoPrimerSalario(rs.getInt("periodoPrimerSalario"));
                vBeneficiario.setSegundoSalario(rs.getBigDecimal("segundoSalario"));
                vBeneficiario.setGestionSegundoSalario(rs.getInt("gestionSegundoSalario"));
                vBeneficiario.setPeriodoSegundoSalario(rs.getInt("periodoSegundoSalario"));
                vBeneficiario.setTercerSalario(rs.getBigDecimal("tercerSalario"));
                vBeneficiario.setGestionTercerSalario(rs.getInt("gestionTercerSalario"));
                vBeneficiario.setPeriodoTercerSalario(rs.getInt("periodoTercerSalario"));
                //vBeneficiario.setUsuarioRegistroId(rs.getLong("usuarioRegistroId"));

                vPersonaRiv = new PersonaBeneficiarioDto();
                vPersonaRiv.setPersonaId(rs.getLong("pnPersonaId"));
                vPersonaRiv.setPaisOrigenId(rs.getInt("paisOrigenId"));
                vPersonaRiv.setPaisOrigenDescripcion(rs.getString("paisOrigenDescripcion"));
                vPersonaRiv.setEstadoCivilId(rs.getInt("estadoCivilId"));
                vPersonaRiv.setEstadoCivilDescripcion(rs.getString("estadoCivilDescripcion"));
                vPersonaRiv.setGeneroId(rs.getInt("generoId"));
                vPersonaRiv.setGeneroDescripcion(rs.getString("generoDescripcion"));
                vPersonaRiv.setNombres(rs.getString("nombres"));
                vPersonaRiv.setPrimerApellido(rs.getString("primerApellido"));
                vPersonaRiv.setSegundoApellido(rs.getString("segundoApellido"));
                vPersonaRiv.setApellidoCasada(rs.getString("apellidoCasada"));
                vPersonaRiv.setFechaNacimiento(LocalDateTime.parse(rs.getString("fechaNacimiento"), formatter));
                vPersonaRiv.setPersonaId(rs.getLong("pnPersonaId"));
                vPersonaRiv.setTipoDocumentoIdentidadId(rs.getInt("ptipoDocumentoIdentidadId"));
                vPersonaRiv.setTipoDocumentoIdentidadDescripcion(rs.getString("ptipoDocumentoIdentidadDescripcion"));
                vPersonaRiv.setLugarExpedicionId(rs.getInt("plugarExpedicionId"));
                vPersonaRiv.setLugarExpedicionDescripcion(rs.getString("plugarExpedicionDescripcion"));
                vPersonaRiv.setTipoPersonaId(rs.getInt("ptipoPersonaId"));
                vPersonaRiv.setCorreoElectronico(rs.getString("pcorreoElectronico"));
                vPersonaRiv.setNumeroDocumento(rs.getString("pnumeroDocumento"));
                vPersonaRiv.setCodigoComplementario(rs.getString("pcodigoComplementario"));
                vPersonaRiv.setCelular(rs.getString("pcelular"));
                vPersonaRiv.setTelefonoReferencia1(rs.getString("ptelefonoReferencia1"));
                vPersonaRiv.setTelefonoReferencia2(rs.getString("ptelefonoReferencia2"));
                vPersonaRiv.setModificadoId(rs.getString("pmodificadoId"));
                vPersonaRiv.setOrigenId(rs.getString("porigenId"));
                vPersonaRiv.setNombreUsuario(rs.getString("nombreUsuario"));
                vBeneficiario.setPersonaBeneficiario(vPersonaRiv);
                map.put(vPosicion, vBeneficiario);
            }

        }
        return new ArrayList<BeneficiarioPersonaRivDto>(map.values());
    }
    }

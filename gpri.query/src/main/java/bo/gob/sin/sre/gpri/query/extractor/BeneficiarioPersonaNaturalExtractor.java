package bo.gob.sin.sre.gpri.query.extractor;

import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.PersonaDto;
import bo.gob.sin.sre.gpri.dto.PersonaNaturalDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeneficiarioPersonaNaturalExtractor implements ResultSetExtractor<List<BeneficiarioDto>> {
    @Override
    public List<BeneficiarioDto> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Long, BeneficiarioDto> map = new HashMap<Long, BeneficiarioDto>();
        Long vPosicion = 0L;
        BeneficiarioDto vBeneficiario = null;
        PersonaNaturalDto vPersonaNatural = new PersonaNaturalDto();
        PersonaDto vPersona=new PersonaDto();

        while(rs.next()) {
            Long vBeneficiarioId = rs.getLong("beneficiarioId");
            vBeneficiario = map.get(vBeneficiarioId);

            if ( vBeneficiario == null ) {
                vPosicion++;

                vBeneficiario = new BeneficiarioDto();

                vBeneficiario.setBeneficiarioId(rs.getLong("beneficiarioId"));
                vBeneficiario.setPersonaId(rs.getLong("personaId"));
                vBeneficiario.setCodigoPersona(rs.getLong("codigoPersona"));
                vBeneficiario.setFormatoNombreId(rs.getString("formatoNombreId"));
                vBeneficiario.setPaisId(rs.getString("paisId"));
                vBeneficiario.setDepartamentoId(rs.getInt("departamentoId"));
                vBeneficiario.setAlcaldiaId(rs.getInt("alcaldiaId"));
                vBeneficiario.setDireccion(rs.getString("direccion"));
                vBeneficiario.setTipoBeneficiarioId(rs.getString("tipoBeneficiarioId"));
                vBeneficiario.setNuaCua(rs.getString("nuaCua"));
                vBeneficiario.setTotalSalario(rs.getBigDecimal("totalSalario"));
                vBeneficiario.setEntidadFinancieraId(rs.getString("entidadFinancieraId"));
                vBeneficiario.setTipoCuentaBancariaId(rs.getString("tipoCuentaBancariaId"));
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

                //vBeneficiario.setUsuarioRegistroId(rs.getLong("usuarioRegistroId"));

                vPersonaNatural = new PersonaNaturalDto();
                vPersonaNatural.setPersonaId(rs.getLong("pnPersonaId"));
                vPersonaNatural.setTramiteId(rs.getLong("pnTramiteId"));
                vPersonaNatural.setDependenciaRegistroId(rs.getInt("dependenciaRegistroId"));
                vPersonaNatural.setDependenciaRegistroDescripcion(rs.getString("dependenciaRegistroDescripcion"));
                vPersonaNatural.setNacionalidadId(rs.getInt("nacionalidadId"));
                vPersonaNatural.setNacionalidadDescripcion(rs.getString("nacionalidadDescripcion"));
                vPersonaNatural.setPaisOrigenId(rs.getInt("paisOrigenId"));
                vPersonaNatural.setPaisOrigenDescripcion(rs.getString("paisOrigenDescripcion"));
                vPersonaNatural.setEstadoCivilId(rs.getInt("estadoCivilId"));
                vPersonaNatural.setEstadoCivilDescripcion(rs.getString("estadoCivilDescripcion"));
                vPersonaNatural.setGeneroId(rs.getInt("generoId"));
                vPersonaNatural.setGeneroDescripcion(rs.getString("generoDescripcion"));
                vPersonaNatural.setInformacionReferencial(rs.getString("informacionReferencial"));
                vPersonaNatural.setNombres(rs.getString("nombres"));
                vPersonaNatural.setPrimerApellido(rs.getString("primerApellido"));
                vPersonaNatural.setSegundoApellido(rs.getString("segundoApellido"));
                vPersonaNatural.setApellidoCasada(rs.getString("apellidoCasada"));
                vPersonaNatural.setCorreoPersonal(rs.getString("correoPersonal"));
                vPersonaNatural.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                vPersonaNatural.setEstadoCorreoPersonal(rs.getBoolean("estadoCorreoPersonal"));
                vPersonaNatural.setEstadoSegip(rs.getBoolean("estadoSegip"));
                vPersonaNatural.setProfesionOcupacionId(rs.getInt("profesionOcupacionId"));
                vPersonaNatural.setNacionalizado(rs.getBoolean("nacionalizado"));


                vPersona=new PersonaDto();
                vPersona.setPersonaId(rs.getLong("pPersonaId"));
                vPersona.setTramiteId(rs.getLong("ptramiteId"));
                vPersona.setDependenciaRegistroId(rs.getInt("pdependenciaRegistroId"));
                vPersona.setDependenciaRegistroDescripcion(rs.getString("pdependenciaRegistroDescripcion"));
                vPersona.setTipoDocumentoIdentidadId(rs.getInt("ptipoDocumentoIdentidadId"));

                vPersona.setLugarExpedicionId(rs.getInt("plugarExpedicionId"));

                vPersona.setTipoPersonaId(rs.getInt("ptipoPersonaId"));
                vPersona.setCorreoElectronico(rs.getString("pcorreoElectronico"));
                vPersona.setNumeroDocumento(rs.getString("pnumeroDocumento"));
                vPersona.setCodigoComplementario(rs.getString("pcodigoComplementario"));
                vPersona.setCelular(rs.getString("pcelular"));
                vPersona.setTelefonoReferencia1(rs.getString("ptelefonoReferencia1"));
                vPersona.setTelefonoReferencia2(rs.getString("ptelefonoReferencia2"));
                vPersona.setFax(rs.getString("pfax"));
                vPersona.setEstadoCelular(rs.getBoolean("pestadoCelular"));



                vPersonaNatural.setPersona(vPersona);
                vBeneficiario.setPersonaNatural(vPersonaNatural);
                map.put(vPosicion, vBeneficiario);
            }

        }
        return new ArrayList<BeneficiarioDto>(map.values());
    }
    }

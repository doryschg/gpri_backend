package bo.gob.sin.sre.gpri.service.impl;
import bo.gob.sin.sre.gpri.client.IBeneficiarioRestClient;
import bo.gob.sin.sre.gpri.client.IPersonaNaturalRestclient;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.BeneficiarioPersonaRivDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.service.IReporteDeclaracionJuradaService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service
@Transactional("transactionManager")
public class ReporteDeclaracionJuradaServiceImpl implements IReporteDeclaracionJuradaService {
    private static final Logger LOG = LoggerFactory.getLogger(ReporteDeclaracionJuradaServiceImpl.class);

    @Autowired
    private IMensajeAplicacionDomain mensajesDomain;

    @Autowired
    private IBeneficiarioRestClient iBeneficiarioRestClient;

    @Override
    public ResultadoGenericoDto<String> obtenerReporteDeclaracionJurada(Long pBeneficiarioId){
        ResultadoGenericoDto<String> vRespuesta=new ResultadoGenericoDto<>();
        vRespuesta.setOk(false);
        ResultadoGenericoDto<BeneficiarioPersonaRivDto> vConsultaBene=null;
        vConsultaBene=iBeneficiarioRestClient.obtenerBeneficiarioPorBeneficiarioId(pBeneficiarioId);
        if(vConsultaBene!=null && vConsultaBene.isOk() && vConsultaBene.getResultadoObjeto().getEstadoBeneficiarioId().equals("H")){
            BeneficiarioPersonaRivDto beneficiario=vConsultaBene.getResultadoObjeto();
            Map<String, Object> vParamReport = new HashMap<String, Object>();
            vParamReport.put("numeroOrden",beneficiario.getNumeroOrden()==null?"":beneficiario.getNumeroOrden().toString());
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            vParamReport.put("fechaRegistro", LocalDate.now().format(formatters));
            vParamReport.put("tipoBeneficiario", beneficiario.getTipoBeneficiarioId().equals("D")?"DEPENDIENTE":beneficiario.getTipoBeneficiarioId().equals("J")?"JUBILADO":"INDEPENDIENTE");
            vParamReport.put("codigoPersona", beneficiario.getCodigoPersona());
            vParamReport.put("apellidoPaterno", beneficiario.getPersonaBeneficiario().getPrimerApellido());
            vParamReport.put("apellidoMaterno", beneficiario.getPersonaBeneficiario().getSegundoApellido());
            vParamReport.put("apellidoCasada", beneficiario.getPersonaBeneficiario().getApellidoCasada());
            vParamReport.put("nombres", beneficiario.getPersonaBeneficiario().getNombres());
            vParamReport.put("fechaNacimiento", beneficiario.getPersonaBeneficiario().getFechaNacimiento().format(formatters));
            vParamReport.put("numeroDocumentoIdentidad", beneficiario.getPersonaBeneficiario().getNumeroDocumento());
            vParamReport.put("tipoDocumentoIdentidad", beneficiario.getPersonaBeneficiario().getTipoDocumentoIdentidadDescripcion());
            vParamReport.put("nuaCua", beneficiario.getNuaCua());
            vParamReport.put("correoElectronico", beneficiario.getPersonaBeneficiario().getCorreoElectronico());
            vParamReport.put("direccion", beneficiario.getDireccion());
            vParamReport.put("telefono", beneficiario.getPersonaBeneficiario().getTelefonoReferencia1());
            vParamReport.put("celular", beneficiario.getPersonaBeneficiario().getCelular());
            vParamReport.put("nroCuenta", beneficiario.getCuentaBanco());
            vParamReport.put("tipoCuenta", beneficiario.getTipoCuentaDescripcion());
            vParamReport.put("entidadFinanciera", beneficiario.getEntidadFinancieraDescripcion());
            vParamReport.put("nombreCompleto", beneficiario.getPersonaBeneficiario().getNombres()+" "+ beneficiario.getPersonaBeneficiario().getPrimerApellido()+" "+beneficiario.getPersonaBeneficiario().getSegundoApellido());
            vParamReport.put("primerSalario", beneficiario.getPrimerSalario());
            vParamReport.put("segundoSalario", beneficiario.getSegundoSalario());
            vParamReport.put("tercerSalario", beneficiario.getTercerSalario());
            vParamReport.put("periodoPrimerSalario", beneficiario.getPeriodoPrimerSalario());
            vParamReport.put("periodoSegundoSalario", beneficiario.getPeriodoSegundoSalario());
            vParamReport.put("periodoTercerSalario", beneficiario.getPeriodoTercerSalario());
            vParamReport.put("gestionPrimerSalario", beneficiario.getGestionPrimerSalario());
            vParamReport.put("gestionSegundoSalario", beneficiario.getGestionSegundoSalario());
            vParamReport.put("gestionTercerSalario", beneficiario.getGestionTercerSalario());
            vParamReport.put("totalSalarioPromedio", beneficiario.getTotalSalario());
            vParamReport.put("tipoUsuario", "BENEFICIARIO");
            vParamReport.put("nombreUsuario", beneficiario.getPersonaBeneficiario().getNombreUsuario());
            String reportBase64=generarReporteDeclaracionJurada(vParamReport);
            if(!reportBase64.equals("")){
                vRespuesta.setOk(true);
                vRespuesta.setResultadoObjeto(reportBase64);
            }

        }
        else {
            vRespuesta.setOk(false);
            if(vConsultaBene!=null){
                vRespuesta.setMensajes(vConsultaBene.getMensajes());
            }
        }

        return vRespuesta;
    }
    private String generarReporteDeclaracionJurada( Map<String, Object> pParamReport){
        String respuestaBase64 = "";
        try {
            byte[] reportePdf = null;
            JasperPrint vReporteJasper = null;
            pParamReport.put(JRParameter.REPORT_LOCALE, Locale.US);
            pParamReport.put("IMAGE_logo_io", getClass().getResourceAsStream("/logo.png"));
            InputStream archivoReport=getClass().getResourceAsStream("/declaracionJuradaRegimenReintegroIVA.jasper");
            vReporteJasper = JasperFillManager.fillReport(archivoReport, pParamReport,
                     new JREmptyDataSource());
            reportePdf = JasperExportManager.exportReportToPdf(vReporteJasper);
            respuestaBase64 = Base64.getEncoder().encodeToString(reportePdf);

        } catch (
                JRException exception) {
            LOG.error(exception.getCause().toString(), exception);
        }

        return respuestaBase64;
    }


}

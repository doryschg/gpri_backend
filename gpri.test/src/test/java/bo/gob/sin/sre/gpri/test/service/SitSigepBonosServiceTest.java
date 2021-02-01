package bo.gob.sin.sre.gpri.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import bo.gob.sin.sit.api.sigep.models.dtos.solicitud.SolicitudRegistroDatosPagoBonoDto;
import bo.gob.sin.sit.client.sigep.SigepRestClient;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { AppConfig.class, HibernateConfig.class, RedisConfigTest.class })
//@Transactional
//@Rollback
public class SitSigepBonosServiceTest {
	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJQYWluYTkzMDYiLCJvZmljaW5hIjoiSDRzSUFBQUFBQUFBQURNQUFDSGYyX1FCQUFBQSIsIm5pdCI6Ikg0c0lBQUFBQUFBQUFMTTBOakF6TkRVd01MUUFBR1dfVnJJS0FBQUEiLCJpZCI6NzEwNjUyLCJleHAiOjE2MDg1NzU0MjEsImlhdCI6MTYwODU2MDk2MSwiZGVwZW5kZW5jaWEiOiJINHNJQUFBQUFBQUFBRE0wTUFBQTZsQjNJd01BQUFBPSJ9.l1L58OW8UOWeI99MFDIXSLY3mfxchgEPtaUx3-6obLVekgO_G2n3Y1ZSRa_Y2RKFryCXC3Z5FGSNrh4At246Kw";

	@Autowired
	private SigepRestClient client = new SigepRestClient();

	//@Test
	public void registroDatosPagoBonoTest() {
		SolicitudRegistroDatosPagoBonoDto vSolicitudPago = new SolicitudRegistroDatosPagoBonoDto();
		vSolicitudPago.setBeneficiario(2L);
		vSolicitudPago.setMonto(100D);
		vSolicitudPago.setGestion(2021);
		vSolicitudPago.setMes(1);

		var vRespuesta = client.registroDatosPagoBono(vSolicitudPago, token);
		Assert.isTrue(vRespuesta.getResultadoObjeto().getIdentificadorPagoBono() > 0);
	}
}

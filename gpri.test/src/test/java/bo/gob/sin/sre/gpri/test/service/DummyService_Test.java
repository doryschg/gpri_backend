package bo.gob.sin.sre.gpri.test.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import bo.gob.sin.config.AppConfig;
//import bo.gob.sin.sre.gpri.service.IMovimientoBeneficiarioService;
import bo.gob.sin.config.HibernateConfig;
import bo.gob.sin.config.RedisConfigTest;

//@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = { AppConfig.class, HibernateConfig.class, RedisConfigTest.class })
//@ContextConfiguration(classes = { AppConfig.class })
@Transactional
@Rollback
public class DummyService_Test {

	// @Autowired
	// private IMovimientoBeneficiarioService dummyService;
	
	@Test
	public void dummy_test() {
		Assert.isTrue(1 == 1);
	}
}

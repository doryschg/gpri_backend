package bo.gob.sin.sre.gpri.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import bo.gob.sin.config.AppConfig;
import bo.gob.sin.config.HibernateConfig;
import bo.gob.sin.config.RedisConfigTest;
import bo.gob.sin.sre.gpri.dao.IMovimientoBeneficiarioDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppConfig.class, HibernateConfig.class, RedisConfigTest.class })
@Transactional
@Rollback
public class DummyDao_Test {


	@Autowired(required = true)
	private IMovimientoBeneficiarioDao beneficiarioDao;

	@Test
	public void dummy_test() {
		var valor = beneficiarioDao.findById(1L);
		Assert.isTrue(valor != null);
	}
}

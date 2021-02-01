package bo.gob.sin.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {
	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		Properties JPA_PROPERTIES = new Properties() {
			{
				put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
				put("hibernate.hbm2ddl.auto", "none");
				put("hibernate.ddl-auto", "none");
				put("show-sql", "true");
			}
		};
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("bo.gob.sin");
		sessionFactory.setHibernateProperties(JPA_PROPERTIES);
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));

		System.out.println("VALORES EN BEAN DATASOURCER");
		return dataSourceBuilder.build();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}

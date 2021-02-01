package bo.gob.sin.sre.gpri.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import bo.gob.sin.util.swagger.SwaggerUtil;
import springfox.documentation.spring.web.plugins.Docket;

@EnableCaching
@EnableAsync
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class GestionPagoReintegroRest {
	@Autowired
    SwaggerUtil swaggerUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionPagoReintegroRest.class, args);
	}

	@Bean
	@Primary
	public TaskExecutor taskExecutor() {
		return new ThreadPoolTaskExecutor();
	}	
    
    @Bean
	public Docket swaggerGestionPagoReintegroApi10() {
    	return swaggerUtil.createDocket("1.0", "GestionPagoReintegro");
    }
}

package bo.gob.sin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan(value = { "bo.gob.sin" })
public class AppConfig {

}
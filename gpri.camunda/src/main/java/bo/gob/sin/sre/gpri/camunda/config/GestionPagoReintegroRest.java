package bo.gob.sin.sre.gpri.camunda.config;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAutoConfiguration
@EnableProcessApplication
@ComponentScan
public class GestionPagoReintegroRest {

	public static void main(String... args) throws Exception {
		SpringApplication.run(GestionPagoReintegroRest.class, args);
		// Start H2 server to be able to connect to database from the outside
		Server.createTcpServer(new String[] { "-tcpPort", "8092", "-tcpAllowOthers" }).start();
	}
}

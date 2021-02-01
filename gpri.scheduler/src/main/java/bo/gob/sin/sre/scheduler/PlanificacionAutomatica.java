package bo.gob.sin.sre.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bo.gob.sin.sre.gpri.service.IDetalleConciliacionService;

@Component
public class PlanificacionAutomatica {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlanificacionAutomatica.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	IDetalleConciliacionService service;

	@Scheduled(fixedRate = 3000)
	public void tareaConRangoFijo() {
		LOGGER.info("Tarea Automatica 1: " + dateTimeFormatter.format(LocalDateTime.now()));
		System.out.println("Tarea Automatica1: " + dateTimeFormatter.format(LocalDateTime.now()));
		var respuesta = service.obtenerDetallePorBeneficiario(7L);
		System.out.println(respuesta.getResultadoLista().toString());
		LOGGER.info("Respuesta del metodo: ", respuesta.isOk());
	}

	@Scheduled(cron = "0 33 9,11 * * MON-FRI")
	public void tareaParaObtenerSirefos() {
		LOGGER.info("Tarea Automatica2 : " + dateTimeFormatter.format(LocalDateTime.now()));
		System.out.println("Tarea Automatica 2: " + dateTimeFormatter.format(LocalDateTime.now()));
		LOGGER.info("Respuesta del metodo");
	}
}

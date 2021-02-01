package bo.gob.sin.sre.gpri.config;

import java.util.ResourceBundle;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

/**
 * RepositoryConfig define la configuracion basica para los repositorios de
 * datos.
 * <p>
 * TRANS spring.datasource.tomcat.log-abandoned=true
 * spring.datasource.tomcat.remove-abandoned=true
 * spring.datasource.tomcat.remove-abandoned-timeout=15
 * spring.datasource.tomcat.test-while-idle=true
 * spring.datasource.tomcat.time-between-eviction-runs-millis=3600000
 * spring.datasource.tomcat.validation-query=SELECT 1
 * spring.datasource.tomcat.validation-interval=0
 * spring.datasource.tomcat.max-wait=5000 spring.datasource.tomcat.max-active=10
 * spring.datasource.tomcat.max-idle=3 spring.datasource.tomcat.min-idle=1
 * <p>
 * PG12 dataSource.setLogAbandoned(Boolean.TRUE);
 * dataSource.setRemoveAbandonedOnBorrow(Boolean.TRUE);
 * dataSource.setRemoveAbandonedTimeout(15);
 * dataSource.setTestWhileIdle(Boolean.TRUE);
 * dataSource.setTimeBetweenEvictionRunsMillis(6 * 60 * 1000);
 * dataSource.setValidationQuery("SELECT 1");
 * dataSource.setValidationQueryTimeout(0);
 * dataSource.setFastFailValidation(Boolean.TRUE);
 * dataSource.setTestOnBorrow(Boolean.TRUE);
 * dataSource.setTestOnReturn(Boolean.TRUE); //dataSource.setTestOnConnect()
 * dataSource.setMaxWaitMillis(5000); dataSource.setMaxTotal(10);
 * dataSource.setMaxIdle(3); dataSource.setMinIdle(1);
 * dataSource.setDefaultQueryTimeout(30);///para consutlas
 *
 * @author vigmarcarlo
 * @version 14/09/2020
 */

public class RepositoryConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryConfig.class);

	private static Sql2o sql2o;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

	public static Sql2o getSql2o() {
		LOGGER.debug("Obteniendo referencia a Sql2o");
		if (sql2o == null) {
			LOGGER.debug("Instancciando Sql2o");
			sql2o = getSql2o(resourceBundle.getString("db.driverClassName"), resourceBundle.getString("db.url"),
					resourceBundle.getString("db.username"), resourceBundle.getString("db.password"));
		}
		return sql2o;
	}

	public static Sql2o getSql2o(String driver, String url, String username, String password) {
		try {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);

			// configuracion adicional
			// la validación falla rápidamente para las conexiones que han arrojado
			// SQLExceptions
			dataSource.setFastFailValidation(Boolean.TRUE);
			// Marcar para registrar los seguimientos de la pila para el código de la
			// aplicación que abandonó una declaración o conexión.
			dataSource.setLogAbandoned(Boolean.TRUE);
			// Banderas para eliminar las conexiones abandonadas si superan el
			// removeAbandonedTimout.
			dataSource.setRemoveAbandonedOnBorrow(Boolean.TRUE);
			// Tiempo de espera en segundos antes de que se pueda eliminar una conexión
			// abandonada.
			dataSource.setRemoveAbandonedTimeout(15);
			// los objetos serán validados por el desalojador de objetos inactivos
			dataSource.setTestWhileIdle(Boolean.TRUE);
			// El número de milisegundos para dormir entre ejecuciones del subproceso de
			// desalojo de objetos inactivo.
			dataSource.setTimeBetweenEvictionRunsMillis(6 * 60 * 1000);
			// consulta SQL que se utilizará para validar las conexiones
			dataSource.setValidationQuery("SELECT 1");
			// tiempo de espera en segundos antes de que fallen las consultas de validación
			// de la conexión.
			dataSource.setValidationQueryTimeout(1);
			// los objetos se validarán antes de tomarlos prestados del grupo
			dataSource.setTestOnBorrow(Boolean.TRUE);
			// La indicación de si los objetos se validarán antes de que se devuelvan al
			// grupo.
			dataSource.setTestOnReturn(Boolean.TRUE);
			// dataSource.setTestOnConnect()
			// milisegundos que esperará el grupo (cuando no hay conexiones disponibles)
			// para que se devuelva una conexión antes de lanzar una excepción
			dataSource.setMaxWaitMillis(500);
			// El número máximo de conexiones activas que se pueden asignar desde este grupo
			// al mismo tiempo, o negativo para ningún límite.
			dataSource.setMaxTotal(10);
			// La cantidad máxima de conexiones que pueden permanecer inactivas en el grupo,
			// sin que se liberen otras adicionales
			dataSource.setMaxIdle(3);
			// El número mínimo de conexiones que pueden permanecer inactivas en el grupo,
			// sin que se creen más conexiones,
			dataSource.setMinIdle(1);
			// tiempo de espera de la consulta que se usará para las declaraciones creadas a
			// partir de conexiones administradas por el grupo
			dataSource.setDefaultQueryTimeout(30);/// para consutlas
			sql2o = new Sql2o(dataSource);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sql2o;
	}
}

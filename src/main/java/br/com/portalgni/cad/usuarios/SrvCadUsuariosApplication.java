package br.com.portalgni.cad.usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SrvCadUsuariosApplication {

	private static final Logger logger = LoggerFactory.getLogger(SrvCadUsuariosApplication.class);

	@Value("${app.name}")
	private static String appName;

	@Value("${app.version}")
	private static String appVersion;

	public static void main(String[] args) {

		SpringApplication.run(SrvCadUsuariosApplication.class, args);

		logger.info("Aplicação: {} - Release: {}", appName, appVersion);

	}

}

package com.example.MicroservicoUtilizadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicoUtilizadoresApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoUtilizadoresApplication.class, args);
	}

	@Autowired
	private ControladorUtilizador controladorUtilizador;

	@Autowired
	private RepositorioUtilizador repositorioUtilizador;
	@Override
	public void run(ApplicationArguments args) throws Exception {

		controladorUtilizador.save(new UtilizadorDTO(0L,"admin","admin@gmail.com","admin","ROLE_ADMIN"));
		controladorUtilizador.save(new UtilizadorDTO(0L,"João","joao@gmail.com","joao","ROLE_STANDARD"));


	}
}

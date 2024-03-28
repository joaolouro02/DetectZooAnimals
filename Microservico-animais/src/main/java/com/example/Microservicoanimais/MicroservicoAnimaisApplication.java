package com.example.Microservicoanimais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicoAnimaisApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoAnimaisApplication.class, args);
	}

	@Autowired
	private RepositorioAnimal repositorioAnimal;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repositorioAnimal.save(new Animal("Cão","cao.jpg"));
		repositorioAnimal.save(new Animal("Cavalo","cavalo.jpeg"));
		repositorioAnimal.save(new Animal("Elefante","elefante.webp"));
		repositorioAnimal.save(new Animal("Gato","gato.webp"));
		repositorioAnimal.save(new Animal("Girafa","girafa.jpg"));
		repositorioAnimal.save(new Animal("Ovelha","ovelha.jpg"));
		repositorioAnimal.save(new Animal("Urso","urso.jpg"));
		repositorioAnimal.save(new Animal("Vaca","vaca.jpg"));
		repositorioAnimal.save(new Animal("Zebra","zebra.jpg"));
	}
}

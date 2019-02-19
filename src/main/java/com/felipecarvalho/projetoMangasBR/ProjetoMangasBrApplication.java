package com.felipecarvalho.projetoMangasBR;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;

@SpringBootApplication
public class ProjetoMangasBrApplication implements CommandLineRunner{
	
	@Autowired
	private PublisherRepository publisherRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoMangasBrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Publisher p1 = new Publisher(null, "Panini");
		Publisher p2 = new Publisher(null, "JBC");
		Publisher p3 = new Publisher(null, "New Pop");
		
		publisherRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}

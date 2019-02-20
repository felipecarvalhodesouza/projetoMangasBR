package com.felipecarvalho.projetoMangasBR;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;

@SpringBootApplication
public class ProjetoMangasBrApplication implements CommandLineRunner{
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private TitleRepository titleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoMangasBrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Publisher p1 = new Publisher(null, "Panini");
		Publisher p2 = new Publisher(null, "JBC");
		Publisher p3 = new Publisher(null, "New Pop");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Title t1 = new Title(null, "Naruto", p1, true, sdf.parse("05/2007"), sdf.parse("06/2015"));
		Title t2 = new Title(null, "Your Name", p2, true, sdf.parse("08/2017"), sdf.parse("01/2018"));
		Title t3 = new Title(null, "No Game No Life", p3, false, sdf.parse("12/2014"), null);
		
		publisherRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		titleRepository.saveAll(Arrays.asList(t1,t2,t3));
		
		
		
	}

}

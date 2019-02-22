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
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeRepository;

@SpringBootApplication
public class ProjetoMangasBrApplication implements CommandLineRunner{
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private VolumeRepository volumeRepository;
	
	@Autowired
	private UserRepository userRepository;

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
		
		Volume v1 = new Volume(null, "Volume 1", t1, sdf.parse("05/2007"), 8.90);
		Volume v2 = new Volume(null, "Volume 2", t1, sdf.parse("06/2007"), 8.90);
		Volume v3 = new Volume(null, "Volume 3", t1, sdf.parse("07/2007"), 8.90);
		Volume v4 = new Volume(null, "Volume 4", t1, sdf.parse("08/2007"), 8.90);
		Volume v5 = new Volume(null, "Volume 5", t1, sdf.parse("09/2007"), 8.90);
		Volume v6 = new Volume(null, "Volume 6", t1, sdf.parse("10/2007"), 8.90);
		
		publisherRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		titleRepository.saveAll(Arrays.asList(t1,t2,t3));
		
		volumeRepository.saveAll(Arrays.asList(v1,v2,v3,v4,v5,v6));
		
		User user1 = new User(null, "Felipe Carvalho de Souza", "desouzafelipecarvalho@gmail.com", "123");
		
		userRepository.save(user1);
	}

}

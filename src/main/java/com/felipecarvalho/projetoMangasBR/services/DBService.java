package com.felipecarvalho.projetoMangasBR.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionTitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeUserRepository;

@Service
public class DBService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private VolumeRepository volumeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	@Autowired
	private CollectionTitleRepository collectionTitleRepository;

	@Autowired
	private VolumeUserRepository volumeUserRepository;

	public void instantiateTestDataBase() throws ParseException {

		Publisher p1 = new Publisher(null, "Panini");
		Publisher p2 = new Publisher(null, "JBC");
		Publisher p3 = new Publisher(null, "New Pop");

		publisherRepository.saveAll(Arrays.asList(p1, p2, p3));

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
		Volume v7 = new Volume(null, "Volume 1", t3, sdf.parse("09/2007"), 10.90);
		Volume v8 = new Volume(null, "Volume 2", t3, sdf.parse("10/2007"), 10.90);
		Volume v9 = new Volume(null, "Volume 1", t2, sdf.parse("05/2012"), 15.90);

		titleRepository.saveAll(Arrays.asList(t1, t2, t3));

		volumeRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9));

		User user1 = new User(null, "Felipe Carvalho de Souza", "desouzafelipecarvalho@gmail.com", "123");

		Collection c1 = new Collection(null, user1);

		CollectionTitle ct1 = new CollectionTitle(c1, t1);
		CollectionTitle ct2 = new CollectionTitle(c1, t3);

		c1.getCollectionTitle().addAll(Arrays.asList(ct1, ct2));

		t1.getCollectionsTitle().addAll(Arrays.asList(ct1));
		t3.getCollectionsTitle().addAll(Arrays.asList(ct2));

		userRepository.save(user1);

		collectionRepository.save(c1);

		collectionTitleRepository.saveAll(Arrays.asList(ct1, ct2));

		VolumeUser vu1 = new VolumeUser(ct1, v1);
		VolumeUser vu2 = new VolumeUser(ct1, v2);
		VolumeUser vu3 = new VolumeUser(ct1, v3);
		VolumeUser vu4 = new VolumeUser(ct1, v4);
		VolumeUser vu5 = new VolumeUser(ct1, v5);
		VolumeUser vu6 = new VolumeUser(ct1, v6);
		VolumeUser vu7 = new VolumeUser(ct2, v7);
		VolumeUser vu8 = new VolumeUser(ct2, v8);

		volumeUserRepository.saveAll(Arrays.asList(vu1, vu2, vu3, vu4, vu5, vu6));
		volumeUserRepository.saveAll(Arrays.asList(vu7, vu8));

	}
}

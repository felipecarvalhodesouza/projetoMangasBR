package com.felipecarvalho.projetoMangasBR.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Author;
import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Review;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.domain.enums.Perfil;
import com.felipecarvalho.projetoMangasBR.repositories.AuthorRepository;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionTitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.repositories.ReviewRepository;
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
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDataBase() throws ParseException {

//		Publisher p1 = new Publisher(null, "Panini", "Planet Manga é uma linha editorial da Panini Group que se especializa da publicação de quadrinhos japoneses (mangás). \n Atualmente toda a produção é terceirizada para a editora Mythos, desde a tradução, até a preparação e adaptação do mangá. \n Também é a equipe da Mythos que comparece a eventos e faz o contato leitor-empresa.\n Diferente do que ocorre no Japão, onde os mangás são publicados em antologias parecidas com listas telefônicas, impressas em papel-jornal e depois são reeditados nos chamados tankōbons (livros de bolso) e num papel de melhor qualidade, no Brasil, a Panini Comics começou a publicar mangás em 2002 no formato conhecido como meio-tankōbon (metade das páginas de um tankōbon tradicional).\n Essa prática continuou até 2005 e atualmente apenas um de seus títulos continua sendo lançado nesse formato, Berserk.\n No final de 2004 a editora lançou \"Lobo solitário\" no estilo tankōbon, mas com muito mais páginas como foi feito no Japão.\n Já em 2006 a editora passou a trabalhar com o formato Tankōbon (o mesmo que no japão e em formato maior, conhecido como formatinho), que continua até os dias atuais.\n Em maio de 2010, a Panini resolve publicar uma versão Pocket, reedição do mangá Naruto no formato dos tankōbons (11,4 cm x 17,7 cm), o título havia sido prometido pela editora em Junho de 2009.\n Em 2013 a editora lança também seu primeiro trabalho em formato de luxo e tamanho maior: Highschool of the Dead - Fullcolor Edition.\n Foi nesse ano também que a editora passou a trabalhar com databooks.");
//		Publisher p2 = new Publisher(null, "JBC", "A JBC foi criada em 1992 em Tóquio como uma redistribuidora de jornais em português destinados ao público dekassegui no Japão. Em 1993, passou a publicar também seu próprio jornal, o Jornal Tudo Bem. A empresa lançou seu primeiro produto no Brasil em setembro de 1997, a revista Made in Japan, que teve sua última edição em março de 2010. As edições em português de histórias em quadrinhos japonesas, conhecidas como mangá, são traduzidas pela própria JBC dos originais japoneses e são vendidas em todo o Brasil. A editora entrou no mercado de mangá em 2001 com títulos como Samurai X, Sakura Card Captors, Video Girl Ai e Guerreiras Mágicas de Rayearth. Inicialmente, a editora optou pelo formato original de tankōbon, semelhante ao livro de bolso, e o número de páginas de um volume publicado pela editora correspondia à metade de um volume japonês. A partir da publicação de X, no final de 2003, passou a usar o formato padrão equivalente ao mesmo número de páginas de um volume japonês. No Japão, além de continuar editando o Jornal Tudo Bem, a JBC lançou em 2005 a Gambare! uma revista em português sobre o mercado de trabalho japonês.");
//		Publisher p3 = new Publisher(null, "New Pop", "NewPOP Editora é uma editora brasileira especializada em mangás, comics, novels e variantes, fundada em janeiro de 2007 por Júnior Fonseca. A editora se destaca pelas obras de caráter mais alternativo, lançamentos de mangás brasileiros, várias adaptações de outras obras e light novels. Além do conteúdo diferenciado, a editora também utiliza formatos e acabamentos de maior qualidade (se comparado às outras editoras brasileiras), com páginas em papel offset e coloridas. O lema da editora é \"Uma alternativa a todos\", mas desde a atualização do design do site o slogan sumiu.");
//
//		publisherRepository.saveAll(Arrays.asList(p1, p2, p3));
//
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//		SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
//		sdf3.setTimeZone(TimeZone.getTimeZone("GMT"));
//		
//		Author a1 = new Author(null, "Masashi Kishimoto");
//		authorRepository.save(a1);
//
//		Title t1 = new Title(null, "Naruto", p1, a1, true, sdf.parse("05/2007"), sdf.parse("06/2015"));
//		Title t2 = new Title(null, "Your Name", p2, null, true, sdf.parse("08/2017"), sdf.parse("01/2018"));
//		Title t3 = new Title(null, "No Game No Life", p3,null, false, sdf.parse("12/2014"), null);
//		Title t4 = new Title(null, "Wotakoi", p1,null, false, sdf.parse("03/2019"), null);
//		Title t5 = new Title(null, "Sword Art Online", p1,null, false, sdf.parse("12/2014"), null);
//		Title t6 = new Title(null, "Chobits", p2,null, false, sdf.parse("12/2014"), null);
//		Title t7 = new Title(null, "Re:Zero", p3,null, false, sdf.parse("01/2018"), null);
//		Title t8 = new Title(null, "No Game No Life Desu", p3,null, false, sdf.parse("12/2014"), null);
//		Title t9 = new Title(null, "The Legend Of Zelda", p1,null, false, sdf.parse("12/2014"), null);
//		Title t10 = new Title(null, "Afro Samurai", p1,null, false, sdf.parse("12/2014"), null);
//	
//		Volume v1 = new Volume(null, "Volume 1", t1, sdf.parse("05/2007"), 8.90);
//		Volume v2 = new Volume(null, "Volume 2", t1, sdf.parse("06/2007"), 8.90);
//		Volume v3 = new Volume(null, "Volume 3", t1, sdf.parse("07/2007"), 8.90);
//		Volume v4 = new Volume(null, "Volume 4", t1, sdf.parse("08/2007"), 8.90);
//		Volume v5 = new Volume(null, "Volume 5", t1, sdf.parse("09/2007"), 8.90);
//		Volume v6 = new Volume(null, "Volume 6", t1, sdf.parse("10/2007"), 8.90);
//		
//		Volume v07 = new Volume(null, "Volume 1", t3, sdf.parse("09/2007"), 10.90);
//		Volume v08 = new Volume(null, "Volume 2", t3, sdf.parse("10/2007"), 10.90);
//		Volume v09 = new Volume(null, "Volume 1", t2, sdf.parse("05/2012"), 15.90);
//		
//		Volume v7 = new Volume(null, "Volume 7", t1, sdf.parse("11/2007"), 8.90);
//		Volume v8 = new Volume(null, "Volume 8", t1, sdf.parse("12/2007"), 8.90);
//		Volume v9 = new Volume(null, "Volume 9", t1, sdf.parse("01/2008"), 8.90);
//		Volume v10 = new Volume(null, "Volume 10", t1, sdf.parse("02/2008"), 9.90);
//		Volume v11 = new Volume(null, "Volume 11", t1, sdf.parse("03/2008"), 9.90);
//		Volume v12 = new Volume(null, "Volume 12", t1, sdf.parse("04/2008"), 9.90);
//		Volume v13 = new Volume(null, "Volume 13", t1, sdf.parse("05/2008"), 9.90);
//		Volume v14 = new Volume(null, "Volume 14", t1, sdf.parse("06/2008"), 9.90);
//		Volume v15 = new Volume(null, "Volume 15", t1, sdf.parse("07/2008"), 9.90);
//		Volume v16 = new Volume(null, "Volume 16", t1, sdf.parse("08/2008"), 9.90);
//		Volume v17 = new Volume(null, "Volume 17", t1, sdf.parse("09/2008"), 9.90);
//		Volume v18 = new Volume(null, "Volume 18", t1, sdf.parse("10/2008"), 9.90);
//		Volume v19 = new Volume(null, "Volume 19", t1, sdf.parse("11/2008"), 9.90);
//		Volume v20 = new Volume(null, "Volume 20", t1, sdf.parse("12/2008"), 9.90);
//		Volume v21 = new Volume(null, "Volume 21", t1, sdf.parse("01/2009"), 9.90);
//		Volume v22 = new Volume(null, "Volume 22", t1, sdf.parse("02/2009"), 9.90);
//		Volume v23 = new Volume(null, "Volume 23", t1, sdf.parse("03/2009"), 9.90);
//		Volume v24 = new Volume(null, "Volume 24", t1, sdf.parse("04/2009"), 9.90);
//		Volume v25 = new Volume(null, "Volume 25", t1, sdf.parse("05/2009"), 9.90);
//		Volume v26 = new Volume(null, "Volume 26", t1, sdf.parse("06/2009"), 9.90);
//		Volume v27 = new Volume(null, "Volume 27", t1, sdf.parse("07/2009"), 9.90);
//		Volume v28 = new Volume(null, "Volume 28", t1, sdf.parse("08/2009"), 9.90);
//		Volume v29 = new Volume(null, "Volume 29", t1, sdf.parse("09/2009"), 9.90);
//		Volume v30 = new Volume(null, "Volume 30", t1, sdf.parse("10/2009"), 9.90);
//		Volume v31 = new Volume(null, "Volume 31", t1, sdf.parse("11/2009"), 9.90);
//		Volume v32 = new Volume(null, "Volume 32", t1, sdf.parse("12/2009"), 9.90);
//		Volume v33 = new Volume(null, "Volume 33", t1, sdf.parse("01/2010"), 9.90);
//		Volume v34 = new Volume(null, "Volume 34", t1, sdf.parse("02/2010"), 9.90);
//		Volume v35 = new Volume(null, "Volume 35", t1, sdf.parse("03/2010"), 9.90);
//		Volume v36 = new Volume(null, "Volume 36", t1, sdf.parse("04/2010"), 9.90);
//		Volume v37 = new Volume(null, "Volume 37", t1, sdf.parse("05/2010"), 9.90);
//		Volume v38 = new Volume(null, "Volume 38", t1, sdf.parse("06/2010"), 9.90);
//		Volume v39 = new Volume(null, "Volume 39", t1, sdf.parse("07/2010"), 9.90);
//		Volume v40 = new Volume(null, "Volume 40", t1, sdf.parse("08/2010"), 9.90);
//		Volume v41 = new Volume(null, "Volume 41", t1, sdf.parse("09/2010"), 9.90);
//		Volume v42 = new Volume(null, "Volume 42", t1, sdf.parse("10/2010"), 9.90);
//		Volume v43 = new Volume(null, "Volume 43", t1, sdf.parse("11/2010"), 9.90);
//		Volume v44 = new Volume(null, "Volume 44", t1, sdf.parse("12/2010"), 9.90);
//		Volume v45 = new Volume(null, "Volume 45", t1, sdf.parse("01/2011"), 9.90);
//		Volume v46 = new Volume(null, "Volume 46", t1, sdf.parse("02/2011"), 9.90);
//		Volume v47 = new Volume(null, "Volume 47", t1, sdf.parse("03/2011"), 9.90);
//		Volume v48 = new Volume(null, "Volume 48", t1, sdf.parse("04/2011"), 9.90);
//		Volume v49 = new Volume(null, "Volume 49", t1, sdf.parse("06/2011"), 9.90);
//		Volume v50 = new Volume(null, "Volume 50", t1, sdf.parse("08/2011"), 9.90);
//		Volume v51 = new Volume(null, "Volume 51", t1, sdf.parse("10/2011"), 9.90);
//		Volume v52 = new Volume(null, "Volume 52", t1, sdf.parse("12/2011"), 9.90);
//		Volume v53 = new Volume(null, "Volume 53", t1, sdf.parse("02/2012"), 10.90);
//		Volume v54 = new Volume(null, "Volume 54", t1, sdf.parse("04/2012"), 10.90);
//		Volume v55 = new Volume(null, "Volume 55", t1, sdf.parse("06/2012"), 10.90);
//		Volume v56 = new Volume(null, "Volume 56", t1, sdf.parse("08/2012"), 10.90);
//		Volume v57 = new Volume(null, "Volume 57", t1, sdf.parse("10/2012"), 10.90);
//		Volume v58 = new Volume(null, "Volume 58", t1, sdf.parse("12/2012"), 10.90);
//		Volume v59 = new Volume(null, "Volume 59", t1, sdf.parse("02/2013"), 10.90);
//		Volume v60 = new Volume(null, "Volume 60", t1, sdf.parse("05/2013"), 10.90);
//		Volume v61 = new Volume(null, "Volume 61", t1, sdf.parse("07/2013"), 10.90);
//		Volume v62 = new Volume(null, "Volume 62", t1, sdf.parse("09/2013"), 10.90);
//		Volume v63 = new Volume(null, "Volume 63", t1, sdf.parse("11/2013"), 10.90);
//		Volume v64 = new Volume(null, "Volume 64", t1, sdf.parse("01/2014"), 10.90);
//		Volume v65 = new Volume(null, "Volume 65", t1, sdf.parse("04/2014"), 10.90);
//		Volume v66 = new Volume(null, "Volume 66", t1, sdf.parse("06/2014"), 11.50);
//		Volume v67 = new Volume(null, "Volume 67", t1, sdf.parse("08/2014"), 11.50);
//		Volume v68 = new Volume(null, "Volume 68", t1, sdf.parse("10/2014"), 11.50);
//		Volume v69 = new Volume(null, "Volume 69", t1, sdf.parse("12/2014"), 11.50);
//		Volume v70 = new Volume(null, "Volume 70", t1, sdf.parse("02/2015"), 11.50);
//		Volume v71 = new Volume(null, "Volume 71", t1, sdf.parse("04/2015"), 11.50);
//		Volume v72 = new Volume(null, "Volume 72", t1, sdf.parse("06/2015"), 11.50);
//		Volume v1x = new Volume(null, "O Livro Secreto do Confronto", t1, sdf.parse("05/2013"), 16.90);
//		Volume v1x2 = new Volume(null, "O Livro Secreto do Guerreiro", t1, sdf.parse("10/2014"), 17.90);
//		Volume v1x3 = new Volume(null, "O Livro Secreto da Batalha", t1, null, null);
//		Volume v1x4 = new Volume(null, "O Livro Secreto da Formação", t1, sdf.parse("12/2016"), 18.90);
//
//		titleRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10));
//
//		volumeRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10));
//		volumeRepository.saveAll(Arrays.asList(v11, v12, v13, v14, v15, v16, v17, v18, v19, v20));
//		volumeRepository.saveAll(Arrays.asList(v21, v22, v23, v24, v25, v26, v27, v28, v29, v30));
//		volumeRepository.saveAll(Arrays.asList(v31, v32, v33, v34, v35, v36, v37, v38, v39, v40));
//		volumeRepository.saveAll(Arrays.asList(v41, v42, v43, v44, v45, v46, v47, v48, v49, v50));
//		volumeRepository.saveAll(Arrays.asList(v51, v52, v53, v54, v55, v56, v57, v58, v59, v60));
//		volumeRepository.saveAll(Arrays.asList(v61, v62, v63, v64, v65, v66, v67, v68, v69, v70));
//		volumeRepository.saveAll(Arrays.asList(v71, v72, v1x, v1x2, v1x3, v1x4, v07, v08, v09));
//
//		User user1 = new User(null, "Felipe Carvalho de Souza", "desouzafelipecarvalho@gmail.com", pe.encode("123"), sdf3.parse("14/05/2019"));
//		user1.addPerfil(Perfil.ADMIN);
//		user1.setLastPasswordChange(new Date());
//		user1.setEnabled(true);
//		user1.setChangePasswordOnLogin(false);
//		
//		User user2 = new User(null, "Isabela de Paula Bernardo", "belinhaenois@gmail.com", pe.encode("1234"), sdf3.parse("24/02/2019"));
//
//		Collection c1 = new Collection(null, user1);
//		Collection c2 = new Collection(null, user2);
//
//		CollectionTitle ct1 = new CollectionTitle(c1, t1);
//		CollectionTitle ct2 = new CollectionTitle(c1, t2);
//		CollectionTitle ct3 = new CollectionTitle(c1, t3);
//		CollectionTitle ct4 = new CollectionTitle(c1, t4);
//		CollectionTitle ct5 = new CollectionTitle(c1, t5);
//		CollectionTitle ct6 = new CollectionTitle(c1, t6);
//		CollectionTitle ct7 = new CollectionTitle(c1, t7);
//		CollectionTitle ct8 = new CollectionTitle(c1, t8);
//		CollectionTitle ct9 = new CollectionTitle(c1, t9);
//		CollectionTitle ct10 = new CollectionTitle(c1, t10);
//
//
//		c1.getCollectionTitle().addAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5, ct6, ct7, ct8, ct9, ct10));
//
//		t1.getCollectionsTitle().addAll(Arrays.asList(ct1));
//		t2.getCollectionsTitle().addAll(Arrays.asList(ct2));
//		t3.getCollectionsTitle().addAll(Arrays.asList(ct3));
//		t4.getCollectionsTitle().addAll(Arrays.asList(ct4));
//		t5.getCollectionsTitle().addAll(Arrays.asList(ct5));
//		t6.getCollectionsTitle().addAll(Arrays.asList(ct6));
//		t7.getCollectionsTitle().addAll(Arrays.asList(ct7));
//		t8.getCollectionsTitle().addAll(Arrays.asList(ct8));
//		t9.getCollectionsTitle().addAll(Arrays.asList(ct9));
//		t10.getCollectionsTitle().addAll(Arrays.asList(ct10));
//		
//		userRepository.saveAll(Arrays.asList(user1,user2));
//
//		collectionRepository.saveAll(Arrays.asList(c1,c2));
//
//		collectionTitleRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5, ct6, ct7, ct8, ct9, ct10));
//
//		VolumeUser vu1 = new VolumeUser(ct1, v1);
//		VolumeUser vu2 = new VolumeUser(ct1, v2);
//		VolumeUser vu3 = new VolumeUser(ct1, v3);
//		VolumeUser vu4 = new VolumeUser(ct1, v4);
//		VolumeUser vu5 = new VolumeUser(ct1, v5);
//		VolumeUser vu6 = new VolumeUser(ct1, v6);
//		VolumeUser vu7 = new VolumeUser(ct1, v7);
//		VolumeUser vu8 = new VolumeUser(ct1, v8);
//		VolumeUser vu9 = new VolumeUser(ct1, v9);
//		VolumeUser vu10 = new VolumeUser(ct1, v10);
//		VolumeUser vu11 = new VolumeUser(ct1, v11);
//		VolumeUser vu12 = new VolumeUser(ct1, v12);
//		VolumeUser vu13 = new VolumeUser(ct1, v13);
//		VolumeUser vu14 = new VolumeUser(ct1, v14);
//		VolumeUser vu15 = new VolumeUser(ct1, v15);
//		VolumeUser vu16 = new VolumeUser(ct1, v16);
//		VolumeUser vu17 = new VolumeUser(ct1, v17);
//		VolumeUser vu18 = new VolumeUser(ct1, v18);
//		VolumeUser vu19 = new VolumeUser(ct1, v19);
//		VolumeUser vu20 = new VolumeUser(ct1, v20);
//		VolumeUser vu21 = new VolumeUser(ct1, v21);
//		VolumeUser vu22 = new VolumeUser(ct1, v22);
//		VolumeUser vu23 = new VolumeUser(ct1, v23);
//		VolumeUser vu24 = new VolumeUser(ct1, v24);
//		VolumeUser vu25 = new VolumeUser(ct1, v25);
//		VolumeUser vu26 = new VolumeUser(ct1, v26);
//		VolumeUser vu27 = new VolumeUser(ct1, v27);
//		VolumeUser vu28 = new VolumeUser(ct1, v28);
//		VolumeUser vu29 = new VolumeUser(ct1, v29);
//		VolumeUser vu30 = new VolumeUser(ct1, v30);
//		VolumeUser vu31 = new VolumeUser(ct1, v31);
//		VolumeUser vu32 = new VolumeUser(ct1, v32);
//		VolumeUser vu33 = new VolumeUser(ct1, v33);
//		VolumeUser vu34 = new VolumeUser(ct1, v34);
//		VolumeUser vu35 = new VolumeUser(ct1, v35);
//		VolumeUser vu36 = new VolumeUser(ct1, v36);
//		VolumeUser vu37 = new VolumeUser(ct1, v37);
//		VolumeUser vu38 = new VolumeUser(ct1, v38);
//		VolumeUser vu39 = new VolumeUser(ct1, v39);
//		VolumeUser vu40 = new VolumeUser(ct1, v40);
//		VolumeUser vu41 = new VolumeUser(ct1, v41);
//		VolumeUser vu42 = new VolumeUser(ct1, v42);
//		VolumeUser vu43 = new VolumeUser(ct1, v43);
//		VolumeUser vu44 = new VolumeUser(ct1, v44);
//		VolumeUser vu45 = new VolumeUser(ct1, v45);
//		VolumeUser vu46 = new VolumeUser(ct1, v46);
//		VolumeUser vu47 = new VolumeUser(ct1, v47);
//		VolumeUser vu48 = new VolumeUser(ct1, v48);
//		VolumeUser vu49 = new VolumeUser(ct1, v49);
//		VolumeUser vu50 = new VolumeUser(ct1, v50);
//		VolumeUser vu51 = new VolumeUser(ct1, v51);
//		VolumeUser vu52 = new VolumeUser(ct1, v52);
//		VolumeUser vu53 = new VolumeUser(ct1, v53);
//		VolumeUser vu54 = new VolumeUser(ct1, v54);
//		VolumeUser vu55 = new VolumeUser(ct1, v55);
//		VolumeUser vu56 = new VolumeUser(ct1, v56);
//		VolumeUser vu57 = new VolumeUser(ct1, v57);
//		VolumeUser vu58 = new VolumeUser(ct1, v58);
//		VolumeUser vu59 = new VolumeUser(ct1, v59);
//		VolumeUser vu60 = new VolumeUser(ct1, v60);
//		VolumeUser vu61 = new VolumeUser(ct1, v61);
//		VolumeUser vu62 = new VolumeUser(ct1, v62);
//		VolumeUser vu63 = new VolumeUser(ct1, v63);
//		VolumeUser vu64 = new VolumeUser(ct1, v64);
//		VolumeUser vu65 = new VolumeUser(ct1, v65);
//		VolumeUser vu66 = new VolumeUser(ct1, v66);
//		VolumeUser vu67 = new VolumeUser(ct1, v67);
//		VolumeUser vu68 = new VolumeUser(ct1, v68);
//		VolumeUser vu69 = new VolumeUser(ct1, v69);
//		VolumeUser vu70 = new VolumeUser(ct1, v70);
//		VolumeUser vu71 = new VolumeUser(ct1, v71);
//		VolumeUser vu72 = new VolumeUser(ct1, v72);
//		
//		VolumeUser vu07 = new VolumeUser(ct2, v07);
//		VolumeUser vu08 = new VolumeUser(ct2, v08);
//		VolumeUser vu09 = new VolumeUser(ct2, v09);
//		
//		VolumeUser vux = new VolumeUser(ct1, v1x);
//		VolumeUser vux1 = new VolumeUser(ct1, v1x2);
//		VolumeUser vux2 = new VolumeUser(ct1, v1x3);
//		VolumeUser vux3 = new VolumeUser(ct1, v1x4);
//		
//
//		volumeUserRepository.saveAll(Arrays.asList(vu1, vu2, vu3, vu4, vu5, vu6, vu7, vu8, vu9, vu10));
//		volumeUserRepository.saveAll(Arrays.asList(vu11, vu12, vu13, vu14, vu15, vu16, vu17, vu18, vu19, vu20));
//		volumeUserRepository.saveAll(Arrays.asList(vu21, vu22, vu23, vu24, vu25, vu26, vu27, vu28, vu29, vu30));
//		volumeUserRepository.saveAll(Arrays.asList(vu31, vu32, vu33, vu34, vu35, vu36, vu37, vu38, vu39, vu40));
//		volumeUserRepository.saveAll(Arrays.asList(vu41, vu42, vu43, vu44, vu45, vu46, vu47, vu48, vu49, vu50));
//		volumeUserRepository.saveAll(Arrays.asList(vu51, vu52, vu53, vu54, vu55, vu56, vu57, vu58, vu59, vu60));
//		volumeUserRepository.saveAll(Arrays.asList(vu61, vu62, vu63, vu64, vu65, vu66, vu67, vu68, vu69, vu70));
//		volumeUserRepository.saveAll(Arrays.asList(vu71, vu72, vux, vux1, vux2, vux3));
//		
//		volumeUserRepository.saveAll(Arrays.asList(vu07, vu08, vu09));
//		
//		Review r1 = new Review(null, t1, 10, "Naruto é massa", sdf2.parse("03/02/2018 13:45"), user1);
//		Review r2 = new Review(null, t1, 8, "Narutão da hora", sdf2.parse("08/01/2017 05:45"), user1);
//		Review r3 = new Review(null, t2, 7, "Podia ser melhor", sdf2.parse("18/12/2016 23:15"), user1);
//		
//		user1.getGivenReviews().addAll(Arrays.asList(r1,r2,r3));
//		
//		reviewRepository.saveAll(Arrays.asList(r1,r2,r3));

	}
}

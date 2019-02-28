package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;

@Service
public class PublisherService {
	
	@Autowired
	private PublisherRepository repo;
	
	public Publisher find(Integer id) {
		Optional<Publisher> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Publisher> findAll(){
		return repo.findAll();
	}
	
	public Publisher insert(Publisher obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Publisher update(Publisher obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}

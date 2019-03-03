package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.DataIntegrityException;

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
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Editora que possui Títulos");
		}
	}
}

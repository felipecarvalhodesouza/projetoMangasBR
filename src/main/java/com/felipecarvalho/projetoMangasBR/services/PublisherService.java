package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Page<Publisher> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);			
	}
	
	public Publisher insert(Publisher obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Publisher update(Publisher obj) {
		Publisher newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
	
	private void updateData(Publisher newObj, Publisher obj) {
		newObj.setName(obj.getName());
	}
}

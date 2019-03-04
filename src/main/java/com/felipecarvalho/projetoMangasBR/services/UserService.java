package com.felipecarvalho.projetoMangasBR.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.DataIntegrityException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CollectionRepository collectionRepository;
	
	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public User insert(User obj) {
		obj.setId(null);
		Collection col = new Collection(null, obj);
		obj.setCollection(col);
		obj = repo.save(obj);
		collectionRepository.save(col);
		return obj; 
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}

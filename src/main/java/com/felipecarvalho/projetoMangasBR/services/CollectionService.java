package com.felipecarvalho.projetoMangasBR.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;

@Service
public class CollectionService {
	
	@Autowired
	private CollectionRepository repo;
	
	public Collection findByUser(Integer userId){
		return repo.findCollection(userId);
	}
}

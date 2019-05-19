package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Author;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.repositories.AuthorRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository repo;
	
	public Author find(Integer id) {
		Optional<Author> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Autor não encontrado! Id: " + id + ", Tipo: " + Author.class.getName()));
	}
	
	public List<Title> findByAuthor(Integer authorId){
		return repo.findTitles(authorId);
	}

}

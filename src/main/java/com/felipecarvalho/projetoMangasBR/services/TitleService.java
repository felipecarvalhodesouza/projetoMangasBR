package com.felipecarvalho.projetoMangasBR.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;

@Service
public class TitleService {
	
	@Autowired
	private TitleRepository repo;
	
	public Title find(Integer id) {
		Optional<Title> obj = repo.findById(id);
		return obj.orElse(null);
	}
}

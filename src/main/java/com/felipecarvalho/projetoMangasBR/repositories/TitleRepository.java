package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecarvalho.projetoMangasBR.domain.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{
	
}

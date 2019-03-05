package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{
	
	@Transactional(readOnly=true)
	Page<Title> findDistinctByNameContaining(String name, Pageable pageRequest);
}

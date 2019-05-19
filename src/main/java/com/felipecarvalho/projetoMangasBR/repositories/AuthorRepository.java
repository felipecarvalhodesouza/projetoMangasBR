package com.felipecarvalho.projetoMangasBR.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Author;
import com.felipecarvalho.projetoMangasBR.domain.Title;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Title obj WHERE obj.author.id = :authorId ORDER BY obj.name")
	public List<Title> findTitles(@Param("authorId")Integer author_Id);
}

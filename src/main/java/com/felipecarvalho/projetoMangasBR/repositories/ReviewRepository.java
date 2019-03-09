package com.felipecarvalho.projetoMangasBR.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Review obj WHERE obj.title.id = :titleId ORDER BY obj.id")
	public List<Review> findReviews(@Param("titleId")Integer title_Id);
}

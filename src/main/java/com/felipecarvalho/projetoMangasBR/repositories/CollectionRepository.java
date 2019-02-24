package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Collection obj WHERE obj.owner.id = :userId")
	public Collection findCollection(@Param("userId")Integer user_Id);
}

package com.felipecarvalho.projetoMangasBR.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Volume;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Volume obj WHERE obj.title.id = :titleId ORDER BY obj.name")
	public List<Volume> findVolumes(@Param("titleId")Integer title_Id);
}

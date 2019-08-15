package com.felipecarvalho.projetoMangasBR.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;

@Repository
public interface VolumeUserRepository extends JpaRepository<VolumeUser, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM VolumeUser obj WHERE obj.collectionTitle.id.title = :titleId AND obj.collectionTitle.id.collection = :collectionId ORDER BY obj.id")
	public List<VolumeUser> findVolumes(@Param("collectionId")Collection collection_Id, @Param("titleId")Title title_Id);
	
	@Transactional(readOnly=true)
	public Page<VolumeUser> findDistinctByCollectionTitleOrderByVolumeIdAsc(CollectionTitle collectionTitle, Pageable pageRequest);
	
}

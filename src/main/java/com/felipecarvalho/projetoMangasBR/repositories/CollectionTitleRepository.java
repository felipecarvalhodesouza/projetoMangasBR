package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;

@Repository
public interface CollectionTitleRepository extends JpaRepository<CollectionTitle, Integer>{

}

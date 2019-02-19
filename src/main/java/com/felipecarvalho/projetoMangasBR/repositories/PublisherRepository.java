package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{

}

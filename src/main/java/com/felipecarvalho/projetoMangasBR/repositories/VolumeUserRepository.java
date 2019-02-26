package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;

@Repository
public interface VolumeUserRepository extends JpaRepository<VolumeUser, Integer>{

}

package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeUserRepository;

@Service
public class CollectionService {
	
	@Autowired
	private CollectionRepository repo;
	
	@Autowired
	private VolumeUserRepository volumeUserRepository;
	
	public Collection findByUser(Integer userId){
		return repo.findCollection(userId);
	}
	
	public List<VolumeUser> findTitleVolumes(Collection userId, Title title){
		return volumeUserRepository.findVolumes(userId, title);
	}
}

package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeRepository;

@Service
public class VolumeService {
	
	@Autowired
	private VolumeRepository repo;
	
	public List<Volume> findByTitle(Integer titleId){
		return repo.findVolumes(titleId);
	}

}

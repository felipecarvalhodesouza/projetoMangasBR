package com.felipecarvalho.projetoMangasBR.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeUserRepository;

@Service
public class VolumeUserService {
	
	@Autowired
	private VolumeUserRepository volumeUserRepository;
	
	public VolumeUser updateVolumeUser(VolumeUser volumeUser) {
		return volumeUserRepository.save(volumeUser);
	}
	
	public VolumeUser findVolumeUser(Integer volumeUserId) {
		return volumeUserRepository.findVolumeUserById(volumeUserId);
	}
}

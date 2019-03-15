package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionTitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeUserRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectAlreadyExistsException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class CollectionService {
	
	@Autowired
	private CollectionRepository repo;
	
	@Autowired
	private VolumeUserRepository volumeUserRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private CollectionTitleRepository collectionTitleRepository;
	
	public Collection findByUser(Integer userId){
		if(repo.findCollection(userId)==null) {
			throw new ObjectNotFoundException("O usuário pesquisado não existe");
		}
		return repo.findCollection(userId);
	}
	
	public List<VolumeUser> findTitleVolumes(Collection userId, Title title){
		return volumeUserRepository.findVolumes(userId, title);
	}
	
	public Collection insertTitle(Collection col, Integer titleId){
		Title title = titleRepository.
				findById(titleId).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + titleId + ", Tipo: " + Title.class.getName()));
		if(col.getTitles().contains(title)) {
			throw new ObjectAlreadyExistsException("O título já está na coleção");
		}
		
		CollectionTitle ct = new CollectionTitle(col, title);
		
		collectionTitleRepository.save(ct);
		
		for(Volume volume : title.getVolumes()) {
			VolumeUser vl = new VolumeUser(ct, volume);
			volumeUserRepository.save(vl);
			ct.getVolumesUser().add(vl);
		}
		
		col.getTitles().add(title);
		title.getCollections().add(col);
		col.getCollectionTitle().add(ct);
		title.getCollectionsTitle().add(ct);

		return col;
	}
	
	public Collection removeTitle(Collection col, Integer titleId){
		Title title = titleRepository.
				findById(titleId).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + titleId + ", Tipo: " + Title.class.getName()));
		if(!col.getTitles().contains(title)) {
			throw new ObjectNotFoundException("O título selecionado não existe na coleção");
		}
		
		for(CollectionTitle ct : col.getCollectionTitle()) {
			if (ct.getTitle().getId().equals(title.getId())) {
				col.getCollectionTitle().remove(ct);
				title.getCollectionsTitle().remove(ct);
				collectionTitleRepository.delete(ct);
				break;
			}
		}
		
		col.getTitles().remove(title);		
		title.getCollections().remove(col);

		return col;
	}
}

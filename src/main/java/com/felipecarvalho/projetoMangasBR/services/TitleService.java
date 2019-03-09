package com.felipecarvalho.projetoMangasBR.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.CollectionTitle;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.DataIntegrityException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class TitleService {
	
	@Autowired
	private TitleRepository repo;
	
	@Autowired
	private VolumeRepository volumeRepository;
	
	public Title find(Integer id) {
		Optional<Title> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Title.class.getName()));
	}
	
	public Title update(Title obj) {
		Title newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um título que possui volumes relacionados");
		}
	}
	
	public List<Title> findAll(){
		return repo.findAll();
	}
	
	public Page<Title> search(String name, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findDistinctByNameContainingIgnoreCase(name, pageRequest);
	}
	
	private void updateData(Title newObj, Title obj) {
		newObj.setName(obj.getName());
		newObj.setFinished(obj.isFinished());
		newObj.setEnd(obj.getEnd());
	}

	public Volume insertVolume(@Valid Volume obj) {
		Set<CollectionTitle> teste = obj.getTitle().getCollectionsTitle();
		for(CollectionTitle x : teste) {
			x.getVolumesUser().add(new VolumeUser(x, obj));
		}
		return volumeRepository.save(obj);
	}
}

package com.felipecarvalho.projetoMangasBR.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.services.TitleService;
import com.felipecarvalho.projetoMangasBR.services.VolumeService;

@RestController
@RequestMapping(value="/titles")
public class TitleResource {
	
	@Autowired
	private TitleService service;
	
	@Autowired
	private VolumeService volumeService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Title> find(@PathVariable Integer id){
		
		Title obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//entre chaves por ser um parâmetro de URL	
	@RequestMapping(value="/{titleId}/volumes", method=RequestMethod.GET)
	public ResponseEntity<List<Volume>> findVolumes(@PathVariable Integer titleId){
		List<Volume> list = volumeService.findByTitle(titleId);
		//pesquisar função stream da lista.
//		List<VolumeDTO> listDto = list.stream().map(obj -> new VolumeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

}

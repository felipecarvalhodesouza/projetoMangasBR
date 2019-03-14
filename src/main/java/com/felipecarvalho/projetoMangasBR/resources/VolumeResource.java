package com.felipecarvalho.projetoMangasBR.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.services.VolumeService;

@RestController
@RequestMapping(value="/volumes")
public class VolumeResource {
	
	@Autowired
	private VolumeService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Volume> find(@PathVariable Integer id){
		
		Volume obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}

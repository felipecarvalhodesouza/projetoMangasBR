package com.felipecarvalho.projetoMangasBR.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.services.VolumeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/volumes")
public class VolumeResource {
	
	@Autowired
	private VolumeService service;
	
	@ApiOperation(value="Busca de volume por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Volume> find(@PathVariable Integer id){
		
		Volume obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{titleId}/{volumeId}/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadVolumePicture(@RequestParam(name="file") MultipartFile file, 
													@PathVariable Integer titleId, 
													@PathVariable Integer volumeId){
		URI uri =  service.uploadVolumePicture(file, titleId, volumeId);
		return ResponseEntity.created(uri).build();
	}
}

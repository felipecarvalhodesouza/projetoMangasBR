package com.felipecarvalho.projetoMangasBR.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.dto.UserNewDTO;
import com.felipecarvalho.projetoMangasBR.services.CollectionService;
import com.felipecarvalho.projetoMangasBR.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private CollectionService collectionService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id){
		
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UserNewDTO objNewDto){
		User obj = service.fromDTO(objNewDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{userId}/collection", method=RequestMethod.GET)
	public ResponseEntity<Collection> findCollection(@PathVariable Integer userId){
		Collection col = collectionService.findByUser(userId);
		return ResponseEntity.ok().body(col);
	}
	
	@RequestMapping(value="/{userId}/collection/{titleId}", method=RequestMethod.GET)
	public ResponseEntity<List<VolumeUser>> findCollectionVolumes(@PathVariable Integer userId, @PathVariable Integer titleId){
		
		Title title = collectionService.findByUser(userId).getTitles().get(titleId-1);
		Collection collection = collectionService.findByUser(userId);

		List<VolumeUser> list = collectionService.findTitleVolumes(collection, title);
		return ResponseEntity.ok().body(list);
	}

}

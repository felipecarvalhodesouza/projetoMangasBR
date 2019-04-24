package com.felipecarvalho.projetoMangasBR.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.VolumeUser;
import com.felipecarvalho.projetoMangasBR.dto.UserDTO;
import com.felipecarvalho.projetoMangasBR.dto.UserNewDTO;
import com.felipecarvalho.projetoMangasBR.services.CollectionService;
import com.felipecarvalho.projetoMangasBR.services.TitleService;
import com.felipecarvalho.projetoMangasBR.services.UserService;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private CollectionService collectionService;
	
	@Autowired
	private TitleService titleService;
	
	@ApiOperation(value="Busca de usuário por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id){
		
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Busca de usuário por email")
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<User> find(@RequestParam(value="value") String email) {
		
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Edição de usuário por id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto, @PathVariable Integer id){
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Deleção de usuário por id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Inserção de usuário")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(HttpServletResponse response, @Valid @RequestBody UserNewDTO objNewDto){
		User obj = service.fromDTO(objNewDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Busca a coleção de um usuário por seu id")
	@RequestMapping(value="/{userId}/collection", method=RequestMethod.GET)
	public ResponseEntity<Collection> findCollection(@PathVariable Integer userId){
		service.find(userId);
		Collection col = collectionService.findByUser(userId);
		return ResponseEntity.ok().body(col);
	}
	
	@ApiOperation(value="Busca um título específico da coleção de um usuário")
	@RequestMapping(value="/{userId}/collection/{titleId}", method=RequestMethod.GET)
	public ResponseEntity<List<VolumeUser>> findCollectionVolumes(@PathVariable Integer userId, @PathVariable Integer titleId){
		service.find(userId);
		
		if(collectionService.findByUser(userId).getTitles().size()<(titleId)) {
			throw new ObjectNotFoundException("O título informado não foi encontrado na coleção");
		}
		
		Title title = collectionService.findByUser(userId).getTitles().get(titleId-1);
		Collection collection = collectionService.findByUser(userId);

		List<VolumeUser> list = collectionService.findTitleVolumes(collection, title);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Inserção por id de um título na coleção de um usuário")
	@RequestMapping(value="/{userId}/collection/{titleId}", method=RequestMethod.POST)
	public ResponseEntity<Void> insertTitle(@Valid @PathVariable Integer userId, @PathVariable Integer titleId){
		Collection col = collectionService.findByUser(userId);
		col.setId(userId);
		col = collectionService.insertTitle(col, titleId);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
		location = location.substring(0, location.length()-titleId.toString().length());
		Title title = titleService.find(titleId);
		URI uri = ServletUriComponentsBuilder.fromPath(location).path(Integer.toString(col.getTitles().indexOf(title) + 1)).buildAndExpand(col.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Deleção por id de um título na coleção de um usuário")
	@RequestMapping(value="/{userId}/collection/{titleId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeTitle(@Valid @PathVariable Integer userId, @PathVariable Integer titleId){
		Collection col = collectionService.findByUser(userId);
		col.setId(userId);
		col = collectionService.removeTitle(col, titleId);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file){
		URI uri =  service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}

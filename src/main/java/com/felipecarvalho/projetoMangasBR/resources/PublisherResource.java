package com.felipecarvalho.projetoMangasBR.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.dto.TitleNewDTO;
import com.felipecarvalho.projetoMangasBR.services.PublisherService;
import com.felipecarvalho.projetoMangasBR.services.TitleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/publishers")
public class PublisherResource {
	
	@Autowired
	private PublisherService service;
	
	@Autowired
	private TitleService titleService;
	
	@ApiOperation(value="Busca de editora por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Publisher> find(@PathVariable Integer id){
		
		Publisher obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Busca de títulos de determinada editora")
	@RequestMapping(value="/{id}/titles", method=RequestMethod.GET)
	public ResponseEntity<List<Title>> findTitles(@PathVariable Integer id){
		
		List<Title> list = service.findTitles(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Inserção de editora")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Publisher obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Inserção de um título para determinada editora")
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TitleNewDTO newObj, @PathVariable Integer id){
		Publisher pub = service.find(id);
		Title obj = service.fromDTO(newObj, pub);
		obj = service.insert(obj);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
		location = location.substring(0,location.length()-(Integer.toString(id).length()+11));
		URI uri = ServletUriComponentsBuilder.fromPath(location + "titles/").path(Integer.toString(titleService.findAll().indexOf(obj) + 1)).buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Busca de todas as editoras")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Publisher>> findAll() {
		List<Publisher> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Busca de editoras por paginação")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Publisher>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Publisher> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Edição de editora por id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Publisher obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Deleção de editora por id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

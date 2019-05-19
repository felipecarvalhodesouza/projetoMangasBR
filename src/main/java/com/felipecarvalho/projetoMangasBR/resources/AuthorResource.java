package com.felipecarvalho.projetoMangasBR.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.domain.Author;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.services.AuthorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/author")
public class AuthorResource {
	
	@Autowired
	private AuthorService service;
	
	@ApiOperation(value="Busca de autor por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Author> find(@PathVariable Integer id){
		
		Author obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Busca os títulos de determinado autor")
	@RequestMapping(value="/{authorId}/titles", method=RequestMethod.GET)
	public ResponseEntity<List<Title>> findTitles(@PathVariable Integer authorId){
		find(authorId);
		List<Title> list = service.findByAuthor(authorId);
		return ResponseEntity.ok().body(list);
	}
}

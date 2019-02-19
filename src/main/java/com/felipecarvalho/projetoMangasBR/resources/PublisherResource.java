package com.felipecarvalho.projetoMangasBR.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.services.PublisherService;

@RestController
@RequestMapping(value="/publishers")
public class PublisherResource {
	
	@Autowired
	private PublisherService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Publisher obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}

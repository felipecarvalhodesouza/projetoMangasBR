package com.felipecarvalho.projetoMangasBR.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.domain.Review;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.resources.utils.URL;
import com.felipecarvalho.projetoMangasBR.services.ReviewService;
import com.felipecarvalho.projetoMangasBR.services.TitleService;
import com.felipecarvalho.projetoMangasBR.services.VolumeService;

@RestController
@RequestMapping(value="/titles")
public class TitleResource {
	
	@Autowired
	private TitleService service;
	
	@Autowired
	private VolumeService volumeService;
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Title> find(@PathVariable Integer id){
		
		Title obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Title obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//entre chaves por ser um parâmetro de URL	
	@RequestMapping(value="/{titleId}/volumes", method=RequestMethod.GET)
	public ResponseEntity<List<Volume>> findVolumes(@PathVariable Integer titleId){
		List<Volume> list = volumeService.findByTitle(titleId);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{titleId}/reviews", method=RequestMethod.GET)
	public ResponseEntity<List<Review>> findReviews(@PathVariable Integer titleId){
		List<Review> list = reviewService.findByTitle(titleId);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Title>> findPage(
			@RequestParam(value="name", defaultValue="")String name,
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="id")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		String nameDecoded = URL.decodeParam(name);
		Page<Title> list = service.search(nameDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}

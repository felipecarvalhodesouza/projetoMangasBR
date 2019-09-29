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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipecarvalho.projetoMangasBR.domain.Review;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.dto.VolumeDTO;
import com.felipecarvalho.projetoMangasBR.resources.utils.URL;
import com.felipecarvalho.projetoMangasBR.services.ReviewService;
import com.felipecarvalho.projetoMangasBR.services.TitleService;
import com.felipecarvalho.projetoMangasBR.services.VolumeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/titles")
public class TitleResource {
	
	@Autowired
	private TitleService service;
	
	@Autowired
	private VolumeService volumeService;
	
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value="Busca de título por id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Title> find(@PathVariable Integer id){
		
		Title obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Edição de título por id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Title obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Deleção de título por id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Busca os volumes de determinado título")
	@RequestMapping(value="/{titleId}/volumes", method=RequestMethod.GET)
	public ResponseEntity<List<VolumeDTO>> findVolumes(@PathVariable Integer titleId){
		find(titleId);
		List<Volume> list = volumeService.findByTitle(titleId);
		List<VolumeDTO> listDto = volumeService.toDto(list);
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Insere um novo título")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertTitle(@Valid @RequestBody Title obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ApiOperation(value="Insere um novo volume em determinado título")
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Volume obj, @PathVariable Integer id){
		Title title = service.find(id);
		obj.setTitle(title);
		obj = service.insertVolume(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Busca as reviews de determinado título")
	@RequestMapping(value="/{titleId}/reviews", method=RequestMethod.GET)
	public ResponseEntity<List<Review>> findReviews(@PathVariable Integer titleId){
		List<Review> list = reviewService.findByTitle(titleId);
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Busca de títulos por parâmetros com paginação")
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
	
	@RequestMapping(value="/{titleId}/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadVolumePicture(@RequestParam(name="file") MultipartFile file, 
													@PathVariable Integer titleId){
		URI uri =  service.uploadTitlePicture(file, titleId);
		return ResponseEntity.created(uri).build();
	}
}

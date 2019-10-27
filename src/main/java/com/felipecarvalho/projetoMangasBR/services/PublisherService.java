package com.felipecarvalho.projetoMangasBR.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;
import com.felipecarvalho.projetoMangasBR.domain.Title;
import com.felipecarvalho.projetoMangasBR.dto.TitleNewDTO;
import com.felipecarvalho.projetoMangasBR.repositories.PublisherRepository;
import com.felipecarvalho.projetoMangasBR.repositories.TitleRepository;
import com.felipecarvalho.projetoMangasBR.services.exceptions.DataIntegrityException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class PublisherService {
	
	@Autowired
	private PublisherRepository repo;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3Service;
	
	public Publisher find(Integer id) {
		Optional<Publisher> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Publisher.class.getName()));
	}
	
	public List<Publisher> findAll(){
		return repo.findAll();
	}
	
	public Page<Publisher> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);			
	}
	
	public Publisher insert(Publisher obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Title insert(Title obj) {
		obj.setId(null);
		return titleRepository.save(obj);
	}
	
	public Publisher update(Publisher obj) {
		Publisher newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Editora que possui Títulos");
		}
	}
	
	private void updateData(Publisher newObj, Publisher obj) {
		newObj.setName(obj.getName());
	}
	
	public Title fromDTO(TitleNewDTO objDto, Publisher publisher) {
		Title title = new Title();
		title.setName(objDto.getName());
		title.setStart(objDto.getStart());
		title.setEnd(objDto.getEnd());
		title.setPublisher(publisher);
		return title;
	}

	public List<Title> findTitles(Integer id) {
		return find(id).getPublications();
	}
	
	public URI uploadPublisherPicture(MultipartFile multipartFile, Integer publisherId) {
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.resizeVolumeImg(jpgImage, 110, 75);
		
		String fileName = "publisher" + publisherId + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}

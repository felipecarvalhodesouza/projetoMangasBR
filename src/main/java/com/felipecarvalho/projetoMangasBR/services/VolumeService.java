package com.felipecarvalho.projetoMangasBR.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.felipecarvalho.projetoMangasBR.domain.Volume;
import com.felipecarvalho.projetoMangasBR.dto.VolumeDTO;
import com.felipecarvalho.projetoMangasBR.repositories.VolumeRepository;
import com.felipecarvalho.projetoMangasBR.security.UserSS;
import com.felipecarvalho.projetoMangasBR.services.exceptions.AuthorizationException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class VolumeService {
	
	@Autowired
	private VolumeRepository repo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3Service;
	
	@Value("${img.volume.prefix}")
	private String prefix;
	
	@Value("${img.volume.size}")
	private Integer size;
	
	public List<Volume> findByTitle(Integer titleId){
		return repo.findVolumes(titleId);
	}
	
	public Volume find(Integer id) {
		Optional<Volume> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Volume.class.getName()));
	}

	public List<VolumeDTO> toDto(List<Volume> list) {
		List<VolumeDTO> listDto = new ArrayList<VolumeDTO>();
		for(Volume volume : list) {
			VolumeDTO obj = new VolumeDTO();
			obj.setName(volume.getName());
			obj.setTitle(volume.getTitle());
			obj.setPrice(volume.getPrice());
			obj.setDate(volume.getDate());
			listDto.add(obj);
		}
		return listDto;
	}
	
	public URI uploadVolumePicture(MultipartFile multipartFile, Integer titleId, Integer volumeId) {
	
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.resizeVolumeImg(jpgImage, size, 180);
		
		String fileName = "title" + titleId + prefix + volumeId + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}

package com.felipecarvalho.projetoMangasBR.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.felipecarvalho.projetoMangasBR.domain.Collection;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.enums.Perfil;
import com.felipecarvalho.projetoMangasBR.dto.UserDTO;
import com.felipecarvalho.projetoMangasBR.dto.UserNewDTO;
import com.felipecarvalho.projetoMangasBR.repositories.CollectionRepository;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.security.UserSS;
import com.felipecarvalho.projetoMangasBR.services.exceptions.AuthorizationException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.DataIntegrityException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CollectionRepository collectionRepository;
	
	@Autowired
	private S3Service s3Service;
	
	public User find(Integer id) {
		
		UserSS user = UserAuthenticationService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public User insert(User obj) {
		obj.setId(null);
		Collection col = new Collection(null, obj);
		obj.setCollection(col);
		obj = repo.save(obj);
		collectionRepository.save(col);
		emailService.sendSignUpConfirmationHtmlEmail(obj);
		return obj; 
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserNewDTO objDto) {
		User user = new User(null, objDto.getName(), objDto.getEmail(), null);
		return user;
	}
	
	public User fromDTO(UserDTO objDto) {
		User user = new User(null, objDto.getName(), objDto.getEmail(), null);
		return user;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
}

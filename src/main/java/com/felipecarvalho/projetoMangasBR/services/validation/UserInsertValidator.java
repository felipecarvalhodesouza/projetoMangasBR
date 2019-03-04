package com.felipecarvalho.projetoMangasBR.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.dto.UserNewDTO;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserNewDTO obj, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		User aux = repo.findByEmail(obj.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
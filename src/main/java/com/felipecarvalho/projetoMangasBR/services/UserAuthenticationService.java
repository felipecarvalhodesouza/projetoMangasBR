package com.felipecarvalho.projetoMangasBR.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.felipecarvalho.projetoMangasBR.security.UserSS;

public class UserAuthenticationService {

	// função para retornar o usuário que está autenticado
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e){
			return null;
		}
	}
}
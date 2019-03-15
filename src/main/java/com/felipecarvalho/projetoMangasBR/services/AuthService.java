package com.felipecarvalho.projetoMangasBR.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.security.JWTUtil;
import com.felipecarvalho.projetoMangasBR.security.UserSS;

@Service
public class AuthService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;

	private Random rand = new Random();

	public String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0 ) { // gera um dígito
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) { //gera letra maiúscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { //gera letra minúscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
	public void validateToken(String token) {
		if(jwtUtil.tokenValido(token)) {
			
			UserSS user = new UserSS();
			user.setEnabled();
		}
	}
}

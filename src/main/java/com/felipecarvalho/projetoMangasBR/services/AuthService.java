package com.felipecarvalho.projetoMangasBR.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.security.JWTUtil;
import com.felipecarvalho.projetoMangasBR.services.exceptions.ObjectNotFoundException;
import com.felipecarvalho.projetoMangasBR.services.exceptions.TokenNotFoundException;

@Service
public class AuthService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	public BCryptPasswordEncoder pe;

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
		User user = userRepository.findByEmail(jwtUtil.getUsername(token));
		if(jwtUtil.tokenValido(token)) {
			if(user.getSenha()==null) {
				user.setEnabled(true);
				userRepository.save(user);
				emailService.sendSignUpValidationSuccesfulHtmlEmail(user);	
			}
		}
		else {
			throw new TokenNotFoundException("O token fornecido não é válido.");
		}
	}
	
	public void resendToken(String email) {
		User temp = userRepository.findByEmail(email);
		if(temp != null && !temp.isEnabled())
			emailService.sendSignUpConfirmationHtmlEmail(temp);
	}
	
	public void sendNewPassword(String email) {

		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		user.setSenha(pe.encode(newPass));
		
		user.setChangePasswordOnLogin(true);

		userRepository.save(user);

		emailService.sendNewPasswordHtmlEmail(user, newPass);
	}
}

package com.felipecarvalho.projetoMangasBR.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.security.JWTUtil;

public abstract class AbstractEmailService implements EmailService {
	
	@Autowired
	public BCryptPasswordEncoder pe;
	
	@Autowired
	public AuthService auth;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendSignUpConfirmationEmail(User obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(User obj) {
		String senha = auth.newPassword();
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		String token = jwtUtil.generateToken(obj.getEmail());
		sm.setSubject("Usuário cadastrado com sucesso! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Parabéns, seu usuário foi cadastrado com sucesso. /n"
				+ "Sua senha é:" + senha + "/n"
				+ "Para validar a sua conta, acesso o link a seguir: /n"
				+ "http://localhost:8080/auth?token=" + token);
		
		obj.setSenha(pe.encode(senha));
		userRepository.save(obj);
		
		return sm;
	}
}
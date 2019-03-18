package com.felipecarvalho.projetoMangasBR.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.felipecarvalho.projetoMangasBR.domain.User;

public interface EmailService {

	void sendSignUpConfirmationEmail(User obj);

	void sendEmail(SimpleMailMessage msg);
	
	// versão HTML dos métodos acima

	void sendSignUpConfirmationHtmlEmail(User obj);

	void sendHtmlEmail(MimeMessage msg);
}
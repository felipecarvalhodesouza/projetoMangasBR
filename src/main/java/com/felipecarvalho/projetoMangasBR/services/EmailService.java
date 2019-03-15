package com.felipecarvalho.projetoMangasBR.services;

import org.springframework.mail.SimpleMailMessage;

import com.felipecarvalho.projetoMangasBR.domain.User;

public interface EmailService {

	void sendOrderConfirmationEmail(User obj);

	void sendEmail(SimpleMailMessage msg);
}
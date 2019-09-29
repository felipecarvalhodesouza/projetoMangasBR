package com.felipecarvalho.projetoMangasBR.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.Volume;

public interface EmailService {

	void sendSignUpConfirmationEmail(User obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendValidationSucessfulEmail(User obj);
	
	void sendNewPasswordEmail(User user, String newPass);
	
	void sendNewVolumeNotificationEmail(User user, Volume volume);
	
	// versão HTML dos métodos acima

	void sendSignUpConfirmationHtmlEmail(User obj);

	void sendHtmlEmail(MimeMessage msg);
	
	void sendSignUpValidationSuccesfulHtmlEmail(User obj);
	
	void sendNewPasswordHtmlEmail(User user, String newPass);
	
	void sendNewVolumeNotificationHtmlEmail(User obj, Volume volume);
}
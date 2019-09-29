
package com.felipecarvalho.projetoMangasBR.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.domain.Volume;
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
	
	@Value("${location}")
	private String location;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendSignUpConfirmationEmail(User obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromSignUp(obj);
		sendEmail(sm);
	}
	
	@Override
	public void sendValidationSucessfulEmail(User obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromValidationSuccesful(obj);
		sendEmail(sm);
	}
	
	@Override
	public void sendNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm =  prepareSimpleMailNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}
	
	@Override
	public void sendNewVolumeNotificationEmail(User user, Volume volume) {
		SimpleMailMessage sm =  prepareSimpleMailNewVolumeNotificationEmail(user, volume);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromValidationSuccesful(User obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		String token = jwtUtil.generateToken(obj.getEmail());
		sm.setSubject("Usuário cadastrado com sucesso! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Parabéns, seu usuário foi cadastrado com sucesso. \n"
				+ "Para validar a sua conta, acesso o link a seguir: \n"
				+ location + token);
		return sm;
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromSignUp(User obj) {
		String senha = auth.newPassword();
		obj.setSenha(pe.encode(senha));
		userRepository.save(obj);
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Conta ativada com sucesso!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Parabéns " + obj.getName() + "! Você agora faz parte da comunidade de colecionadores brasileiros. \n"
				+ "Sua senha é: "+ senha + ". Lembre-se de trocá-la ao fazer seu primeiro login. \n"
				+ "A equipe MangasBR espera que você aproveite bastante o aplicativo, e fica a disposição para sugestões.");
		return sm;
	}
	
	protected SimpleMailMessage prepareSimpleMailNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
	protected SimpleMailMessage prepareSimpleMailNewVolumeNotificationEmail(User obj, Volume volume) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Novidades na sua coleção");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá, " + obj.getName() + " \n"
				+ "Um novo volume foi adicionado em um título que você acompanha atualmente, \n"
				+ "Título: " + volume.getTitleName() + "\n"
				+ "Volume: " + volume.getName());
		return sm;
	}
	
	protected String htmlFromTemplateSignUp(User obj) {
		Context context = new Context();
		String token = jwtUtil.generateToken(obj.getEmail());
		context.setVariable("user", obj);
		context.setVariable("location", location + token);
		return templateEngine.process("email/signUpConfirmation", context);
	}
	
	protected String htmlFromTemplateValidationSuccesful(User obj) {
		Context context = new Context();
		String senha = auth.newPassword();
		obj.setSenha(pe.encode(senha));
		userRepository.save(obj);
		context.setVariable("user", obj);
		context.setVariable("senha", senha);
		return templateEngine.process("email/signUpSucessfulConfirmation", context);
	}
	
	protected String htmlFromTemplateNewPassword(User user, String newPass) {
		Context context = new Context();
		context.setVariable("user", user);
		context.setVariable("senha", newPass);
		return templateEngine.process("email/newPassword", context);
	}
	
	protected String htmlFromTemplateNewVolumeNotification(User user, Volume volume) {
		Context context = new Context();
		context.setVariable("user", user);
		context.setVariable("volume", volume);
		return templateEngine.process("email/volumeNotification", context);
	}
	
	protected MimeMessage prepareMimeMessageFromSignUp(User obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Usuário cadastrado com sucesso! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateSignUp(obj), true);
		return mimeMessage;
	}
	
	protected MimeMessage prepareMimeMessageFromValidationSuccesful(User obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Conta ativada com sucesso!");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateValidationSuccesful(obj), true);
		return mimeMessage;
	}
	
	protected MimeMessage prepareMimeMessageFromNewPassword(User obj, String newPass) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Solicitação de nova senha");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateNewPassword(obj, newPass), true);
		return mimeMessage;
	}
	
	protected MimeMessage prepareMimeMessageFromVolumeNotification(User obj, Volume volume) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Novidades em sua coleção");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateNewVolumeNotification(obj, volume), true);
		return mimeMessage;
	}
	
	@Override
	public void sendSignUpConfirmationHtmlEmail(User obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromSignUp(obj);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendSignUpConfirmationEmail(obj);
		}
	}
	
	public void sendSignUpValidationSuccesfulHtmlEmail(User obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromValidationSuccesful(obj);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendValidationSucessfulEmail(obj);
		}
	}
	
	public void sendNewPasswordHtmlEmail(User obj, String newPass) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromNewPassword(obj, newPass);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendNewPasswordEmail(obj, newPass);
		}
	}
	
	public void sendNewVolumeNotificationHtmlEmail(User obj, Volume volume) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromVolumeNotification(obj, volume);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendNewVolumeNotificationEmail(obj, volume);
		}
	}
}
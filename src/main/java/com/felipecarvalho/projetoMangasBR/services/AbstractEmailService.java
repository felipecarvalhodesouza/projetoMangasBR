
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
	
	public void sendValidationSucessfulEmail(User obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromValidationSuccesful(obj);
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
	
}
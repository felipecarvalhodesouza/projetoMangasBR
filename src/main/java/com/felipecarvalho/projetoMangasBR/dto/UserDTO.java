package com.felipecarvalho.projetoMangasBR.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import com.felipecarvalho.projetoMangasBR.services.validation.UserUpdate;

@UserUpdate
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	//@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	private String senha;
	
	private boolean changePasswordOnLogin; 
	
	private Date memberSince;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isChangePasswordOnLogin() {
		return changePasswordOnLogin;
	}

	public void setChangePasswordOnLogin(boolean changePasswordOnLogin) {
		this.changePasswordOnLogin = changePasswordOnLogin;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

}

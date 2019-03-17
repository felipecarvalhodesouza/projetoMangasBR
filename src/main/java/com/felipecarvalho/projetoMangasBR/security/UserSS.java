package com.felipecarvalho.projetoMangasBR.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.felipecarvalho.projetoMangasBR.domain.enums.Perfil;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;

public class UserSS implements UserDetails {
	
	@Autowired
	private UserRepository repo;
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;
	private boolean isEnabled;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS(){
	}

	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis, boolean isEnabled) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
		this.isEnabled = isEnabled;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}

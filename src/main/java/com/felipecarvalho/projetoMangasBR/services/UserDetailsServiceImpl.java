package com.felipecarvalho.projetoMangasBR.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipecarvalho.projetoMangasBR.domain.User;
import com.felipecarvalho.projetoMangasBR.repositories.UserRepository;
import com.felipecarvalho.projetoMangasBR.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		if(user.getLastPasswordChange()==null) {
			user.setChangePasswordOnLogin(true);
		} else {
			Date date = (Date) user.getLastPasswordChange();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 3);
			if(cal.before(new Date()) && !user.isChangePasswordOnLogin()) {
				user.setChangePasswordOnLogin(true);
				repo.save(user);
			}
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfis(), user.isEnabled());
	}

}

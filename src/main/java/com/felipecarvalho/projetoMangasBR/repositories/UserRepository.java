package com.felipecarvalho.projetoMangasBR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecarvalho.projetoMangasBR.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	User findByEmail(String email);
}

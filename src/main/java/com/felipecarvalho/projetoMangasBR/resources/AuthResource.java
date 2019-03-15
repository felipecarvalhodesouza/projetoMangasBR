package com.felipecarvalho.projetoMangasBR.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.security.JWTUtil;
import com.felipecarvalho.projetoMangasBR.security.UserSS;
import com.felipecarvalho.projetoMangasBR.services.AuthService;
import com.felipecarvalho.projetoMangasBR.services.UserAuthenticationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@ApiOperation(value="Refresh token")
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserAuthenticationService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Void> findPage(@RequestParam(value="token", defaultValue="")String token) {
		authService.validateToken(token);
		return ResponseEntity.ok().build();
	}
}
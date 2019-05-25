package com.felipecarvalho.projetoMangasBR.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.dto.EmailDTO;
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
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Autenticador de contas")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Void> findPage(@RequestParam(value="token", defaultValue="")String token) {
		authService.validateToken(token);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Resend validation token")
	@RequestMapping(value = "/resend_token", method = RequestMethod.POST)
	public ResponseEntity<Void> resendToken(@Valid @RequestBody EmailDTO objDto) {
		authService.resendToken(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Enviar nova senha para o email")
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		authService.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
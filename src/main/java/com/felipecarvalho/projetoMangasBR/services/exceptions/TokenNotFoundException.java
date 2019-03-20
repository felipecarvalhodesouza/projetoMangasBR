package com.felipecarvalho.projetoMangasBR.services.exceptions;

public class TokenNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TokenNotFoundException(String msg) {
		super(msg);
	}
	
	public TokenNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

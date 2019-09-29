package com.felipecarvalho.projetoMangasBR.services.exceptions;

public class EmailNotSentException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailNotSentException(String msg) {
		super(msg);
	}
	
	public EmailNotSentException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

package com.example.adesao.exceptions;

public class IdJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdJaExisteException() {
		super("ID já cadastrado");
	}
}

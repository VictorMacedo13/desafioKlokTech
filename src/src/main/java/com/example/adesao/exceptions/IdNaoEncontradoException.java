package com.example.adesao.exceptions;

public class IdNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public IdNaoEncontradoException() {
		super("ID já cadastrado");
	}
}

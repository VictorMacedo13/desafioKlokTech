package com.example.adesao.exceptions;

public class CpfNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CpfNaoEncontradoException() {
		super("CPF não está cadastrado");
	}
}

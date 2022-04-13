package com.example.adesao.exceptions;

public class CpfJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public CpfJaExisteException() {
		super("CPF já cadastrado");
	}
}

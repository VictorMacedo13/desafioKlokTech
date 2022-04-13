package com.example.adesao.exceptions;

public class CobrancaFinalizadaException extends Exception {

	private static final long serialVersionUID = 1L;

	public CobrancaFinalizadaException() {
		super("Todas as cobranças já foram efetuadas");
	}
}

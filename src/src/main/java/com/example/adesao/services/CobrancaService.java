package com.example.adesao.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.adesao.dtos.CobrancaDTO;
import com.example.adesao.exceptions.CobrancaFinalizadaException;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Cobranca;

@Service
public interface CobrancaService {
	
	public Cobranca cadastrar(Cobranca cobranca) throws IdJaExisteException;

	public Cobranca atualizar(Integer idCobranca, CobrancaDTO objDTO) throws IdNaoEncontradoException;

	public void remover(Integer idCobranca) throws IdNaoEncontradoException;

	public Cobranca buscarPorId(Integer idCobranca) throws IdNaoEncontradoException;
	
	public CobrancaDTO agendarCobranca(LocalDate data, Integer dd) throws CobrancaFinalizadaException;
}

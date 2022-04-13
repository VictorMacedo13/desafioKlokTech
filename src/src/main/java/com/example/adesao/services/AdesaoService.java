package com.example.adesao.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.adesao.dtos.AdesaoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Adesao;

@Service
public interface AdesaoService {

	public Adesao cadastrar(Adesao adesao) throws IdJaExisteException;
	
	public Adesao atualizar(Integer idAdesao, @Valid AdesaoDTO objDTO) throws IdNaoEncontradoException;
	
	public void cancelar(Integer idAdesao) throws IdNaoEncontradoException;
	
	public Adesao buscarPorId(Integer IdArtesao) throws IdNaoEncontradoException;
	
}

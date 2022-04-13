package com.example.adesao.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adesao.dtos.AdesaoDTO;
import com.example.adesao.enums.StatusAdesao;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Adesao;
import com.example.adesao.repositories.AdesaoRepository;
import com.example.adesao.services.AdesaoService;

@Service
public class AdesaoServiceImpl implements AdesaoService{

	@Autowired
	private AdesaoRepository repository;
	
	@Override
	public Adesao cadastrar(Adesao adesao) throws IdJaExisteException{
		Optional<Adesao> adesaoExistente = repository.findById(adesao.getId());
		if(adesaoExistente.isPresent()) {
			throw new IdJaExisteException();
		}
		return repository.save(adesao);
	}

	@Override
	public Adesao atualizar(Integer idAdesao, AdesaoDTO dto) throws IdNaoEncontradoException {
		Adesao adesao = buscarPorId(idAdesao);
		dto.setId(idAdesao);
		adesao = dto.atualizarAdesao(adesao);
		return repository.save(adesao);
	} 

	@Override
	public void cancelar(Integer idAdesao) throws IdNaoEncontradoException {
		Adesao adesao = buscarPorId(idAdesao);
		adesao.setStatus(StatusAdesao.CANCELADA);
		repository.save(adesao);
	}

	@Override
	public Adesao buscarPorId(Integer idAdesao) throws IdNaoEncontradoException {
		Optional<Adesao> adesaoExistente = repository.findById(idAdesao);
		if(!adesaoExistente.isPresent()) {
			throw new IdNaoEncontradoException();
		}
		return adesaoExistente.get();
	}
	
	

}

package com.example.adesao.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adesao.dtos.AdesaoDTO;
import com.example.adesao.dtos.CobrancaDTO;
import com.example.adesao.enums.StatusCobranca;
import com.example.adesao.exceptions.CobrancaFinalizadaException;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Adesao;
import com.example.adesao.model.Cobranca;
import com.example.adesao.rabbitMQ.RabbitMQConstantes;
import com.example.adesao.rabbitMQ.RabbitMQService;
import com.example.adesao.repositories.CobrancaRepository;
import com.example.adesao.services.AdesaoService;
import com.example.adesao.services.CobrancaService;

@Service
public class CobrancaServiceImpl implements CobrancaService{

	@Autowired
	CobrancaRepository repository;
	
	@Autowired
	RabbitMQService rabbitMQService;
	
	@Autowired
	AdesaoService adesaoService;
	
	@Override
	public Cobranca cadastrar(Cobranca cobranca) throws IdJaExisteException {
		Optional<Cobranca> cobrancaExistente = repository.findById(cobranca.getId());
		if(cobrancaExistente.isPresent()) {
			throw new IdJaExisteException();
		}
		return repository.save(cobranca);
	}

	@Override
	public Cobranca atualizar(Integer idCobranca, CobrancaDTO cobrancaDTO) throws IdNaoEncontradoException {
		Cobranca cobranca = buscarPorId(idCobranca);
		cobranca = cobrancaDTO.atualizarCobranca(cobranca);
		return repository.save(cobranca);
	}

	@Override
	public void remover(Integer idCobranca) throws IdNaoEncontradoException {
		Cobranca cobranca = buscarPorId(idCobranca);
		repository.delete(cobranca);
	}

	@Override
	public Cobranca buscarPorId(Integer idCobranca) throws IdNaoEncontradoException {
		Optional<Cobranca> cobrancaExistente = repository.findById(idCobranca);
		if(!cobrancaExistente.isPresent()) {
			throw new IdNaoEncontradoException();
		}
		return cobrancaExistente.get();
	}

	@Override
	public CobrancaDTO agendarCobranca(LocalDate data, Integer id) throws CobrancaFinalizadaException {
		Adesao adesao = null;
		try {
			adesao = adesaoService.buscarPorId(id);
		} catch (IdNaoEncontradoException e1) {
			e1.printStackTrace();
		}
		List<Cobranca> cobrancas = adesao.getCobrancas();
		
		if(cobrancas.get(cobrancas.size()-1).getStatus() == StatusCobranca.REALIZADA && adesao.getData().isAfter(data) ) {
			Cobranca cobranca = adesao.criarCobranca();
			cobrancas = adesao.getCobrancas();
			AdesaoDTO adesaoDTO = new AdesaoDTO(adesao);
			CobrancaDTO cobrancaDTO = new CobrancaDTO(cobranca);
			try {
				adesaoService.atualizar(adesao.getId(), adesaoDTO);
			} catch (IdNaoEncontradoException e) {
				e.printStackTrace();
			}
			
			this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_COBRANCA, cobrancaDTO);
			return cobrancaDTO;
		}
		else {
			throw new CobrancaFinalizadaException();
		}
	}
	
	
	
	

}

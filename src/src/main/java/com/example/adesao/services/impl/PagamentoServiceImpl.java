package com.example.adesao.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adesao.dtos.PagamentoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Pagamento;
import com.example.adesao.repositories.PagamentoRepository;
import com.example.adesao.services.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{

	@Autowired
	PagamentoRepository repository;
	
	@Override
	public Pagamento cadastrar(Pagamento pagamento) throws IdJaExisteException {
		Optional<Pagamento> pagamentoExistente = repository.findById(pagamento.getId());
		if(pagamentoExistente.isPresent()) {
			throw new IdJaExisteException();
		}
		return repository.save(pagamento);
	}

	@Override
	public Pagamento atualizar(Integer idPagamento, PagamentoDTO pagamentoDTO) throws IdNaoEncontradoException {
		Pagamento pagamento = buscarPorId(idPagamento);
		pagamento = pagamentoDTO.atualizarPagamento(pagamento);
		return repository.save(pagamento);
	}

	@Override
	public void remover(Integer idPagamento) throws IdNaoEncontradoException {
		Pagamento pagamento = buscarPorId(idPagamento);
		repository.delete(pagamento);
	}

	@Override
	public Pagamento buscarPorId(Integer idPagamento) throws IdNaoEncontradoException {
		Optional<Pagamento> pagamentoExistente = repository.findById(idPagamento);
		if(!pagamentoExistente.isPresent()) {
			throw new IdNaoEncontradoException();
		}
		return pagamentoExistente.get();
	}

}

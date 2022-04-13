package com.example.adesao.services;

import org.springframework.stereotype.Service;

import com.example.adesao.dtos.PagamentoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Pagamento;

@Service
public interface PagamentoService {

	public Pagamento cadastrar(Pagamento pagamento) throws IdJaExisteException;

	public Pagamento atualizar(Integer idPagamento, PagamentoDTO objDTO) throws IdNaoEncontradoException;

	public void remover(Integer idPagamento) throws IdNaoEncontradoException;

	public Pagamento buscarPorId(Integer idPagamento) throws IdNaoEncontradoException;
}

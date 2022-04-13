package com.example.adesao.services;

import org.springframework.stereotype.Service;

import com.example.adesao.dtos.ProdutoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Produto;

@Service
public interface ProdutoService {

	public Produto cadastrar(Produto produto) throws IdJaExisteException;

	public Produto atualizar(Integer idProduto, ProdutoDTO objDTO)throws IdNaoEncontradoException;

	public void remover(Integer idProduto)throws IdNaoEncontradoException;

	public Produto buscarPorId(Integer idProduto) throws IdNaoEncontradoException;
}

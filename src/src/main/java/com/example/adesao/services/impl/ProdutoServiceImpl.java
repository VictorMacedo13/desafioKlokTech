package com.example.adesao.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adesao.dtos.ProdutoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Produto;
import com.example.adesao.repositories.ProdutoRepository;
import com.example.adesao.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	ProdutoRepository repository;
	
	@Override
	public Produto cadastrar(Produto produto) throws IdJaExisteException {
		Optional<Produto> produtoExistente = repository.findById(produto.getId());
		if(produtoExistente.isPresent()) {
			throw new IdJaExisteException();
		}
		return repository.save(produto);
	}

	@Override
	public Produto atualizar(Integer idProduto, ProdutoDTO produtoDTO) throws IdNaoEncontradoException {
		Produto produto = buscarPorId(idProduto);
		produto = produtoDTO.atualizarProduto(produto);
		return repository.save(produto);
	}

	@Override
	public void remover(Integer idProduto) throws IdNaoEncontradoException {
		Produto produto = buscarPorId(idProduto);
		repository.delete(produto);
	}

	@Override
	public Produto buscarPorId(Integer idProduto) throws IdNaoEncontradoException {
		Optional<Produto> produtoExistente = repository.findById(idProduto);
		if(!produtoExistente.isPresent()) {
			throw new IdNaoEncontradoException();
		}
		return produtoExistente.get();
	}

}

package com.example.adesao.dtos;

import java.io.Serializable;

import com.example.adesao.model.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;

	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
	}

	public Produto cadastrar() {
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setNome(nome);
		return produto;
	}

	public Produto atualizarProduto(Produto produto) {
		produto.setDescricao(descricao);
		produto.setNome(nome);
		return produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}

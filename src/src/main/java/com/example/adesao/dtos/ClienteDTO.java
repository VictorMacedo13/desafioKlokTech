package com.example.adesao.dtos;

import java.io.Serializable;
import java.util.List;

import com.example.adesao.model.Adesao;
import com.example.adesao.model.Cliente;
import com.example.adesao.model.Contato;
import com.example.adesao.model.Endereco;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nome;
	private List<Adesao> adesoes;
	private Endereco endereco;
	private Contato contato;

	public ClienteDTO(Cliente obj) {
		cpf = obj.getCpf();
		nome = obj.getNome();
		adesoes = obj.getAdesoes();
		endereco = obj.getEndereco();
		contato = obj.getContato();
	}

	public Cliente cadastrar() {
		Cliente cliente = new Cliente();
		cliente.setAdesoes(adesoes);
		cliente.setContato(contato);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setNome(nome);

		return cliente;
	}

	public Cliente atualizarCliente(Cliente cliente) {
		cliente.setAdesoes(adesoes);
		cliente.setContato(contato);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setNome(nome);
		return cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Adesao> getAdesoes() {
		return adesoes;
	}

	public void setAdesoes(List<Adesao> adesoes) {
		this.adesoes = adesoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}

package com.example.adesao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;


@Entity
public class Cliente {

	@Id
	@CPF
	@Column(unique = true, name= "cpf_cliente")
	private String cpf;
	
	@NotNull
	private String nome;
	
	@OneToMany
	@JoinColumn(name = "cpf_cliente")
	private List<Adesao> adesoes;
	
	@NotNull
	@Embedded
	private Endereco endereco;
	
	@NotNull
	@Embedded
	private Contato contato;

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

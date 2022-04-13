package com.example.adesao.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private Integer numero;
	private Integer apartamento;
	private String infoAdicional;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getApartamento() {
		return apartamento;
	}
	public void setApartamento(Integer apartamento) {
		this.apartamento = apartamento;
	}
	public String getInfoAdicional() {
		return infoAdicional;
	}
	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
	@Override
	public String toString() {
		return "Endereco [estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero="
				+ numero + ", apartamento=" + apartamento + ", informacao adicional=" + infoAdicional + "]";
	}
	
	
	
}

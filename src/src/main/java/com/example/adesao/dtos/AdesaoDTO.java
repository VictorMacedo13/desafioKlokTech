package com.example.adesao.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.adesao.enums.StatusAdesao;
import com.example.adesao.model.Adesao;

public class AdesaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer parcelaAtual;
	private Integer quantidadeDeParcela;
	private StatusAdesao status;
	private BigDecimal valor;
	private Integer idProduto;
	private String cpfCliente;

	public AdesaoDTO(Adesao obj) {
		this.id = obj.getId();
		this.parcelaAtual = obj.getParcelaAtual();
		this.quantidadeDeParcela = obj.getQuantidadeDeParcelas();
		this.status = obj.getStatus();
		this.valor = obj.getValor();
		this.idProduto = obj.getProduto().getId();
		this.cpfCliente = obj.getCliente().getCpf();
	}

	public Adesao cadastrar() {
		Adesao adesao = new Adesao();
		adesao.setParcelaAtual(1);
		adesao.setQuantidadeDeParcelas(this.quantidadeDeParcela);
		adesao.setStatus(StatusAdesao.ATIVA);
		adesao.setValor(this.valor);
		adesao.setData(LocalDate.now());
		return adesao;
	}

	public Adesao atualizarAdesao(Adesao adesao) {

		adesao.setParcelaAtual(this.parcelaAtual);
		adesao.setQuantidadeDeParcelas(this.quantidadeDeParcela);
		adesao.setStatus(this.status);
		adesao.setValor(this.valor);
		return adesao;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParcelaAtual() {
		return parcelaAtual;
	}

	public void setParcelaAtual(Integer parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}

	public Integer getQuantidadeDeParcela() {
		return quantidadeDeParcela;
	}

	public void setQuantidadeDeParcela(Integer quantidadeDeParcela) {
		this.quantidadeDeParcela = quantidadeDeParcela;
	}

	public StatusAdesao getStatus() {
		return status;
	}

	public void setStatus(StatusAdesao status) {
		this.status = status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getIdCliente() {
		return cpfCliente;
	}

	public void setIdCliente(String idCliente) {
		this.cpfCliente = idCliente;
	}

}

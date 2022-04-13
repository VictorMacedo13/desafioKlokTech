package com.example.adesao.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.adesao.enums.StatusPagamento;
import com.example.adesao.model.Adesao;
import com.example.adesao.model.Cobranca;
import com.example.adesao.model.Pagamento;

public class PagamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private BigDecimal valor;
	private Adesao adesao;
	private Cobranca cobranca;
	private LocalDate data;
	private StatusPagamento status;
	
	public PagamentoDTO(Pagamento obj) {
		valor = obj.getValor();
		adesao = obj.getAdesao();
		cobranca = obj.getCobranca();
		data = obj.getData();
		status = obj.getStatus();
		id = obj.getId();
	}

	public Pagamento cadastrar() {
		Pagamento pagamento = new Pagamento();
		pagamento.setAdesao(adesao);
		pagamento.setCobranca(cobranca);
		pagamento.setData(LocalDate.now());
		pagamento.setStatus(StatusPagamento.PENDENTE);
		
		return pagamento;
	}

	public Pagamento atualizarPagamento(Pagamento pagamento) {
		pagamento.setAdesao(adesao);
		pagamento.setCobranca(cobranca);
		pagamento.setData(data);
		pagamento.setStatus(status);
		
		return pagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Adesao getAdesao() {
		return adesao;
	}

	public void setAdesao(Adesao adesao) {
		this.adesao = adesao;
	}

	public Cobranca getCobranca() {
		return cobranca;
	}

	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	
	

}

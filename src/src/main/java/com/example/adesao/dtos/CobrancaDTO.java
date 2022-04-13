package com.example.adesao.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.adesao.enums.StatusCobranca;
import com.example.adesao.model.Adesao;
import com.example.adesao.model.Cobranca;
import com.example.adesao.model.Pagamento;

public class CobrancaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Adesao adesao;
	private LocalDate data;
	private Pagamento pagamento;
	private StatusCobranca status;
	private BigDecimal valor;

	public CobrancaDTO(Cobranca obj) {
		
		id = obj.getId();
		adesao = obj.getAdesao();
		data = obj.getData();
		pagamento = obj.getPagamento();
		status = obj.getStatus();
		valor = obj.getValor();

	}

	public Cobranca cadastrar() {

		Cobranca cobranca = new Cobranca();
		cobranca.setAdesao(adesao);
		cobranca.setData(LocalDate.now());
		cobranca.setPagamento(pagamento);
		cobranca.setStatus(StatusCobranca.PENDENTE);
		cobranca.setValor(valor);

		return cobranca;
	}

	public Cobranca atualizarCobranca(Cobranca cobranca) {
		cobranca.setAdesao(adesao);
		cobranca.setData(data);
		cobranca.setPagamento(pagamento);
		cobranca.setStatus(status);
		cobranca.setValor(valor);

		return cobranca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Adesao getAdesao() {
		return adesao;
	}

	public void setAdesao(Adesao adesao) {
		this.adesao = adesao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public StatusCobranca getStatus() {
		return status;
	}

	public void setStatus(StatusCobranca status) {
		this.status = status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}

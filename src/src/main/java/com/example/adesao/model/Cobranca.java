package com.example.adesao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.adesao.enums.StatusCobranca;
import com.sun.istack.NotNull;



@Entity
public class Cobranca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_cobranca")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_adesao")
	private Adesao adesao;
	
	@NotNull
	private LocalDate data;
	
	@OneToOne(mappedBy = "cobranca")
	private Pagamento pagamento;
	
	@NotNull
	private StatusCobranca status;
	
	@NotNull
	private BigDecimal valor;

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

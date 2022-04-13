package com.example.adesao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.adesao.enums.StatusAdesao;
import com.example.adesao.enums.StatusCobranca;
import com.example.adesao.enums.StatusPagamento;
import com.sun.istack.NotNull;

@Entity
public class Adesao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_adesao")
	private Integer id;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private Integer quantidadeDeParcelas;
	
	@NotNull
	private Integer parcelaAtual;
	
	@OneToMany
	@JoinColumn(name = "id_adesao")
	private List<Cobranca> cobrancas;
	
	@OneToMany
	@JoinColumn(name = "id_produto")
	private Produto produto; 
	
	@NotNull	
	@ManyToOne
	@JoinColumn(name = "cpf_cliente")
	private Cliente cliente;
	
	@NotNull
	private StatusAdesao status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidadeDeParcelas() {
		return quantidadeDeParcelas;
	}

	public void setQuantidadeDeParcelas(Integer quantidadeDeParcelas) {
		this.quantidadeDeParcelas = quantidadeDeParcelas;
	}

	public Integer getParcelaAtual() {
		return parcelaAtual;
	}

	public void setParcelaAtual(Integer parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}

	public List<Cobranca> getCobrancas() {
		return cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusAdesao getStatus() {
		return status;
	}

	public void setStatus(StatusAdesao status) {
		this.status = status;
	}

	public Cobranca criarCobranca() {
		 
		Cobranca cobranca = new Cobranca();
		cobranca.setValor(valor.divide(BigDecimal.valueOf(quantidadeDeParcelas)));
		cobranca.setAdesao(this);
		cobranca.setData(LocalDate.now());
		cobranca.setStatus(StatusCobranca.PENDENTE);
		
		Pagamento pagamento = new Pagamento();
		pagamento.setAdesao(this);
		pagamento.setCobranca(cobranca);
		pagamento.setStatus(StatusPagamento.PENDENTE);
		pagamento.setValor(cobranca.getValor());
		cobranca.setPagamento(pagamento);
		cobrancas.add(cobranca);
		
		parcelaAtual+=1;
		if(parcelaAtual == quantidadeDeParcelas) {
			this.status = StatusAdesao.FINALIZADA;
		}
		return cobranca;
		
	}
	
	
	
	
	
	
	
	
	
}

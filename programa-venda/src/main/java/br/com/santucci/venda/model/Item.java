package br.com.santucci.venda.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer quantidade;
	private BigDecimal valor;
	private BigDecimal valorTotal;

	public Item(String nome, Integer quantidade, BigDecimal valor, BigDecimal valorTotal) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.valorTotal = valorTotal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}

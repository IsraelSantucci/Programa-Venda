package br.com.santucci.venda.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nome;
	private Integer quantidade;
	private BigDecimal precoDeVenda;
	private BigDecimal precoDeCompra;
	
	
	public Produto(String nome, Integer quantidade, BigDecimal precoDeCompra, BigDecimal precoDeVenda) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.precoDeCompra = precoDeCompra;
		this.precoDeVenda = precoDeVenda;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoDeVenda() {
		return precoDeVenda.setScale(2);
	}
	
	public BigDecimal getPrecoDeCompra() {
		return precoDeCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((precoDeCompra == null) ? 0 : precoDeCompra.hashCode());
		result = prime * result + ((precoDeVenda == null) ? 0 : precoDeVenda.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (precoDeCompra == null) {
			if (other.precoDeCompra != null)
				return false;
		} else if (!precoDeCompra.equals(other.precoDeCompra))
			return false;
		if (precoDeVenda == null) {
			if (other.precoDeVenda != null)
				return false;
		} else if (!precoDeVenda.equals(other.precoDeVenda))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return this.getNome();
	}
	
	

}

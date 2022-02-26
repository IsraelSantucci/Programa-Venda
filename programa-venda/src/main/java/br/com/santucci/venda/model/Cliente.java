package br.com.santucci.venda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private String cpf;
	private LocalDate dataDeNacimento;
	private BigDecimal valorDaDivida;
	private List<Compra> compras = new ArrayList<>();
	
	
	public Cliente(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
		this.valorDaDivida = BigDecimal.ZERO;
		this.dataDeNacimento = LocalDate.now();
	}

	public Cliente(int codigo, String nome, String cpf, LocalDate dataDeNascimento) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNacimento = dataDeNascimento;
		this.valorDaDivida = BigDecimal.ZERO;

	}

	public int getCodigo() {
		return codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeNacimento() {
		return dataDeNacimento;
	}

	public String getDataDeNacimentoFormatada() {
		return dataDeNacimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	public void setDataDeNacimento(LocalDate dataDeNacimento) {
		this.dataDeNacimento = dataDeNacimento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}


	public String getNome() {
		return nome;
	}

	public BigDecimal getValorDaDivida() {
		return valorDaDivida;
	}

	public void setValorDaDivida(BigDecimal valorDaDivida) {
		this.valorDaDivida = valorDaDivida;
	}

	public List<Compra> getCompras() {
		return Collections.unmodifiableList(this.compras);
	}

	public void AdicionarCompra(Compra compra) {
		compras.add(compra);
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}

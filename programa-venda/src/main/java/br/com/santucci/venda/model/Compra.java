package br.com.santucci.venda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra implements Serializable {
	
	private List<Item> itens = new ArrayList<>();
	private BigDecimal valorTotal;
	private Funcionario funcionarioVenda;
	private LocalDate dataDaCompra;
	
	public Compra(List<Item> itens, BigDecimal valorTotal,LocalDate dataDaCompra ) {
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.dataDaCompra = dataDaCompra;
	}
	
	


	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public String getDataCompraFormatado() {
		return dataDaCompra.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}

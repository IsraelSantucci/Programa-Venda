package br.com.santucci.venda.view.tablemodel;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.santucci.venda.model.entity.Item;

public class ModeloTabelaItem extends AbstractTableModel{
	
	private List<Item> dados = null;
	private String [] colunas = {"NOME", "QUANTIDADE", "VALOR UNITARIO", "VALOR TOTAL"};
	
	public ModeloTabelaItem(List<Item> dados) {
		this.dados = dados;
	}
    
	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] objetos = {dados.get(rowIndex).getNome(), dados.get(rowIndex).getQuantidade(), dados.get(rowIndex).getValor(), dados.get(rowIndex).getValorTotal()};
		return objetos[columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		
		return colunas[column];
	}
	
	public BigDecimal valorTotal() {
		BigDecimal total = dados.stream().map(Item::getValorTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
		return total;	
	}
}

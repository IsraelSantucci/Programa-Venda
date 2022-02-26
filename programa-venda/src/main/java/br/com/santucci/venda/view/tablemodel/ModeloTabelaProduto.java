package br.com.santucci.venda.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.santucci.venda.model.Produto;

public class ModeloTabelaProduto extends AbstractTableModel{
	
	private List<Produto> dados = null;
	private String[] colunas = null;
	
	public ModeloTabelaProduto(List<Produto> dados, String[] colunas) {
		setDados(dados);
		setColunas(colunas);
	}

	public List<Produto> getDados() {
		return dados;
	}

	public void setDados(List<Produto> dados) {
		this.dados = dados;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
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
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public Object getValueAt(int numLinha, int numColuna) {
		
		Object[] linha = new Object[] {dados.get(numLinha).getNome(), dados.get(numLinha).getQuantidade(), dados.get(numLinha).getPrecoDeVenda()};
		return linha[numColuna];
	}

}

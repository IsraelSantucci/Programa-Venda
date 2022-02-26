package br.com.santucci.venda.view.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.santucci.venda.model.Funcionario;

public class ModeloTabelaFuncionarios extends AbstractTableModel{
	
	private List<Funcionario> dados = null;
	private String[] colunas = {"nome", "Senha"};
	
	public ModeloTabelaFuncionarios(List<Funcionario> funcionarios) {
		this.dados = funcionarios;
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
	public Object getValueAt(int numeroLinha, int numeroColuna ) {
		Object[] objeto = {dados.get(numeroLinha).getNome(), dados.get(numeroLinha).getSenha()};
		return objeto[numeroColuna];
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

}

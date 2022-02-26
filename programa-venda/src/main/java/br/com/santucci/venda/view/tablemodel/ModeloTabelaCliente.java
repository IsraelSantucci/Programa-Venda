package br.com.santucci.venda.view.tablemodel;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.santucci.venda.model.Cliente;

public class ModeloTabelaCliente extends AbstractTableModel{

	private List<Cliente> dados = null;
	private String[] colunas = {"Cod", "Nome", "cpf", "Data de Nasc", "Divida"};
	
	public ModeloTabelaCliente(List<Cliente> clientes) {
		this.dados = clientes;
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
		//Aqui é cliada uma array para cada objeto da lista e cada atributo que agora é um elemento do array é chamado por vez;
		Object[] objeto = {dados.get(rowIndex).getCodigo(), 
				dados.get(rowIndex).getNome(),
				dados.get(rowIndex).getCpf(),
				dados.get(rowIndex).getDataDeNacimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
				dados.get(rowIndex).getValorDaDivida()
				};
		
		return objeto[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	

}

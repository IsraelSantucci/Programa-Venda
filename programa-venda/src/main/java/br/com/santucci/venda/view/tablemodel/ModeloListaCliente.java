package br.com.santucci.venda.view.tablemodel;

import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;

import br.com.santucci.venda.dao.ClienteDAO;
import br.com.santucci.venda.model.Cliente;

public class ModeloListaCliente extends AbstractListModel{

	private List<Cliente> clientes = new ClienteDAO().getClientes();
	
	@Override
	public int getSize() {
		return clientes.size();
	}

	@Override
	public Object getElementAt(int index) {
		return clientes.get(index).getNome() + "  " + clientes.get(index).getDataDeNacimentoFormatada();
	}

	public void adicionarColecao(List<Cliente> colecao) {
		clientes = colecao;
		
	}
	
}

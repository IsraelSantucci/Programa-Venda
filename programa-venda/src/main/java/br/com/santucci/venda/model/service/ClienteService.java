package br.com.santucci.venda.model.service;

import java.util.List;

import br.com.santucci.venda.model.dao.ClienteDAO;
import br.com.santucci.venda.model.entity.Cliente;

public class ClienteService {
	
	private ClienteDAO dao;
	
	public ClienteService() {
		dao = new ClienteDAO();
	}

	public  List<Cliente> salvar(Cliente cliente) {
		dao.salvar(cliente);
		return dao.getClientes();
	}

	public List<Cliente> excluir(int indice) {
		dao.excluir(indice);
		return dao.getClientes();
		
	}

	public Cliente pequisar(int indice) {
		return dao.pequisar(indice);
	}
	
	

}

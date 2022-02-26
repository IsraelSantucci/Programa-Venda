package br.com.santucci.venda.dao;

import org.junit.jupiter.api.Test;

import br.com.santucci.venda.model.Cliente;

class ClienteDAOTest {

	@Test
	void testandoAContrucaoDoClienteDAOParaConferirSeEstaCarregandoOsDadosDoArquivo() {
		ClienteDAO dao = new ClienteDAO();
	}
	
	@Test
	void testandoOMetodoSalvarArquivo() {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente("Maria Teste");
		dao.salvar(cliente);
	}

}

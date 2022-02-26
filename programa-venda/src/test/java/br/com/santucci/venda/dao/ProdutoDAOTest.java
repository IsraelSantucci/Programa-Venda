package br.com.santucci.venda.dao;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.santucci.venda.model.Produto;

class ProdutoDAOTest {

	@Test
	void testProdutoDAO() {
		//fail("Not yet implemented");
	}

	@Test
	void testSalvar() {
		Produto produto = new Produto("batata", 2, new BigDecimal(5), new BigDecimal(10));
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
		
	}

	@Test
	void testExcluir() {
		//fail("Not yet implemented");
	}

	@Test
	void testEditar() {
		//fail("Not yet implemented");
	}

	@Test
	void testPesquisar() {
		//fail("Not yet implemented");
	}

}

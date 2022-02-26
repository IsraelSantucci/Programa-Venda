package br.com.santucci.venda.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.santucci.venda.model.Funcionario;

class FuncionarioDAOTest {

	@Test
	void testSalvar() {
		Funcionario funcionario = new Funcionario("Maria Apareciada", "123456");
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(funcionario);
		
	}
	
	@Test
	void testGetFuncionarios() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.getFuncionarios();
		funcionarios.stream().forEach(funcionario -> System.out.println(funcionario));
	}

}

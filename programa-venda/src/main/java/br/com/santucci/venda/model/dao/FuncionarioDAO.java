package br.com.santucci.venda.model.dao;

import java.util.Collections;
import java.util.List;

import br.com.santucci.venda.model.entity.Funcionario;

public class FuncionarioDAO {

	private List<Funcionario> funcionarios = null;
	private String nomeDoArquivo = "funcionarios.txt";
	
	public FuncionarioDAO() {
		abrirArquivo();
	}
	
	public void salvar(Funcionario funcionario) {
		funcionarios.add(funcionario);
		salvarArquivo();
	}
	
	public void excluir(int indice) {
		funcionarios.remove(indice);
		salvarArquivo();
	}
	
	public void pesquisar() {
		
	}
	
	public List<Funcionario> getFuncionarios() {
		return Collections.unmodifiableList(funcionarios);
	}
	
	private void abrirArquivo() {
		GerenciadorDeArquivo<Funcionario> gerenciador = new GerenciadorDeArquivo<>(nomeDoArquivo);
		funcionarios = gerenciador.listar();
		
	}
	
	private void salvarArquivo() {
		GerenciadorDeArquivo<Funcionario> gerenciador = new GerenciadorDeArquivo<>(nomeDoArquivo);
		gerenciador.salvar(funcionarios);	
	}
}

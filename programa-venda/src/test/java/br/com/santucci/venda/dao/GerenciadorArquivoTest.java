package br.com.santucci.venda.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GerenciadorArquivoTest {

	@Test
	void testandoOConstrutorDaClasseGerenciadorArquivo() {
		GerenciadorDeArquivo<String> gerenciador = new GerenciadorDeArquivo("teste.txt");
	}

	@Test
	void testSalvar() {
		GerenciadorDeArquivo<String> gerenciador = new GerenciadorDeArquivo("teste.txt");
		List<String> lista = new ArrayList<String>();
		lista.add("palavra1");
		lista.add("palavra2");
		gerenciador.salvar(lista);
	}

	@Test
	void testandoOMetodoListar() {
		GerenciadorDeArquivo<String> gerenciador = new GerenciadorDeArquivo("teste.txt");
		List<String> lista = new ArrayList<String>();
		lista.add("palavra1");
		lista.add("palavra2");
		gerenciador.salvar(lista);
		
		List<String> segundaLista = gerenciador.listar();
		
	}

}

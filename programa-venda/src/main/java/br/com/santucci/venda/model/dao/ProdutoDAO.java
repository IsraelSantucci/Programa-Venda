package br.com.santucci.venda.model.dao;

import java.util.Collections;
import java.util.List;

import br.com.santucci.venda.model.entity.Produto;

public class ProdutoDAO {
	private List<Produto> produtos = null;
	private String nomeDoArquivo = "produtos.txt";
	
	public ProdutoDAO() {
		carregarArquivo();
	}

	private void carregarArquivo() {
		
		GerenciadorDeArquivo<Produto> gerenciador = new GerenciadorDeArquivo<>(nomeDoArquivo);
		produtos = gerenciador.listar();
	}

	public void salvar(Produto produto) {
		produtos.add(produto);
		salvarArquivo();
		
	}
	
	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}

	public void excluir(int indice) {
		produtos.remove(indice);
		salvarArquivo();
	}

	public boolean editar(Produto produto) {
		
		try {
			if (produtos.contains(produto)) {
				int indice = pesquisar(produto);
				produtos.add(indice, produto);
			}
			salvarArquivo();
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}

	public int pesquisar(Produto produto) {
		if (produtos.contains(produto)) {
			int i = 0;
			for (i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getNome().equals(produto.getNome())) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private void salvarArquivo() {
		GerenciadorDeArquivo<Produto> gerenciador = new GerenciadorDeArquivo<>(nomeDoArquivo);
		gerenciador.salvar(produtos);
	}
}

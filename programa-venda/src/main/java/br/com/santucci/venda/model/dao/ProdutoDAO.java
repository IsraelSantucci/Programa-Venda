package br.com.santucci.venda.model.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.santucci.venda.model.entity.Produto;

public class ProdutoDAO {
	private List<Produto> produtos = null;
	
	public ProdutoDAO() {
		carregarArquivo();
	}

	private void carregarArquivo() {
		
		
		try(FileInputStream fis  = new FileInputStream(new File("produtos.txt"))) {

			try {			
				ObjectInputStream ois = new ObjectInputStream(fis);
				produtos = (List<Produto>) ois.readObject();
			}catch(EOFException ex) {
				System.out.println("Deu erro carregar");
				produtos = new ArrayList<Produto>();
			}
			
		} catch (Exception e) {
			produtos = new ArrayList<Produto>();
			
		}
		
		
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
		try {
			FileOutputStream fos = new FileOutputStream(new File("produtos.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(produtos);
			oos.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}

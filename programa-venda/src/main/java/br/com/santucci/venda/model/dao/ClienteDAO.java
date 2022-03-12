package br.com.santucci.venda.model.dao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.santucci.venda.model.entity.Cliente;
import br.com.santucci.venda.model.entity.Compra;

public class ClienteDAO {

	private List<Cliente> clientes;
	private GerenciadorDeArquivo<Cliente> gerenciadorDeArquivo;
	private String nomeDoArquivo = "clientes.txt";

	public ClienteDAO() {
		gerenciadorDeArquivo = new GerenciadorDeArquivo<Cliente>(nomeDoArquivo);
		clientes = gerenciadorDeArquivo.listar();
	}

	public void salvar(Cliente cliente) {
		clientes.add(cliente);
		salvarArquivo();
	}

	public void excluir(int indice) {
		clientes.remove(indice);
		salvarArquivo();

	}

	public void editar(int indice, Cliente cliente) {
		try {
			clientes.remove(indice);
			clientes.add(indice,cliente);
			salvarArquivo();
		}catch (Exception e) {
			e.printStackTrace();
			clientes = gerenciadorDeArquivo.listar();
		}
		
	}
	public List<Cliente> getClientes() {
		return Collections.unmodifiableList(clientes);
	}

	public Cliente pequisar(int indice) {
		return clientes.get(indice);
	}

	public void adicionarCompraCliente(int i, Compra compra) {
		clientes.get(i).AdicionarCompra(compra);
		clientes.get(i).setValorDaDivida(compra.getValorTotal());
		salvarArquivo();

	}

	private void salvarArquivo() {
		gerenciadorDeArquivo.salvar(clientes);
	}

	public List<Cliente> pequisar(String nome) {
		return clientes.stream().filter(cliente -> cliente.getNome().toLowerCase().contains(nome.toLowerCase()))
				.collect(Collectors.toList());

	}

}

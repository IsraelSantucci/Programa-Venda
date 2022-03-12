package br.com.santucci.venda.controller;

import br.com.santucci.venda.view.ClienteForm;

public interface InterfaceController {

	public void executaCadastro(Object view);
	
	public void executaRemocao(Object view);
	
	public void executaEdicao(Object view);
}

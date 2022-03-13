package br.com.santucci.venda.controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.santucci.venda.model.entity.Cliente;
import br.com.santucci.venda.model.service.ClienteService;
import br.com.santucci.venda.view.ClienteForm;

public class ClienteController implements InterfaceController{

	private Cliente cliente;
	private ClienteForm form;
	private ClienteService service;
	
	public ClienteController() {
		service = new ClienteService();
	}
	@Override
	public void executaCadastro(Object view) {
		
		form = (ClienteForm) view;
		
		Integer codigo = Integer.valueOf(form.getTxtCodigo().getText());
		String nome = form.getTxtNome().getText();
		String cpf = form.getFtxtCpf().getText();
		LocalDate data = validarData(form.getFtxtDataDeNascimento().getText());
		
		cliente = new Cliente(codigo, nome, cpf, data);
		
		List<Cliente> clientes = service.salvar(cliente);
		form.carregarTabelateste(clientes);
	}
	
	
	@Override
	public void executaRemocao(Object view) {
		form = (ClienteForm) view;
		int indice = form.getTabelaClientes().getSelectedRow();
		if(indice >= 0) {
			int decisao = JOptionPane.showConfirmDialog(form, "Deseja excluir o Cliente " + service.pequisar(indice).getNome(),
					"Excluir", JOptionPane.YES_NO_CANCEL_OPTION);
			if (decisao == JOptionPane.OK_OPTION) {
				List<Cliente> clientes = service.excluir(indice);
				form.carregarTabelateste(clientes);
			}
		}
		
	}

	public LocalDate validarData(String dataDeNascimento) {
		String[] data = dataDeNascimento.split("/");
		
		int dia = Integer.valueOf(data[0]);
		int mes = Integer.valueOf(data[1]);
		int ano = Integer.valueOf(data[2]);
		
		return LocalDate.of(ano, mes, dia);
	}
	
	@Override
	public void executaEdicao(Object view) {
		form = (ClienteForm) view;
		Cliente cliente = service.pequisar(form.getTabelaClientes().getSelectedRow());
		form.getTxtCodigo().setText(cliente.getCodigo().toString());
		form.getTxtNome().setText(cliente.getNome());
		form.getFtxtCpf().setText(cliente.getCpf());
		form.getFtxtDataDeNascimento().setText(cliente.getDataDeNacimentoFormatada());
	}
}

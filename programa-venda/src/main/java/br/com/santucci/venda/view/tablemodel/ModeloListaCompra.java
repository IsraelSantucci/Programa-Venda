package br.com.santucci.venda.view.tablemodel;

import java.util.List;

import javax.swing.AbstractListModel;

import br.com.santucci.venda.model.Compra;

public class ModeloListaCompra extends AbstractListModel{

	private List<Compra> compras;
	
	public ModeloListaCompra(List<Compra> compras) {
		this.compras = compras;
	}
	@Override
	public int getSize() {
		return compras.size();
	}

	@Override
	public Object getElementAt(int index) {
		return "Dia Compra: " + compras.get(index).getDataCompraFormatado() + "- Valor Total: " + compras.get(index).getValorTotal();
	}

}

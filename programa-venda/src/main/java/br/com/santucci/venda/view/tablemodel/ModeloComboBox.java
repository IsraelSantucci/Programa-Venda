package br.com.santucci.venda.view.tablemodel;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ModeloComboBox<T> extends AbstractListModel implements ComboBoxModel {

	private List<T> lista;
	private Object clienteSelecionado;

	public ModeloComboBox(List<T> clientes) {
		this.lista = clientes;
	}

	@Override
	public int getSize() {

		return lista.size();
	}

	@Override
	public Object getElementAt(int index) {
		return lista.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {

		clienteSelecionado = anItem;
		fireContentsChanged(this.lista, 0, lista.size());

	}

	@Override
	public Object getSelectedItem() {
		return clienteSelecionado;
	}

}

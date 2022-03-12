package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.santucci.venda.model.dao.ClienteDAO;
import br.com.santucci.venda.model.entity.Cliente;
import br.com.santucci.venda.model.entity.Compra;
import br.com.santucci.venda.view.tablemodel.ModeloListaCliente;
import br.com.santucci.venda.view.tablemodel.ModeloListaCompra;

public class JanelaConsultaDeClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JList listClientes;
	private JList listCompras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaConsultaDeClientes frame = new JanelaConsultaDeClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaConsultaDeClientes() {
		setTitle("Pequisar de Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1034, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNome.setBounds(28, 86, 80, 23);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(96, 86, 230, 25);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 220, 449, 346);
		panel.add(scrollPane);

		listClientes = new JList();
		listClientes.setBackground(Color.LIGHT_GRAY);
		listClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		listClientes.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				Integer indice = listClientes.getSelectedIndex();
				System.out.println(indice);
				if(indice != -1) {
					ClienteDAO dao = new ClienteDAO();
					Cliente cliente = dao.pequisar(indice);
					List<Compra> compras = cliente.getCompras();
					carregarListaCompras(compras);
					System.out.println(e);
				}
				
			}
		});
		ListModel model = carregarLista();
		listClientes.setModel(model);
		scrollPane.setViewportView(listClientes);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPesquisar.setBounds(336, 86, 131, 26);
		btnPesquisar.addActionListener(e -> {
			acaoBotaoPequisar(e);
		});
		panel.add(btnPesquisar);

		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClientes.setBounds(197, 187, 106, 23);
		panel.add(lblClientes);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(580, 222, 420, 344);
		panel.add(scrollPane_1);

		listCompras = new JList();
		listCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
		listCompras.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setViewportView(listCompras);

		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCompras.setBounds(746, 196, 106, 23);
		panel.add(lblCompras);
	}

	private void acaoBotaoPequisar(ActionEvent e) {
		String nome = txtNome.getText();
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.pequisar(nome);
		ModeloListaCliente modelo = new ModeloListaCliente();
		modelo.adicionarColecao(clientes);
		listClientes.setModel(modelo);

	}

	private ListModel carregarLista() {
		ModeloListaCliente modelo = new ModeloListaCliente();
		return modelo;
	}

	private void carregarListaCompras(List<Compra> lista) {
		ModeloListaCompra compras = new ModeloListaCompra(lista);
		listCompras.setModel(compras);
	}
}

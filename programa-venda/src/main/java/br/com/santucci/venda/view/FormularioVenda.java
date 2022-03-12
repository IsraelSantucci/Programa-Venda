package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.santucci.venda.model.dao.ClienteDAO;
import br.com.santucci.venda.model.dao.ProdutoDAO;
import br.com.santucci.venda.model.entity.Compra;
import br.com.santucci.venda.model.entity.Item;
import br.com.santucci.venda.model.entity.Produto;
import br.com.santucci.venda.view.tablemodel.ModeloComboBox;
import br.com.santucci.venda.view.tablemodel.ModeloTabelaItem;

public class FormularioVenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtQuantidade;
	private List<Item> itens = new ArrayList<>();
	private JLabel lblTotal;
	private JComboBox comboBoxCliente;
	private JComboBox comboBoxProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioVenda frame = new FormularioVenda();

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
	public FormularioVenda() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblTitulo = new JLabel("Venda");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(438, 10, 111, 44);
		panel.add(lblTitulo);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCliente.setBounds(41, 73, 97, 44);
		panel.add(lblCliente);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProduto.setAlignmentX(0.5f);
		lblProduto.setBounds(474, 73, 120, 44);
		panel.add(lblProduto);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditar.setBounds(172, 170, 134, 31);
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExcluir.setBounds(307, 170, 127, 31);
		panel.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 211, 917, 217);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		comboBoxProduto = new JComboBox();
		comboBoxProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		carregarComboBoxProdutos();

		comboBoxProduto.setBounds(580, 73, 273, 37);

		panel.add(comboBoxProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuantidade.setAlignmentX(0.5f);
		lblQuantidade.setBounds(474, 127, 143, 39);
		panel.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(608, 127, 96, 39);
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(e -> {
			Produto produto = (Produto) comboBoxProduto.getSelectedItem();
			Integer quantidade = Integer.parseInt(txtQuantidade.getText());

			BigDecimal valorTotal = produto.getPrecoDeVenda().multiply(new BigDecimal(quantidade));

			Item item = new Item(produto.getNome(), quantidade, produto.getPrecoDeVenda(), valorTotal);

			itens.add(item);

			carregarTabela();
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdicionar.setBounds(10, 170, 161, 31);
		panel.add(btnAdicionar);

		JLabel lblTotalTexto = new JLabel("TOTAL:");
		lblTotalTexto.setForeground(Color.BLUE);
		lblTotalTexto.setBackground(Color.RED);
		lblTotalTexto.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTotalTexto.setBounds(371, 468, 75, 44);
		panel.add(lblTotalTexto);

		lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBackground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTotal.setBounds(456, 468, 75, 44);
		panel.add(lblTotal);

		JLabel lblData = new JLabel("data");
		lblData.setForeground(Color.BLUE);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblData.setBounds(741, 10, 255, 22);
		lblData.setText(LocalDate.now().toString());
		panel.add(lblData);
		
		comboBoxCliente = new JComboBox();
		carregarComboBoxCliente();
		comboBoxCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxCliente.setBounds(126, 73, 273, 37);
		panel.add(comboBoxCliente);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(e -> {
			acaoBotaoFinalizar(e);
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFinalizarCompra.setBounds(745, 488, 191, 31);
		panel.add(btnFinalizarCompra);
		
	
	}

	private void acaoBotaoFinalizar(ActionEvent e) {
		
		validarDados();
		Compra compra = new Compra(itens,new BigDecimal(lblTotal.getText()), LocalDate.now());
		ClienteDAO dao = new ClienteDAO();
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja finalizar a compra no valor de "+ lblTotal.getText());
		if(opcao == 0) {
			System.out.println(opcao);
			dao.adicionarCompraCliente(comboBoxCliente.getSelectedIndex(), compra);
			
			txtQuantidade.setText("0");	
			limparCampos();
		}	
		
	}

	private void carregarTabela() {
		ModeloTabelaItem model = new ModeloTabelaItem(itens);
		table.setModel(model);

		lblTotal.setText(model.valorTotal().toString());

	}
	
	private void carregarComboBoxCliente() {
		ClienteDAO dao = new ClienteDAO();
		comboBoxCliente.setModel(new ModeloComboBox(dao.getClientes()));
	}

	private void carregarComboBoxProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		comboBoxProduto.setModel(new ModeloComboBox(dao.getProdutos()));
	}
	
	
	
	private void limparCampos() {
		itens.clear();
		carregarComboBoxCliente();
		carregarComboBoxProdutos();
		txtQuantidade.setText("");
		lblTotal.setText("0");
		table.setModel(new DefaultTableModel());
		
	}
	
	private void validarDados() {
		boolean matches = txtQuantidade.getText().matches("^//d$");
		
		System.out.println(matches);
	}
}

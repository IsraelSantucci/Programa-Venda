package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.com.santucci.venda.model.dao.ProdutoDAO;
import br.com.santucci.venda.model.entity.Produto;
import br.com.santucci.venda.view.tablemodel.ModeloTabelaProduto;

public class FormularioProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtProduto;
	private JTextField txtQuantidade;
	private JTable tableProdutos;
	private JTextField txtCodigo;
	private JTextField txtPrecoDeCompra;
	private JTextField txtPrecoDeVenda;
	private JTextField txtPesquisar;
	private boolean estaAtualizando = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioProduto frame = new FormularioProduto();
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
	public FormularioProduto() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1058, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Cadastro de Produtos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(338, 10, 218, 44);
		panel.add(lblTitulo);

		JLabel lblProduto = new JLabel("produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProduto.setAlignmentX(0.5f);
		lblProduto.setBounds(261, 85, 97, 36);
		panel.add(lblProduto);

		JLabel lblQuantidade = new JLabel("quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuantidade.setAlignmentX(0.5f);
		lblQuantidade.setBounds(688, 85, 133, 36);
		panel.add(lblQuantidade);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCadastrar.setBounds(10, 263, 132, 31);
		panel.add(btnCadastrar);

		btnCadastrar.addActionListener(e -> {
			acaoBotaoCadastrar(e);
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditar.setBounds(152, 263, 120, 31);
		btnEditar.addActionListener(e -> {
			acaoBotaoEditar(e);
		});
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExcluir.setBounds(282, 263, 120, 31);
		btnExcluir.addActionListener(e -> {
			acaoBotaoExcluir(e);
		});
		panel.add(btnExcluir);

		txtProduto = new JTextField();
		txtProduto.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtProduto.setColumns(20);
		txtProduto.setBounds(359, 86, 317, 36);
		panel.add(txtProduto);

		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtQuantidade.setColumns(20);
		txtQuantidade.setBounds(812, 86, 184, 36);
		panel.add(txtQuantidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 376, 898, 119);
		panel.add(scrollPane);

		tableProdutos = new JTable();
		scrollPane.setViewportView(tableProdutos);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCodigo.setColumns(20);
		txtCodigo.setBounds(96, 86, 113, 36);
		panel.add(txtCodigo);

		JLabel lblCodigo = new JLabel("cod:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCodigo.setAlignmentX(0.5f);
		lblCodigo.setBounds(31, 85, 55, 36);
		panel.add(lblCodigo);

		txtPrecoDeCompra = new JTextField();
		txtPrecoDeCompra.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPrecoDeCompra.setColumns(20);
		txtPrecoDeCompra.setBounds(192, 190, 184, 36);
		panel.add(txtPrecoDeCompra);

		JLabel lblPrecoDeCompra = new JLabel("pre\u00E7o de compra:");
		lblPrecoDeCompra.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrecoDeCompra.setAlignmentX(0.5f);
		lblPrecoDeCompra.setBounds(10, 189, 182, 36);
		panel.add(lblPrecoDeCompra);

		txtPrecoDeVenda = new JTextField();
		txtPrecoDeVenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPrecoDeVenda.setColumns(20);
		txtPrecoDeVenda.setBounds(611, 190, 184, 36);
		panel.add(txtPrecoDeVenda);

		JLabel lblPrecoDeVenda = new JLabel("pre\u00E7o de venda:");
		lblPrecoDeVenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrecoDeVenda.setAlignmentX(0.5f);
		lblPrecoDeVenda.setBounds(421, 190, 168, 36);
		panel.add(lblPrecoDeVenda);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setBounds(412, 263, 132, 31);
		btnCancelar.addActionListener(e -> {
			resetarCampos();
		});
		panel.add(btnCancelar);

		txtPesquisar = new JTextField();
		txtPesquisar.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPesquisar.setColumns(20);
		txtPesquisar.setBounds(586, 263, 209, 36);
		panel.add(txtPesquisar);

		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPesquisar.setBounds(805, 263, 133, 31);
		panel.add(btnPesquisar);
		atualizarTabela();
	}

	private void acaoBotaoEditar(ActionEvent e) {
		ProdutoDAO dao = new ProdutoDAO();
		int linhaSelecionada = tableProdutos.getSelectedRow();

		txtProduto.setText(dao.getProdutos().get(linhaSelecionada).getNome());
		txtQuantidade.setText(dao.getProdutos().get(linhaSelecionada).getQuantidade().toString());
		txtPrecoDeCompra.setText(dao.getProdutos().get(linhaSelecionada).getPrecoDeCompra().toString());
		txtPrecoDeVenda.setText(dao.getProdutos().get(linhaSelecionada).getPrecoDeVenda().toString());
		estaAtualizando = true;
		atualizarTabela();	
	}

	private void acaoBotaoExcluir(ActionEvent e) {
		ProdutoDAO dao = new ProdutoDAO();
		int linhaSelecionada = tableProdutos.getSelectedRow();
		dao.excluir(linhaSelecionada);
		atualizarTabela();
		
	}

	private void acaoBotaoCadastrar(ActionEvent e) {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			if (txtProduto.getText().isEmpty()) {
				throw new IllegalArgumentException("O nome do Produto não pode ser Vazio.");
			}

			String nomeProduto = txtProduto.getText();
			Integer quantidade = Integer.parseInt(txtQuantidade.getText());
			BigDecimal precoDeCompra = new BigDecimal(txtPrecoDeCompra.getText());
			BigDecimal precoDeVenda = new BigDecimal(txtPrecoDeVenda.getText());

			if (dao.getProdutos().contains(new Produto(nomeProduto, quantidade, precoDeCompra, precoDeVenda))
					&& !estaAtualizando) {
				throw new IllegalArgumentException("Esse Produto ja esta Cadastrado");
			}
			dao.salvar(new Produto(nomeProduto, quantidade, precoDeCompra, precoDeVenda));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Digito um Valor valido no Campo Valor");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		atualizarTabela();

	}

	public void atualizarTabela() {

		ProdutoDAO dao = new ProdutoDAO();

		String[] colunas = new String[] { "Nome", "Quantidade", "Preço" };
		ModeloTabelaProduto modelo = new ModeloTabelaProduto(dao.getProdutos(), colunas);

		tableProdutos.setModel(modelo);
		tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableProdutos.getColumnModel().getColumn(0).setResizable(false);
		tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableProdutos.getColumnModel().getColumn(1).setResizable(false);
		tableProdutos.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableProdutos.getColumnModel().getColumn(2).setResizable(false);
		tableProdutos.getTableHeader().setReorderingAllowed(false);
		tableProdutos.setAutoResizeMode(tableProdutos.AUTO_RESIZE_OFF);
		tableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	private void resetarCampos() {
		txtCodigo.setText("");
		txtProduto.setText("");
		txtQuantidade.setText("");
		txtPrecoDeCompra.setText("");
		txtPrecoDeVenda.setText("");
		estaAtualizando = false;
	}
}

package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import br.com.santucci.venda.dao.ClienteDAO;
import br.com.santucci.venda.model.Cliente;
import br.com.santucci.venda.view.tablemodel.ModeloTabelaCliente;

public class FormularioCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tableClientes;
	private JTextField txtCodigo;
	private JFormattedTextField ftxtDataDeNascimento;
	private JFormattedTextField ftxtCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioCliente frame = new FormularioCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public FormularioCliente() throws ParseException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1133, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblCadastroDeClientes = new JLabel("Cadastro De Clientes");
		lblCadastroDeClientes.setBounds(322, 10, 283, 31);
		lblCadastroDeClientes.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblCadastroDeClientes);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNome.setBounds(191, 104, 66, 25);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setBounds(267, 105, 399, 25);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 332, 1053, 243);
		panel.add(scrollPane);

		tableClientes = new JTable();
		scrollPane.setViewportView(tableClientes);

		carregarTabela();

		JButton btnCadastrar = new JButton("Cadastrar");
		
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCadastrar.setBounds(31, 228, 142, 31);
		btnCadastrar.addActionListener(e -> acaoBotaoCadastrar(e));
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditar.setBounds(187, 228, 131, 31);
		btnEditar.addActionListener(e -> acaoBotaoEditar(e));
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExcluir.setBounds(328, 228, 131, 31);
		btnExcluir.addActionListener(e -> acaoBotaoExcluir(e));
		panel.add(btnExcluir);

		JLabel lblCod = new JLabel("Cod:");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCod.setBounds(31, 105, 56, 25);
		panel.add(lblCod);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(94, 105, 87, 25);
		txtCodigo.setEditable(false);
		panel.add(txtCodigo);

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCpf.setBounds(677, 104, 42, 25);
		panel.add(lblCpf);

		ftxtCpf = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		ftxtCpf.setBounds(729, 104, 283, 26);
		panel.add(ftxtCpf);

		JLabel lblDataDeNasc = new JLabel("Data de Nascimento:");
		lblDataDeNasc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDataDeNasc.setBounds(31, 157, 211, 25);
		panel.add(lblDataDeNasc);

		ftxtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftxtDataDeNascimento.setBounds(252, 157, 142, 26);
		panel.add(ftxtDataDeNascimento);
	}

	private void acaoBotaoEditar(ActionEvent e) {
		
	}

	private void acaoBotaoCadastrar(ActionEvent e) {
		try {
			// lança uma exceçao se tiver algo de errado
			this.validarDados();
			int codigo = txtCodigo.getText().equals("")? -1 : Integer.parseInt(txtCodigo.getText());
			ClienteDAO dao = new ClienteDAO();
			if(codigo == -1) {
				codigo = dao.getClientes().size();
			}
		
			
			String nome = txtNome.getText();
			String cpf = ftxtCpf.getText();

			String[] data = ftxtDataDeNascimento.getText().split("/");
			int dia = Integer.valueOf(data[0]);
			int mes = Integer.valueOf(data[1]);
			int ano = Integer.valueOf(data[2]);
			System.out.println(dia);
			System.out.println(mes);
			System.out.println(ano);
			LocalDate dataDeNascimento = LocalDate.of(ano, mes, dia);
			Cliente cliente = new Cliente(codigo, nome, cpf, dataDeNascimento);
			
			dao.salvar(cliente);
			carregarTabela();
			limparCampos();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	private void acaoBotaoExcluir(ActionEvent e) {
		int indice = tableClientes.getSelectedRow();
		if(indice >= 0) {
			ClienteDAO dao = new ClienteDAO();
			int decisao = JOptionPane.showConfirmDialog(this, "Deseja excluir o Cliente " + dao.pequisar(indice).getNome(),
					"Excluir", JOptionPane.YES_NO_CANCEL_OPTION);
			if (decisao == JOptionPane.OK_OPTION) {
				dao.excluir(indice);
				carregarTabela();
			}
		}
		
	}

	private void carregarTabela() {
		ClienteDAO dao = new ClienteDAO();
		ModeloTabelaCliente modelo = new ModeloTabelaCliente(dao.getClientes());
		tableClientes.setModel(modelo);
		configurarTabela();
	}

	private void validarDados() {

		
//		if(!txtCodigo.getText().matches("^\\d+$")) {
//			throw new IllegalArgumentException("O campo Código Invalido");
//		}

		if (txtNome.getText().isEmpty()) {
			throw new IllegalArgumentException("O campo nome deve ser prenchido");
		}

		if (ftxtCpf.getText().isEmpty()) {
			throw new IllegalArgumentException("O campo Cpf deve ser prenchido");
		}

		if (ftxtDataDeNascimento.getText().isEmpty()) {
			throw new IllegalArgumentException("O campo Data de Nascimento deve ser prenchido");
		}
		
		
		validarCampoData();
	}
	
	private void validarCampoData() {
		String[] data = ftxtDataDeNascimento.getText().split("/");
		int dia = Integer.valueOf(data[0]);
		if(dia <= 0 || dia > 31) {
			throw new IllegalArgumentException("Dia da Data de Nascimento inválida");
		}
		int mes = Integer.valueOf(data[1]);
		
		if(mes <= 0 || mes > 12) {
			throw new IllegalArgumentException("Mês da Data de Nascimento inválida");
		}
		
		int ano = Integer.valueOf(data[2]);
		if(ano <= 0) {
			throw new IllegalArgumentException("Ano da Data de Nascimento inválida");
		}
	}

	private void configurarTabela() {

		// configurando a celula da tabela para exibir os dados da coluna no centro
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);

		tableClientes.getColumnModel().getColumn(0).setCellRenderer(centro);
		tableClientes.getColumnModel().getColumn(0).setMaxWidth(100);
		tableClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableClientes.getColumnModel().getColumn(0).setResizable(false);

		tableClientes.getColumnModel().getColumn(1).setCellRenderer(centro);
		tableClientes.getColumnModel().getColumn(1).setResizable(false);
		tableClientes.getColumnModel().getColumn(1).setPreferredWidth(100);

		tableClientes.getColumnModel().getColumn(2).setCellRenderer(centro);
		tableClientes.getColumnModel().getColumn(2).setResizable(false);
		tableClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableClientes.getColumnModel().getColumn(2).setMaxWidth(100);

		tableClientes.getColumnModel().getColumn(3).setCellRenderer(centro);
		tableClientes.getColumnModel().getColumn(3).setResizable(false);
		tableClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableClientes.getColumnModel().getColumn(3).setMaxWidth(100);

		tableClientes.getColumnModel().getColumn(4).setCellRenderer(centro);
		tableClientes.getColumnModel().getColumn(4).setResizable(false);
		tableClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableClientes.getColumnModel().getColumn(4).setMaxWidth(100);

		tableClientes.getTableHeader().setReorderingAllowed(false);

	}
	
	private void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		ftxtCpf.setText("");
		ftxtDataDeNascimento.setText("");
	}
}

package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.List;

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

import br.com.santucci.venda.controller.ClienteController;
import br.com.santucci.venda.model.dao.ClienteDAO;
import br.com.santucci.venda.model.entity.Cliente;
import br.com.santucci.venda.model.service.ClienteService;
import br.com.santucci.venda.view.tablemodel.ModeloTabelaCliente;

public class ClienteForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tabelaClientes;
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
					ClienteForm frame = new ClienteForm();
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
	public ClienteForm() throws ParseException {
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

		tabelaClientes = new JTable();
		scrollPane.setViewportView(tabelaClientes);

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
		if(this.getTabelaClientes().getSelectedRow() < 0) {
			System.out.println("selecione um cliente para editar");
		}else {
			ClienteController controller = new ClienteController();
			controller.executaEdicao(this);
		}
	}

	private void acaoBotaoCadastrar(ActionEvent e) {
		try {
			this.validarDados();
			
			ClienteController controller = new ClienteController();
			controller.executaCadastro(this);
			limparCampos();
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	private void acaoBotaoExcluir(ActionEvent e) {
		int indice = tabelaClientes.getSelectedRow();
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
		tabelaClientes.setModel(modelo);
		configurarTabela();
	}

	public void carregarTabelateste(List<Cliente> clientes) {	
		ModeloTabelaCliente modelo = new ModeloTabelaCliente(clientes);
		tabelaClientes.setModel(modelo);
		configurarTabela();
	}
	
	private void validarDados() {

		
		if(!txtCodigo.getText().matches("^\\d+$")) {
			throw new IllegalArgumentException("O campo Código Invalido");
		}

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

		tabelaClientes.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(100);
		tabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabelaClientes.getColumnModel().getColumn(0).setResizable(false);

		tabelaClientes.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabelaClientes.getColumnModel().getColumn(1).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(100);

		tabelaClientes.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabelaClientes.getColumnModel().getColumn(2).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabelaClientes.getColumnModel().getColumn(2).setMaxWidth(100);

		tabelaClientes.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabelaClientes.getColumnModel().getColumn(3).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabelaClientes.getColumnModel().getColumn(3).setMaxWidth(100);

		tabelaClientes.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabelaClientes.getColumnModel().getColumn(4).setResizable(false);
		tabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabelaClientes.getColumnModel().getColumn(4).setMaxWidth(100);

		tabelaClientes.getTableHeader().setReorderingAllowed(false);

	}
	
	private void limparCampos() {
		txtCodigo.setText("");
		txtNome.setText("");
		ftxtCpf.setText("");
		ftxtDataDeNascimento.setText("");
	}
	
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
	
	public JTextField getTxtNome() {
		return txtNome;
	}
	
	public JFormattedTextField getFtxtCpf() {
		return ftxtCpf;
	}
	
	public JFormattedTextField getFtxtDataDeNascimento() {
		return ftxtDataDeNascimento;
	}
	
	
	public JTable getTabelaClientes() {
		return tabelaClientes;
	}
}

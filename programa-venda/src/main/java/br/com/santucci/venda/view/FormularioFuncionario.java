package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.santucci.venda.model.dao.FuncionarioDAO;
import br.com.santucci.venda.model.entity.Funcionario;
import br.com.santucci.venda.view.tablemodel.ModeloTabelaFuncionarios;

public class FormularioFuncionario extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblTituloDoFormulario;
	private JLabel lblNome;
	private JLabel lblSenha;
	private JTextField txtNome;
	private JTextField txtSenha;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	private JTable tabelaFuncionarios;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioFuncionario frame = new FormularioFuncionario();
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
	public FormularioFuncionario() {
		
		setTitle("Cadastro de Funcionarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 494);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		contentPane.add(panel, BorderLayout.CENTER);
		
		
		lblTituloDoFormulario = new JLabel("Cadastro De Funcionarios");
		lblTituloDoFormulario.setBounds(265, 5, 286, 27);
		lblTituloDoFormulario.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		panel.add(lblTituloDoFormulario);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNome.setBounds(40, 100, 88, 27);
		
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(111, 100, 202, 27);
		txtNome.setColumns(10);
		
		panel.add(txtNome);
		
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSenha.setBounds(369, 100, 88, 27);
		
		panel.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(440, 100, 202, 27);
		
		panel.add(txtSenha);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCadastrar.setBounds(40, 201, 131, 27);
		btnCadastrar.addActionListener(e -> acaoBotaoCadastrar(e));
		
		panel.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditar.setBounds(181, 201, 131, 27);
		
		panel.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExcluir.setBounds(326, 201, 131, 27);
		btnExcluir.addActionListener(e -> acaoBotaoExcluir(e));
		
		panel.add(btnExcluir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 274, 577, 163);
		
		panel.add(scrollPane);
		
		tabelaFuncionarios = new JTable();
		
		scrollPane.setViewportView(tabelaFuncionarios);
		
		exibirTabela();
	}

	

	private void acaoBotaoCadastrar(ActionEvent e) {
		try {
			String nome = txtNome.getText();
			String senha = txtSenha.getText();
			
			if(nome.isEmpty()) {
				throw new IllegalArgumentException("O campo do nome deve ser preenchido");
			}
			if(senha.isEmpty()) {
				throw new IllegalArgumentException("O campo do Senha deve ser preenchido");
			}
			Funcionario funcionario = new Funcionario(nome, senha);
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.salvar(funcionario);
			exibirTabela();
		}catch(IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	private void acaoBotaoExcluir(ActionEvent e) {
		int indice = tabelaFuncionarios.getSelectedRow();
		new FuncionarioDAO().excluir(indice);
		exibirTabela();
	}
	
	private void exibirTabela() {
		FuncionarioDAO dao = new FuncionarioDAO();
		ModeloTabelaFuncionarios model = new ModeloTabelaFuncionarios(dao.getFuncionarios());
		tabelaFuncionarios.setModel(model);
	}
	
	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}
}

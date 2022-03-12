package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

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
	private JTextField txtNome;
	private JTextField txtSenha;
	private JTable tableFuncionarios;

	/**
	 * Launch the application.
	 */
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCadastroDeFuncionarios = new JLabel("Cadastro De Funcionarios");
		lblCadastroDeFuncionarios.setBounds(265, 5, 286, 27);
		lblCadastroDeFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 22));
		panel.add(lblCadastroDeFuncionarios);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNome.setBounds(40, 100, 88, 27);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(111, 100, 202, 27);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSenha.setBounds(369, 100, 88, 27);
		panel.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(440, 100, 202, 27);
		panel.add(txtSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(e -> {
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
			
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCadastrar.setBounds(40, 201, 131, 27);
		panel.add(btnCadastrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditar.setBounds(181, 201, 131, 27);
		panel.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExcluir.setBounds(326, 201, 131, 27);
		panel.add(btnExcluir);
		btnExcluir.addActionListener(e ->{
			int indice = tableFuncionarios.getSelectedRow();
			new FuncionarioDAO().excluir(indice);
			exibirTabela();
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 274, 577, 163);
		panel.add(scrollPane);
		
		tableFuncionarios = new JTable();
		scrollPane.setViewportView(tableFuncionarios);
		
		exibirTabela();
	}
	
	private void exibirTabela() {
		FuncionarioDAO dao = new FuncionarioDAO();
		ModeloTabelaFuncionarios model = new ModeloTabelaFuncionarios(dao.getFuncionarios());
		tableFuncionarios.setModel(model);
	}
}

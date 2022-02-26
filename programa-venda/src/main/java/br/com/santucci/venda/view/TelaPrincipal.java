package br.com.santucci.venda.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Gerenciador De Vendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 414);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);
		
		JMenuItem mntmFazerVenda = new JMenuItem("Fazer Venda");
		mntmFazerVenda.addActionListener(e -> {
			FormularioVenda telaVenda =  new FormularioVenda();
			telaVenda.setVisible(true);
		});
		mnVendas.add(mntmFazerVenda);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmNovoCliente = new JMenuItem("clientes");
		mnCadastrar.add(mntmNovoCliente);
		mntmNovoCliente.addActionListener(e -> {
			FormularioCliente formCliente;
			try {
				formCliente = new FormularioCliente();
				formCliente.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		JMenuItem mntmFuncionarios = new JMenuItem("funcionarios");
		mnCadastrar.add(mntmFuncionarios);
		mntmFuncionarios.addActionListener(e -> {
			FormularioFuncionario formFuncionario = new FormularioFuncionario();
			formFuncionario.setVisible(true);
		});
		
		JMenuItem mntmProdutos = new JMenuItem("produtos");
		mntmProdutos.addActionListener(e -> {
			FormularioProduto formProduto = new FormularioProduto();
			formProduto.setVisible(true);
			
		});
		mnCadastrar.add(mntmProdutos);
		
		JMenu mnPequisar = new JMenu("Pequisar");
		menuBar.add(mnPequisar);
		
		JMenuItem mntmPequisarCliente = new JMenuItem("Pequisar Cliente");
		mntmPequisarCliente.addActionListener(e -> {
			JanelaConsultaDeClientes tela = new JanelaConsultaDeClientes();
			tela.setVisible(true);
		});
		mnPequisar.add(mntmPequisarCliente);
		
		
	}
	
	

}

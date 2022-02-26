package br.com.santucci.venda.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.santucci.venda.model.Funcionario;

public class FuncionarioDAO {

	private List<Funcionario> funcionarios = null;
	
	public FuncionarioDAO() {
		abrirArquivo();
	}
	
	public void salvar(Funcionario funcionario) {
		funcionarios.add(funcionario);
		salvarArquivo();
	}
	
	public void excluir(int indice) {
		funcionarios.remove(indice);
		salvarArquivo();
	}
	
	public void pesquisar() {
		
	}
	
	public List<Funcionario> getFuncionarios() {
		return Collections.unmodifiableList(funcionarios);
	}
	
	private void abrirArquivo() {
		
		try (FileInputStream fis = new FileInputStream("funcionarios.txt")){
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);
				funcionarios = (List<Funcionario>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(EOFException ex) {
				funcionarios = new ArrayList<>();
			}
		} catch (FileNotFoundException e) {
			File arquivoFuncionarios = new File("funcionarios.txt");
			try {
				arquivoFuncionarios.createNewFile();
				funcionarios = new ArrayList<>();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void salvarArquivo() {
		try(FileOutputStream fos = new FileOutputStream("funcionarios.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(funcionarios);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

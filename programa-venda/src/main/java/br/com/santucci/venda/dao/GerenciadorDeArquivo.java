package br.com.santucci.venda.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author israel
 *
 * @param <T>
 * Classe para facilitar o salvamento de lista em arquivos e o seu carregamento
 */
public class GerenciadorDeArquivo <T>{
	
	private String nomeDoArquivo;
	
	/**
	 * 
	 * @param nomeDoArquivo
	 * No construtor deve-se fornecer o nome do arquivo que sera aberto para que seja feitas as manipulações pelos metodos da classe
	 */
	public GerenciadorDeArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	/**
	 * 
	 * @param lista
	 * metodo para salvar uma lista no arquivo com o nome fornecido no construtor
	 */
	public  void salvar(List<T> lista) {
		
		try(FileOutputStream fis = new FileOutputStream(nomeDoArquivo)){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(lista);
		} catch (FileNotFoundException e) {
			File arquivo = new File(nomeDoArquivo);
			try {
				arquivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return 
	 * retorna uma List<T> salva no nome do arquivo fornecido ou uma lista vazia se o arquivo nao for encontrado
	 * no processo de abertura e se cria um arquivo com o nome fornecido no construtor
	 */
	public List<T> listar(){
		
		List<T> lista = null;
		
		try(FileInputStream fis = new FileInputStream(nomeDoArquivo)){
			ObjectInputStream ois = new ObjectInputStream(fis);
			lista = (List<T>) ois.readObject();
			return lista;
		} catch (FileNotFoundException e) {
			//Arquivo nao existe
			//no tratamento esta criando o arquivo com o nome fornecido para evitar futuros erros
			File arquivo = new File(nomeDoArquivo);
			try {
				arquivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		lista = new ArrayList<T>();
		return lista;
	}

}

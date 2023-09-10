package com.serratec.ListaClasse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.serratec.classes.Cliente;
import com.serratec.conexao.Conexao;
import com.serratec.dao.ClienteDAO;
import com.serratec.dml.ClienteDML;

public class ListaCliente {

	private static Conexao con;
	private static String schema;
	
	static ArrayList<Cliente> clientes = new ArrayList<>();
	
	public ListaCliente(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaClientes();
	} // talvez criar uma nova lista pra atualizar e tentar descobrir como apagar a ultima? ok ok

	private Cliente dadosCliente(ResultSet tabela) {
		Cliente c = new Cliente();
		
		try {			
			c.setNome(tabela.getString("nome"));
			c.setCpf_cnpj(tabela.getString("cpf"));
			c.setEndereco(tabela.getString("endereco"));
			c.setTelefone(tabela.getString("telefone"));
			c.setEmail(tabela.getString("email"));
			c.setDtnasc(tabela.getString("dtnasc"));
			c.setIdCliente(tabela.getInt("idcliente"));
			
			return (Cliente) c;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void carregarListaClientes() {
		ClienteDAO cdao = new ClienteDAO(con, schema);
		
		ResultSet tabela = cdao.carregarClientes();
		ListaCliente.clientes.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				ListaCliente.clientes.add(dadosCliente(tabela));				
			}
			
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void imprimirClientes() {
		System.out.println("\nLista de clientes: ");
		System.out.println("\n===================");
		System.out.println("\nNome\t\t| CPF\t\t| Data de nascimento");
		System.out.println("\n===================");
		
		for (Cliente c : clientes){
			System.out.println (c.getNome() + "\t\t" + c.getCpf_cnpj()+ "\t\t"+ c.getDtnasc());
		}
		
	}
	
	public static Cliente localizarCliente(int opt) {
		Cliente localizado = null;
		int idcliente;
		String cpfcliente;
		Scanner input = new Scanner(System.in);
			
		switch(opt)	{
		
			case 1: {
					idcliente = input.nextInt();	
					for (Cliente c : clientes) {
						if (c.getIdCliente()== idcliente) {
							localizado = c;
							break;}
							System.out.println("Cliente não localizado, retornando ao menu."); input.next(); break;
					}
			}
			
			case 2: {
					cpfcliente = input.nextLine();
					for (Cliente c : clientes) {
						if (c.getCpf_cnpj().equals(cpfcliente)) {
							localizado = c;
							break;}
							
					} //System.out.println("Cliente não localizado, retornando ao menu."); input.next(); break;
			}
		}return localizado;
	}
	
	public static boolean excluirCliente(Cliente c) {
	
		
		boolean excluido = false;
		for (Cliente cl : clientes) {
			if(cl.getIdCliente() == c.getIdCliente()) {
				clientes.remove(clientes.lastIndexOf(cl));
				ClienteDML.excluirCliente(con, schema, c);
				excluido = true;
				break;
			}
		}
		return excluido;
	}
}


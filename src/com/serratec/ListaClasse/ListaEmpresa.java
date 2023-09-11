package com.serratec.ListaClasse;

import com.serratec.classes.Empresa;
import com.serratec.classes.Produto;
import com.serratec.conexao.Conexao;
import com.serratec.dao.EmpresaDAO;
import com.serratec.dml.EmpresaDML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaEmpresa {
	private static Conexao con;
	private static String schema;

	static ArrayList<Empresa> empresas = new ArrayList<>();

	public ListaEmpresa(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		carregarListaEmpresas();
	}

	private Empresa dadosEmpresa(ResultSet tabela) {
		Empresa e = new Empresa();

		try {
			e.setNome(tabela.getString("nome"));
			e.setCpf_cnpj(tabela.getString("cnpj"));
			e.setEndereco(tabela.getString("endereco"));
			e.setTelefone(tabela.getString("telefone"));
			e.setEmail(tabela.getString("email"));
			e.setIdEmpresa(tabela.getInt("idEmpresa"));
			return e;
		} catch (SQLException var4) {
			var4.printStackTrace();
			return null;
		}
	}

	private void carregarListaEmpresas() {
		EmpresaDAO edao = new EmpresaDAO(con, schema);
		ResultSet tabela = edao.carregarEmpresa();
		ListaEmpresa.empresas.clear();

		try {
			tabela.beforeFirst();

			while (tabela.next()) {
				ListaEmpresa.empresas.add(this.dadosEmpresa(tabela));
			}

			tabela.close();
		} catch (Exception var4) {
			System.err.println(var4);
			var4.printStackTrace();
		}

	}

	public void imprimirEmpresas() {
		System.out.println("\nLista de empresas: ");
		System.out.println("\n===================");
		System.out.println("\nNome\t\t| CNPJ\t\t| email");
		System.out.println("\n===================");
		
		for (Empresa e : empresas){
			System.out.println (e.getNome() + "\t\t" + e.getCpf_cnpj()+ "\t\t"+ e.getEmail());
		}
		
	}

	public static Empresa localizarEmpresa() {
		Empresa localizado = null;
		String cnpjempresa;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
			
				cnpjempresa = input.nextLine();
				for (Empresa e : empresas) {
					if (e.getCpf_cnpj().equals(cnpjempresa)) {
						localizado = e;
						break;}
			
			}return localizado;
	}

	public static boolean excluirEmpresa(Empresa e) {
	
		
		boolean excluido = false;
		for (Empresa cl : empresas) {
			if(cl.getIdEmpresa() == e.getIdEmpresa()) {
				empresas.remove(empresas.lastIndexOf(cl));
				EmpresaDML.excluirEmpresa(con, schema, e);
				excluido = true;
				break;
			}
					
		}
		return excluido;
	}

	public void adicionarEmpresaLista(Empresa e) {
		ListaEmpresa.empresas.add(e);
	}
}
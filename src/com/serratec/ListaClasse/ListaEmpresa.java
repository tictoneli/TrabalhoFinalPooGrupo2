package com.serratec.ListaClasse;

import com.serratec.classes.Empresa;
import com.serratec.conexao.Conexao;
import com.serratec.dao.EmpresaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaEmpresa {
	private static Conexao con;
	private static String schema;

	ArrayList<Empresa> Empresas = new ArrayList<>();

	public ListaEmpresa(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		this.carregarListaEmpresas();
	}

	private Empresa dadosEmpresa(ResultSet tabela) {
		Empresa e = new Empresa();

		try {
			e.setNome(tabela.getString("nome"));
			e.setCpf_cnpj("cpf");
			e.setEndereco(tabela.getString("endereco"));
			e.setTelefone("telefone");
			e.setEmail("email");
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
		this.Empresas.clear();

		try {
			tabela.beforeFirst();

			while (tabela.next()) {
				this.Empresas.add(this.dadosEmpresa(tabela));
			}

			tabela.close();
		} catch (Exception var4) {
			System.err.println(var4);
			var4.printStackTrace();
		}

	}
}
package com.serratec.ListaClasse;

import com.serratec.classes.Pedido;
import com.serratec.conexao.Conexao;
import com.serratec.dao.PedidoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaPedido {
	private Conexao con;
	private String schema;

	static ArrayList<Pedido> pedidos = new ArrayList<>();
/*
	public ListaPedido(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		this.carregarListaPedidos();
	}

	private Pedido dadosPedido(ResultSet tabela) {
		Pedido p = new Pedido(0, 0, null, null, null);

		try {
			p.setNome(tabela.getString("nome"));
			p.setCpf_cnpj("cpf");
			p.setEndereco(tabela.getString("endereco"));
			p.setTelefone("telefone");
			p.setEmail("email");
			p.setIdEmpresa(tabela.getInt("idEmpresa"));
			return p;
		} catch (SQLException var4) {
			var4.printStackTrace();
			return null;
		}
	}

	private void carregarListaPedidos() {
		PedidoDAO edao = new PedidoDAO(con, schema);
		ResultSet tabela = edao.carregarEmpresa();
		this.pedidos.clear();

		try {
			tabela.beforeFirst();

			while (tabela.next()) {
				this.pedidos.add(this.dadosPedido(tabela));
			}

			tabela.close();
		} catch (Exception var4) {
			System.err.println(var4);
			var4.printStackTrace();
		}

	}*/
}
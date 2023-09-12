package com.serratec.ListaClasse;

import com.serratec.classes.Cliente;
import com.serratec.classes.Empresa;
import com.serratec.classes.Pedido;
import com.serratec.classes.ProdutoPedido;
import com.serratec.conexao.Conexao;
import com.serratec.dao.PedidoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaPedido {
	private Conexao con;
	private String schema;

	static ArrayList<Pedido> pedidos = new ArrayList<>();

	public ListaPedido(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		this.carregarListaPedidos();
	}

	private Pedido dadosPedido(ResultSet tabela) {
		Pedido p = new Pedido();

		try {
			p.setCdPedido(tabela.getLong("codigo"));
			String datapedido = tabela.getString("datapedido");
			p.setDtPedido(LocalDate.parse(datapedido));
			p.setCliente(new Cliente());
			p.setEmpresa(new Empresa());
			p.setProdutos(new ProdutoPedido());
			p.setIdPedido(tabela.getLong("idpedido"));
			return p;
		} catch (SQLException var4) {
			var4.printStackTrace();
			return null;
		}
	}

	private void carregarListaPedidos() {
		PedidoDAO pdao = new PedidoDAO(con, schema);
		ResultSet tabela = pdao.carregarPedido();
		ListaPedido.pedidos.clear();

		try {
			while (tabela.next()) {
				ListaPedido.pedidos.add(this.dadosPedido(tabela));
			}

			tabela.close();
		} catch (Exception var4) {
			System.err.println(var4);
			var4.printStackTrace();
		}

	}
}
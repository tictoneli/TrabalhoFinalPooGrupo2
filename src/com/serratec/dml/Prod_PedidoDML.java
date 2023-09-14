package com.serratec.dml;

import com.serratec.classes.Pedido;
import com.serratec.classes.Prod_Pedido;
import com.serratec.classes.Produto;
import com.serratec.conexao.Conexao;
import com.serratec.dao.Prod_PedidoDAO;

public class Prod_PedidoDML {

	public static void gravarPedido(Conexao con, String schema, Produto prod, Pedido ped) {
		Prod_PedidoDAO pdao = new Prod_PedidoDAO(con, schema);
		pdao.incluirProd_Pedido(0, prod, ped);

	}

	public static void alterarPedido(Conexao con, String schema, Prod_Pedido p, Produto prod, Pedido ped) {
		Prod_PedidoDAO pdao = new Prod_PedidoDAO(con, schema);
		pdao.alterarProd_Pedido(p, 0, prod, ped);
	}

	public static void excluirPedido(Conexao con, String schema, Prod_Pedido p) {
		Prod_PedidoDAO pdao = new Prod_PedidoDAO(con, schema);
		pdao.excluirProd_Pedido(p);
	}
}

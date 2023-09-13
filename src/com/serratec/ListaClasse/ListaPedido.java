package com.serratec.ListaClasse;

import com.serratec.classes.Cliente;
import com.serratec.classes.Empresa;
import com.serratec.classes.Pedido;
import com.serratec.classes.ProdutoPedido;
import com.serratec.conexao.Conexao;
import com.serratec.constantes.Util;
import com.serratec.dao.PedidoDAO;
import com.serratec.dml.PedidoDML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void carregarListaPedidos() {
		PedidoDAO pdao = new PedidoDAO(con, schema);
		ResultSet tabela = pdao.carregarPedido();
		ListaPedido.pedidos.clear();

		try {
			while (tabela.next()) {
				ListaPedido.pedidos.add(dadosPedido(tabela));
			}

			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void adicionarPedidoLista(Pedido ped) {
		ListaPedido.pedidos.add(ped);
	}
	
	public static Pedido localizarPedido() {
		Pedido localizado = null;
		Long ped;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		ped = input.nextLong();
		
		for(Pedido p : pedidos) {
			if (p.getCdPedido() == ped) {
				localizado = p;
				break;
			}
		}
		return localizado;
	}
	
	public boolean excluirPedido(Pedido pExcluir) {
		boolean excluido = false;
		for (Pedido p : pedidos) {
			if (p.getIdPedido() == pExcluir.getIdPedido()) {
				pedidos.remove(pedidos.lastIndexOf(p));
				PedidoDML.excluirPedido(con, schema, pExcluir);
				excluido = true;
				break;
			}
		}
		return excluido;
	}
	
	public static void imprimirPedido() {

		System.out.println(Util.LINHAD);
		Util.escrever("Relatório de Pedidos:");
		System.out.println(Util.LINHA);
		Util.escrever("Número\t | Data\t\t| Cliente\t\t| Empresa\t| Lista Produtos");
		
		for (Pedido p : pedidos) {
			System.out.println(p.getCdPedido() + "\t\t" + p.getDtPedido() + "\t\t" + p.getCliente().getNome() + p.getEmpresa().getNome() + p.getProdutos());
		}//varrer itens print produtos?					
	}
}
package com.serratec.menu;

import java.sql.ResultSet;

import com.serratec.ListaClasse.ListaPedido;
import com.serratec.ListaClasse.ListaProduto;
import com.serratec.classes.Pedido;
import com.serratec.classes.Prod_Pedido;
import com.serratec.classes.Produto;
import com.serratec.conexao.Connect;
import com.serratec.constantes.Util;
import com.serratec.dao.PedidoDAO;
import com.serratec.dml.PedidoDML;
import com.serratec.dml.Prod_PedidoDML;

public class MenuPedido {
	public static int menu() {

		Util.escrever(Util.LINHAD);
		Util.escrever("Menu Pedido");
		Util.escrever(Util.LINHAD);
		Util.escrever("1- Novo Pedido");
		Util.escrever("2- Alterar");
		Util.escrever("3- Excluir");
		Util.escrever("4- Listar");
		Util.escrever("5- Voltar");
		Util.escrever("6- Sair");
		Util.escrever(Util.LINHA);

		return Util.validarInteiro("Informe uma opcao: ");
	}

	public static int opcoes(int opcao) {

		switch (opcao) {
		case 1:
			cadastrar();
			break;
		case 2:
			alterar();
			break;
		case 3:
			excluir();
			break;
		case 4:
			listar();
			break;
		case 5:
			int opcaoMenuPrincipal = MenuPrincipal.menuPrincipal();
			return MenuPrincipal.opcoes(opcaoMenuPrincipal);
		case 6:
			Util.escrever("Sistema Finalizado!");
			break;
		default:
			Util.escrever("Opcao invalida");
			Util.aperteEnter();
			return opcoes(menu());
		}
		return opcao;
	}

	
	public static int cadastrar() {

		Produto prod = null;
		Pedido p = Pedido.cadastrarPedido();
		Connect.pedidos.adicionarPedidoLista(p);
		Pedido l = null;
		
		for (int i = 1; i <= ListaPedido.pedidos.size() ; i++) {
			if(!(ListaPedido.localizarIdPedido(i) == null));{
				l = ListaPedido.localizarIdPedido(i);
				 
			}
		}
		
		//Pedido l = ListaPedido.localizarIdPedido(i);
		
		
		for (int i = 0; i <= ListaProduto.produtos.size() ; i++) {
			if(!(Prod_Pedido.localizarIdItem(i) == null));{
				prod = Prod_Pedido.localizarIdItem(i);
			}
		}
		
		
		//Produto prod = Prod_Pedido.localizarIdItem();
		
		PedidoDML.gravarPedido(Connect.getCon(), Connect.dadosCon.getSchema(), p);
		
		if (!(l == null)) {
			Prod_PedidoDML.gravarPedido(Connect.getCon(), Connect.dadosCon.getSchema(), prod, l);
		}
		
		if (p == null) {
			System.err.println("Pedido não cadastrado! ");
			return opcoes(menu());
		}
		Util.aperteEnter();

		return opcoes(menu());
	}

	public static int alterar() {
		Util.aperteEnter();
		return opcoes(menu());
	}

	public static int excluir() {
		Util.aperteEnter();
		return opcoes(menu());
	}

	public static int listar() {
		ListaPedido.imprimirPedido();
		Util.aperteEnter();
		return opcoes(menu());
	}
}

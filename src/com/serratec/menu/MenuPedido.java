package com.serratec.menu;

import com.serratec.classes.Pedido;
import com.serratec.classes.ProdutoPedido;
import com.serratec.conexao.*;
import com.serratec.constantes.Util;

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
		case 1:	cadastrar(); break;
		case 2:	alterar(); break;
		case 3:	excluir(); break;
		case 4:	listar(); break;
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
		
		
		if(Pedido.cadastrarPedido() == null) {
			System.err.println("Pedido não cadastrado! ");
			Util.aperteEnter();
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
	
		Util.aperteEnter();
		return opcoes(menu());
	}
}
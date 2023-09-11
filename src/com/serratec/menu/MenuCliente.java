package com.serratec.menu;

import com.serratec.classes.Cliente;
import com.serratec.conexao.Connect;
import com.serratec.constantes.Util;
import com.serratec.dml.ClienteDML;
import java.util.Scanner;
import com.serratec.ListaClasse.ListaCliente;

public class MenuCliente {	
	
	public static int menu() {
		
		Util.escrever(Util.LINHAD);
		Util.escrever("Menu Cliente");
		Util.escrever(Util.LINHAD);
		Util.escrever("1- Cadastrar");
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
			case 1: cadastrar("Cadastro de cliente: "); break;
			case 2: alterar("Alteração de cliente - insira o CPF do cliente a ser alterado: "); break;
			case 3: excluir("Exclusão de cliente - insira o CPF do cliente a ser excluido: "); break;
			case 4: listar(); break;
			case 5: 
				int opcaoMenuPrincipal = MenuPrincipal.menuPrincipal();
				return MenuPrincipal.opcoes(opcaoMenuPrincipal);
			case 6: Util.escrever("Sistema Finalizado!"); break;
			default: Util.escrever("Opcao invalida");
		}
		return opcao;
	}
		
	public static int cadastrar(String msg) {
		
		System.out.println(msg);
		
		ClienteDML.gravarCliente(Connect.getCon(), Connect.dadosCon.getSchema(), Cliente.cadastrarCliente());
		Connect.clientes.carregarListaClientes();
		return opcoes(menu());
	}
	
	public static int alterar(String msg) {
		System.out.println(msg);
		
		Cliente c = ListaCliente.localizarCliente();
		if (!(c == null)) {
		
		Cliente.alterarCliente(c);
		ClienteDML.alterarCliente(Connect.getCon(), Connect.dadosCon.getSchema(), c);
		
		}else {System.out.println("Cliente não encontrado, retornando ao menu."); }
		
		return opcoes(menu());
	}
	
	public static int excluir(String msg) {
		System.out.println(msg);
		if(	ListaCliente.excluirCliente(ListaCliente.localizarCliente())){
			
			System.out.println("Cliente excluído com sucesso!");
		}else{ System.out.println("Cliente não encontrado, retornando ao menu."); }
		
		return opcoes(menu());
	}
	
	public static int listar() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Connect.clientes.imprimirClientes();
		input.next();
		return opcoes(menu());
	}
}

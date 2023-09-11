package com.serratec.menu;

import java.util.Scanner;

import com.serratec.ListaClasse.ListaCliente;
import com.serratec.ListaClasse.ListaEmpresa;
import com.serratec.classes.Cliente;
import com.serratec.classes.Empresa;
import com.serratec.conexao.Connect;
import com.serratec.constantes.Util;
import com.serratec.dml.EmpresaDML;

public class MenuEmpresa {

	public static int menu() {

		Util.escrever(Util.LINHAD);
		Util.escrever("Menu Empresa");
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
		case 1: cadastrar("Cadastro de empresa: "); break;
		case 2: alterar("Alteração de empresa - insira o CNPJ da empresa a ser alterada: "); break;
		case 3: excluir("Exclusão de empresa - insira o CNPJ da empresa a ser excluida: "); break;
		case 4: listar(); break;
		case 5:
			int opcaoMenuPrincipal = MenuPrincipal.menuPrincipal();
			return MenuPrincipal.opcoes(opcaoMenuPrincipal);
		case 6:
			Util.escrever("Sistema Finalizado!");
			break;
		default:
			Util.escrever("Opcao invalida");
		}
		return opcao;
	}

	public static int cadastrar(String msg) {
		System.out.println(msg);
		EmpresaDML.gravarEmpresa(Connect.getCon(), Connect.dadosCon.getSchema(), Empresa.cadastrarEmpresa());
		return opcoes(menu());
	}

	public static int alterar(String msg) {
		System.out.println(msg);
		
	
		
		Empresa e = ListaEmpresa.localizarEmpresa();
		if (!(e == null)) {
		Empresa.alterarEmpresa(e);
		EmpresaDML.alterarEmpresa(Connect.getCon(), Connect.dadosCon.getSchema(), e);
			
		}else {System.out.println("Empresa não encontrada, retornando ao menu."); }
		
		return opcoes(menu());
	}

	public static int excluir(String msg) {
		System.out.println(msg);
		if(	ListaEmpresa.excluirEmpresa(ListaEmpresa.localizarEmpresa())){
			
			System.out.println("Empresa excluída com sucesso!");
		}else{ System.out.println("Empresa não excluída!");};
		return opcoes(menu());
		
	}

	public static int listar() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Connect.empresas.imprimirEmpresas();
		input.next();
		return opcoes(menu());
	}
}

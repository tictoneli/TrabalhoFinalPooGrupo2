package com.serratec.menu;

import com.serratec.classes.Empresa;
import com.serratec.conexao.*;
import com.serratec.constantes.Util;
import com.serratec.dml.EmpresaDML;

public class MenuPedido {
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
		}
		return opcao;
	}

	public static int cadastrar() {
		EmpresaDML.gravarEmpresa(Connect.getCon(), Connect.dadosCon.getSchema(), Empresa.cadastrarEmpresa());
		return menu();
	}

	public static int alterar() {
		return menu();
	}

	public static int excluir() {
		return menu();
	}

	public static int listar() {
		return menu();
	}
}
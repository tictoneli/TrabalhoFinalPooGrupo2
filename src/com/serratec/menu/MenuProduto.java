package com.serratec.menu;

import com.serratec.ListaClasse.ListaProduto;
import com.serratec.classes.Produto;
import com.serratec.conexao.Connect;
import com.serratec.conexao.DadosConexao;
import com.serratec.constantes.Util;
import com.serratec.dml.ProdutoDML;

public class MenuProduto {

	public static int menu() {

		Util.escrever(Util.LINHAD);
		Util.escrever("Menu Produto");
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
		Produto produto = Produto.cadastrarProduto();
		ProdutoDML.gravarProduto(Connect.getCon(), Connect.dadosCon.getSchema(), produto);
		return menu();
	}

	public static int alterar() {
	    int idProduto = Util.validarInteiro("Informe o ID do produto que deseja alterar:");
	    Produto produtoExistente = encontrarProdutoPorId(idProduto);

	    if (produtoExistente != null) {
	        Produto.alterarProduto(produtoExistente);
	        ProdutoDML.alterarProduto(Connect.getCon(), Connect.dadosCon.getSchema(), produtoExistente);
	    } else {
	        System.out.println("Produto não encontrado com o ID especificado.");
	    }

	    return menu();
	}

	private static Produto encontrarProdutoPorId(int idProduto) {
	    ListaProduto listaProduto = new ListaProduto(Connect.getCon(), Connect.dadosCon.getSchema());
	    for (Produto produto : listaProduto.produtos) {
	        if (produto.getIdProduto() == idProduto) {
	            return produto;
	        }
	    }
	    return null;
	}


	public static int excluir() {
		return menu();
	}

	public static int listar() {
		return menu();
	}
}

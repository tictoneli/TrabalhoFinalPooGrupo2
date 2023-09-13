package com.serratec.menu;

import java.util.Scanner;

import com.serratec.ListaClasse.ListaCliente;
import com.serratec.ListaClasse.ListaProduto;
import com.serratec.classes.Cliente;
import com.serratec.classes.Produto;
import com.serratec.conexao.Connect;
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
		case 1: cadastrar(); break;
		case 2: alterar("Alteração do produto! Insira o código do produto a ser alterado."); break;
		case 3: excluir("Exclusão do produto! Insira o código do produto a ser excluído."); break;
		case 4: listar(); break;
		case 5:
			int opcaoMenuPrincipal = MenuPrincipal.menuPrincipal();
			return MenuPrincipal.opcoes(opcaoMenuPrincipal);
		case 6:
			Util.escrever("Sistema Finalizado!");
			break;
		default:
			Util.escrever("Opcao inválida");
			Util.aperteEnter();
			return opcoes(menu());
		}
		return opcao;
	}

	public static void cadastrar() {
		
// método cadastrarproduto retorna um novo produto, método cadastrar guarda esse novo produto
		
		Produto produto = Produto.cadastrarProduto();

//método gravarproduto chama o produtoDAO, que escreve esse novo produto em um query pro banco de dados
		
		ProdutoDML.gravarProduto(Connect.getCon(), Connect.dadosCon.getSchema(), produto);

//aqui adicionamos o novo produto ao array do java, para excluir, imprimir, alterar, entre outras coisas
//puxamos os array criados na classe connect, pois é onde criamos esses arrays ao abrir o nosso aplicativo
		
		Connect.produtos.adicionarProdutoLista(produto);
		
//depois de criar o produto, incluir no banco de dados e adicionar no array, voltamos ao menu
		
		opcoes(menu());
	}

	public static void alterar(String msg) {
		
		System.out.println(msg);
		
//primeiro localizamos o produto no array do java, o mesmo criado no connect, quando abrimos o aplicativo
		
		Produto p = ListaProduto.localizarProduto();
	
//só alterar caso o produto localizado não seja nulo, via if else
//ao alterar um produto existente, não precisamos atualizar a lista java, mas precisamos atualizar o banco de dados com o produtoDML
		
		if (!(p == null)) {
			Produto.alterarProduto(p);
			ProdutoDML.alterarProduto(Connect.getCon(), Connect.dadosCon.getSchema(), p);
		}
		else System.out.println("Produto não encontrado!");Util.aperteEnter();
		opcoes(menu());
	}
	
	public static void excluir(String msg) {
		
//mesmo procedimento do alterar, com a diferença que o método excluirproduto (que recebe o produto localizado como parâmetro)
//retorna um valor boolean que pode ser usado como condição do if else
		
		System.out.println(msg);
		if	(Connect.produtos.excluirProduto(ListaProduto.localizarProduto())) {
			System.out.println("Produto excluído com sucesso!");
			Util.aperteEnter();
		}else {System.out.println("Produto não encontrado, retornando ao menu.");Util.aperteEnter(); }
		
		
		opcoes(menu());
	}

	public static int listar() {
		
// a listagem de produtos exibe todos os produtos que estão
//atualmente salvos no array criado no início do aplicativo
		
		ListaProduto.imprimirProdutos();
		
		Util.aperteEnter();
		return opcoes(menu());
	}
}

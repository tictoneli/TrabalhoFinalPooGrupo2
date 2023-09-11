package com.serratec.classes;

import java.util.ArrayList;
import java.util.Scanner;

import com.serratec.constantes.Util;

public class ProdutoPedido {

	private class Itens extends Produto {

		private int quantidade;

		@SuppressWarnings("unused")
		public int getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
	}

	private ArrayList<Itens> produtos = new ArrayList<>();

	public ArrayList<Itens> getProdutos() {
		return produtos;
	}

	public void AdicionarProdutos() {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean continua = true;
		String resposta;

		do {
			Itens itens = new Itens();
			Util.escrever("Informe o cÃ³digo do produto:");
			int codigo = in.nextInt();
			in.nextLine();

			Util.escrever("Informe a Quantidade:");
			int quantidade = in.nextInt();
			in.nextLine();
			itens.setCdProduto(codigo);
			itens.setQuantidade(quantidade);

			produtos.add(itens);

			Util.escrever("Digite 'ENTER' para adicionar mais produtos, digite 's' para sair");
			resposta = in.nextLine();
			continua = !resposta.equals("s");
			
		} while (continua);
	}

}
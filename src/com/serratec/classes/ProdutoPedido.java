package com.serratec.classes;

import java.util.ArrayList;
import java.util.Scanner;

import com.serratec.ListaClasse.ListaProduto;
import com.serratec.constantes.Util;

public class ProdutoPedido {
	
	private long idProdPedido;
	private ArrayList<Itens> produtosped = new ArrayList<>();

	public long getIdProdPedido() {
		return idProdPedido;
	}

	public void setIdProdPedido(long idProdPedido) {
		this.idProdPedido = idProdPedido;
	}

	public ArrayList<Itens> getProdutosped() {
		return produtosped;
	}

	public void setProdutosped(ArrayList<Itens> produtosped) {
		this.produtosped = produtosped;
	}

	private class Itens extends Produto {

		private int quantidade;

		public int getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
	}

	public void AdicionarProdutos() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		boolean continua = true;
		String resposta;

		do {
			Itens itens = new Itens();

			Util.escrever("Informe o código do produto:");

			Produto produto = ListaProduto.localizarProduto();

			if (produto != null) {
				Util.escrever("Informe a quantidade:");
				int quantidade = in.nextInt();
				in.nextLine();

				itens.setIdProduto(produto.getIdProduto());
				
				itens.setQuantidade(quantidade);

				produtosped.add(itens);

				Util.escrever("Produto adicionado com sucesso!");
			} else {
				Util.escrever("Produto não encontrado. Verifique o código informado.");

			}

			Util.escrever("Digite 'ENTER' para adicionar mais produtos, digite 's' para sair");
			resposta = in.nextLine();
			continua = !resposta.equals("s");

		} while (continua);
	}

	
	@Override
	public String toString() {
		return "ProdutoPedido [idProdPedido=" + idProdPedido + ", produtosped=" + produtosped + "]";
	}

	
}

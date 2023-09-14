package com.serratec.classes;

import java.util.ArrayList;
import java.util.Scanner;

import com.serratec.ListaClasse.ListaProduto;
import com.serratec.constantes.Util;

public class Prod_Pedido {

	private long idProdPedido;
	private static ArrayList<Itens> produtosped = new ArrayList<>();

	public static ArrayList<Itens> getProdutosped() {
		return produtosped;
	}

	public long getIdProdPedido() {
		return idProdPedido;
	}

	public void setIdProdPedido(long idProdPedido) {
		this.idProdPedido = idProdPedido;
	}

	public class Itens extends Produto {
		private int quantidade;

		public Itens(long idProduto, long cdProduto, String nome, double valorVenda, int quantidade) {
			super(idProduto, cdProduto, nome, valorVenda);
			this.quantidade = quantidade;
		}

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
			Util.escrever("Informe o código do produto:");

			Produto produto = ListaProduto.localizarProduto();

			if (produto != null) {
				Util.escrever("Informe a quantidade:");
				int quantidade = in.nextInt();
				in.nextLine();

				Itens item = new Itens(produto.getIdProduto(), produto.getCdProduto(), produto.getNome(), produto.getValorVenda(), quantidade);
				produtosped.add(item);

				Util.escrever("Produto adicionado com sucesso!");
			} else {
				Util.escrever("Produto não encontrado. Verifique o código informado.");
			}

			Util.escrever("Digite 'ENTER' para adicionar mais produtos, digite 's' para sair");
			resposta = in.nextLine();
			continua = !resposta.equals("s");

		} while (continua);
	}

	public static Produto localizarIdItem(long codigo) {

		Itens item = null;
		for (int i = 0; i < produtosped.size(); i++) {
			if (produtosped.get(i).getIdProduto() == codigo) {
				item = produtosped.get(i);
				System.out.println(item.getIdProduto());
				return item;
			}
		}
		return null;

	}

	public void imprimirItens() {

		Util.escrever("codigo do produto \tnome \tvalor \tquant. \ttotal");
		for (Itens i : produtosped) {
			Produto produto = i;
			System.out.println(produto.getCdProduto() + "\t" + produto.getNome() + "\t" + produto.getValorVenda() + "\t"
					+ i.getQuantidade() + "\t" + (produto.getValorVenda() * i.getQuantidade()));
		}
	}
}

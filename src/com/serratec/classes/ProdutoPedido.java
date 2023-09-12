package com.serratec.classes;

import java.util.ArrayList;
import java.util.Scanner;
import com.serratec.constantes.Util;
import com.serratec.ListaClasse.*;
public class ProdutoPedido {
	
	
	
	

		private class Itens extends Produto {
	
			private int quantidade;
			@Override
			public String toString() {
				return "Itens [quantidade=" + quantidade + ", iditem=" + iditem + "]";
			}

			private long iditem;
			
			
			public int getQuantidade() {
				return quantidade;
			}
	
			public void setQuantidade(int quantidade) {
				this.quantidade = quantidade;
			}
				
			public long getIditem() {
				return iditem;
			}

			private void setIditem(long iditem) {
				this.iditem = iditem;
			}
		}
	
	private ArrayList<Itens> produtosped = new ArrayList<>();

	@Override
	public String toString() {
		return "ProdutoPedido [produtosped=" + produtosped + "]";
	}

	public ArrayList<Itens> getProdutos() {
		return produtosped;
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

                itens.setIditem(produto.getCdProduto());
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
	
	

}
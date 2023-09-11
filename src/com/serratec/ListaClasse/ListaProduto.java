package com.serratec.ListaClasse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.serratec.classes.Produto;
import com.serratec.conexao.Conexao;
import com.serratec.constantes.Util;
import com.serratec.dao.ProdutoDAO;

public class ListaProduto {

	private Conexao con;
	private String schema;

	static ArrayList<Produto> produtos = new ArrayList<>();

	public ListaProduto(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;

		carregarListaProdutos();
	}

	private Produto dadosProduto(ResultSet tabela) {
		Produto p = new Produto();

		try {
			p.setCdProduto(tabela.getLong("cdproduto"));
			p.setNome(tabela.getString("nome"));
			p.setDescricao(tabela.getString("descricao"));
			p.setValorUnit(tabela.getDouble("valorunit"));
			p.setPorcento(tabela.getDouble("porcento"));
			p.somarValor(tabela.getDouble("valorvenda"));
			p.setIdProduto(tabela.getLong("idproduto"));

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void carregarListaProdutos() {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, schema);

		ResultSet tabela = produtoDAO.carregarProdutos();
		ListaProduto.produtos.clear();

		try {
			tabela.beforeFirst();

			while (tabela.next()) {
				ListaProduto.produtos.add(dadosProduto(tabela));
			}

			tabela.close();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public void adicionarProdutoLista(Produto prod) {
		ListaProduto.produtos.add(prod);
	}

	public Produto localizarProduto(int idProduto) {
		Produto localizado = null;

		for (Produto prod : produtos) {
			if (prod.getIdProduto() == idProduto) {
				localizado = prod;
				break;
			}
		}

		return localizado;
	}

	public void excluirProduto(Produto prodExcluir) {

		Iterator<Produto> pExcluir = produtos.iterator();

		while (pExcluir.hasNext()) {
			Produto prod = pExcluir.next();
			if (prod.getIdProduto() == prodExcluir.getIdProduto()) {
				pExcluir.remove();
			}
		}
	}

	public static void imprimirProdutos() {

		System.out.println(Util.LINHAD);
		Util.escrever("Relatório de Produtos:");
		System.out.println(Util.LINHA);
		Util.escrever("Código\t | Nome\t\t| descrição| vl. Unit.\t\t| Porc. de Lucro\t| vl. Venda");

		for (Produto p : produtos) {
			System.out.println(p.getCdProduto() + "\t\t" + p.getNome() + " " + p.getDescricao() + "\t\t"
					+ p.getValorUnit() + "\t\t" + p.getPorcento() + "\t\t" + p.getValorVenda());
		}
	}
}

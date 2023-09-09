package com.serratec.ListaClasse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.serratec.classes.Produto;
import com.serratec.conexao.Conexao;
import com.serratec.dao.ProdutoDAO;

public class ListaProduto {

	private Conexao con;
	private String schema;

	public ArrayList<Produto> produtos = new ArrayList<>();

	public ListaProduto(Conexao con, String schema) {
		this.con = con;
		this.schema = schema;

		carregarListaProdutos();
	}

	private Produto dadosProduto(ResultSet tabela) {
		Produto p = new Produto();

		try {
			p.setIdProduto(tabela.getLong("idproduto"));
			p.setCdProduto(tabela.getLong("cdproduto"));
			p.setNome(tabela.getString("nome"));
			p.setDescricao(tabela.getString("descricao"));
			p.setValorUnit(tabela.getDouble("valorunit"));
			p.setPorcento(tabela.getDouble("porcento"));
			p.somarValor(tabela.getDouble("valorvenda"));

			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void carregarListaProdutos() {
		ProdutoDAO produtoDAO = new ProdutoDAO(con, schema);

		ResultSet tabela = produtoDAO.carregarProdutos();
		this.produtos.clear();

		try {
			tabela.beforeFirst();

			while (tabela.next()) {
				this.produtos.add(dadosProduto(tabela));
			}

			tabela.close();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}

package com.serratec.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.serratec.classes.Pedido;
import com.serratec.conexao.Conexao;

public class PedidoDAO {
	private Conexao conexao;
	private String schema;

	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pExclusao;
	PreparedStatement pLocalizacao;

	public PedidoDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlExclusao();
	}

	private void prepararSqlExclusao() {
		String sql = "delete from " + this.schema + ".pedido";
		sql += " where idpedido = ?";

		try {
			this.pExclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	private void prepararSqlInclusao() {
		String sql = "insert into " + this.schema + ".pedido";
		sql += " (codigo, datapedido, idcliente, idempresa)";
		sql += " values ";
		sql += " (?, ?, ?, ?)";

		try {
			this.pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	private void prepararSqlAlteracao() {
		String sql = "update " + this.schema + ".pedido";
		sql += " set codigo = ?,";
		sql += " datapedido = ?,";
		sql += " idcliente = ?,";
		sql += " idempresa = ?,";
		sql += " where idpedido = ?";

		try {
			this.pAlteracao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public int alterarPedido(Pedido pedido) {
		try {
			pAlteracao.setLong(1, pedido.getCdPedido());
			pAlteracao.setDate(2, Date.valueOf(pedido.getDtPedido()));
			pAlteracao.setLong(3, pedido.getCliente().getIdCliente());
			pAlteracao.setLong(4, pedido.getEmpresa().getIdEmpresa());
			pAlteracao.setLong(6, pedido.getIdPedido());

			return pAlteracao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido nao alterado.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	public int incluirPedido(Pedido pedido) {
		try {

			pInclusao.setLong(1, pedido.getCdPedido());
			pInclusao.setDate(2, Date.valueOf(pedido.getDtPedido()));
			pInclusao.setLong(3, pedido.getCliente().getIdCliente());
			pInclusao.setLong(4, pedido.getEmpresa().getIdEmpresa());

			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido nao incluida.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public int localizarPedido(Pedido pedido) {
		try {
			pLocalizacao.setLong(1, pedido.getIdPedido());

			return pLocalizacao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido nao localizado.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	public int excluirPedido(Pedido pedido) {
		try {
			pExclusao.setLong(1, pedido.getIdPedido());

			return pExclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido nao excluido.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	public ResultSet carregarPedido() {
		ResultSet tabela;
		String sql = "select * from " + this.schema + ".pedido order by idpedido";

		tabela = conexao.query(sql);

		return tabela;
	}
}

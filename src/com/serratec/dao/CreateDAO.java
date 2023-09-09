package com.serratec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.serratec.conexao.Conexao;
import com.serratec.conexao.DadosConexao;

public class CreateDAO {
	private static Conexao conexao;

	public static boolean createBD(String bd, String schema, DadosConexao dadosCon) {
		boolean bdCriado = false;
		conexao = conectar("postgres", dadosCon);

		if (criarDatabase(conexao, bd)) {
			desconectar(conexao);

			conexao = conectar(bd, dadosCon);

			if (criarSchema(conexao, schema)) {
				criarEntidadeCliente(conexao, schema);
				criarEntidadeEmpresa(conexao, schema);
				criarEntidadeProduto(conexao, schema);
				criarEntidadePedido(conexao, schema);

				bdCriado = true;
			}
		}
		desconectar(conexao);

		return bdCriado;
	}

	private static Conexao conectar(String bd, DadosConexao dadosCon) {
		dadosCon.setBanco(bd);
		Conexao conexao = new Conexao(dadosCon);
		conexao.conect();
		return conexao;
	}

	private static void desconectar(Conexao con) {
		con.disconect();
	}

	private static boolean criarDatabase(Conexao con, String bd) {
		boolean bdExiste;
		int tentativas = 1;
		String sql;

		class Database {
			public static ResultSet Exists(Conexao con, String bd) {
				ResultSet entidade;
				String sql = "select datname from pg_database where datname = '" + bd + "'";
				entidade = con.query(sql);
				return entidade;
			}
		}

		do {
			try {
				bdExiste = Database.Exists(con, bd).next();

				if (!bdExiste) {
					sql = "create database " + bd;
					con.query(sql);
					tentativas++;
				}
			} catch (Exception e) {
				System.err.printf("Nao foi possivel criar o database %s: %s", bd, e);
				e.printStackTrace();
				return false;
			}
		} while (!bdExiste && (tentativas <= 3));

		return bdExiste;
	}

	private static boolean criarSchema(Conexao con, String schema) {
		boolean schemaExiste;
		int tentativas = 1;
		String sql;

		class Schema {
			public static ResultSet Exists(Conexao con, String schema) {
				ResultSet entidade;
				String sql = "select * from pg_namespace where nspname = '" + schema + "'";
				entidade = con.query(sql);
				return entidade;
			}
		}

		do {
			try {
				schemaExiste = Schema.Exists(con, schema).next();

				if (!schemaExiste) {
					sql = "create schema " + schema;
					con.query(sql);
					tentativas++;
				}
			} catch (Exception e) {
				System.err.printf("Nao foi possivel criar o schema %s: %s", schema, e);
				e.printStackTrace();
				return false;
			}
		} while (!schemaExiste && (tentativas <= 3));

		return schemaExiste;
	}

	private static void criarTabela(Conexao con, String entidade, String schema) {
		String sql = "create table " + schema + "." + entidade + " ()";
		con.query(sql);
	}

	private static void criarCampo(Conexao con, String schema, String entidade, String atributo, String tipoAtributo,
			boolean primario, boolean estrangeiro, String entidadeEstrangeira, String atributoEstrangeiro) {

		if (!atributoExists(con, schema, entidade, atributo)) {
			String sql = "alter table " + schema + "." + entidade + " add column " + atributo + " " + tipoAtributo
					+ " ";

			if (primario) {
				sql += "primary key ";
			}

			if (estrangeiro) {
				sql += "references " + schema + "." + entidadeEstrangeira + "(" + atributoEstrangeiro + ")";
			}

			con.query(sql);
		}
	}

	// Criação das Entidades do Trabalho

	private static void criarEntidadeCliente(Conexao con, String schema) {
		String entidade = "cliente";

		if (!entidadeExists(con, schema, entidade))
			criarTabela(con, entidade, schema);

		if (entidadeExists(con, schema, entidade)) {
			criarCampo(con, schema, entidade, "idcliente", "serial", true, false, null, null);
			criarCampo(con, schema, entidade, "nome", "varchar(100)", false, false, null, null);
			criarCampo(con, schema, entidade, "cpf", "varchar(11)", false, false, null, null);
			criarCampo(con, schema, entidade, "telefone", "varchar(15)", false, false, null, null);
			criarCampo(con, schema, entidade, "email", "varchar(100)", false, false, null, null);
			criarCampo(con, schema, entidade, "endereco", "varchar(150)", false, false, null, null);
			criarCampo(con, schema, entidade, "dtnasc", "varchar(100)", false, false, null, null);
		}
	}

	private static void criarEntidadeEmpresa(Conexao con, String schema) {
		String entidade = "empresa";

		if (!entidadeExists(con, schema, entidade))
			criarTabela(con, entidade, schema);

		if (entidadeExists(con, schema, entidade)) {
			criarCampo(con, schema, entidade, "idempresa", "serial", true, false, null, null);
			criarCampo(con, schema, entidade, "nome", "varchar(100)", false, false, null, null);
			criarCampo(con, schema, entidade, "cnpj", "varchar(14)", false, false, null, null);
			criarCampo(con, schema, entidade, "telefone", "varchar(15)", false, false, null, null);
			criarCampo(con, schema, entidade, "email", "varchar(100)", false, false, null, null);
			criarCampo(con, schema, entidade, "endereco", "varchar(150)", false, false, null, null);
		}
	}

	private static void criarEntidadeProduto(Conexao con, String schema) {
		String entidade = "produto";

		if (!entidadeExists(con, schema, entidade))
			criarTabela(con, entidade, schema);

		if (entidadeExists(con, schema, entidade)) {
			criarCampo(con, schema, entidade, "idproduto", "serial", true, false, null, null);
			criarCampo(con, schema, entidade, "nome", "varchar(100)", false, false, null, null);
			criarCampo(con, schema, entidade, "cdproduto", "bigint", false, false, null, null);
			criarCampo(con, schema, entidade, "descricao", "varchar(150)", false, false, null, null);
			criarCampo(con, schema, entidade, "valorunit", "double precision", false, false, null, null);
			criarCampo(con, schema, entidade, "porcento", "double precision", false, false, null, null);
			criarCampo(con, schema, entidade, "valorvenda", "double precision", false, false, null, null);
		}
	}

	private static void criarEntidadePedido(Conexao con, String schema) {
		String entidade = "pedido";

		if (!entidadeExists(con, schema, entidade))
			criarTabela(con, entidade, schema);

		if (entidadeExists(con, schema, entidade)) {
			criarCampo(con, schema, entidade, "idpedido", "serial", true, false, null, null);
			criarCampo(con, schema, entidade, "codigo", "varchar(10)", false, false, null, null);
			criarCampo(con, schema, entidade, "datapedido", "date", false, false, null, null);
			criarCampo(con, schema, entidade, "idcliente", "integer", false, true, "cliente", "idcliente");
			criarCampo(con, schema, entidade, "idempresa", "integer", false, true, "empresa", "idempresa");
			criarCampo(con, schema, entidade, "idproduto", "integer", false, true, "produto", "idproduto");
			criarCampo(con, schema, entidade, "qtprodutos", "integer", false, false, null, null);
		}	// melhor uma entidade parceiros para puxar ids cliente e empresa? else tabela pedidos terá muitos nulls no sql
	}

	// Verificando se já existe no BD

	public static boolean databaseExists(Conexao con, String bd) {
		ResultSet entidade;
		boolean dbExists = false;

		String sql = "select datname from pg_database where datname = '" + bd + "'";
		entidade = con.query(sql);

		try {
			dbExists = entidade.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dbExists;
	}

	public static boolean entidadeExists(Conexao con, String schema, String entidade) {
		boolean entidadeExist = false;
		String sql = "SELECT n.nspname AS schemaname, c.relname AS tablename " + "FROM pg_class c "
				+ "LEFT JOIN pg_namespace n ON n.oid = c.relnamespace "
				+ "LEFT JOIN pg_tablespace t ON t.oid = c.reltablespace " + "WHERE c.relkind = 'r' "
				+ "AND n.nspname = '" + schema + "' " + "AND c.relname = '" + entidade + "'";

		ResultSet tabela = con.query(sql);

		try {
			entidadeExist = (tabela.next() ? true : false);

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return entidadeExist;
	}

	public static boolean atributoExists(Conexao con, String schema, String entidade, String atributo) {

		boolean atributoExist = false;

		String sql = "select table_schema, table_name, column_name from information_schema.columns "
				+ "where table_schema = '" + schema + "' " + "and table_name = '" + entidade + "' "
				+ "and column_name = '" + atributo + "'";

		ResultSet result = con.query(sql);

		try {
			atributoExist = (result.next() ? true : false);

		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}

		return atributoExist;
	}

}

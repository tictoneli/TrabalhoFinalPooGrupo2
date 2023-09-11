package com.serratec.classes;

import java.time.LocalDate;
import java.util.Scanner;

import com.serratec.ListaClasse.ListaCliente;
import com.serratec.constantes.Util;

public class Pedido {

	private long idPedido;
	private long cdPedido;
	private LocalDate dtPedido;
	private Cliente cliente;
	private ProdutoPedido produtos;
	private Empresa empresa;

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getCdPedido() {
		return cdPedido;
	}

	public void setCdPedido(long cdPedido) {
		this.cdPedido = cdPedido;
	}

	public LocalDate getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(LocalDate dtPedido) {
		this.dtPedido = dtPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ProdutoPedido getProdutos() {
		return produtos;
	}

	public void setProdutos(ProdutoPedido produtos) {
		this.produtos = produtos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public static Pedido cadastrarPedido() {
		Pedido p = new Pedido();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.println(Util.LINHA);
		Util.escrever("Cadastro de novo pedido: ");
		System.out.println(Util.LINHA);

		Util.br();

		Util.escrever("Informe o número do pedido:");
		int i = in.nextInt();
		in.nextLine();
		p.setCdPedido(i);

		Util.escrever("Informe o cpf do Cliente:");
		String cpf = in.nextLine();
		Cliente cliente = ListaCliente.localizarCliente();

		//cliente.setCpf_cnpj(cpf);

		p.setCliente(cliente);

		Util.escrever("Informe o nome da empresa:");
		String nome = in.nextLine();
		Empresa empresa = new Empresa();
		empresa.setNome(nome);
		p.setEmpresa(empresa);

		p.produtos.AdicionarProdutos();

		p.dtPedido = LocalDate.now();
		
		return p;
	}
}
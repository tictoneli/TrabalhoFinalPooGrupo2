package com.serratec.classes;

import java.time.LocalDate;
import java.util.Scanner;

import com.serratec.ListaClasse.ListaCliente;
import com.serratec.ListaClasse.ListaEmpresa;
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

		Util.escrever("Informe o código do pedido:");
		int i = in.nextInt();
		in.nextLine();
		p.setCdPedido(i);

		Util.escrever("Informe o cpf do Cliente:");
		Cliente cliente = ListaCliente.localizarCliente();
			if(!(cliente == null)) {
				p.setCliente(cliente);
				}else { System.err.println("Cliente não encontrado! ");
					return null;
				}
			
		

		Util.escrever("Informe o CNPJ da empresa responsável: ");
		Empresa empresa = ListaEmpresa.localizarEmpresa();
		if(!(empresa == null)) {
			p.setEmpresa(empresa);
			}else { System.err.println("Empresa não encontrada! ");
				return null;
			}
		
		
		ProdutoPedido pd = new ProdutoPedido();
		
		pd.AdicionarProdutos();
		p.setProdutos(pd);
//		System.out.println(p.getProdutos().getProdutos().toString());
		
		/*
		p.produtos.AdicionarProdutos();
		if(!(p.getProdutos().getProdutos() == null || p.getProdutos().getProdutos().isEmpty())) {
			
			}else {System.out.println("Carrinho vazio, por favor insira mais de um produto para criar o pedido!");
			return null;
			}
		*/
		
		
		p.dtPedido = LocalDate.now();
		
		return p;
	}
}
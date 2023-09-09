package com.serratec.classes;

import java.util.List;

public class Pedido {
	
	private long idPedido;
	private long cdPeido;
	private Cliente cliente;
	private List<Produto> produtos;
	private Empresa empresa;
	
	public Pedido(long idPedido, long cdPeido,
		Cliente cliente, List<Produto> produtos, Empresa empresa) {
		this.idPedido = idPedido;
		this.cdPeido = cdPeido;
		this.cliente = cliente;
		this.produtos = produtos;
		this.empresa = empresa;
	}

	
	/////////////////////////////////////////////////////////////////
	
public long getIdPedido() {
		return idPedido;
	}

	
protected void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	
public long getCdPeido() {
		return cdPeido;
	}

	
protected void setCdPeido(long cdPeido) {
		this.cdPeido = cdPeido;
	}

	
public Cliente getCliente() {
		return cliente;
	}

	
protected void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
public List<Produto> getProdutos() {
		return produtos;
	}

	
protected void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
public Empresa getEmpresa() {
		return empresa;
	}

	
protected void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
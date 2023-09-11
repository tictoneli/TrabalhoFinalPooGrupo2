package com.serratec.classes;

import java.util.List;

public class Pedido {
	
	private long idPedido;
	private long cdPedido;
	private int qtProduto;
	private Cliente cliente;
	private List<Produto> produtos;
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
	public int getQtProduto() {
		return qtProduto;
	}
	public void setQtProduto(int qtProduto) {
		this.qtProduto = qtProduto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
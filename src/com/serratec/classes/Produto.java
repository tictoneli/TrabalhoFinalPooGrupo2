package com.serratec.classes;

import java.util.Scanner;

import com.serratec.calculos.Calculos;
import com.serratec.constantes.Util;

public class Produto implements Calculos {

	private long idProduto;
	private long cdProduto;
	private String nome;
	private String descricao;
	private Double valorUnit;
	private Double valorVenda;
	private Double porcento;

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public long getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(long cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public Double getPorcento() {
		return porcento;
	}

	public void setPorcento(Double porcento) {
		this.porcento = porcento;
	}

	@Override
	public void somarValor(Double valorVenda) {
		this.valorVenda = valorUnit + (valorUnit * (porcento / 100));

	}

	public static Produto cadastrarProduto() {

		Produto p = new Produto();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.println(Util.LINHA);
		Util.escrever("Cadastro de produto: ");
		System.out.println(Util.LINHA);

		Util.br();

		Util.escrever("Informe o nome:");
		String s = in.nextLine();
		p.setNome(s);

		Util.escrever("Informe o código:");
		Integer i = in.nextInt();
		in.nextLine();
		p.setCdProduto(i);

		Util.escrever("Informe a descrição: ");
		s = in.nextLine();
		p.setDescricao(s);

		Util.escrever("Informe o valor unitário:");
		Double d = in.nextDouble();
		in.nextLine();
		p.setValorUnit(d);

		Util.escrever("Informe a porcentagem de lucro:");
		d = in.nextDouble();
		in.nextLine();
		p.setPorcento(d);

		p.somarValor(d);

		return p;
	}

	public static Produto alterarProduto(Produto produtoExistente) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		Double valor = 0.0;

		System.out.println(Util.LINHA);
		Util.escrever("Alteração de produto: ");
		System.out.println(Util.LINHA);

		Util.br();

		Util.escrever("Alterar o nome:");
		String nome = in.nextLine();
		produtoExistente.setNome(nome);

		Util.escrever("Alterar o código:");
		long codigo = Util.validarInteiro("Código: ");
		produtoExistente.setCdProduto(codigo);

		Util.escrever("Alterar a descrição:");
		String descricao = in.nextLine();
		produtoExistente.setDescricao(descricao);

		Util.escrever("Alterar o valor unitário:");
		Double valorUnitario = Util.validarDouble("Valor Unitário: ");
		produtoExistente.setValorUnit(valorUnitario);

		Util.escrever("Alterar a porcentagem de lucro:");
		Double porcentagem = Util.validarDouble("Porcentagem: ");
		produtoExistente.setPorcento(porcentagem);

		produtoExistente.somarValor(valor);

		return produtoExistente;
	}

}
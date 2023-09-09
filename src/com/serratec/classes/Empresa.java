package com.serratec.classes;

import java.util.Scanner;

import com.serratec.constantes.Util;

public class Empresa extends Parceiro {

	private int idEmpresa;

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@SuppressWarnings("resource")
	public static Empresa cadastrarEmpresa() {

		Empresa e = new Empresa();

		Scanner in = new Scanner(System.in);

		System.out.println(Util.LINHA);
		System.out.println("Cadastro de empresa: ");
		System.out.println(Util.LINHA);

		Util.br();

		System.out.println("Informe o nome da empresa:");
		String s = in.nextLine();
		e.setNome(s);

		System.out.println("Informe o CNPJ da empresa:");
		s = in.nextLine();
		e.setCpf_cnpj(s);

		System.out.println("Informe o endereço da empresa: ");
		s = in.nextLine();
		e.setEndereco(s);

		System.out.println("Informe o telefone da empresa:");
		s = in.nextLine();
		e.setTelefone(s);

		System.out.println("Informe o email da empresa:");
		s = in.nextLine();
		e.setEmail(s);

		return e;
	}
}

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

	public static Empresa alterarEmpresa(Empresa e) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);


		System.out.println(Util.LINHA);
		Util.escrever("Alteração de empresa: ");
		System.out.println(Util.LINHA);

		Util.br();

		Util.escrever("Alterar o nome ou pressione ENTER para manter original: ");
		String nome = in.nextLine();
			if (nome != null && !nome.trim().isEmpty()) {
				e.setNome(nome);
			}

		Util.escrever("Alterar o CPF ou pressione ENTER para manter original::");
		String cnpj = in.nextLine();
			if (cnpj != null && !cnpj.trim().isEmpty()) {
				e.setCpf_cnpj(cnpj);
			}

		Util.escrever("Alterar o telefone ou pressione ENTER para manter original::");
		String tel = in.nextLine();
			if (tel != null && !tel.trim().isEmpty()) {
				e.setTelefone(tel);
			}

		Util.escrever("Alterar o email ou pressione ENTER para manter original::");
		String email = in.nextLine();
			if (email != null && !email.trim().isEmpty()) {
				e.setEmail(email);
			}
		
		Util.escrever("Alterar o endereço ou pressione ENTER para manter original::");
		String end = in.nextLine();
			if (end != null && !end.trim().isEmpty()) {
				e.setEndereco(end);
			}
		
		return e;
	}
}

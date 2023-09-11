
package com.serratec.classes;

import java.util.Scanner;

import javax.swing.text.DateFormatter;

import com.serratec.constantes.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class Cliente extends Parceiro {

	private int idCliente;
	private LocalDate dtnasc;
	
	public LocalDate getDtnasc() {
		return dtnasc;
	}

	public void setDtnasc(LocalDate dtnasc) {
		this.dtnasc = dtnasc;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@SuppressWarnings("resource")
	public static Cliente cadastrarCliente() {
		
		Cliente c = new Cliente();
		Scanner in = new Scanner(System.in);
		
		System.out.println(Util.LINHA);
		System.out.println("Cadastro de cliente: ");
		System.out.println(Util.LINHA);
		
		Util.br();
		
		System.out.println("Informe o nome do cliente:");
		String s = in.nextLine();
		c.setNome(s);
	
		System.out.println("Informe o CPF do cliente:");
		s = in.nextLine();
		c.setCpf_cnpj(s);
		
		System.out.println("Informe o endereco do cliente: ");
		s = in.nextLine();
		c.setEndereco(s);
		
		System.out.println("Informe o telefone do cliente:");
		s = in.nextLine();
		c.setTelefone(s);
		
		System.out.println("Informe o email do cliente:");
		s = in.nextLine();
		c.setEmail(s);
		
		c.setDtnasc((Util.validarData("Informe a data de nascimento do cliente : ")));
		
		return c;
	}
	
	public static Cliente alterarCliente(Cliente c) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);


		System.out.println(Util.LINHA);
		Util.escrever("Alteração de cliente: ");
		System.out.println(Util.LINHA);

		Util.br();

		Util.escrever("Alterar o nome ou pressione ENTER para manter original: ");
		String nome = in.nextLine();
			if (nome != null && !nome.trim().isEmpty()) {
				c.setNome(nome);
			}

		Util.escrever("Alterar o CPF ou pressione ENTER para manter original: ");
		String cpf = in.nextLine();
			if (cpf != null && !cpf.trim().isEmpty()) {
				c.setCpf_cnpj(cpf);
			}

		Util.escrever("Alterar o telefone ou pressione ENTER para manter original: ");
		String tel = in.nextLine();
			if (tel != null && !tel.trim().isEmpty()) {
				c.setTelefone(tel);
			}

		Util.escrever("Alterar o email ou pressione ENTER para manter original: ");
		String email = in.nextLine();
			if (email != null && !email.trim().isEmpty()) {
				c.setEmail(email);
			}
		
		Util.escrever("Alterar o endereço ou pressione ENTER para manter original: ");
		String end = in.nextLine();
			if (end != null && !end.trim().isEmpty()) {
				c.setEndereco(end);
			}
			LocalDate conf;
			conf = Util.validarData("Alterar a data de nascimento ou pressione ENTER para manter original: ");
		if(!(conf == null)) {
			 c.setDtnasc(conf);
		}
		
		return c;
	}
}
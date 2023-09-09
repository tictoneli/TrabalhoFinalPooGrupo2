
package com.serratec.classes;

import java.util.Scanner;
import com.serratec.constantes.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente extends Parceiro {


	private int idCliente;

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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Informe a data de nascimento (dd/mm/aaaa)");
		try {
			Date ld = sdf.parse(in.nextLine());
			c.setDtnasc(sdf.format(ld));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}
	
}

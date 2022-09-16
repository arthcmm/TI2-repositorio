package com.postgres;

import java.util.List;

import com.postgres.DAO;
import com.postgres.funcionarioDAO;
import com.postgres.funcionario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		funcionarioDAO funcionarioDAO = new funcionarioDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		funcionario funcionario = new funcionario(11, "pablo", "pablo",'M');
		if(funcionarioDAO.insert(funcionario) == true) {
			System.out.println("Inserção com sucesso -> " + funcionario.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + funcionario.getnome() + "): " + funcionarioDAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<funcionario> funcionarios = funcionarioDAO.getSexoMasculino();
		for (funcionario u: funcionarios) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar senha (código (" + funcionario.getiden() + ") === ");
		funcionario.setSenha(DAO.toMD5("pablo"));
		funcionarioDAO.update(funcionario);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + funcionario.getnome() + "): " + funcionarioDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + funcionario.getnome() + "): " + funcionarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		funcionarios = funcionarioDAO.getOrderByiden();
		for (funcionario u: funcionarios) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + funcionario.getiden() + ") === ");
		funcionarioDAO.delete(funcionario.getiden());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por nome === ");
		funcionarios = funcionarioDAO.getOrderBynome();
		for (funcionario u: funcionarios) {
			System.out.println(u.toString());
		}
	}
}
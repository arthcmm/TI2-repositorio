package com.postgres;

public class funcionario {

	private int iden;
	private String nome;
	private String senha;
	private char sexo;
	
	public funcionario() {
		this.iden = -1;
		this.nome = "";
		this.senha = "";
		this.sexo = '*';
	}
	
	public funcionario(int iden, String nome, String senha, char sexo) {
		this.iden = iden;
		this.nome = nome;
		this.senha = senha;
		this.sexo = sexo;
	}

	public int getiden() {
		return iden;
	}

	public void setiden(int iden) {
		this.iden = iden;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Funcionario [iden=" + iden + ", nome=" + nome + ", senha=" + senha + ", sexo=" + sexo + "]";
	}

}

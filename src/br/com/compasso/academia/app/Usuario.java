package br.com.compasso.academia.app;

import java.util.Random;

public class Usuario {

	private String nome;
	private String cpf;
	private String turno;
	private String matricula;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String cpf, String turno) {
        this.nome = nome;
        this.cpf = cpf;
        this.turno = turno;
        this.matricula = String.valueOf(new Random().nextInt(899999)+100000);
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
    @Override
    public String toString() {
    	return "Nome: " + this.nome + ", CPF: " + this.cpf + ", Turno: " + this.turno + ", Matricula: " + this.matricula;
    }
	
}

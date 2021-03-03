package br.com.compasso.academia.app;

import java.sql.*;
import java.util.Random;

import br.com.compasso.academia.services.ConectaBanco;

public class Usuario {
	private int id;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}

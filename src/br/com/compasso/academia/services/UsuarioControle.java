package br.com.compasso.academia.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.compasso.academia.app.Menu;
import br.com.compasso.academia.app.Usuario;

public class UsuarioControle {

	
	private static Scanner leitura = new Scanner(System.in);
	
	public static void cadastro() {
		
		System.out.print("Digite o nome do aluno: ");
        String nome = leitura.nextLine();

        System.out.print("\nDigite o CPF:\n");
        String cpf = leitura.nextLine();
        
        System.out.println("Digite o turno escolhido: matutino - vespertino - noturno\n");
        String turno = leitura.nextLine();
        
        Usuario user = new Usuario(nome, cpf, turno);
        cadastrarUsuario(user);

        System.out.println("Aluno(a) " + nome + " foi cadastrado(a) com sucesso e sua matrícula é: " + user.getMatricula());
	}
	
	public static void cadastrarUsuario(Usuario user) {
        ConectaBanco conecta = new ConectaBanco();
        conecta.conectar();
        
        try {
        	PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO usuarios (nome, cpf, turno, matricula) VALUES (?,?,?,?)");
        	pst.setString(1, user.getNome());
            pst.setString(2, user.getCpf());
            pst.setString(3, user.getTurno());
            pst.setString(4, user.getMatricula());
            pst.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!\n");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar usuário.\nErro: " + ex);
        } finally {
            conecta.desconectar();
        }
        Menu.menuInicial();
	}
	
	public static void pegarMatricula() {
		System.out.println("Digite a matricula a ser pesquisada: ");
        String matricula = leitura.nextLine();
        consultarUsuario();
	}
	
	public static void consultarUsuario() {
		Usuario user = new Usuario();
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios WHERE matricula='"+user.getMatricula()+"'");
        
        try {
        	conecta.rs.first();
            System.out.println("Nome: "+conecta.rs.getString("nome")+
                        	   " CPF: "+conecta.rs.getString("cpf")+
                        	   " Turno: "+conecta.rs.getString("turno")+
                        	   " Matrícula: "+conecta.rs.getString("matricula"));
        } catch (SQLException ex) {
            System.out.println("Falha ao pesquisar dados.\nErro: " + ex);
        } finally {
        	conecta.desconectar();
        }
    }
    
    public static void listarUsuarios() {
        ConectaBanco service = new ConectaBanco();
        service.conectar();
        service.executarSQL("SELECT * FROM usuarios");
        
        try {
            service.rs.first();
            do {
                System.out.println("Nome: "+service.rs.getString("nome")+
                        " CPF: "+service.rs.getString("cpf")+
                        " Turno: "+service.rs.getString("turno")+
                        " Matrícula: "+service.rs.getString("matricula"));
            } while(service.rs.next());
        } catch (SQLException ex) {
            System.out.println("Erro ao pesquisar dados :(");
        } finally {
            service.desconectar();
        }
    }
    
    public static void deletarUsuario() {
    	System.out.println("Digite a matricula a ser pesquisada: ");
        String matricula = leitura.nextLine();

        Usuario user = new Usuario();
    	ConectaBanco conecta = new ConectaBanco();
    	conecta.conectar();
        conecta.executarSQL("DELETE FROM usuarios WHERE matricula='"+user.getMatricula()+"''");
        conecta.desconectar();
    }
    
}

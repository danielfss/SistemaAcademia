package br.com.compasso.academia.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.compasso.academia.app.Usuario;

public class UsuarioControle {

	private static Scanner leitura = new Scanner(System.in);

	public static void cadastrarUsuario(Usuario usuario) {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		
		try {
			PreparedStatement pst = conecta.conn
					.prepareStatement("INSERT INTO usuarios (nome, cpf, turno, matricula) VALUES (?,?,?,?)");
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getCpf());
			pst.setString(3, usuario.getTurno());
			pst.setString(4, usuario.getMatricula());
			pst.executeUpdate();
			System.out.println("Usuário(a) " + usuario.getNome() + " foi cadastrado(a) com sucesso.\n"
					+ "O nosso sistema gerou o seu número de matrícula: " + usuario.getMatricula());
			JOptionPane.showMessageDialog(null, "Usuário(a) " + usuario.getNome() + " foi cadastrado(a) com sucesso.\n"
					+ "O nosso sistema gerou o seu número de matrícula: " + usuario.getMatricula());
		} catch (SQLException ex) {
			System.out.println("Erro ao cadastrar usuário.\nErro: " + ex);
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
		} finally {
			conecta.desconectar();
		}
	}

	public static void consultarUsuario(Usuario usuario) {

		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios WHERE matricula='" + usuario.getMatricula() + "'");

		try {
			conecta.rs.first();
			usuario.setNome(conecta.rs.getString("nome"));
			usuario.setCpf(conecta.rs.getString("cpf"));
			usuario.setTurno(conecta.rs.getString("turno"));
			System.out.println("Nome: " + usuario.getNome() + " | CPF: " + usuario.getCpf()
					+ " | Turno: " + usuario.getTurno() + " | Matrícula: " + usuario.getMatricula());
			
		} catch (SQLException ex) {
			System.out.println("Falha ao pesquisar dados.\nErro: " + ex);
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário.");
		} finally {
			conecta.desconectar();
		}
	}

//	public static void listarUsuarios() {
//		ConectaBanco conecta = new ConectaBanco();
//		conecta.conectar();
//		conecta.executarSQL("SELECT * FROM usuarios");
//		List lista = new ArrayList<>();
//		try {
//			conecta.rs.first();
//			do {
//				Usuario usuario = new Usuario();
//				usuario.setNome(conecta.rs.getString("nome"));
//				usuario.setCpf(conecta.rs.getString("cpf"));
//				usuario.setTurno(conecta.rs.getString("turno"));
//				usuario.setMatricula(conecta.rs.getString("matricula"));
////				System.out.println(
////						"Nome: " + conecta.rs.getString("nome") + " | CPF: " + conecta.rs.getString("cpf") + " | Turno: "
////								+ conecta.rs.getString("turno") + " | Matrícula: " + conecta.rs.getString("matricula"));
//				lista.add(usuario);
//			} while (conecta.rs.next());
//		} catch (SQLException ex) {
//			System.out.println("Erro ao pesquisar dados :(");
//		} finally {
//			conecta.desconectar();
//		}
//	}
	
	public static List<Usuario> read() {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		List<Usuario> usuarios = new ArrayList<>();
		try {
			conecta.executarSQL("SELECT * FROM usuarios");
			while (conecta.rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(conecta.rs.getString("nome"));
				usuario.setCpf(conecta.rs.getString("cpf"));
				usuario.setTurno(conecta.rs.getString("turno"));
				usuario.setMatricula(conecta.rs.getString("matricula"));
				usuarios.add(usuario);
				System.out.println("Usuário listado com sucesso!");
			}
			
		} catch (SQLException ex) {
			System.out.println("Erro ao pesquisar dados :(");
		} finally {
			conecta.desconectar();
		}
		return usuarios;
		
	}

	public static void deletarUsuario() {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		try {
			PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM usuarios WHERE matricula=?");
			System.out.println("Digite a matricula a ser deletada: ");
			String matricula = leitura.nextLine();
			pst.setString(1, matricula);
			pst.executeUpdate();
			System.out.println("Este usuário foi deletado com sucesso!\n");
		} catch (SQLException ex) {
			System.out.println("Erro ao deletar dados.\nErro: " + ex);
		} finally {
			conecta.desconectar();
		}
	}

}

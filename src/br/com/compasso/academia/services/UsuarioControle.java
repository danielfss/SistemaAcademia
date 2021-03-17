package br.com.compasso.academia.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.compasso.academia.app.Usuario;

public class UsuarioControle {

	// CADASTRAR UM USUARIO
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

	// CONSULTAR PELA MATRICULA
	public static void consultarUsuario(Usuario usuario) {

		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios WHERE matricula='" + usuario.getMatricula() + "'");

		try {
			conecta.rs.first();
			usuario.setNome(conecta.rs.getString("nome"));
			usuario.setCpf(conecta.rs.getString("cpf"));
			usuario.setTurno(conecta.rs.getString("turno"));
			System.out.println("Nome: " + usuario.getNome() + " | CPF: " + usuario.getCpf() + " | Turno: "
					+ usuario.getTurno() + " | Matrícula: " + usuario.getMatricula());
			JOptionPane.showMessageDialog(null, "Usuário encontrado! Altere as informações necessárias.");
		} catch (SQLException ex) {
			System.out.println("Falha ao pesquisar dados.\nErro: " + ex);
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário.");
		} finally {
			conecta.desconectar();
		}
	}

	// LISTAR TODOS OS USUARIOS NUMA TABELA
	public static List<Usuario> listarUsuarios() {
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
	
	// ALTERAR OS DADOS DE UM USUARIO
	public static void editarUsuario(Usuario usuario){
		
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE usuarios SET nome=?, cpf=?, turno=? WHERE matricula=?");
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getCpf());
            pst.setString(3, usuario.getTurno());
            pst.setString(4, usuario.getMatricula());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Os dados do usuários foram alterados com sucesso!\n" + 
            		"Nome: " + usuario.getNome() + 
            		" | CPF: " + usuario.getCpf() + 
            		" | Turno: " +  usuario.getTurno() + 
            		" | Matrícula: " + usuario.getMatricula());
            System.out.println("Nome: " + usuario.getNome() + " | CPF: " + usuario.getCpf() + " | Turno: "
					+ usuario.getTurno() + " | Matrícula: " + usuario.getMatricula());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do usuário!\n Erro: " + ex);
        } finally {
			conecta.desconectar();
		}
    }

	// DELETAR UM USUARIO
	public static void deletarUsuario(Usuario usuario) {
		
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		
		try {
			PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM usuarios WHERE matricula=?");
			System.out.println("Digite a matricula a ser deletada: " + usuario.getMatricula());
			pst.setString(1, usuario.getMatricula());
			pst.executeUpdate();
			System.out.println("Este usuário foi deletado com sucesso!\n");
			JOptionPane.showMessageDialog(null, "Este usuário foi deletado com sucesso!");
		} catch (SQLException ex) {
			System.out.println("Erro ao deletar dados.\nErro: " + ex);
		} finally {
			conecta.desconectar();
		}
	}
	
	// VERIFICAR SE O CPF JÁ ESTÁ REGISTRADO NO BANCO
	public static boolean verificarCPF(Usuario usuario) {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios WHERE cpf='" + usuario.getCpf() + "'");
		
		try {
			if(conecta.rs.first()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException ex) {
			System.out.println("Erro na consulta do CPF.\nErro: " + ex);
			return false;
		} finally {
			conecta.desconectar();
		}
		
	}

}

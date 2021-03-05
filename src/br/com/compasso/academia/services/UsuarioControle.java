package br.com.compasso.academia.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import br.com.compasso.academia.app.Menu;

public class UsuarioControle {

	private static Scanner leitura = new Scanner(System.in);

	public static void cadastrarUsuario() {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		System.out.print("Digite o nome do aluno: ");
		String nome = leitura.nextLine();

		System.out.print("\nDigite o CPF:\n");
		String cpf = leitura.nextLine();
		
		System.out.println("Digite a letra referente ao turno o turno: "
				+ "\nM - matutino"
				+ "\nV - vespertino"
				+ "\nN - noturno\n");
		String turno = leitura.nextLine();
		switch(turno.toLowerCase()) {
		case "m":
		case "matutino":
			turno = "matutino";
			System.out.println("O turno escolhido foi: " + turno);
			break;
		case "v":
		case "vespertino":
			turno = "vespertino";
			System.out.println("O turno escolhido foi: " + turno);
			break;
		case "N":
		case "noturno":
			turno = "noturno";
			System.out.println("O turno escolhido foi: " + turno);
			break;
		default:
			System.out.println("Opção inválida, escolha um dos turnos disponíveis!");
		}
		
		String matricula = String.valueOf(new Random().nextInt(999999));
		
		try {
			PreparedStatement pst = conecta.conn
					.prepareStatement("INSERT INTO usuarios (nome, cpf, turno, matricula) VALUES (?,?,?,?)");
			pst.setString(1, nome);
			pst.setString(2, cpf);
			pst.setString(3, turno);
			pst.setString(4, matricula);
			pst.executeUpdate();
			System.out.println("Usuário(a) " + nome + " foi cadastrado(a) com sucesso.\n"
					+ "O nosso sistema gerou o seu número de matrícula: " + matricula);
		} catch (SQLException ex) {
			System.out.println("Erro ao cadastrar usuário.\nErro: " + ex);
		} finally {
			conecta.desconectar();
			Menu.menuInicial();
		}
	}

	public static void consultarUsuario() {

		System.out.println("Digite a matricula a ser pesquisada: ");
		String matricula = leitura.nextLine();

		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios WHERE matricula='" + matricula + "'");

		try {
			conecta.rs.first();
			System.out.println("Nome: " + conecta.rs.getString("nome") + " CPF: " + conecta.rs.getString("cpf")
					+ " Turno: " + conecta.rs.getString("turno") + " Matrícula: " + conecta.rs.getString("matricula"));
		} catch (SQLException ex) {
			System.out.println("Falha ao pesquisar dados.\nErro: " + ex);
		} finally {
			conecta.desconectar();
			Menu.menuInicial();
		}
	}

	public static void listarUsuarios() {
		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();
		conecta.executarSQL("SELECT * FROM usuarios");

		try {
			conecta.rs.first();
			do {
				System.out.println(
						"Nome: " + conecta.rs.getString("nome") + " | CPF: " + conecta.rs.getString("cpf") + " | Turno: "
								+ conecta.rs.getString("turno") + " | Matrícula: " + conecta.rs.getString("matricula"));
			} while (conecta.rs.next());
		} catch (SQLException ex) {
			System.out.println("Erro ao pesquisar dados :(");
		} finally {
			conecta.desconectar();
			Menu.menuInicial();
		}
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
			Menu.menuInicial();
		}
	}

}

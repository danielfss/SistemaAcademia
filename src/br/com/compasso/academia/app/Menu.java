package br.com.compasso.academia.app;

import java.util.Scanner;

import br.com.compasso.academia.services.UsuarioControle;

public class Menu {
	
	private static Scanner leitura = new Scanner(System.in);
	
	public static void menuInicial() {
		
		System.out.println("Seja bem-vindo a Academia CompassoUOL!\nEscolha uma das op��es:\n"
				+ "A) Cadastrar usu�rio\n"
				+ "B) Consultar cadastro de um usu�rio\n"
				+ "C) Listar todos os usu�rios\n"
				+ "D) Deletar usu�rio");
		
		String opcao = leitura.next();
		
		while(true) {
			switch (opcao.toLowerCase()) {
				case "a":
					UsuarioControle.cadastro();
					break;
		
				case "b":
					UsuarioControle.consultarUsuario();
					break;
		
				case "c":
					UsuarioControle.listarUsuarios();
					break;
		
				case "d":
					UsuarioControle.deletarUsuario();
					break;
		
				default:
					System.out.println("Op��o inv�lida, tente novamente!");
					menuInicial();
					break;
			}
		}
	}
}

package br.com.compasso.academia.app;

import java.util.Scanner;

import br.com.compasso.academia.services.UsuarioControle;

public class Menu {
	
	private static Scanner leitura = new Scanner(System.in);
	
	public static void menuInicial() {
		
		System.out.println("Seja bem-vindo a Academia CompassoUOL!\nEscolha uma das opções:\n"
				+ "A) Cadastrar usuário\n"
				+ "B) Consultar cadastro de um usuário\n"
				+ "C) Listar todos os usuários\n"
				+ "D) Deletar usuário");
		
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
					System.out.println("Opção inválida, tente novamente!");
					menuInicial();
					break;
			}
		}
	}
}

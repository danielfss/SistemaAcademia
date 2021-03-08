package br.com.compasso.academia.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TelaPrincipal extends JFrame implements ActionListener {

	JPanel painel = new JPanel();
	Font fonte = new Font("Serif", Font.BOLD, 15);
	JLabel legenda = new JLabel("Academia Compasso UOL");
	JButton cadastrar = new JButton("Cadastrar usuário");
	JButton consultar = new JButton("Consultar usuário");
	JButton listar = new JButton("Listar usuários");
	JButton deletar = new JButton("Deletar usuário");
	JButton sair = new JButton("Sair");

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cadastrar) {
			new TelaCadastrar();
		}

		if (e.getSource() == consultar) {
			new TelaConsultar();
		}

		if (e.getSource() == listar) {
			new TelaListar();
		}

		if (e.getSource() == deletar) {
			new TelaDeletar();
		}

		if (e.getSource() == sair) {
			dispose();
		}
	}

	public TelaPrincipal() {

		// BOTÃO CADASTRAR
		cadastrar.setFont(fonte);
		cadastrar.addActionListener(this);
		setLayout(null); // aqui estamos dizendo que não será utilizado layouts.
		// desse modo, temos que definir o layout por conta própria, informar a posição
		// e o tamanho dos componentes.
		cadastrar.setBounds(60, 130, 200, 60); // X, Y, Largura, Altura
		add(cadastrar);

		// BOTÃO CONSULTAR
		consultar.setFont(fonte);
		consultar.addActionListener(this);
		consultar.setBounds(60, 200, 200, 60);
		add(consultar);

		// BOTÃO LISTAR
		listar.setFont(fonte);
		listar.addActionListener(this);
		listar.setBounds(350, 130, 200, 60);
		add(listar);
		
		// BOTÃO DELETAR
		deletar.setFont(fonte);
		deletar.addActionListener(this);
		deletar.setBounds(350, 200, 200, 60);
		add(deletar);
		

		listar.setFont(fonte);
		listar.addActionListener(this);
		listar.setBounds(350, 130, 200, 60);
		add(listar);

		// LEGENDA
		legenda.setFont(fonte);
		add(legenda);
		legenda.setBounds(200, 25, 200, 20);

		// JANELA
		setTitle("Sistema - Academia Compasso UOL");
		setSize(600, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao cliccar no "x", ele finaliza completamente o programa.
		setLocationRelativeTo(null); // Centraliza a abertura do programa na tela
		setResizable(false); // Retira o redimensionamento da tela.
		setVisible(true); // Torna a tela visível
	}

}

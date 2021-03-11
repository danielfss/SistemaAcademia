package br.com.compasso.academia.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.compasso.academia.app.Usuario;
import br.com.compasso.academia.services.UsuarioControle;

public class TelaConsultar extends JFrame implements ActionListener {

	JPanel painel = new JPanel();
	Font fonte = new Font("Serif", Font.BOLD, 15);

	JLabel legenda = new JLabel("Consultar usuário");
	JLabel matricula = new JLabel("Matricula: ");
	JLabel nome = new JLabel("Nome: ");
	JLabel cpf = new JLabel("CPF: ");
	JLabel turno = new JLabel("Turno: ");

	JTextField matriculaCampo = new JTextField("");
	JTextField nomeCampo = new JTextField("");
	JTextField cpfCampo = new JTextField("");
	JTextField turnoCampo = new JTextField("");

	JButton pesquisar = new JButton("Pesquisar");
	JButton limpar = new JButton("Limpar campos");
	JButton voltarMenu = new JButton("Voltar");
	JButton alterar = new JButton("Alterar cadastro");
	JButton deletar = new JButton("Deletar cadastro");

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pesquisar) {
			if (matriculaCampo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Você não digitou nenhuma matricula, preencha o campo com a matricula desejada!");
				new TelaConsultar();
				this.dispose();
			} else {
				Usuario usuario = new Usuario();
				usuario.setMatricula(matriculaCampo.getText());
				UsuarioControle.consultarUsuario(usuario);
				// PUXA OS DADOS DA CLASSE USUARIO QUE ESTÃO SALVOS NO BANCO
				nomeCampo.setText(usuario.getNome());
				cpfCampo.setText(usuario.getCpf());
				turnoCampo.setText(usuario.getTurno());
				
				// DESABILITA O CAMPO DE MATRICULA E O BOTÃO PESQUISAR
				matriculaCampo.setEnabled(false);
				pesquisar.setEnabled(false);
				// HABILITA OS CAMPOS
				nomeCampo.setEnabled(true);
				turnoCampo.setEnabled(true);
				// HABILITA OS BOTÕES
				alterar.setEnabled(true);
				deletar.setEnabled(true);
				limpar.setEnabled(true);
			}

		}

		if (e.getSource() == limpar) {
			matriculaCampo.setText("");
			nomeCampo.setText("");
			cpfCampo.setText("");
			turnoCampo.setText("");
		}

		if (e.getSource() == alterar) {
			Usuario usuario = new Usuario();
			usuario.setMatricula(matriculaCampo.getText());
			UsuarioControle.editarUsuario(usuario);
		}

		if (e.getSource() == deletar) {
			Usuario usuario = new Usuario();
			usuario.setMatricula(matriculaCampo.getText());
			UsuarioControle.deletarUsuario(usuario); 
			matriculaCampo.setText("");
			nomeCampo.setText("");
			cpfCampo.setText("");
			turnoCampo.setText("");
			alterar.setEnabled(false);
			deletar.setEnabled(false);
			limpar.setEnabled(false);
		}

		if (e.getSource() == voltarMenu) {
			dispose();
		}
	}

	public TelaConsultar() {

		// LABEL
		matricula.setFont(fonte);
		add(matricula);
		matricula.setBounds(25, 100, 80, 30);
		nome.setFont(fonte);
		add(nome);
		nome.setBounds(25, 150, 50, 30);

		cpf.setFont(fonte);
		add(cpf);
		cpf.setBounds(25, 200, 50, 30);

		turno.setFont(fonte);
		add(turno);
		turno.setBounds(25, 250, 50, 30);

		// CAMPOS
		matriculaCampo.setBounds(120, 100, 200, 30);
		add(matriculaCampo);

		nomeCampo.setBounds(120, 150, 200, 30);
		add(nomeCampo);
		nomeCampo.setEnabled(false);

		cpfCampo.setBounds(120, 200, 200, 30);
		add(cpfCampo);
		cpfCampo.setEnabled(false);

		turnoCampo.setBounds(120, 250, 200, 30);
		add(turnoCampo);
		turnoCampo.setEnabled(false);

		// BOTÃO PESQUISAR
		pesquisar.setFont(fonte);
		pesquisar.addActionListener(this);
		setLayout(null);
		pesquisar.setBounds(90, 300, 200, 40);
		add(pesquisar);

		// BOTÃO ALTERAR
		alterar.setFont(fonte);
		alterar.addActionListener(this);
		alterar.setBounds(90, 350, 200, 40);
		add(alterar);
		alterar.setEnabled(false);

		// BOTÃO DELETAR
		deletar.setFont(fonte);
		deletar.addActionListener(this);
		deletar.setBounds(90, 400, 200, 40);
		add(deletar);
		deletar.setEnabled(false);

		// BOTÃO LIMPAR
		limpar.setFont(fonte);
		limpar.addActionListener(this);
		limpar.setBounds(90, 450, 200, 40);
		add(limpar);
		limpar.setEnabled(false);

		// BOTÃO VOLTAR
		voltarMenu.setFont(fonte);
		voltarMenu.addActionListener(this);
		voltarMenu.setBounds(90, 500, 200, 40);
		add(voltarMenu);

		// LEGENDA
		legenda.setFont(fonte);
		add(legenda);
		legenda.setBounds(130, 50, 150, 20);

		
		painel.setBackground(Color.LIGHT_GRAY);
		painel.setBounds(15, 140, 350, 150);
		add(painel);
		
		// JANELA
		setTitle("Tela de Cadastro de usuários");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}

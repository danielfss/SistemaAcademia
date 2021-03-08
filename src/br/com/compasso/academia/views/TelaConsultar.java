package br.com.compasso.academia.views;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pesquisar) {
			if (matriculaCampo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Você não digitou nenhuma matricula, preencha o campo com a matricula desejada!");
				new TelaConsultar();
				this.dispose();
			} else {

				Usuario usuario = new Usuario();
				usuario.setMatricula(matriculaCampo.getText());
				UsuarioControle.consultarUsuario(usuario);
				nomeCampo.setText(usuario.getNome());
				cpfCampo.setText(usuario.getCpf());
				turnoCampo.setText(usuario.getTurno());
			}

		}
		
		if (e.getSource() == limpar) {
			matriculaCampo.setText("");
			matriculaCampo.setText("");
			matriculaCampo.setText("");
			matriculaCampo.setText("");
		}

		if (e.getSource() == voltarMenu) {
			dispose();
		}
	}

	public TelaConsultar() {

		// LABEL
		matricula.setFont(fonte);
		add(matricula);
		matricula.setBounds(25, 100, 50, 30);
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
		matriculaCampo.setBounds(100, 100, 200, 30);
		add(matriculaCampo);
		
		nomeCampo.setBounds(100, 150, 200, 30);
		add(nomeCampo);
		nomeCampo.setEnabled(false);

		cpfCampo.setBounds(100, 200, 200, 30);
		add(cpfCampo);
		cpfCampo.setEnabled(false);

		turnoCampo.setBounds(100, 250, 200, 30);
		add(turnoCampo);
		turnoCampo.setEnabled(false);

		// BOTÃO PESQUISAR
		pesquisar.setFont(fonte);
		pesquisar.addActionListener(this);
		setLayout(null);
		pesquisar.setBounds(90, 300, 200, 60);
		add(pesquisar);

		// BOTÃO LIMPAR
		limpar.setFont(fonte);
		limpar.addActionListener(this);
		limpar.setBounds(90, 370, 200, 60);
		add(limpar);

		// BOTÃO VOLTAR
		voltarMenu.setFont(fonte);
		voltarMenu.addActionListener(this);
		voltarMenu.setBounds(90, 440, 200, 60);
		add(voltarMenu);

		// LEGENDA
		legenda.setFont(fonte);
		add(legenda);
		legenda.setBounds(80, 50, 200, 20);

		// JANELA
		setTitle("Tela de Cadastro de usuários");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}

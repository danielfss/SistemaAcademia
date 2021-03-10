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

public class TelaCadastrar extends JFrame implements ActionListener {

	JPanel painel = new JPanel();
	Font fonte = new Font("Serif", Font.BOLD, 15);

	JLabel legenda = new JLabel("Cadastro de usuários");
	JLabel nome = new JLabel("Nome: ");
	JLabel cpf = new JLabel("CPF: ");
	JLabel turno = new JLabel("Turno: ");

	JTextField nomeCampo = new JTextField("");
	JTextField cpfCampo = new JTextField("");

	String turnoCampo[] = { "matutino", "vespertino", "noturno" };
	JComboBox<String> cb = new JComboBox<String>(turnoCampo);

	JButton finalizarCadastro = new JButton("Finalizar cadastro");
	JButton limpar = new JButton("Limpar");
	JButton voltarMenu = new JButton("Voltar");

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == finalizarCadastro) {
			if (nomeCampo.getText().isEmpty() || cpfCampo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Voce esqueceu algum campo vazio, preencha todos os campos!");
				new TelaCadastrar();
				this.dispose();
			} else {
				String nome = "";
				String cpf = "";
				String turno = "";

				Usuario usuario = new Usuario(nome, cpf, turno);
				usuario.setNome(nomeCampo.getText());
				usuario.setCpf(cpfCampo.getText());
				usuario.setTurno(turno = String.valueOf(cb.getSelectedItem())); // pega o texto do combobox

				UsuarioControle.cadastrarUsuario(usuario);
			}

		}

		if (e.getSource() == limpar) {
			nomeCampo.setText("");
			cpfCampo.setText("");
		}

		if (e.getSource() == voltarMenu) {
			dispose();
		}

	}

	public TelaCadastrar() {

		// LABEL
		nome.setFont(fonte);
		add(nome);
		nome.setBounds(25, 100, 50, 30);

		cpf.setFont(fonte);
		add(cpf);
		cpf.setBounds(25, 150, 50, 30);

		turno.setFont(fonte);
		add(turno);
		turno.setBounds(25, 200, 50, 30);

		// CAMPOS
		nomeCampo.setBounds(75, 100, 200, 30);
		add(nomeCampo);

		cpfCampo.setBounds(75, 150, 200, 30);
		add(cpfCampo);

		// COMBOBOX
		cb.setFont(fonte);
		add(cb);
		cb.setBounds(75, 210, 100, 30);

		// BOTÃO CADASTRAR
		finalizarCadastro.setFont(fonte);
		finalizarCadastro.addActionListener(this);
		setLayout(null);
		finalizarCadastro.setBounds(90, 270, 200, 60);
		add(finalizarCadastro);

		// BOTÃO LIMPAR
		limpar.setFont(fonte);
		limpar.addActionListener(this);
		setLayout(null);
		limpar.setBounds(90, 340, 200, 60);
		add(limpar);

		// BOTÃO VOLTAR
		voltarMenu.setFont(fonte);
		voltarMenu.addActionListener(this);
		setLayout(null);
		voltarMenu.setBounds(90, 410, 200, 60);
		add(voltarMenu);

		// LEGENDA
		legenda.setFont(fonte);
		add(legenda);
		legenda.setBounds(130, 50, 150, 20);

		// JANELA
		setTitle("Tela de Cadastro de usuários");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}

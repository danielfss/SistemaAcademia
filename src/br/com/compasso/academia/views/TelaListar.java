package br.com.compasso.academia.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.compasso.academia.app.Usuario;
import br.com.compasso.academia.services.ConectaBanco;
import br.com.compasso.academia.services.UsuarioControle;

public class TelaListar extends JFrame implements ActionListener {

	Font fonte = new Font("Serif", Font.BOLD, 15);
	JLabel legenda = new JLabel("Cadastro de usuários");
	JPanel painel;
	JTable table;
	JButton voltarMenu = new JButton("Voltar");

	public TelaListar() {
		// JANELA
		setTitle("Sistema - Academia Compasso UOL");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao cliccar no "x", ele finaliza completamente o programa.
		setLocationRelativeTo(null); // Centraliza a abertura do programa na tela
		setResizable(false); // Retira o redimensionamento da tela.
		setVisible(true); // Torna a tela visível

		// BOTÃO VOLTAR
		voltarMenu.setFont(fonte);
		voltarMenu.addActionListener(this);
		voltarMenu.setBounds(350, 500, 100, 50);
		getContentPane().add(voltarMenu);

		// LEGENDA
		legenda.setFont(fonte);
		legenda.setBounds(200, 400, 200, 20);
		getContentPane().add(legenda);

		table = new JTable();
		table.setBounds(20, 11, 505, 257);
		getContentPane().add(table);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 546, 561);
		getContentPane().add(panel);
		carregarTabela();

	}

	public void carregarTabela() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addColumn("Nome"); // adiciono as colunas
		modelo.addColumn("CPF");
		modelo.addColumn("Turno");
		modelo.addColumn("Matricula");
		modelo.setNumRows(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);

		ConectaBanco conecta = new ConectaBanco();
		conecta.conectar();

		for (Usuario u : UsuarioControle.read()) {
			modelo.addRow(new Object[] { u.getNome(), u.getCpf(), u.getTurno(), u.getMatricula() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == voltarMenu) {
			dispose();
		}
	}

}

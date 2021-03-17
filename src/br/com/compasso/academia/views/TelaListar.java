package br.com.compasso.academia.views;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.compasso.academia.app.Usuario;
import br.com.compasso.academia.services.UsuarioControle;

public class TelaListar extends JFrame implements ActionListener {

	Font fonte = new Font("Serif", Font.BOLD, 15);
	JLabel legenda = new JLabel("Lista de todos os usuários");
	JTable table;
	JButton voltarMenu = new JButton("Voltar");
	JPanel panel;

	public TelaListar() {
		// JANELA
		setTitle("Sistema - Academia Compasso UOL");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao cliccar no "x", ele finaliza completamente o programa.
		setLocationRelativeTo(null); // Centraliza a abertura do programa na tela
		setResizable(false); // Retira o redimensionamento da tela.
		setVisible(true); // Torna a tela visível

		// LEGENDA
		legenda.setFont(fonte);
		legenda.setBounds(200, 400, 200, 20);
		getContentPane().add(legenda);

		// BOTÃO VOLTAR
		voltarMenu.setFont(fonte);
		voltarMenu.addActionListener(this);
		voltarMenu.setBounds(200, 500, 200, 50);
		getContentPane().add(voltarMenu);

		table = new JTable();
		table.setBounds(60, 20, 450, 250);
		getContentPane().add(table);
		
		// SCROLLBAR COM AWT
		ScrollPane scroll = new ScrollPane();
		scroll.add(table);
		getContentPane().add(scroll);
		scroll.setBounds(60, 20, 450, 250);

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 600);
		getContentPane().add(panel);
		
		carregarTabela();

	}

	public void carregarTabela() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// ADICIONA AS COLUNAS
//		Object[] colunas = {"nome", "cpf", "turno", "matricula"};
//		modelo.setColumnIdentifiers(colunas);
//		table.setModel(modelo);
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Turno");
		modelo.addColumn("Matricula");
		modelo.setNumRows(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);

		for (Usuario u : UsuarioControle.listarUsuarios()) {
			modelo.addRow(new Object[] {
					u.getNome(),
					u.getCpf(),
					u.getTurno(),
					u.getMatricula()
			});
			panel.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == voltarMenu) {
			dispose();
		}
	}

}

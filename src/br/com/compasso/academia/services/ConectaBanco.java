package br.com.compasso.academia.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConectaBanco {

	public Statement stm; // responsável por preparar e realizar consultas no BD
	public ResultSet rs; // responsável por armazenar o resultado da consulta do BD
	private String driver = "org.postgresql.Driver"; // responsável por identificar o serviço de BD
	private String url = "jdbc:postgresql://localhost:5432/sistema_academia"; // responsável por setar o local do BD
	private String user = "postgres"; // usuario e senha do BD
	private String pass = "root123";
	public Connection conn; // responsável por fazer a conexão com o BD
	
	public void conectar() {
		try {
			System.setProperty("jdbc.Drivers", driver); // seta a propriedade do driver de conexão
			conn = DriverManager.getConnection(url, user, pass); // realiza a conexão com o BD
//			JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
			System.out.println("Conectado com sucesso!");
		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Erro de conexão com o BD!\nErro: " + ex.getMessage());
			System.out.println("Erro de conexão com o BD!\nErro: " + ex.getMessage());
		}
	}
	
	public void desconectar() {
		try {
			conn.close(); // fecha a conexão com o BD
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o BD!\nErro: " + ex.getMessage());
			System.out.println("Erro ao fechar a conexão com o BD!\nErro: " + ex.getMessage());
		}
	}
	
	public void executarSQL(String sql) {
        try {
            this.stm = this.conn.createStatement(this.rs.TYPE_SCROLL_INSENSITIVE, this.rs.CONCUR_READ_ONLY);
            this.rs = this.stm.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao executar consulta SQL :(");
        }
    }
	
}

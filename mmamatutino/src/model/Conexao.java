package model;

import java.sql.Connection; // conexao BD
import java.sql.DriverManager; // Gerenciar o DRIVER
import java.sql.SQLException;

public class Conexao {
    private Connection conexao; // permitir a conexao com BD
    private final String URL = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";
    private final String DRIVER = "com.mysql.jdbc.Driver";
	   
    public Conexao() { // construtor
		
	}
       
    public Connection getConexao() throws ClassNotFoundException, SQLException {
    	Class.forName(DRIVER);
    	this.conexao = DriverManager.getConnection(URL,"root","");
    	return this.conexao;
    }

	public String getURL() {
		return URL;
	}

	public String getDRIVER() {
		return DRIVER;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
    
    
    
}

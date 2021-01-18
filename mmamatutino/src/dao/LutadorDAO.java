package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import model.Conexao;
import model.ComputadorRede;

public class LutadorDAO {
	private Connection conexao;
	private String sql;
	private PreparedStatement prepararSQL;
	private ResultSet rs;
	
	public LutadorDAO() throws ClassNotFoundException, SQLException {
		this.conexao = new Conexao().getConexao();
	}
	
	public boolean insert(Lutador lutador) throws SQLException {
		sql = "INSERT INTO lutador (nome,peso,altura) values (?,?,?)";
		prepararSQL = conexao.prepareStatement(sql);
		prepararSQL.setString(1, lutador.getNome());
		prepararSQL.setDouble(2, lutador.getPeso());
		prepararSQL.setDouble(3, lutador.getAltura());
		prepararSQL.execute();
		prepararSQL.close();
		return true;
	}
	
	public boolean delete(Lutador lutador) throws SQLException {
		sql = "DELETE FROM lutador where id=?";
		prepararSQL = conexao.prepareStatement(sql);
		prepararSQL.setInt(1, lutador.getId());
		prepararSQL.execute();
		prepararSQL.close();
		return true;
	}
		
	public boolean update(Lutador lutador) throws SQLException {
		sql = "UPDATE lutador set nome=?, peso=?,altura=? where id=?";
		prepararSQL = conexao.prepareStatement(sql);
		prepararSQL.setString(1, lutador.getNome());
		prepararSQL.setDouble(2, lutador.getPeso());
		prepararSQL.setDouble(3, lutador.getAltura());
		prepararSQL.setInt(4,lutador.getId());
		prepararSQL.executeUpdate();
		prepararSQL.close();
		return true;
	}
	
	public DefaultTableModel selectAll() throws SQLException {
		Lutador lutador = new Lutador();
		DefaultTableModel tabela = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==0) {
					return false;
				}else {
					return true;
				}
			}		
		};
		sql = "SELECT * FROM lutador order by nome";
		prepararSQL = this.conexao.prepareStatement(sql);
		rs = prepararSQL.executeQuery(sql); // guardar o resultado do SQL
		
		
		// adiciona as colunas na tabela
		tabela.addColumn("ID");
		tabela.addColumn("Nome");
		tabela.addColumn("Peso");
		tabela.addColumn("Altura");
		
		// adicinar a linha do cabeçalho da tabeal
		String cabecalho[] = {"ID","Nome","Peso","Altura"};
		tabela.addRow(cabecalho);
		
		// adicionar as linhas do select 
		while (rs.next()) {
			tabela.addRow(new String[] {rs.getString("id"),
			                         rs.getString("nome"),
			                         rs.getString("peso"),
			                         rs.getString("altura")});
		}
        prepararSQL.close();
		return tabela;
	}
	
}

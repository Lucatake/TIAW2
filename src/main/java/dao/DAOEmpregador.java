package dao;
import java.sql.*;

import model.Empregador;
import model.Estudante;

public class DAOEmpregador {
	private Connection conexao;
	
	public DAOEmpregador() {
		conexao = null;
	}
	public boolean conectar () {
		boolean status = false;

		try {
			Class.forName(CredenciaisDB.driverName);
			conexao = DriverManager.getConnection(CredenciaisDB.url, CredenciaisDB.username, CredenciaisDB.password);
			status = (conexao == null);
		} catch (ClassNotFoundException e) { 
			System.err.println("Conex�o N�O efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conex�o N�O efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public Empregador getEmpregador(String usuario1) 
	{
		Empregador e1 = new Empregador();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM empregador WHERE cnpj = '" + usuario1 + "'");
			if(rs.next()) 
			{
				e1 = new Empregador(rs.getInt("id"), rs.getString("cnpj"),rs.getString("senha"), rs.getString("nome"), rs.getString("email"), rs.getString("descricao"), rs.getString("site"));//acabar
			}
			st.close();
		} catch(Exception erro) {
			System.err.println(erro.getMessage());
		}
		return e1;
	}
	
	public boolean addEmpregador(Empregador empregador) {
		boolean status1 = false;
		try {
			Statement st1 = conexao.createStatement();
			st1.executeUpdate("INSERT INTO empregador(id, cnpj, email, descricao, nome, site, senha)"
					+ "VALUES ("+empregador.getId()+",'"+empregador.getCnpj()+"','"+empregador.getEmail()+"', '"+empregador.getDescricao()+"', '"+empregador.getNome()+"', '"+empregador.getSite()+"', '"+empregador.getSenha()+"');");
			st1.close();
			status1 = true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return status1;
		
	}
	
	public Empregador[] getAllEmpregador() 
	{
		Empregador[]e = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM empregador ORDER BY id;");		
	         if(rs.next()){
	             rs.last();
	             e = new Empregador[rs.getRow()];
	             rs.beforeFirst();
	             
	             for(int i = 0; rs.next(); i++) {
	                e[i] = new Empregador(rs.getInt("id"),rs.getString("senha"), rs.getString("cnpj"), rs.getString("nome"), rs.getString("email"), rs.getString("descricao"), rs.getString("site"));
	             }
	          }
	          st.close();
		} catch(Exception erro) {
			System.err.println(erro.getMessage());
		}
		return e;
	}
}

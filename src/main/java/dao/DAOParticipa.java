package dao;
import java.sql.*;
import model.Participa;

public class DAOParticipa {

private Connection conexao;
	
	public DAOParticipa() {
		conexao = null;
	}

	public boolean conectar() {
		boolean status = false;

		try {
			Class.forName(CredenciaisDB.driverName);
			conexao = DriverManager.getConnection(CredenciaisDB.url, CredenciaisDB.username, CredenciaisDB.password);
			status = (conexao == null);
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
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
	
	public boolean deletado(int id) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM participa WHERE id = " + id+";");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean add(Participa p) { //conexao com bd
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO participa (id, id_estudante, id_vaga) " 
					       + "VALUES ("+p.getId()+", "+p.getIdEstudante()+", "+p.getIdVaga()+");");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Participa[] getAll() {
		Participa[] p = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM participa;"); // talvez filtrar por id de aluno
	         if(rs.next()){
	             rs.last();
	             p = new Participa[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                p[i] = new Participa(rs.getInt("id"), rs.getInt("id_estudante"), rs.getInt("id_vaga"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return p;
	}
	
	public Participa[] getAllEstudante(int id) {
		Participa[] p = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM participa WHERE id_estudante = "+id+";"); // talvez filtrar por id de aluno
	         if(rs.next()){
	             rs.last();
	             p = new Participa[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                p[i] = new Participa(rs.getInt("id"), rs.getInt("id_estudante"), rs.getInt("id_vaga"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return p;
	}
	
}

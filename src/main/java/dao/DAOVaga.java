package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Vaga;

public class DAOVaga {
	
	private Connection conexao;

	public DAOVaga() {
		conexao = null;
	}
	
	public boolean conectar() {
		boolean status = false;

		try {
			Class.forName(CredenciaisDB.driverName);
			conexao = DriverManager.getConnection(CredenciaisDB.url, CredenciaisDB.username, CredenciaisDB.password);
			status = (conexao == null);
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexao nao efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao nao efetuada com o postgres -- " + e.getMessage());
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
	
	public Vaga getVaga(int id) 
	{
		Vaga v = new Vaga();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM vaga WHERE id = " + id + "");		
	         if(rs.next()){
	        	v = new Vaga(rs.getInt("id"), rs.getInt("empregador_id"), rs.getString("titulo"), rs.getString("descricao"), rs.getFloat("salario"));
	          }
	          st.close();
		} catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		return v;
	}
	
	public boolean addVaga(Vaga vaga) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO vaga (id, empregador_id, titulo, descricao, salario) "
                           + "VALUES ("+vaga.getId()+", "+vaga.getEmpregadorId()+", '"+vaga.getTitulo()+"', '"+vaga.getDescricao()+"', "+vaga.getSalario()+");");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
	
	public Vaga[] getAllVaga() {
		Vaga[]v = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM vaga ORDER BY id;");		
	         if(rs.next()){
	             rs.last();
	             v = new Vaga[rs.getRow()];
	             rs.beforeFirst();
	             
	             for(int i = 0; rs.next(); i++) {
	                v[i] = new Vaga(rs.getInt("id"), rs.getInt("empregador_id"), rs.getString("titulo"), rs.getString("descricao"), rs.getFloat("salario"));
	             }
	          }
	          st.close();
		} catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		return v;
	}
	
	public Vaga[] getAllVagaAluno(int id[]) {
		Vaga[]vagas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			vagas = new Vaga[id.length];
			for(int i = 0; i < id.length; i++) {
				String k = id[i] + "";
				ResultSet rs = st.executeQuery("SELECT * FROM vaga WHERE id = "+k+";");
				rs.next();
		        vagas[i] = new Vaga(rs.getInt("id"), rs.getInt("empregador_id"), rs.getString("titulo"), rs.getString("descricao"), rs.getFloat("salario"));
		             
		        
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return vagas;
	}
	
	public boolean deleteVaga(int id) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM vaga WHERE id = " + id+";");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

}

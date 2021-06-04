package dao;
import java.sql.*;

import model.Estudante;

public class DAOEstudante {
	private Connection conexao;
	
	public DAOEstudante() {
		conexao = null;
	}
	
	public boolean conectar() {
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
	
	public Estudante getEstudante(String usuario) 
	{
		Estudante e = new Estudante();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM estudante WHERE usuario = '" + usuario + "'");		
	         if(rs.next()){
	        	e = new Estudante(rs.getInt("id"), rs.getString("cpf"), rs.getString("usuario"), rs.getString("senha"), rs.getString("prenome"), rs.getString("email"), rs.getInt("periodo"),rs.getString("curso"), rs.getInt("telefone"));
	          }
	          st.close();
		} catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		return e;
	}
	
	public boolean addEstudante(Estudante estud) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO estudante (id, cpf, usuario, senha, prenome, email, periodo, curso, telefone) "
                           + "VALUES ("+estud.getId()+" ,'"+estud.getCpf()+"', '"+estud.getUsuario()+"', '"+estud.getSenha()+"', '"+estud.getPrenome()+"', '"+estud.getEmail()+"', "+estud.getPeriodo()+", '"+estud.getCurso()+"', "+estud.getTelefone()+");");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
	
	public Estudante[] getAllEstudante() {
		Estudante[]e = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM estudante ORDER BY id;");		
	         if(rs.next()){
	             rs.last();
	             e = new Estudante[rs.getRow()];
	             rs.beforeFirst();
	             
	             for(int i = 0; rs.next(); i++) {
	                e[i] = new Estudante(rs.getInt("id"), rs.getString("cpf"), rs.getString("usuario"), rs.getString("senha"), rs.getString("prenome"), rs.getString("email"), rs.getInt("periodo"),rs.getString("curso"), rs.getInt("telefone"));
	             }
	          }
	          st.close();
		} catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		return e;
	}
}

// public Empregador(String cpf, String usuario, String senha, String cnpj, String nome, 
// String email, int periodo, String descricao, String site, String endereco) {
// this.cpf = cpf;
// this.usuario = usuario;
// this.senha = senha;
// this.cnpj = cnpj;
// this.nome = nome;
// this.email = email;
// this.descricao = descricao;
// this.site = site;
// this.endereco = endereco;

// }
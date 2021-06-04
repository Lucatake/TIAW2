package services;
import java.sql.*;
import spark.Request;

import dao.CredenciaisDB;
import dao.DAOEstudante;
import model.Estudante;

public class EstudanteServices {
	private DAOEstudante conexao;
	
	public EstudanteServices() {
		conexao = new DAOEstudante();
	}
	
	
	public Object getEsdudante(Request request)
	{
		conexao.conectar();
		String usuario = request.queryParams("usuario");
		Estudante e = conexao.getEstudante(usuario);
		StringBuffer returnValue = new StringBuffer("[");
		returnValue.append(e.toJson());
		returnValue.append("]");
		
		conexao.close(); //abriu conexao fecha 
		return returnValue.toString();
	}

	public String adicionarEstudante(Request request){
        conexao.conectar();
        int maiorId = 0;
        String user = request.queryParams("username");
        String pass = request.queryParams("password");
        String prenome = request.queryParams("prenome");
        String email = request.queryParams("email");
        String cpf = request.queryParams("cpf");
        String curso = request.queryParams("curso");
        int periodo = Integer.parseInt(request.queryParams("periodo"));
        int telefone = Integer.parseInt(request.queryParams("telefone"));
        
        Estudante[]es = conexao.getAllEstudante();
		
		if (es!= null) 
			for(int i = 0; i < es.length; i++) 
				if(maiorId < es[i].getId()) 
					maiorId = es[i].getId();

        Estudante e = new Estudante(++maiorId, cpf, user, pass, prenome, email, periodo, curso, telefone);

        boolean resp = conexao.addEstudante(e);
        conexao.close();

        return resp ? "200" : "Error";
    }


} 


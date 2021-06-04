package services;
import java.sql.*;
import spark.Request;

import dao.CredenciaisDB;
import dao.DAOEmpregador;
import model.Empregador;
import model.Estudante;

public class EmpregadorServices {
	
	private DAOEmpregador conexao;
	
	public EmpregadorServices() {
		conexao = new DAOEmpregador();
	}
	public Object getEmpregador(Request request) 
	{
		conexao.conectar();
		String usuarioEmpregador = request.queryParams("cnpjEmpregador");
		Empregador e1 = conexao.getEmpregador(usuarioEmpregador);
		StringBuffer returnValue1 = new StringBuffer("[");
		returnValue1.append(e1.toJson());
		returnValue1.append("]");
		conexao.close();
		System.out.println(e1.getId());
		return returnValue1.toString();
	}
	
	public String adicionarEmpregador(Request request1) {
		conexao.conectar(); //verificar dps
		String cnpj = request1.queryParams("cnpj");
		String email = request1.queryParams("email");
		String nome = request1.queryParams("nome");
		String descricao = request1.queryParams("descricao");
		String site = request1.queryParams("site");
		String senha = request1.queryParams("senha");
		
		Empregador[]es = conexao.getAllEmpregador();
		int maiorId = 0;
		if (es!= null) 
			for(int i = 0; i < es.length; i++) 
				if(maiorId < es[i].getId()) 
					maiorId = es[i].getId();
		
		Empregador e1 = new Empregador(++maiorId, cnpj, senha, nome, email, descricao, site);
		
		boolean resp1 = conexao.addEmpregador(e1);
		conexao.close();
		
		return resp1 ? "200" : "Error";
	}

}

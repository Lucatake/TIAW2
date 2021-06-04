package services;

import model.Vaga;
import dao.DAOVaga;
import java.sql.*;
import spark.Request;

public class VagaServices {
	private DAOVaga conexao;

	public VagaServices() {
		conexao = new DAOVaga();
	}
	
	public Object getAllVaga(Request request) {
		conexao.conectar();
		Vaga[]v = conexao.getAllVaga();
		StringBuffer returnValue = new StringBuffer("[");
		if(v != null) {
			
			for(int i = 0;i < v.length; i++) {
				if(i != v.length-1)
					returnValue.append(v[i].toJson()+",");
				else
					returnValue.append(v[i].toJson());
			}
			returnValue.append("]");
		}
		
		
		conexao.close();
		return returnValue.toString() != "[]" ? returnValue.toString() : "Error";
	}
	
	public String addVaga(Request request) {
		conexao.conectar();
		int maiorId = 0;
		int empregador_id = Integer.parseInt(request.queryParams("empregador_id"));
		String titulo = request.queryParams("titulo");
		String descricao = request.queryParams("descricao");
		float salario = (float)Integer.parseInt(request.queryParams("salario"));
		
		Vaga[]vt = conexao.getAllVaga();
		
		if(vt != null) 
			for(int i = 0; i < vt.length; i++) 
				if(maiorId < vt[i].getId()) 
					maiorId = vt[i].getId();
		
		Vaga v = new Vaga(++maiorId, empregador_id, titulo, descricao, salario);
		
		boolean resp = conexao.addVaga(v);
        conexao.close();
		
		return resp ? "200" : "Error";
	}

	public Object deletarVaga(Request request) {
		conexao.conectar();
		int id = Integer.parseInt(request.queryParams("idVaga"));
		boolean resp = conexao.deleteVaga(id);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}
}

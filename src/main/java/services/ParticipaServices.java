package services;
import dao.*;
import model.*;
import spark.Request;

public class ParticipaServices {
	
	private DAOParticipa conexao;

	public ParticipaServices() {
		conexao = new DAOParticipa();
	}
	
	public String addParticipa(Request request) { //tratamento de objetos
		conexao.conectar();
		int alunoId = Integer.parseInt(request.queryParams("userId"));
		int vagaId = Integer.parseInt(request.queryParams("idVaga"));
		
		int maiorId = 0;
		Participa[]p = conexao.getAll();
		
		if(p != null) 
			for(int i = 0; i < p.length; i++) 
				if(maiorId < p[i].getId()) 
					maiorId = p[i].getId();
		
		Participa p1 = new Participa(++maiorId, alunoId, vagaId);
		
		boolean resp = conexao.add(p1); //funcao add
		conexao.close();
		return resp ? "200" : "Error";
	}
	
	public Object getAllParticipaEstudante(Request request) {
		DAOVaga conexaoVaga = new DAOVaga();
		StringBuffer returnValue = new StringBuffer("[");
		//abrir conexoes
		conexao.conectar();
		conexaoVaga.conectar();
		
		int id = Integer.parseInt(request.queryParams("userId"));
		
		Participa[]partEstudante = conexao.getAllEstudante(id);
		if(partEstudante != null) {
			int[]idVagas = new int[partEstudante.length];
			for(int i = 0; i < partEstudante.length; i++)
				idVagas[i] = partEstudante[i].getIdVaga();
			
			if(conexaoVaga.getAllVagaAluno(idVagas) != null) {
				Vaga[]v = conexaoVaga.getAllVagaAluno(idVagas);
				
				for(int i = 0;i < v.length; i++) {
					if(i != v.length-1)
						returnValue.append(v[i].toJson()+",");
					else
						returnValue.append(v[i].toJson());
				}
				returnValue.append("]");
			}
		}
		conexao.close();
		conexaoVaga.close();
		return returnValue.toString() != "[]" ? returnValue.toString() : "Error";
	}
	
	public Object deleteParticipa(Request request) {
		conexao.conectar();
		int id = Integer.parseInt(request.queryParams("idParticipa"));
		boolean resp = conexao.deletado(id);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}

}

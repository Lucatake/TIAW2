package app;
import static spark.Spark.*;

import services.*;

public class Main {
	
	private final static EstudanteServices estudanteService = new EstudanteServices();
	private final static EmpregadorServices empregadorService = new EmpregadorServices();
	private final static VagaServices vagaService = new VagaServices();
	private final static ParticipaServices participaService = new ParticipaServices();

	public static void main(String[] args) {
		//CONFIGS INICIAIS DE REQUISI��ES E PORTAS
		
		port(getHerokuAssignedPort());
		
		
		/*after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Mtethods", "GET, POST, DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        });*/
		options("/*",	(request, response) -> {

		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }  

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
///////////////////////////////////ESTUDANTE/////////////////////////////////////////////////////////////////////////////////
		get("/estudante/get", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return estudanteService.getEsdudante(request);
		});
			
		post("/estudante/add", (request, response) -> estudanteService.adicionarEstudante(request));
		
//////////////////////////////////EMPREGADOR/////////////////////////////////////////////////////////////////////////////////
		get("/empregador/get", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return empregadorService.getEmpregador(request);
		});
		
		post("/empregador/add", (request, response) -> empregadorService.adicionarEmpregador(request));
		
//////////////////////////////////VAGA//////////////////////////////////////////////////////////////////////////////////////
		get("/vaga/getAll", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return vagaService.getAllVaga(request);
		});
		
		post("/vaga/add", (request, response) -> vagaService.addVaga(request));
		
		delete("/vaga/delete", (request, response) -> vagaService.deletarVaga(request));
		
////////////////////////////PARTICIPA//////////////////////////////////////////////////////////////////////////////////////
		get("/participa/getAllEstudante", (request, response) -> {
			response.header("Content-Type", "application/json");
			response.header("Content-Encoding", "UTF-8");
			return participaService.getAllParticipaEstudante(request);
		});
		
		post("/participa/add", (request,response) ->  participaService.addParticipa(request));
		
		delete("/participa/delete","application/json", (request, response) -> participaService.deleteParticipa(request));
	}
	//java detecta a url com conexao spark, ativa a funcao de tratamento de dados e a query ira para o sql
	
		
	private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 6543; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}

package model;

import org.json.JSONObject;

public class Estudante implements JsonFormatter{
	private int id;
    private String cpf;
    private String usuario;
    private String senha;
	private String prenome;
    private String email;
    private int periodo;
    private String curso;
    private int telefone;
  
	
	public Estudante() {
		
	}
	
	public Estudante(int id, String cpf, String usuario, String senha, String prenome, String email, int periodo,
			String curso, int telefone) {
		this.id = id;
		this.cpf = cpf;
		this.usuario = usuario;
		this.senha = senha;
		this.prenome = prenome;
		this.email = email;
		this.periodo = periodo;
		this.curso = curso;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPrenome() {
		return prenome;
	}

	public void setPrenome(String prenome) {
		this.prenome = prenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	/**
	 * Converte um estudante para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("cpf", this.getCpf());
        obj.put("usuario", this.getUsuario());
        obj.put("senha", this.getSenha());
        obj.put("prenome", this.getPrenome());
        obj.put("email", this.getEmail());
        obj.put("periodo", this.getPeriodo());
        obj.put("curso", this.getCurso());
		return obj;
	}
	
}

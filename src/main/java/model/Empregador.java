package model;

import org.json.JSONObject;

public class Empregador implements JsonFormatter{
	private int id;
    private String cnpj;
    private String senha;
    private String nome;
    private String email;
    private String descricao;
    private String site;
	
	public Empregador() {
		
	}

	public Empregador(int id, String cnpj, String senha, String nome, String email, String descricao, String site) {
		this.id = id;
		this.cnpj = cnpj;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.site = site;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Converte um Empregador para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
        obj.put("senha", this.getSenha());
        obj.put("cnpj", this.getCnpj());
        obj.put("nome", this.getNome());
        obj.put("email", this.getEmail());
        obj.put("descricao", this.getDescricao());
        obj.put("site", this.getSite());
		
		return obj;
	}
	
}

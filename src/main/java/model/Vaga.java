package model;

import org.json.JSONObject;

public class Vaga implements JsonFormatter{

	private int id;
	private int empregadorId;
	private String titulo;
	private String descricao;
	private float salario;
	
	public Vaga() {
		
	}

	public Vaga(int id, int empregadorId, String titulo, String descricao, float salario) {
		super();
		this.id = id;
		this.empregadorId = empregadorId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.salario = salario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpregadorId() {
		return empregadorId;
	}

	public void setEmpregadorId(int empregadorId) {
		this.empregadorId = empregadorId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	/**
	 * Converte um Empregador para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
        obj.put("id", this.getId());
        obj.put("empregadorId", this.getEmpregadorId());
        obj.put("titulo", this.getTitulo());
        obj.put("descricao", this.getDescricao());
        obj.put("salario", this.getSalario());
		
		return obj;
	}
	
}

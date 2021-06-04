package model;

import org.json.JSONObject;

public class Participa implements JsonFormatter{
	
	private int id;
	private int idEstudante;
	private int idVaga;

	public Participa() {
		
	}

	public Participa(int id, int idEstudante, int idVaga) {
		super();
		this.id = id;
		this.idEstudante = idEstudante;
		this.idVaga = idVaga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEstudante() {
		return idEstudante;
	}

	public void setIdestudante(int idestudante) {
		this.idEstudante = idestudante;
	}

	public int getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(int idVaga) {
		this.idVaga = idVaga;
	}
	
	/**
	 * Converte um Empregador para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
        obj.put("id", this.getId());
        obj.put("idEstudante", this.getIdEstudante());
        obj.put("idVaga", this.getIdVaga());
		
		return obj;
	}
	
}

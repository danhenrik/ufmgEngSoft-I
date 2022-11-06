package br.ufmg.engsoft.urna.services.input;

public class UpdateQuestionInput {

	private String id;
	private String body;

	public UpdateQuestionInput(String id, String body) {
		this.id = id;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}

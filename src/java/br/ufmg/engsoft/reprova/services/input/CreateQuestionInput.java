package br.ufmg.engsoft.reprova.services.input;

public class CreateQuestionInput {
	
	private String body;

	public CreateQuestionInput(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}

package br.ufmg.engsoft.urna.services.output;

public class CreateQuestionOutput {
	
	boolean created;

	public CreateQuestionOutput(boolean created) {
		this.created = created;
	}

	public boolean isCreated() {
		return created;
	}

}

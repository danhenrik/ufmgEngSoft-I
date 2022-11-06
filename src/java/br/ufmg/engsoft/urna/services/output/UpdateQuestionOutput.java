package br.ufmg.engsoft.urna.services.output;

public class UpdateQuestionOutput {

	boolean updated;

	public UpdateQuestionOutput(boolean updated) {
		this.updated = updated;
	}

	public boolean isUpdated() {
		return updated;
	}
	
}

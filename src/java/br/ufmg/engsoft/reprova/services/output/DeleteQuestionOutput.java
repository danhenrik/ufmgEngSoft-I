package br.ufmg.engsoft.reprova.services.output;

public class DeleteQuestionOutput {

	boolean deleted;

	public DeleteQuestionOutput(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}
	
}

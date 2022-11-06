package br.ufmg.engsoft.urna.services.output;

import br.ufmg.engsoft.urna.model.Question;

public class GetQuestionByIdOutput {

	private Question question;

	public GetQuestionByIdOutput(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}

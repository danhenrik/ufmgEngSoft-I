package br.ufmg.engsoft.urna.services.output;

import br.ufmg.engsoft.urna.entities.Urna;

public class GetQuestionByIdOutput {

	private Urna question;

	public GetQuestionByIdOutput(Urna question) {
		this.question = question;
	}

	public Urna getQuestion() {
		return question;
	}

	public void setQuestion(Urna question) {
		this.question = question;
	}

}

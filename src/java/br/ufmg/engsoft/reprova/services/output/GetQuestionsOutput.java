package br.ufmg.engsoft.reprova.services.output;

import java.util.Collection;

import br.ufmg.engsoft.reprova.model.Question;

public class GetQuestionsOutput {
	
	private Collection<Question> questions;

	public GetQuestionsOutput(Collection<Question> questions) {
		this.questions = questions;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

}

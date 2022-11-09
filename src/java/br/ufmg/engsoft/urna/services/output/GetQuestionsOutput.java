package br.ufmg.engsoft.urna.services.output;

import java.util.Collection;

import br.ufmg.engsoft.urna.entities.Urna;

public class GetQuestionsOutput {
	
	private Collection<Urna> questions;

	public GetQuestionsOutput(Collection<Urna> questions) {
		this.questions = questions;
	}

	public Collection<Urna> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Urna> questions) {
		this.questions = questions;
	}

}

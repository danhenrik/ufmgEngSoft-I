package br.ufmg.engsoft.urna.services.handlers;

import java.util.Collection;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.model.Question;
import br.ufmg.engsoft.urna.services.input.GetQuestionsInput;
import br.ufmg.engsoft.urna.services.interfaces.IGetQuestionsHandler;
import br.ufmg.engsoft.urna.services.output.GetQuestionsOutput;

public class GetQuestionsHandler implements IGetQuestionsHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public GetQuestionsOutput handle(GetQuestionsInput input) {
		Collection<Question> questions = DAO.list(null, input.getAuth() ? null : false);

    return new GetQuestionsOutput(questions);
	}
	
}

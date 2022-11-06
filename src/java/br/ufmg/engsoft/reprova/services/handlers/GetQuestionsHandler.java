package br.ufmg.engsoft.reprova.services.handlers;

import java.util.Collection;

import br.ufmg.engsoft.reprova.database.QuestionDAO;
import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.services.input.GetQuestionsInput;
import br.ufmg.engsoft.reprova.services.interfaces.IGetQuestionsHandler;
import br.ufmg.engsoft.reprova.services.output.GetQuestionsOutput;

public class GetQuestionsHandler implements IGetQuestionsHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public GetQuestionsOutput handle(GetQuestionsInput input) {
		Collection<Question> questions = DAO.list(null, input.getAuth() ? null : false);

    return new GetQuestionsOutput(questions);
	}
	
}

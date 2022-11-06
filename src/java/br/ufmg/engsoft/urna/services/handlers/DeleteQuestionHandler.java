package br.ufmg.engsoft.urna.services.handlers;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.services.input.DeleteQuestionInput;
import br.ufmg.engsoft.urna.services.interfaces.IDeleteQuestionHandler;
import br.ufmg.engsoft.urna.services.output.DeleteQuestionOutput;

public class DeleteQuestionHandler implements IDeleteQuestionHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public DeleteQuestionOutput handle(DeleteQuestionInput input) {
		boolean deleted = DAO.remove(input.getId());
		
    return new DeleteQuestionOutput(deleted);
	}
	
}

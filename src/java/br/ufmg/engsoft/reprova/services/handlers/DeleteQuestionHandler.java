package br.ufmg.engsoft.reprova.services.handlers;

import br.ufmg.engsoft.reprova.database.QuestionDAO;
import br.ufmg.engsoft.reprova.services.input.DeleteQuestionInput;
import br.ufmg.engsoft.reprova.services.interfaces.IDeleteQuestionHandler;
import br.ufmg.engsoft.reprova.services.output.DeleteQuestionOutput;

public class DeleteQuestionHandler implements IDeleteQuestionHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public DeleteQuestionOutput handle(DeleteQuestionInput input) {
		boolean deleted = DAO.remove(input.getId());
		
    return new DeleteQuestionOutput(deleted);
	}
	
}

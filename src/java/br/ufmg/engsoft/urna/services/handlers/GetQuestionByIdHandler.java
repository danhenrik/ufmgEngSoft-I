package br.ufmg.engsoft.urna.services.handlers;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.entities.Urna;
import br.ufmg.engsoft.urna.services.input.GetQuestionByIdInput;
import br.ufmg.engsoft.urna.services.interfaces.IGetQuestionByIdHandler;
import br.ufmg.engsoft.urna.services.output.GetQuestionByIdOutput;

public class GetQuestionByIdHandler implements IGetQuestionByIdHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public GetQuestionByIdOutput handle(GetQuestionByIdInput input) {
		Urna question = DAO.get(input.getId());

    return new GetQuestionByIdOutput(question);
	}

}

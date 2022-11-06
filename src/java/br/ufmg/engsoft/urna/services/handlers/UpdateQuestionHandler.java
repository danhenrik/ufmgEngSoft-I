package br.ufmg.engsoft.urna.services.handlers;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.mime.json.Json;
import br.ufmg.engsoft.urna.model.Question;
import br.ufmg.engsoft.urna.services.input.UpdateQuestionInput;
import br.ufmg.engsoft.urna.services.interfaces.IUpdateQuestionHandler;
import br.ufmg.engsoft.urna.services.output.UpdateQuestionOutput;

public class UpdateQuestionHandler implements IUpdateQuestionHandler {

	private static QuestionDAO DAO =  QuestionDAO.getInstance();
	
	@Override
	public UpdateQuestionOutput handle(UpdateQuestionInput input) {
		Question question;
    try {
      question = new Json()
        .parse(input.getBody(), Question.Builder.class)
        .build();
    
      if(System.getenv("MULTIPLE_CHOICE") == "false" 
        && question.type.equals("multiple_choice")
      ) {
        throw new Error("Suas configurações não te dão acesso a esta funcionalidade.");
      }
      if(System.getenv("OPEN") == "false"
        && question.type.equals("open")
      ) {
        throw new Error("Suas configurações não te dão acesso a esta funcionalidade.");
      }
    }
    catch (Exception e) {
      throw new Error(e);
    }

    boolean updated = DAO.update(input.getId(), question);
    return new UpdateQuestionOutput(updated);
	}
	
}

package br.ufmg.engsoft.urna.services.handlers;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.entities.Urna;
import br.ufmg.engsoft.urna.mime.json.Json;
import br.ufmg.engsoft.urna.services.input.CreateQuestionInput;
import br.ufmg.engsoft.urna.services.interfaces.ICreateQuestionHandler;
import br.ufmg.engsoft.urna.services.output.CreateQuestionOutput;

public class CreateQuestionHandler implements ICreateQuestionHandler {

  private static QuestionDAO DAO =  QuestionDAO.getInstance();

	@Override
	public CreateQuestionOutput handle(CreateQuestionInput input) {
		Urna question;
    try {
      question = new Json()
      .parse(input.getBody(), Urna.Builder.class)
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
  
    boolean created = DAO.add(question);
		
		return new CreateQuestionOutput(created);
	}
	
}

import static org.junit.Assert.*;
import org.junit.Test;

import Voter;

public class VoterTest {
  @Test
  public void testVoterBuilder(){
    String electoralCard = "123456789012";
    String name = "Roberto";
    String state = "MG";

		Voter voter = new Voter.Builder()
        .electoralCard(electoralCard)
        .name(name)
        .state(state)
        .build();

    assertTrue("O título de eleitor nao esta como definido pelo builder.", voter.getElectoralCard().equals(electoralCard));
    assertTrue("O nome do eleitor nao esta como definido pelo builder.", voter.getName().equals(name));
    assertTrue("O estado do eleitor nao esta como definido pelo builder.", voter.getState().equals(state));
	}
}

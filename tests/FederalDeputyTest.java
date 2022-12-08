import static org.junit.Assert.*;
import org.junit.Test;

import scratch.FederalDeputy;

public class FederalDeputyTest {
  @Test
  public void testFederalDeputyBuilder(){
    String name = "Alberto";
    String party = "PV";
    int number = 1324;
    String state = "MG";

		FederalDeputy fedDeputy = new FederalDeputy.Builder()
        .name(name)
        .party(party)
        .number(number)
        .state(state)
        .build();

    assertTrue("O nome do candidato nao esta como definido pelo builder.", fedDeputy.getName().equals(name));
    assertTrue("O partido do candidato nao esta como definido pelo builder.", fedDeputy.getParty().equals(party));
    assertTrue("O numero do candidato nao esta como definido pelo builder.", fedDeputy.getNumber() == number);
    assertTrue("O estado do candidato nao esta como definido pelo builder.", fedDeputy.getState().equals(state));
	}
}
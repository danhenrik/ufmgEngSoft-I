import static org.junit.Assert.*;
import org.junit.Test;

import President;

public class PresidentTest {
  @Test
  public void testPresidentBuilder(){
    String name = "Alberto";
    String party = "PV";
    int number = 12;

		President pres = new President.Builder()
        .name(name)
        .party(party)
        .number(number)
        .build();

    assertTrue("O nome do candidato nao esta como definido pelo builder.", pres.getName().equals(name));
    assertTrue("O partido do candidato nao esta como definido pelo builder.", pres.getParty().equals(party));
    assertTrue("O numero do candidato nao esta como definido pelo builder.", pres.getNumber() == number);
	}
}
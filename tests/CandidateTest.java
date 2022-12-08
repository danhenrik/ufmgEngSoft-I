import static org.junit.Assert.*;
import org.junit.Test;

import Candidate;

public class CandidateTest {
  @Test
  public void testCandidateConstructor(){
    String name = "Alberto";
    String party = "PV";
    int number = 1232;

		Candidate candidate = new Candidate(name, party, number);

    assertTrue("O nome do candidato nao esta como definido pelo construtor.", candidate.getName().equals(name));
    assertTrue("O partido do candidato nao esta como definido pelo construtor.", candidate.getParty().equals(party));
    assertTrue("O numero do candidato nao esta como definido pelo construtor.", candidate.getNumber() == number);
	}
}

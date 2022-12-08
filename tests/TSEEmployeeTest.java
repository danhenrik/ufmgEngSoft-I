import static org.junit.Assert.*;
import org.junit.Test;

import TSEEmployee;
import President;
import FederalDeputy;

public class TSEEmployeeTest {
  @Test
  public void testTSEEmployeeBuilder(){
    String user = "Alberto";
    String password = "123";

		TSEEmployee tseEmployee = new TSEEmployee.Builder()
      .user(user)
      .password(password)
      .build();

    assertTrue("O nome do empregado do tse nao esta como definido pelo builder.", tseEmployee.getUser().equals(user));
	}

  @Test
  public void addCandidateTest(){
    String user = "Alberto";
    String password = "123";

		TSEEmployee tseEmployee = new TSEEmployee.Builder()
      .user(user)
      .password(password)
      .build();
    
    String electionPassword = "password";

    currentElection = new Election.Builder()
      .password(electionPassword)
      .build();

    President presidentCandidate1 = new President.Builder().name("João").number(123).party("PDS1").build();
    FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1");
    
    tseEmployee.addCandidate(presidentCandidate1, currentElection, electionPassword);
    tseEmployee.addCandidate(federalDeputyCandidate1, currentElection, electionPassword);

    assertTrue("O candidato a presidente não foi adicionado corretamente.", currentElection.getPresidentByNumber(123).getName().equals("João"));
    assertTrue("O candidato a deputado federal não foi adicionado corretamente.", currentElection.getPresidentByNumber(12345).getName().equals("Carlos"));
  }

  @Test
  public void removeCandidateTest(){
    String user = "Alberto";
    String password = "123";

		TSEEmployee tseEmployee = new TSEEmployee.Builder()
        .user(user)
        .password(password)
        .build();
    
    String electionPassword = "password";

    currentElection = new Election.Builder()
      .password(electionPassword)
      .build();

    President presidentCandidate1 = new President.Builder().name("João").number(123).party("PDS1").build();
    FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1");
    
    tseEmployee.addCandidate(presidentCandidate1, currentElection, electionPassword);
    tseEmployee.addCandidate(federalDeputyCandidate1, currentElection, electionPassword);

    tseEmployee.removeCandidate(presidentCandidate1, currentElection, electionPassword);
    tseEmployee.removeCandidate(federalDeputyCandidate1, currentElection, electionPassword);


    assertFalse("O candidato a presidente não foi removido corretamente.", currentElection.getPresidentCandidates.containsKey(123));
    assertFalse("O candidato a deputado federal não foi removido corretamente.", currentElection.getFederalDeputyCandidates.containsKey(12345));
  }
}
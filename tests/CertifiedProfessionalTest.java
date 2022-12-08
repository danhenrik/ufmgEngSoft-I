import static org.junit.Assert.*;
import org.junit.Test;

import CertifiedProfessional;
import Election;
import President;
import FederalDeputy;

public class CertifiedProfessionalTest {
  @Test
  public void testCertifiedProfessionalBuilder(){
    String user = "Alberto";
    String password = "123";

		CertifiedProfessional certifiedProfessional = new CertifiedProfessional.Builder()
      .user(user)
      .password(password)
      .build();

    assertTrue("O nome do empregado do tse nao esta como definido pelo builder.", certifiedProfessional.getUser().equals(user));
	}

  @Test
  public void testStartSession() {
    String user = "Alberto";
    String password = "123";
		CertifiedProfessional certifiedProfessional = new CertifiedProfessional.Builder()
      .user(user)
      .password(password)
      .build();

    String electionPassword = "123";
    Election election = new Election.Builder()
      .password(electionPassword)
      .build();    

    certifiedProfessional.startSession(election, electionPassword);

    assertTrue("A eleicao nao foi iniciada como estperado.", election.getStatus() == true);
  }

  @Test
  public void testStartSessionWrong() {
    String user = "Alberto";
    String password = "123";
		CertifiedProfessional certifiedProfessional = new CertifiedProfessional.Builder()
      .user(user)
      .password(password)
      .build();

    String electionPassword = "123";
    Election election = new Election.Builder()
      .password(electionPassword)
      .build();    

    Exception exception = assertThrows(RuntimeException.class, () -> {
      certifiedProfessional.startSession(election, "12");
    });

    String expectedMessage = "Senha inválida";
    String actualMessage = exception.getMessage();

    assertTrue("A exception de senha incorreta não foi lançada como esperado.", expectedMessage.equals(actualMessage));
  }

  @Test
  public void testEndSession() {
    String user = "Alberto";
    String password = "123";
		CertifiedProfessional certifiedProfessional = new CertifiedProfessional.Builder()
      .user(user)
      .password(password)
      .build();

    String electionPassword = "123";
    Election election = new Election.Builder()
      .password(electionPassword)
      .build();    

    certifiedProfessional.startSession(election, electionPassword);
    certifiedProfessional.endSession(election, electionPassword);

    assertTrue("A eleicao nao foi finalizadada como estperado.", election.getStatus() == false);
  }

  @Test
  public void testEndSessionWrong() {
    String user = "Alberto";
    String password = "123";
		CertifiedProfessional certifiedProfessional = new CertifiedProfessional.Builder()
      .user(user)
      .password(password)
      .build();

    String electionPassword = "123";
    Election election = new Election.Builder()
      .password(electionPassword)
      .build();    

    certifiedProfessional.startSession(election, electionPassword);
    Exception exception = assertThrows(RuntimeException.class, () -> {
      certifiedProfessional.endSession(election, "12");
    });

    String expectedMessage = "Senha inválida";
    String actualMessage = exception.getMessage();

    assertTrue("A exception de senha incorreta não foi lançada como esperado.", expectedMessage.equals(actualMessage));
  }

  @Test
  public void testShowFinalResult(){
    
    String electionPassword = "password";

    currentElection = new Election.Builder()
      .password(electionPassword)
      .build();


    Voter v1 = new Voter.Builder().name("v1").electoralCard("123456789012").state("MG").build();
    Voter v2 = new Voter.Builder().name("v2").electoralCard("223456789022").state("MG").build();
    Voter v3 = new Voter.Builder().name("v3").electoralCard("333456789033").state("MG").build();

    President presidentCandidate1 = new President.Builder().name("João").number(123).party("PDS1").build();
    currentElection.addPresidentCandidate(presidentCandidate1, electionPassword);
    President presidentCandidate2 = new President.Builder().name("Maria").number(124).party("ED").build();
    currentElection.addPresidentCandidate(presidentCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate1, electionPassword);
    FederalDeputy federalDeputyCandidate2 = new FederalDeputy.Builder().name("Cleber").number(54321).party("PDS2")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate3 = new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate3, electionPassword);

    currentElection.start(electionPassword);

    v1.vote(123, currentElection, "President", false);
    v2.vote(123, currentElection, "President", false);
    v3.vote(124, currentElection, "President", false);
    v1.vote(12345, currentElection, "FederalDeputy", false);
    v1.vote(0000, currentElection, "FederalDeputy", false);
    v2.vote(12345, currentElection, "FederalDeputy", false);
    v2.vote(54321, currentElection, "FederalDeputy", false);
    v3.vote(12345, currentElection, "FederalDeputy", false);
    v3.vote(0, currentElection, "FederalDeputy", true);

    String ans = "";
    ans += "Resultado da eleicao:\n";
    ans += "    Votos presidente:\n";
    ans += "    Total: 3\n";
    ans += "    Votos nulos: 0 (0,00%)\n";
    ans += "    Votos brancos: 0 (0,00%)\n";
    ans += "          Numero - Partido - Nome  - Votos  - % dos votos totais\n";
    ans += "          123 - PDS1 - João - 2 - 66,67%\n";
    ans += "          124 - ED - Maria - 1 - 33,33%\n";
    ans += "  \n";
    ans += "  \n";
    ans += "    Presidente eleito:\n";
    ans += "    João do PDS1 com 66,67% dos votos\n";
    ans += "  \n";
    ans += "  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
    ans += "  \n";
    ans += "  \n";
    ans += "  \n";
    ans += "    Votos deputado federal:\n";
    ans += "    Votos nulos: 2 (33,33%)\n";
    ans += "    Votos brancos: 0 (0,00%)\n";
    ans += "    Total: 6\n";
    ans += "          Numero - Partido - Nome - Estado - Votos - % dos votos totais\n";
    ans += "          12345 - PDS1 - MG - Carlos - 3 - 50,00%\n";
    ans += "          54321 - PDS2 - MG - Cleber - 1 - 16,67%\n";
    ans += "          11211 - IHC - MG - Sofia - 0 - 0,00%\n";
    ans += "  \n";
    ans += "  \n";
    ans += "    Deputados eleitos:\n";
    ans += "    1º Carlos do PDS1 com 50,00% dos votos\n";
    ans += "    2º Cleber do PDS2 com 16,67% dos votos";
    currentElection.finish(electionPassword);
    assertTrue("Resultado Incorreto para eleição criada", currentElection.getResults(electionPassword).contains(ans));
	}
}
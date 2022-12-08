package scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import org.junit.Test;
public class ElectionTest {
  @Test
  public void isValidTest(){
    String electionPassword = "password";

    currentElection = new Election.Builder()
      .password(electionPassword)
      .build();

    assertTrue("A senha da eleição deveria ter sido validada corretamente.", currentElection.isValid(electionPassword));
    assertFalse("O candidato a presidente não foi adicionado corretamente.", currentElection.isValid("wrong"));
  }
}

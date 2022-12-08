import static org.junit.Assert.*;
import org.junit.Test;

import TSEProfessional;

public class TSEProfessionalTest {
  @Test
  public void testTSEProfessionalConstructor(){
    String user = "Alberto";
    String password = "123";

		TSEProfessional tseProfessional = new TSEProfessional(user, password);

    assertTrue("O nome do usuario do tse nao esta como definido pelo builder.", tseProfessional.getUser().equals(user));
	}
}

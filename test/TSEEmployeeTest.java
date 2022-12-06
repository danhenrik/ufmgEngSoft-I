package scratch;

import static org.junit.Assert.*;
import org.junit.Test;

import scratch.TSEEmployee;

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
}
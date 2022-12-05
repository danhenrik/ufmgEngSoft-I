package br.ufmg.engsoft.urna.scratch;

import java.util.UUID;

public class TSEProfessional {
  protected final UUID id;

  protected final String user;

  protected final String password;

  public TSEProfessional(
      String user,
      String password) {
    this.id = UUID.randomUUID();
    this.user = user;
    this.password = password;
  }
}

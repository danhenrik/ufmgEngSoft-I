package br.ufmg.engsoft.urna.scratch;

import java.util.UUID;

public class Voter {
  protected final UUID id;

  protected final String electoralCard;

  protected final String name;

  public static class Builder {
    protected String electoralCard;
    protected String name;

    public Builder electoralCard(String electoralCard) {
      this.electoralCard = electoralCard;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Voter build() {
      if (electoralCard == null)
        throw new IllegalArgumentException("electoralCard mustn't be null");

      if (electoralCard.isEmpty())
        throw new IllegalArgumentException("electoralCard mustn't be empty");

      if (name == null)
        throw new IllegalArgumentException("name mustn't be null");

      if (name.isEmpty())
        throw new IllegalArgumentException("name mustn't be empty");

      return new Voter(
          this.electoralCard,
          this.name);
    }
  }

  protected Voter(
      String electoralCard,
      String name) {
    this.id = UUID.randomUUID();
    this.electoralCard = electoralCard;
    this.name = name;
  }
}

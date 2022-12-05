package br.ufmg.engsoft.urna.scratch;

import java.util.UUID;

public class Candidate {
  protected final UUID id;

  public final String name;

  public final String party;

  protected final int number;

  protected int numVotes;

  public Candidate(
      String name,
      String party,
      int number) {

    if (name == null)
      throw new IllegalArgumentException("name mustn't be null");

    if (name.isEmpty())
      throw new IllegalArgumentException("name mustn't be empty");

    if (party == null)
      throw new IllegalArgumentException("party mustn't be empty");

    if (party.isEmpty())
      throw new IllegalArgumentException("party mustn't be empty");

    if (number <= 0)
      throw new IllegalArgumentException("nunmero must be greater or equal to 1");

    this.id = UUID.randomUUID();
    this.name = name;
    this.party = party;
    this.number = number;
    this.numVotes = 0;
  }
}

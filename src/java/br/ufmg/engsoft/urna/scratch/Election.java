package br.ufmg.engsoft.urna.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: Validar antes das operações sensíveis se a senha passada é correta
// TODO: Montar a lógica da main (urna via terminal)
// TODO: Refinar/finalizar as integrações entre as classes
// TODO: Validar tudo

public class Election {
  private final String password;

  private boolean status;

  private int nullVotes;

  private int protestVote;

  // Na prática guardaria uma hash do eleitor
  private Map<Voter, Integer> votersPresident = new HashMap<Voter, Integer>();

  // Na prática guardaria uma hash do eleitor
  private Map<Voter, Integer> votersFederalDeputy = new HashMap<Voter, Integer>();

  // private Map<President, Integer> presidentsVotes = new HashMap<President,
  // Integer>();

  // private Map<FederalDeputy, Integer> federalDeputysVotes = new
  // HashMap<FederalDeputy, Integer>();

  private Map<Integer, President> presidentCandidates = new HashMap<Integer, President>();

  private Map<String, FederalDeputy> federalDeputyCandidates = new HashMap<String, FederalDeputy>();

  // private Map<Voter, Integer> federalDeputyVoters = new HashMap<Voter,
  // Integer>();

  // public int getProtestVotes() {
  // return protestVote;
  // }

  public static class Builder {
    protected String password;

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Election build() {
      if (password == null)
        throw new IllegalArgumentException("password mustn't be null");

      if (password.isEmpty())
        throw new IllegalArgumentException("password mustn't be empty");

      return new Election(
          this.password);
    }
  }

  protected Election(
      String password) {
    this.password = password;
    this.status = false;
    this.nullVotes = 0;
    this.protestVote = 0;
  }

  public void computeVote(Candidate candidate, Voter voter) {
    if (candidate instanceof President) {
      if (this.votersPresident.get(voter) >= 1)
        throw new IllegalAccessError("Cannot vote more than once");
      candidate.numVotes++;
      votersPresident.put(voter, 1);
      return;
    }
    if (candidate instanceof FederalDeputy) {
      if (this.votersFederalDeputy.get(voter) >= 2)
        throw new IllegalAccessError("Cannot vote more than once");
      candidate.numVotes++;
      votersFederalDeputy.put(voter, votersFederalDeputy.get(voter) + 1);
      return;
    }
  };

  public void computeNullVote(String type, Voter voter) {
    if (type.equals("President")) {
      if (this.votersPresident.get(voter) >= 1)
        throw new IllegalAccessError("Cannot vote more than once");
      this.nullVotes++;
      votersPresident.put(voter, 1);
    } else if (type.equals("FederalDeputy")) {
      if (this.votersFederalDeputy.get(voter) >= 2)
        throw new IllegalAccessError("Cannot vote more than once");
      this.nullVotes++;
      votersFederalDeputy.put(voter, votersFederalDeputy.get(voter) + 1);
    }
  }

  // public int getNullVotes() {
  // return nullVotes;
  // }

  public void computeProtestVote(String type, Voter voter) {
    if (type.equals("President")) {
      if (this.votersPresident.get(voter) >= 1)
        throw new IllegalAccessError("Cannot vote more than once");
      this.protestVote++;
      votersPresident.put(voter, 1);
    } else if (type.equals("FederalDeputy")) {
      if (this.votersFederalDeputy.get(voter) >= 2)
        throw new IllegalAccessError("Cannot vote more than once");
      this.protestVote++;
      votersFederalDeputy.put(voter, votersFederalDeputy.get(voter) + 1);
    }
  }

  public boolean getStatus() {
    return status;
  }

  public void start() {
    this.status = true;
  }

  public void finish() {
    this.status = false;
  }

  public President getPresidentByNumber(int number) {
    return this.presidentCandidates.get(number);
  }

  public void addPresidentCandidate(President candidate) {
    this.presidentCandidates.put(candidate.number, candidate);
  }

  public FederalDeputy getFederalDeputyByNumber(int number, String state) {
    return this.federalDeputyCandidates.get(state + number);
  }

  public void addFederalDeputyCandidate(FederalDeputy candidate) {
    this.federalDeputyCandidates.put(candidate.state + candidate.number, candidate);
  }

  public String showResults() {
    var presidentRank = new ArrayList<President>();
    var federalDeputyRank = new ArrayList<FederalDeputy>();

    var builder = new StringBuilder();

    builder.append("Resultado da eleicao:\n");
    builder.append("  Votos brancos: " + this.nullVotes + "\n");
    builder.append("  Votos nulos: " + this.protestVote + "\n");

    int totalVotesP = 0;
    for (Map.Entry<Integer, President> candidateEntry : presidentCandidates.entrySet()) {
      President candidate = candidateEntry.getValue();
      totalVotesP += candidate.numVotes;
      presidentRank.add(candidate);
    }

    int totalVotesFD = 0;
    for (Map.Entry<String, FederalDeputy> candidateEntry : federalDeputyCandidates.entrySet()) {
      FederalDeputy candidate = candidateEntry.getValue();
      totalVotesFD += candidate.numVotes;
      federalDeputyRank.add(candidate);
    }

    var sortedFederalDeputyRank = federalDeputyRank.stream()
        .sorted((o1, o2) -> o1.numVotes == o2.numVotes ? 0 : o1.numVotes < o2.numVotes ? -1 : 1)
        .collect(Collectors.toList());

    var sortedPresidentRank = presidentRank.stream()
        .sorted((o1, o2) -> o1.numVotes == o2.numVotes ? 0 : o1.numVotes < o2.numVotes ? -1 : 1)
        .collect(Collectors.toList());

    builder.append("  Votos presidente:\n");
    builder.append("  Total: " + totalVotesP + "\n");
    builder.append("      Numero \tPartido \tNome \tVotos \t% dos votos totais\n");
    for (President candidate : sortedPresidentRank) {
      builder.append("    " + candidate.number + " \t" + candidate.party + " \t" + candidate.name + " \t"
          + candidate.numVotes + " \t" + candidate.numVotes / totalVotesP * 100
          + "%\n");
    }

    builder.append("\n\n  Votos deputado federal:\n");
    builder.append("  Total: " + totalVotesP + "\n");
    builder.append("      Numero \tPartido \tNome \tEstado \tVotos \t% dos votos totais\n");
    for (FederalDeputy candidate : sortedFederalDeputyRank) {
      builder.append(
          "    " + candidate.number + " \t" + candidate.party + " \t" + candidate.state + " \t" + candidate.name + " \t"
              + candidate.numVotes + " \t" + candidate.numVotes / totalVotesP * 100
              + "%\n");
    }

    President electPresident = sortedPresidentRank.get(0);
    builder.append("\n\n  Presidente eleito:\n");
    builder.append("  " + electPresident.name + " do " + electPresident.party + "\n");

    FederalDeputy firstDeputy = sortedFederalDeputyRank.get(0);
    FederalDeputy secondDeputy = sortedFederalDeputyRank.get(1);
    builder.append("\n\n  Deputados eleitos:\n");
    builder.append("  " + firstDeputy.name + " do " + firstDeputy.party + " com "
        + firstDeputy.numVotes / totalVotesFD * 100 + "% dos votos\n");
    builder.append("  " + secondDeputy.name + " do " + secondDeputy.party + " com "
        + secondDeputy.numVotes / totalVotesFD * 100 + "% dos votos\n");

    return builder.toString();
  }
}

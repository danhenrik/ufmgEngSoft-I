package br.ufmg.engsoft.urna.entities;

import java.util.Map;
import java.util.HashMap;

public class Eleicao {
  private Urna urna;

  private boolean statusSessao;

  private int votosBrancos;

  private int votosNulos;

  private Map<Presidente, Integer> votosPresidente = new HashMap<Presidente, Integer>();

  private Map<DeputadoFederal, Integer> votosDeputado = new HashMap<DeputadoFederal, Integer>();

  private Map<Integer, Presidente> candidatosPresidente = new HashMap<Integer, Presidente>();

  private Map<String, DeputadoFederal> candidatosDeputado = new HashMap<String, DeputadoFederal>();

  public static class Builder {
    public Eleicao build() {
      return new Eleicao();
    }
  }

  protected Eleicao() {
    this.urna = null;
    this.statusSessao = false;
    this.votosBrancos = 0;
    this.votosNulos = 0;
  }

  public int getVotosBrancos() {
    return votosBrancos;
  }

  public void computarVotoBranco() {
    this.votosNulos += 1;
  }

  public int getVotosNulos() {
    return votosNulos;
  }

  public void computarVotoNulo() {
    this.votosNulos += 1;
  }

  public boolean getStatusSessao() {
    return statusSessao;
  }

  public void iniciar() {
    this.statusSessao = true;
  }

  public void finalizar() {
    this.statusSessao = false;
  }

  public Presidente getPresidenteByNumero(int numero) {
    return this.candidatosPresidente.get(numero);
  }

  public void cadastrarCandidatoPresidente(Presidente candidato) {
    this.candidatosPresidente.put(candidato.numero, candidato);
    this.votosPresidente.put(candidato, 0);
  }

  public DeputadoFederal getDeputadoByNumero(int numero, String estado) {
    return this.candidatosDeputado.get(estado + numero);
  }

  public void cadastrarCandidatoDeputadoFederal(DeputadoFederal candidato) {
    this.candidatosDeputado.put(candidato.estado + candidato.numero, candidato);
    this.votosDeputado.put(candidato, 0);
  }

  public void computarVotoPresidente(Presidente candidato) {
    if (this.votosPresidente.get(candidato) == null)
      throw new IllegalAccessError("Candidato is either invalid or not included in this election");
    this.votosPresidente.put(candidato, this.votosPresidente.get(candidato) + 1);
  }

  public void computarVotoDeputado(DeputadoFederal candidato) {
    if (this.votosDeputado.get(candidato) == null)
      throw new IllegalAccessError("Candidato is either invalid or not included in this election");
    this.votosDeputado.put(candidato, this.votosDeputado.get(candidato) + 1);
  }

  public void setUrna(Urna urna) {
    if (this.urna != null)
      throw new IllegalAccessError("There's already a urna for this election");

    this.urna = urna;
    this.urna.eleicao = this;
  }

  public void resetUrna() {
    this.urna = null;
  }

  public String exibirResultado() {
    var builder = new StringBuilder();

    builder.append("Resultado da eleicao:\n");
    builder.append("  Votos brancos: " + this.votosBrancos + "\n");
    builder.append("  Votos nulos: " + this.votosNulos + "\n");
    builder.append("  Votos presidente:\n");

    Map.Entry<Presidente, Integer> maxP = null;
    int totalVotesP = 0;
    for (Map.Entry<Presidente, Integer> candidateEntry : votosPresidente.entrySet()) {
      if (maxP == null || candidateEntry.getValue() > maxP.getValue())
        maxP = candidateEntry;
      Presidente candidate = candidateEntry.getKey();
      int votosCandidate = votosPresidente.get(candidate);
      totalVotesP += votosCandidate;
      candidatosPresidente.get(candidate.numero).numVotos = votosCandidate;
    }

    builder.append("  Votos totais: " + totalVotesP + "\n");
    builder.append("      Numero \tPartido \tNome \t%\n");
    for (Map.Entry<Integer, Presidente> candidateEntry : candidatosPresidente.entrySet()) {
      Presidente candidate = candidateEntry.getValue();
      builder.append(
          "    " + candidate.numero + " \t" + candidate.partido + " \t" + candidate.nome + " \t"
              + candidate.numVotos / totalVotesP * 100 + "%\n");
    }

    builder.append("\n\n  Votos deputado:\n");

    Map.Entry<DeputadoFederal, Integer> maxD1 = null;
    int totalVotesD = 0;
    for (Map.Entry<DeputadoFederal, Integer> candidateEntry : votosDeputado.entrySet()) {
      if (maxD1 == null || candidateEntry.getValue() > maxD1.getValue())
        maxD1 = candidateEntry;
      DeputadoFederal candidate = candidateEntry.getKey();
      int votosCandidate = votosDeputado.get(candidate);
      totalVotesD += votosCandidate;
      candidatosDeputado.get(candidate.estado + candidate.numero).numVotos = votosCandidate;
    }

    builder.append("  Votos totais: " + totalVotesD + "\n");
    builder.append("      Numero \tEstado \tPartido \tNome \t%\n");
    for (Map.Entry<String, DeputadoFederal> candidateEntry : candidatosDeputado.entrySet()) {
      DeputadoFederal candidate = candidateEntry.getValue();
      builder.append(
          "    " + candidate.numero + " \t" + candidate.estado + " \t" + candidate.partido + " \t" + candidate.nome
              + " \t"
              + candidate.numVotos / totalVotesP * 100 + "%\n");
    }

    votosDeputado.remove(maxD1.getKey());

    Map.Entry<DeputadoFederal, Integer> maxD2 = null;
    for (Map.Entry<DeputadoFederal, Integer> candidateEntry : votosDeputado.entrySet()) {
      if (maxD2 == null || candidateEntry.getValue() > maxD2.getValue())
        maxD2 = candidateEntry;
    }

    builder.append("\n\n Presidente eleito " + maxP.getKey().nome + " do partido " + maxP.getKey().partido + " com "
        + maxP.getKey().numVotos + " votos\n");

    builder.append("\n\n Deputados eleitos\n");
    builder.append("1. " + maxD1.getKey().nome + " do partido " + maxD1.getKey().partido + "(" + maxD1.getKey().estado
        + ") com "
        + maxD1.getKey().numVotos + " votos\n");
    builder.append("2. " + maxD2.getKey().nome + " do partido " + maxD2.getKey().partido + "(" + maxD2.getKey().estado
        + ") com "
        + maxD2.getKey().numVotos + " votos\n");

    return builder.toString();
  }
}
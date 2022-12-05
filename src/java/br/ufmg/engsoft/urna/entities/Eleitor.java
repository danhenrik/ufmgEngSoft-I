package br.ufmg.engsoft.urna.entities;

import java.util.UUID;

public class Eleitor {
  public final UUID id;

  public final String tituloDeEleitor;

  public final String nome;

  private final String estado;

  private int votes;

  public final Boolean votar(Urna urna, int numeroCandidato, Boolean nulo) {
    Boolean voted = false;
    // Limita a 3 votos (1 presidente e 2 deputado)
    if (votes < 3) {
      voted = urna.registrarVoto(numeroCandidato, this.estado, nulo);
    }
    if (voted)
      this.votes++;
    return voted;
  }

  protected Eleitor(
      String tituloDeEleitor,
      String nome,
      String estado) {

    if (tituloDeEleitor == null)
      throw new IllegalArgumentException("O título de eleitor não pode ser nulo");

    this.id = UUID.randomUUID();
    this.tituloDeEleitor = tituloDeEleitor;
    this.nome = nome;
    this.estado = estado;
    this.votes = 0;
  }

  public static class Builder {
    protected String tituloDeEleitor;
    protected String nome;
    protected String estado;

    public Builder tituloDeEleitor(String tituloDeEleitor) {
      this.tituloDeEleitor = tituloDeEleitor;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder estado(String estado) {
      this.estado = estado;
      return this;
    }

    public Eleitor build() {
      if (nome == null)
        throw new IllegalArgumentException("theme mustn't be null");

      if (nome.isEmpty())
        throw new IllegalArgumentException("theme mustn't be empty");

      return new Eleitor(
          this.tituloDeEleitor,
          this.nome,
          this.estado);
    }
  }
}

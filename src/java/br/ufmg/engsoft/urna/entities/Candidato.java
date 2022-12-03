package br.ufmg.engsoft.urna.entities;

import java.util.UUID;

/**
 * O tipo Candidato.
 */
public class Candidato {

  /**
   * O ID do Candidato.
   * Quando null, o ID sera automaticamente gerado pelo banco de dados.
   */
  protected final UUID id;

  /**
   * O nome do Candidato.
   */
  public final String nome;

  /**
   * O partido do Candidato.
   */
  public final String partido;

  /**
   * O número de votos recebidos pelo Candidato.
   */
  protected final int numVotos;

  /**
   * Builder de Candidato.
   */
  public static class Builder {
    protected String nome;
    protected String partido;

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder partido(String partido) {
      this.partido = partido;
      return this;
    }

    /**
     * Constrói o Candidato.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public Candidato build() {
      if (nome == null)
        throw new IllegalArgumentException("nome mustn't be null");

      if (nome.isEmpty())
        throw new IllegalArgumentException("nome mustn't be empty");

      if (partido == null)
        throw new IllegalArgumentException("partido mustn't be null");

      if (partido.isEmpty())
        throw new IllegalArgumentException("partido mustn't be empty");

      return new Candidato(
          this.nome,
          this.partido);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected Candidato(
      String nome,
      String partido) {
    this.id = UUID.randomUUID();
    this.nome = nome;
    this.partido = partido;
    this.numVotos = 0;
  }

  /**
   * Comparador de igualdade.
   * Por mais que a classe tenha um id sua comparação é feita em todos os campos.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof Candidato))
      return false;

    var candidato = (Candidato) obj;

    return this.id.equals(candidato.id)
        && this.partido.equals(candidato.partido)
        && this.nome.equals(candidato.nome)
        && this.numVotos == candidato.numVotos;
  }

  /**
   * Converte um Candidato para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("Candidato:\n");
    builder.append("  id: " + this.id + "\n");
    builder.append("  nome: " + this.nome + "\n");
    builder.append("  partido: " + this.partido + "\n");
    builder.append("  numVotos: " + this.numVotos + "\n");

    return builder.toString();
  }
}

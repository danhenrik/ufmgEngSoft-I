package br.ufmg.engsoft.urna.entities;

/**
 * O tipo Candidato.
 */
public class Candidato {

  /**
   * O ID do Candidato.
   * Quando null, o ID sera automaticamente gerado pelo banco de dados.
   */
  private final String id;

  /**
   * O nome do Candidato.
   */
  private final String nome;

  /**
   * O número de votos recebidos pelo Candidato.
   */
  protected final int numVotos;

  /**
   * Builder de Candidato.
   */
  public static class Builder {
    protected String id;
    protected String nome;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
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

      return new Candidato(
          this.id,
          this.nome);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected Candidato(
      String id,
      String nome) {
    this.id = id;
    this.nome = nome;
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
    builder.append("  numVotos: " + this.numVotos + "\n");

    return builder.toString();
  }
}

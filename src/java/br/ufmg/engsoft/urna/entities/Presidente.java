package br.ufmg.engsoft.urna.entities;

/**
 * O tipo Presidente.
 */
public class Presidente extends Candidato {

  /**
   * Número do Presidente.
   */
  protected final int numero;

  /**
   * Builder de Presidente.
   */
  public static class Builder {
    protected String nome;
    protected String partido;
    protected int numero;

    public Builder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public Builder partido(String partido) {
      this.partido = partido;
      return this;
    }

    public Builder numero(int numero) {
      this.numero = numero;
      return this;
    }

    /**
     * Constrói o Presidente.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public Presidente build() {
      if (numero <= 0)
        throw new IllegalArgumentException("numero mustn't be less than or equal to 0");

      if (nome == null)
        throw new IllegalArgumentException("nome mustn't be null");

      if (nome.isEmpty())
        throw new IllegalArgumentException("nome mustn't be empty");

      if (partido == null)
        throw new IllegalArgumentException("partido mustn't be null");

      if (partido.isEmpty())
        throw new IllegalArgumentException("partido mustn't be empty");

      return new Presidente(
          this.nome,
          this.partido,
          this.numero);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected Presidente(
      String nome,
      String partido,
      int numero) {
    super(nome, partido);
    this.numero = numero;
  }

  /**
   * Comparador de igualdade.
   * Por mais que a classe tenha um id sua comparação é feita em todos os campos.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof Presidente))
      return false;

    var presidente = (Presidente) obj;

    return this.numero == presidente.numero;
  }

  /**
   * Converte um Presidente para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("Deputado Federal:\n");
    builder.append("  id: " + super.id + "\n");
    builder.append("  numero: " + this.numero + "\n");
    builder.append("  partido: " + this.partido + "\n");
    builder.append("  numVotos: " + super.numVotos + "\n");

    return builder.toString();
  }
}
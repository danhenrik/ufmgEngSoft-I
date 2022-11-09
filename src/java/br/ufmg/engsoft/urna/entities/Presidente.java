package br.ufmg.engsoft.urna.entities;

/**
 * O tipo Presidente.
 */
public class Presidente extends Candidato {

  /**
   * Número do Presidente.
   */
  private final int numero;

  /**
   * Builder de Presidente.
   */
  public static class Builder {
    protected String id;
    protected String nome;
    protected int numero;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder nome(String nome) {
      this.nome = nome;
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

      return new Presidente(
          this.id,
          this.nome,
          this.numero);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected Presidente(
      String id,
      String nome,
      int numero) {
    super(id, nome);
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
    builder.append("  id: " + super.numVotos + "\n");
    builder.append("  numero: " + this.numero + "\n");

    return builder.toString();
  }
}
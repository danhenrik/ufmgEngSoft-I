package br.ufmg.engsoft.urna.entities;

/**
 * O tipo DeputadoFederal.
 */
public class DeputadoFederal extends Candidato {

  /**
   * Número do DeputadoFederal.
   */
  protected final int numero;

  /**
   * Estado do DeputadoFederal.
   */
  protected final String estado;

  /**
   * Builder de DeputadoFederal.
   */
  public static class Builder {
    protected String nome;
    protected String partido;
    protected int numero;
    protected String estado;

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

    public Builder estado(String estado) {
      this.estado = estado;
      return this;
    }

    /**
     * Constrói o DeputadoFederal.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public DeputadoFederal build() {
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

      if (estado == null)
        throw new IllegalArgumentException("estado mustn't be null");

      if (estado.isEmpty())
        throw new IllegalArgumentException("estado mustn't be empty");

      return new DeputadoFederal(
          this.nome,
          this.partido,
          this.numero,
          this.estado);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected DeputadoFederal(
      String nome,
      String partido,
      int numero,
      String estado) {
    super(nome, partido);
    this.numero = numero;
    this.estado = estado;
  }

  /**
   * Comparador de igualdade.
   * Por mais que a classe tenha um id sua comparação é feita em todos os campos.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof DeputadoFederal))
      return false;

    var deputadoFederal = (DeputadoFederal) obj;

    return this.numero == deputadoFederal.numero
        && this.estado.equals(deputadoFederal.estado);
  }

  /**
   * Converte um DeputadoFederal para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("Deputado Federal:\n");
    builder.append("  id: " + super.id + "\n");
    builder.append("  numero: " + this.numero + "\n");
    builder.append("  partido: " + this.partido + "\n");
    builder.append("  estado: " + this.estado + "\n");
    builder.append("  numVotos: " + super.numVotos + "\n");

    return builder.toString();
  }
}
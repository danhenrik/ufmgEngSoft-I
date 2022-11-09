package br.ufmg.engsoft.urna.entities;

/**
 * O tipo DeputadoFederal.
 */
public class DeputadoFederal extends Candidato {

  /**
   * Número do Candidato.
   */
  private final int numero;

  /**
   * Estado do Candidato.
   */
  private final String estado;

  /**
   * Builder de DeputadoFederal.
   */
  public static class Builder {
    protected String id;
    protected String nome;
    protected int numero;
    protected String estado;

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

    public Builder estado(String estado) {
      this.estado = estado;
      return this;
    }

    /**
     * Constrói o candidato.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public DeputadoFederal build() {
      if (numero <= 0)
        throw new IllegalArgumentException("numero mustn't be less than or equal to 0");

      if (estado == null)
        throw new IllegalArgumentException("estado mustn't be null");

      if (estado.isEmpty())
        throw new IllegalArgumentException("estado mustn't be empty");

      if (estado.length() != 2)
        throw new IllegalArgumentException("estado mustn't be bigger than 2 characters");

      return new DeputadoFederal(
          this.id,
          this.nome,
          this.numero,
          this.estado);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected DeputadoFederal(
      String id,
      String nome,
      int numero,
      String estado) {
    super(id, nome);
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

    return this.numero == deputadoFederal.numero &&
        this.estado.equals(deputadoFederal.estado);
  }

  /**
   * Converte um DeputadoFederal para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("Deputado Federal:\n");
    builder.append("  id: " + super.numVotos + "\n");
    builder.append("  numero: " + this.numero + "\n");
    builder.append("  estado: " + this.estado + "\n");

    return builder.toString();
  }
}
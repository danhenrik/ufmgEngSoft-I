package br.ufmg.engsoft.urna.entities;

// import java.util.Map;

// import java.util.Objects;
// import java.util.HashMap;

/**
 * A semester class.
 * The semester is composed of an year and a reference (1 or 2).
 */
public class Eleitor {

  /**
   * O ID do Eleitor.
   * Quando null, o ID sera automaticamente gerado pelo banco de dados.
   */
  private final String id;

  /**
   * O Titulo de eleitor de um eleitor.
   */
  private final String tituloDeEleitor;

  /**
   * O nome de um eleitor.
   */
  private final String nome;

  /**
   * O estado de eleitor de um eleitor.
   */
  private final String estado;

  public final void votar(int numeroCandidato) {

  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder
   */
  protected Eleitor(String id, String tituloDeEleitor, String nome, String estado) {
    if (tituloDeEleitor == null)
      throw new IllegalArgumentException("O título de eleitor não pode ser nulo");

    this.id = id;
    this.tituloDeEleitor = tituloDeEleitor;
    this.nome = nome;
    this.estado = estado;
  }

  /**
   * Builder de Candidato.
   */
  public static class Builder {
    protected String id;
    protected String tituloDeEleitor;
    protected String nome;
    protected String estado;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

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

    /**
     * Constrói o Candidato.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public Eleitor build() {
      if (nome == null)
        throw new IllegalArgumentException("theme mustn't be null");

      if (nome.isEmpty())
        throw new IllegalArgumentException("theme mustn't be empty");

      return new Eleitor(
          this.id,
          this.tituloDeEleitor,
          this.nome,
          this.estado);
    }

  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof Eleitor))
      return false;

    var eleitor = (Eleitor) obj;

    return this.tituloDeEleitor == eleitor.tituloDeEleitor;
  }

  /**
   * Converte um Eleitor para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("Eleitor:\n");
    builder.append("  id: " + this.id + "\n");
    builder.append("  titulo de eleitor: " + this.tituloDeEleitor + "\n");
    builder.append("  nome: " + this.nome + "\n");
    builder.append("  numVotos: " + this.estado + "\n");

    return builder.toString();
  }
}

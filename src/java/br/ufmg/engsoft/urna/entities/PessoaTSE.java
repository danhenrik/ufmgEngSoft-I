package br.ufmg.engsoft.urna.entities;

import java.util.UUID;

/**
 * O tipo PessoaTSE.
 */
public class PessoaTSE {

  /**
   * O ID do PessoaTSE.
   * Quando null, o ID sera automaticamente gerado pelo banco de dados.
   */
  protected final UUID id;

  /**
   * O usuario do PessoaTSE.
   */
  protected final String usuario;

  /**
   * O número de votos recebidos pelo PessoaTSE.
   */
  protected final String senha;

  /**
   * Builder de PessoaTSE.
   */
  public static class Builder {
    protected String usuario;
    protected String senha;

    public Builder usuario(String usuario) {
      this.usuario = usuario;
      return this;
    }

    public Builder senhna(String senha) {
      this.senha = senha;
      return this;
    }

    /**
     * Constrói o PessoaTSE.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public PessoaTSE build() {
      if (usuario == null)
        throw new IllegalArgumentException("usuario mustn't be null");

      if (usuario.isEmpty())
        throw new IllegalArgumentException("usuario mustn't be empty");

      if (senha == null)
        throw new IllegalArgumentException("senha mustn't be null");

      if (senha.isEmpty())
        throw new IllegalArgumentException("senha mustn't be empty");

      return new PessoaTSE(
          this.usuario,
          this.senha);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected PessoaTSE(
      String usuario,
      String senha) {
    this.id = UUID.randomUUID();
    this.usuario = usuario;
    this.senha = senha;
  }

  /**
   * Comparador de igualdade.
   * Por mais que a classe tenha um id sua comparação é feita em todos os campos.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof PessoaTSE))
      return false;

    var pessoaTSE = (PessoaTSE) obj;

    return this.id.equals(pessoaTSE.id)
        && this.usuario.equals(pessoaTSE.usuario)
        && this.senha == pessoaTSE.senha;
  }

  /**
   * Converte um PessoaTSE para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("PessoaTSE:\n");
    builder.append("  id: " + this.id + "\n");
    builder.append("  usuario: " + this.usuario + "\n");
    builder.append("  senha: " + this.senha + "\n");

    return builder.toString();
  }
}

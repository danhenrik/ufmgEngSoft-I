package br.ufmg.engsoft.urna.entities;

/**
 * O tipo PessoaCredenciada.
 */
public class PessoaCredenciada extends PessoaTSE {

  public void iniciarSessao() {

  }

  public void finalizarSessao() {

  }

  public void exibirResultado() {

  }

  /**
   * Builder de PessoaCredenciada.
   */
  public static class Builder {
    protected String usuario;
    protected String senha;

    public Builder usuario(String usuario) {
      this.usuario = usuario;
      return this;
    }

    public Builder senha(String senha) {
      this.senha = senha;
      return this;
    }

    /**
     * Constrói o PessoaCredenciada.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public PessoaCredenciada build() {
      if (usuario == null)
        throw new IllegalArgumentException("usuario mustn't be null");

      if (usuario.isEmpty())
        throw new IllegalArgumentException("usuario mustn't be empty");

      if (senha == null)
        throw new IllegalArgumentException("senha mustn't be null");

      if (senha.isEmpty())
        throw new IllegalArgumentException("senha mustn't be empty");

      return new PessoaCredenciada(
          this.usuario,
          this.senha);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected PessoaCredenciada(
      String usuario,
      String senha) {
    super(usuario, senha);
  }

  /**
   * Comparador de igualdade.
   * Por mais que a classe tenha um id sua comparação é feita em todos os campos.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof PessoaCredenciada))
      return false;

    var pessoaCredenciada = (PessoaCredenciada) obj;

    return super.usuario.equals(pessoaCredenciada.usuario)
        && super.senha.equals(pessoaCredenciada.senha);
  }

  /**
   * Converte um PessoaCredenciada para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("PessoaCredenciada:\n");
    builder.append("  id: " + super.id + "\n");
    builder.append("  usuario: " + super.usuario + "\n");
    builder.append("  senha: " + super.senha + "\n");

    return builder.toString();
  }
}

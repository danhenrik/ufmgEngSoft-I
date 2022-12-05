package br.ufmg.engsoft.urna.entities;

/**
 * O tipo FuncionarioTSE.
 */
public class FuncionarioTSE extends PessoaTSE {
  public void adicionarCandidato(Candidato candidato, Eleicao eleicao) {
    if (candidato instanceof Presidente) {
      eleicao.cadastrarCandidatoPresidente((Presidente) candidato);
    }
    if (candidato instanceof DeputadoFederal) {
      eleicao.cadastrarCandidatoDeputadoFederal((DeputadoFederal) candidato);
    }
  }

  public void removerCandidato() {
    
  }

  public void classificarCandidato() {

  }

  /**
   * Builder de FuncionarioTSE.
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
     * Constrói o FuncionarioTSE.
     * 
     * @throws IllegalArgumentException se nenhum parâmetro é válido
     */
    public FuncionarioTSE build() {
      if (usuario == null)
        throw new IllegalArgumentException("usuario mustn't be null");

      if (usuario.isEmpty())
        throw new IllegalArgumentException("usuario mustn't be empty");

      if (senha == null)
        throw new IllegalArgumentException("senha mustn't be null");

      if (senha.isEmpty())
        throw new IllegalArgumentException("senha mustn't be empty");

      return new FuncionarioTSE(
          this.usuario,
          this.senha);
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected FuncionarioTSE(
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

    if (!(obj instanceof FuncionarioTSE))
      return false;

    var funcionarioTSE = (FuncionarioTSE) obj;

    return super.usuario.equals(funcionarioTSE.usuario)
        && super.senha.equals(funcionarioTSE.senha);
  }

  /**
   * Converte um FuncionarioTSE para String, utilizado para visualização.
   */
  @Override
  public String toString() {
    var builder = new StringBuilder();

    builder.append("FuncionarioTSE:\n");
    builder.append("  id: " + super.id + "\n");
    builder.append("  usuario: " + super.usuario + "\n");
    builder.append("  senha: " + super.senha + "\n");

    return builder.toString();
  }
}

package br.ufmg.engsoft.urna.entities;

import java.util.UUID;

public class Urna {
  public final UUID id;

  private final String senha;

  public Eleicao eleicao;

  private Boolean verificarEleicao() {
    return this.eleicao == null;
  }

  public void registraCandidato(String senha, Object requisitario, Candidato candidato) {
    if (!verificarEleicao())
      throw new IllegalAccessError("Eleicao is null, can't proceed");

    if (!(requisitario instanceof FuncionarioTSE))
      throw new IllegalAccessError("You have to be an PessoaCredenciada to proceed with this operation");

    if (!senha.equals(this.senha))
      throw new IllegalAccessError("Invalid senha, please try again");

    if (candidato instanceof Presidente) {
      this.eleicao.cadastrarCandidatoPresidente((Presidente) candidato);
    }
    
    if (candidato instanceof DeputadoFederal) {
      this.eleicao.cadastrarCandidatoDeputadoFederal((DeputadoFederal) candidato);
    }
  }

  public Candidato exibirCandidato(int numero, String estado) {
    if (!verificarEleicao())
      throw new IllegalAccessError("Eleicao is null, can't proceed");

    if (estado == null)
      return this.eleicao.getPresidenteByNumero(numero);

    return this.eleicao.getDeputadoByNumero(numero, estado);
  }

  public Boolean registrarVoto(int numero, String estado, Boolean nulo) {
    if (!verificarEleicao())
      return false;

    if (nulo) {
      this.eleicao.computarVotoNulo();
      return true;
    }

    if (numero == 0) {
      this.eleicao.computarVotoBranco();
      return true;
    }

    if (estado == null) {
      var candidato = this.eleicao.getPresidenteByNumero(numero);
      this.eleicao.computarVotoPresidente(candidato);
      return true;
    }

    var candidato = this.eleicao.getDeputadoByNumero(numero, estado);
    this.eleicao.computarVotoDeputado((DeputadoFederal) candidato);
    return true;
  }

  public void iniciarEleicao(String senha, Object requisitario) {
    if (!verificarEleicao())
      throw new IllegalAccessError("Eleicao is null, can't proceed");

    if (!(requisitario instanceof PessoaCredenciada))
      throw new IllegalAccessError("You have to be an PessoaCredenciada to proceed with this operation");

    if (!senha.equals(this.senha))
      throw new IllegalAccessError("Invalid senha, please try again");

    this.eleicao.iniciar();
  }

  public void finalizarEleicao(String senha, Object requisitario) {
    if (!verificarEleicao())
      throw new IllegalAccessError("Eleicao is null, can't proceed");
    if (!(requisitario instanceof PessoaCredenciada))
      throw new IllegalAccessError("You have to be an PessoaCredenciada to proceed with this operation");
    if (!senha.equals(this.senha))
      throw new IllegalAccessError("Invalid senha, please try again");
    this.eleicao.finalizar();
  }

  public String exibirResultado(String senha, Object requisitario) {
    if (!verificarEleicao())
      throw new IllegalAccessError("Eleicao is null, can't proceed");
    if (!(requisitario instanceof PessoaCredenciada))
      throw new IllegalAccessError("You have to be an PessoaCredenciada to proceed with this operation");
    if (!senha.equals(this.senha))
      throw new IllegalAccessError("Invalid senha, please try again");
    return this.eleicao.exibirResultado();
  }

  public static class Builder {
    protected String senha;

    public Builder senha(String senha) {
      this.senha = senha;
      return this;
    }

    public Urna build() {
      if (senha == null)
        throw new IllegalArgumentException("senha mustn't be null");

      if (senha.isEmpty())
        throw new IllegalArgumentException("senha mustn't be empty");

      return new Urna(this.senha);
    }
  }

  protected Urna(String senha) {
    this.id = UUID.randomUUID();
    this.senha = senha;
  }
}

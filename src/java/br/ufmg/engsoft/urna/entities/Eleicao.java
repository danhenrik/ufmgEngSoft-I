package br.ufmg.engsoft.urna.entities;

import java.util.Map;
import java.util.HashMap;
// import java.util.Objects;

/**
 * O tipo DeputadoFederal.
 */
public class Eleicao {

  /**
   * Status da eleicao, pode ser true = em sessao ou false = inativa.
   */
  private boolean statusSessao;

  /**
   * Numero de votos brancos na eleicao.
   */
  private int votosBrancos;

  /**
   * Numero de votos nulos na eleicao.
   */
  private int votosNulos;

  /**
   * Mapa de cada candidato a presidente para o numero de votos que ele recebeu.
   */
  private Map<Presidente, Integer> candidatosPresidente = new HashMap<Presidente, Integer>();

  /**
   * Mapa de cada candidato a deputado para o numero de votos que ele recebeu.
   */
  private Map<DeputadoFederal, Integer> candidatosDeputado = new HashMap<DeputadoFederal, Integer>();

  /**
   * Builder de Eleicao.
   */
  public static class Builder {

    /**
     * Constrói a eleicao.
     */
    public Eleicao build() {
      return new Eleicao();
    }
  }

  /**
   * Protected constructor, deve ser usado apenas pelo builder.
   */
  protected Eleicao() {
    super();
  }

  public int getVotosBrancos() {
    return votosBrancos;
  }

  public int getVotosNulos() {
    return votosNulos;
  }

  public boolean emSessao() {
    return statusSessao;
  }

  public void iniciar() {
    this.statusSessao = true;
  }

  public void finalizar() {
    this.statusSessao = false;
  }

  public void ComputarVotoPresidente(Presidente candidato) {
    this.candidatosPresidente.put(candidato, this.candidatosPresidente.get(candidato)+1);
  }

  public void ComputarVotoDeputado(DeputadoFederal candidato) {
    this.candidatosDeputado.put(candidato, this.candidatosDeputado.get(candidato)+1);
  }

  public void ComputarVotoNulo() {
    this.votosNulos += 1;
  }

  public void ComputarVotoBranco() {
    this.votosNulos += 1;
  }

}
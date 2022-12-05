package br.ufmg.engsoft.urna.entities;

import java.util.Set;

public class DeputadoFederal extends Candidato {
  protected final String estado;

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

      Set<String> estadosValidos = Set.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
          "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");

      if (!estadosValidos.contains(estado))
        throw new IllegalArgumentException("estado is invalid");

      return new DeputadoFederal(
          this.nome,
          this.partido,
          this.numero,
          this.estado);
    }
  }

  protected DeputadoFederal(
      String nome,
      String partido,
      int numero,
      String estado) {
    super(nome, partido, numero);
    this.estado = estado;
  }
}
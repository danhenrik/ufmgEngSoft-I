package br.ufmg.engsoft.urna.entities;

import java.util.UUID;

public class Candidato {
  protected final UUID id;

  public final String nome;

  public final String partido;

  protected final int numero;

  protected int numVotos;

  public Candidato(
      String nome,
      String partido,
      int numero) {

    if (nome == null)
      throw new IllegalArgumentException("nome mustn't be null");

    if (nome.isEmpty())
      throw new IllegalArgumentException("nome mustn't be empty");

    if (partido == null)
      throw new IllegalArgumentException("partido mustn't be empty");

    if (partido.isEmpty())
      throw new IllegalArgumentException("partido mustn't be empty");

    if (numero <= 0)
      throw new IllegalArgumentException("nunmero must be greater or equal to 1");

    this.id = UUID.randomUUID();
    this.nome = nome;
    this.partido = partido;
    this.numero = numero;
    this.numVotos = 0;
  }
}

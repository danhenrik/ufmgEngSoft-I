package br.ufmg.engsoft.reprova.tests.model;

import org.junit.jupiter.api.Test;

import br.ufmg.engsoft.urna.entities.Urna;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class QuestionTest {
  /**
   * A question mustn't have a null theme.
   */
  @Test
  void nullTheme() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        new Urna.Builder()
          .theme(null)
          .description("desc")
          .build();
      }
    );
  }

  /**
   * A question mustn't have an empty theme.
   */
  @Test
  void emptyTheme() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        new Urna.Builder()
          .theme("")
          .description("desc")
          .build();
      }
    );
  }

  /**
   * A question mustn't have a null description.
   */
  @Test
  void nullDescription() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        new Urna.Builder()
          .theme("theme")
          .description(null)
          .build();
      }
    );
  }

  /**
   * A question mustn't have an empty description.
   */
  @Test
  void emptyDescription() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        new Urna.Builder()
          .theme("theme")
          .description("")
          .build();
      }
    );
  }
}

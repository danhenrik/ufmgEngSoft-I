package br.ufmg.engsoft.reprova.tests.mime.json;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import br.ufmg.engsoft.urna.entities.Urna;
import br.ufmg.engsoft.urna.entities.Eleitor;
import br.ufmg.engsoft.urna.mime.json.Json;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class JsonTest {
  /**
   * Rendering then parsing should produce an equivalent object.
   */
  @Test
  void question() {
    var question = new Urna.Builder()
      .id("id")
      .theme("theme")
      .description("description")
      .statement("statement")
      .record(
        Map.of(
          new Eleitor(2019, Eleitor.Reference._1), Map.of(
            "tw", 50.0f,
            "tz", 49.5f,
            "tx", 51.2f
          ),
          new Eleitor(2020, Eleitor.Reference._2), Collections.emptyMap()
        )
      )
      .pvt(false)
      .build();

    var formatter = new Json();

    var json = formatter.render(question);

    var questionCopy = formatter
      .parse(json, Urna.Builder.class)
      .build();

    assertTrue(
      question.equals(questionCopy)
    );
  }
}

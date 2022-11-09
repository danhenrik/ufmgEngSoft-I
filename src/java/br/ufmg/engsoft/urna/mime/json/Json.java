package br.ufmg.engsoft.urna.mime.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import br.ufmg.engsoft.urna.entities.Urna;
import br.ufmg.engsoft.urna.entities.Eleitor;


/**
 * Json format for Reprova's types.
 */
public class Json {
  /**
   * Deserializer for Semester.
   */
  protected static class SemesterDeserializer implements JsonDeserializer<Eleitor> {
    /**
     * The semester format is:
     * "year/ref"
     * Where ref is 1 or 2.
     */
    @Override
    public Eleitor deserialize(
      JsonElement json,
      Type typeOfT,
      JsonDeserializationContext context
    ) {
      String[] values = json.getAsString().split("/");

      if (values.length != 2)
        throw new JsonParseException("invalid semester");

      var year = Integer.parseInt(values[0]);

      var ref = Eleitor.Reference.fromInt(Integer.parseInt(values[1]));

      return new Eleitor(year, ref);
    }
  }


  /**
   * Deserializer for Question.Builder.
   */
  protected static class QuestionBuilderDeserializer
    implements JsonDeserializer<Urna.Builder>
  {
    @Override
    public Urna.Builder deserialize(
      JsonElement json,
      Type typeOfT,
      JsonDeserializationContext context
    ) {
      var parserBuilder = new GsonBuilder();

      parserBuilder.registerTypeAdapter( // Question has a Semester field.
        Eleitor.class,
        new SemesterDeserializer()
      );

      var questionBuilder = parserBuilder
        .create()
        .fromJson(
          json.getAsJsonObject(),
          Urna.Builder.class
        );

      // Mongo's id property doesn't match Question.id:
      var _id = json.getAsJsonObject().get("_id");

      if (_id != null)
        questionBuilder.id(
          _id.getAsJsonObject()
            .get("$oid")
            .getAsString()
        );

      return questionBuilder;
    }
  }



  /**
   * The json formatter.
   */
  protected final Gson gson;



  /**
   * Instantiate the formatter for Reprova's types.
   * Currently, it supports only the Question type.
   */
  public Json() {
    var parserBuilder = new GsonBuilder();

    parserBuilder.registerTypeAdapter(
      Urna.Builder.class,
      new QuestionBuilderDeserializer()
    );

    this.gson = parserBuilder.create();
  }



  /**
   * Parse an object in the given class.
   * @throws JsonSyntaxException  if json is not a valid representation for the given class
   */
  public <T> T parse(String json, Class<T> cls) {
    return this.gson.fromJson(json, cls);
  }


  /**
   * Render an object of the given class.
   */
  public <T> String render(T obj) {
    return this.gson.toJson(obj);
  }
}

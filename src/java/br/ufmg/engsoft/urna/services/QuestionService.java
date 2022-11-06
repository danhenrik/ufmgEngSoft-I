package br.ufmg.engsoft.urna.services;

import java.util.Collection;

import br.ufmg.engsoft.urna.database.QuestionDAO;
import br.ufmg.engsoft.urna.mime.json.Json;
import br.ufmg.engsoft.urna.model.Question;

/**
 * Servuce for Question entities.
 */
public class QuestionService {
  /**
   * DAO for Question.
   */
  private static QuestionDAO DAO =  QuestionDAO.getInstance();
  
    /**
   * Create a new question.
   * @param body the request body.
   * @return Whether the question was successfully added or not.
   */
  public static boolean create(String body) {
    Question question;
    try {
      question = new Json()
      .parse(body, Question.Builder.class)
      .build();
    }
    catch (Exception e) {
      throw new Error(e);
    }
    
    boolean created = DAO.add(question);
    return created;
  }
  
  /**
 * Update a new question.
 * @param id id of the question.
 * @param body the request body.
 * @return Whether the question was successfully updated or not.
 */
  public static boolean update(String id, String body) {
    Question question;
    try {
      question = new Json()
        .parse(body, Question.Builder.class)
        .build();
    }
    catch (Exception e) {
      throw new Error(e);
    }
  
    boolean created = DAO.update(id, question);
    return created;
  }

  /**
   * Get the question with the given id.
   * @param id  the question's id in the database.
   * @return The question, or null if no such question.
   */
  public static Question getByID(String id) {
    Question question = DAO.get(id);
    return question;
  }

    /**
   * Get all questions according to the current level of permission.
   * @param auth  the authentication token to identify access scope.
   * @return all the questions, or null if no questions on database.
   */
  public static Collection<Question> getAll(boolean auth) {
    Collection<Question> questions = DAO.list(null, auth ? null : false);
    return questions;
  }

  /**
   * Delete the question with the given id.
   * @param id  the question id
   * @return Whether the given question was deleted or not.
   * @throws IllegalArgumentException  if any parameter is null
   */
  public static boolean deleteByID(String id) {
    boolean deleted = DAO.remove(id);
    return deleted;
  }
}

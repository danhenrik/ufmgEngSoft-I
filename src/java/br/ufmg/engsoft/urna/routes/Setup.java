package br.ufmg.engsoft.urna.routes;

import spark.Spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufmg.engsoft.urna.routes.controllers.QuestionController;


/**
 * Service setup class.
 * This class is static.
 */
public class Setup {
  /**
   * Static class.
   */
  protected Setup() { }

  /**
   * Logger instance.
   */
  protected static Logger logger = LoggerFactory.getLogger(Setup.class);

  /**
   * The port for the webserver.
   */
  protected static final int port = Integer.parseInt(System.getenv("PORT"));


  /**
   * Setup the service routes.
   * This sets up the routes under the routes directory,
   * and also static files on '/public'.
   * @param questionsDAO  the DAO for Question
   * @throws IllegalArgumentException  if any parameter is null
   */
  public static void routes() {
    Spark.port(Setup.port);

    logger.info("Spark on port " + Setup.port);

    logger.info("Setting up static resources.");
    Spark.staticFiles.location("/public");

    logger.info("Setting up questions route:");
    new QuestionController().setup();
  }
}

package ooga.model.data.gameWriter;

import ooga.controller.PieceDataRetriever;
import ooga.model.data.PropertiesManager;


/**
 * Interface which saves Game files. Implementation of interface allows different types of files to store game data.
 */
public interface GameFileWriter {

  /**
   * Saves game into respective file
   *
   * @param propertiesManager Properties Manager that contains properties to be saved
   * @param gridSize Size of game grid to be saved
   * @param pieceDataRetriever PieceDataRetriever interface which contains ability to retrieve pieceData from GameEngine
   * @param gameName Name of game being saved
   * @param author Author that is saving the game
   * @param description Description of the file being saved
   */
  void saveGameFile(PropertiesManager propertiesManager, int gridSize,
      PieceDataRetriever pieceDataRetriever,
      String gameName, String author, String description);

}

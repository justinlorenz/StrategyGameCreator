package ooga.model.data.gameWriter;

import ooga.controller.PieceDataRetriever;
import ooga.exception.SavingFileException;
import ooga.exception.SavingGameFileException;
import ooga.model.data.GridWriter.GridWriter;
import ooga.model.data.PropertiesManager;

/**
 * Class used to save games into .game files.
 */
public class GameWriter {

  private static final String AUTHOR = "Author";
  private static final String DESCRIPTION = "Description";
  private static final String FILENAME = "InitialConfiguration";

  private final GridWriter gridWriter;

  /**
   * Constructor for GameWriter
   *
   * @param gridWriter gridWriter which saves grid data
   */
  public GameWriter(GridWriter gridWriter) {
    this.gridWriter = gridWriter;
  }

  /**
   * Method called to save game data into .game file
   *
   * @param propertiesManager Properties Manager that contains properties to be saved
   * @param gridSize Size of game grid to be saved
   * @param pieceDataRetriever PieceDataRetriever interface which contains ability to retrieve pieceData from GameEngine
   * @param gameName Name of game being saved
   * @param author Author that is saving the game
   * @param description Description of the file being saved
   */
  public void saveGameFile(PropertiesManager propertiesManager, int gridSize,
      PieceDataRetriever pieceDataRetriever,
      String gameName, String author, String description) {
    try {
      gridWriter.saveGameGrid(gameName, gridSize, pieceDataRetriever);
      String filePath = "data/savedGames/" + gameName + ".game";

      propertiesManager.putProperty(AUTHOR, author);
      propertiesManager.putProperty(DESCRIPTION, description);
      propertiesManager
          .putProperty(FILENAME, "data/savedGridData/" + gameName + gridWriter.gridFileType());
      propertiesManager.save(filePath);
    } catch (SavingFileException e) {
      throw new SavingGameFileException("Error saving the game", e);
    }
  }

}

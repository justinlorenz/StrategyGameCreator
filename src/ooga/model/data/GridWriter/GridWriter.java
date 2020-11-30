package ooga.model.data.GridWriter;

import ooga.controller.PieceDataRetriever;

/**
 * Interface for saving grid files. Interface implementation allows for different types of files to save grid data.
 *
 * @author Loten Lhatsang
 */
public interface GridWriter {

  /**
   * Saves grid into file
   *
   * @param gameName Name of game saved
   * @param gridSize Size of grid being saved
   * @param gameController Controller which is used to retrieve pieceData
   */
  void saveGameGrid(String gameName, int gridSize, PieceDataRetriever gameController);

  /**
   * Gets file extension type
   *
   * @return Return grid file extension as String
   */
  String gridFileType();

}

package ooga.model.data.GridReader;

import ooga.model.PieceBoardStructure;
import ooga.model.data.PropertiesManager;

/**
 * Interface for reading grid files. Interface implementation allows for different types of files to have grid data read.
 *
 * @author Loten Lhatsang
 */
public interface GridReader {

  /**
   * Reads grid from propertiesManager specified property
   *
   * @param propertiesManager PropertiesManager which encapsulates game properties
   * @return Encapsulated PieceBoardStructure from read in grid
   */
  PieceBoardStructure readInGameStates(PropertiesManager propertiesManager);
}

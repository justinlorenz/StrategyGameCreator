package ooga.model.data.gameReader;

import java.io.File;
import java.util.List;
import ooga.model.GameEngine;

/**
 * Interface for reading Game Files. Interface implementation allows for different types of files to have game data read.
 */
public interface GameFileReader {

  /**
   * Loads in game
   *
   * @param gameFile File of loaded game
   * @return GameEngine of the loaded game
   */
  GameEngine loadInGame(File gameFile);

  /**
   * Load in default of game
   *
   * @param gameType GameType that is being loaded for default
   * @param gridSize GridSize of the game
   * @param playerNames Names of the players
   * @param playerTypes Types of the players
   * @param playerImagePaths Image paths for players
   * @param theme Theme used in game
   * @return GameEngine of the default loaded game
   */
  GameEngine loadDefaultGameFromProperties(String gameType, int gridSize, List<String> playerNames,
      List<String> playerTypes, List<String> playerImagePaths, String theme);

  /**
   * Gets game property with key.
   *
   * @param key key to retrieve property of
   * @return property associated with key
   */
  String getGameProperty(String key);

  /** Gets the GameEngine that has been read
   *
   * @return current GameEngine that was read
   */
  GameEngine getCurrGame();
}

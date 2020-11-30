package ooga.model.gameBuildingBlocks.winConditions;

import ooga.model.BoardStructure;
import ooga.model.WinStatus;

/**
 * Interface for WinCondition building block. GameEngine uses dependency injection
 * with WinCondition Interface to allow for different implementations of building blocks.

 */
public interface WinCondition {

  /**
   * Retrieves the WinStatus for the game based on satisfying WinCondition
   *
   * @param currBoard BoardStructure to determine if WinCondition has been satisfied on
   * @return WinStatus of the current game
   */
  WinStatus getWinStatus(BoardStructure currBoard);

}

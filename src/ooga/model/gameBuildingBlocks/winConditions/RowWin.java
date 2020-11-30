package ooga.model.gameBuildingBlocks.winConditions;

import ooga.model.BoardStructure;
import ooga.model.WinStatus;

/**
 * WinCondition building block that checks to see if either player has
 * won the game by getting a certain number of their pieces consecutively
 * within a row.
 *
 * 
 * 
 * 
 */
public class RowWin implements WinCondition {

  private final int winningSize;

  /**
   * Constructor for RowWin
   *
   * @param winningSize Number of pieces in a row required to win the game
   */
  public RowWin(int winningSize) {
    this.winningSize = winningSize;
  }

  /**
   * Retrieves the WinStatus for the game based on satisfying WinCondition
   *
   * @param currBoard BoardStructure to determine if WinCondition has been satisfied on
   * @return WinStatus of the current game
   */
  @Override
  public WinStatus getWinStatus(BoardStructure currBoard) {
    for (int i = 0; i < currBoard.getRows(); i++) {
      int currPlayerNumber = currBoard.getPieceType(i, 0).getPlayerNumber();
      int currPlayerCount = 0;
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (currPlayerNumber == currBoard.getPieceType(i, j).getPlayerNumber() &&
            !currBoard.getPieceType(i, j).isEmpty()) {
          currPlayerCount++;
          if (currPlayerCount == winningSize) {
            return new WinStatus(true, currPlayerNumber);
          }
        } else {
          currPlayerCount = 1;
          currPlayerNumber = currBoard.getPieceType(i, j).getPlayerNumber();
        }
      }
    }
    return new WinStatus();
  }
}

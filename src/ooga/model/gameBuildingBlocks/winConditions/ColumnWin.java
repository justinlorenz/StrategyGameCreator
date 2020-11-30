package ooga.model.gameBuildingBlocks.winConditions;

import ooga.model.BoardStructure;
import ooga.model.WinStatus;

/**
 * WinCondition building block that checks to see if either player has
 * won the game by getting a certain number of their pieces consecutively
 * within a column.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class ColumnWin implements WinCondition {

  private final int winningSize;

  /**
   * Constructor for ColumnWin
   *
   * @param winningSize Number of pieces in a row required to win the game
   */
  public ColumnWin(int winningSize) {
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
    for (int j = 0; j < currBoard.getCols(); j++) {
      int currPlayerNumber = currBoard.getPieceType(0, j).getPlayerNumber();
      int currPlayerCount = 0;
      for (int i = 0; i < currBoard.getRows(); i++) {
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

package ooga.model.gameBuildingBlocks.winConditions;

import java.util.HashMap;
import java.util.Map;
import ooga.model.BoardStructure;
import ooga.model.WinStatus;

/**
 * WinCondition building block that checks to see if either player has
 * won the game by getting a certain number of their pieces consecutively
 * within a diagonal.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class DiagonalWin implements WinCondition {

  private final int winningSize;
  private final int PLAYER_ONE_NUMBER = 1;
  private final int PLAYER_TWO_NUMBER = 2;

  /**
   * Constructor for DiagonalWin
   *
   * @param winningSize Number of pieces in a row required to win the game
   */
  public DiagonalWin(int winningSize) {
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
    Map<Integer, Boolean> winningPlayer = new HashMap<>();
    winningPlayer.put(PLAYER_ONE_NUMBER, false);
    winningPlayer.put(2, false);

    checkUpperLeftToBottomRightDiagonalBottomLeftSideOfBoard(currBoard, winningPlayer);
    checkUpperLeftToBottomRightDiagonalTopRightSideOfBoard(currBoard, winningPlayer);
    checkBottomLeftToUpperRightDiagonalTopLeftSideOfBoard(currBoard, winningPlayer);
    checkBottomLeftToUpperRightDiagonalBottomRightSideOfBoard(currBoard, winningPlayer);

    if (winningPlayer.get(PLAYER_ONE_NUMBER)) {
      return new WinStatus(true, PLAYER_ONE_NUMBER);
    }
    if (winningPlayer.get(PLAYER_TWO_NUMBER)) {
      return new WinStatus(true, PLAYER_TWO_NUMBER);
    }

    return new WinStatus();
  }

  private void checkUpperLeftToBottomRightDiagonalBottomLeftSideOfBoard(BoardStructure currBoard,
      Map winningPlayers) {
    int row = currBoard.getRows() - 1;
    while (row >= 0) {
      int col = 0;
      int rowTemp = row;
      int currPlayerNumber = currBoard.getPieceType(row, col).getPlayerNumber();
      int currPlayerCount = 0;
      while (rowTemp < currBoard.getRows()) {
        if (currPlayerNumber == currBoard.getPieceType(rowTemp, col).getPlayerNumber() &&
            !currBoard.getPieceType(rowTemp, col).isEmpty()) {
          currPlayerCount++;
          if (currPlayerCount == winningSize) {
            winningPlayers.replace(currPlayerNumber, true);
          }
        } else {
          currPlayerCount = 1;
          currPlayerNumber = currBoard.getPieceType(rowTemp, col).getPlayerNumber();
        }
        rowTemp++;
        col++;
      }
      row--;
    }
  }

  private void checkUpperLeftToBottomRightDiagonalTopRightSideOfBoard(BoardStructure currBoard,
      Map winningPlayers) {
    int col = currBoard.getCols() - 1;
    while (col >= 0) {
      int colTemp = col;
      int row = 0;
      int currPlayerNumber = currBoard.getPieceType(row, colTemp).getPlayerNumber();
      int currPlayerCount = 0;
      while (colTemp <= currBoard.getCols() - 1) {
        if (currPlayerNumber == currBoard.getPieceType(row, colTemp).getPlayerNumber() &&
            !currBoard.getPieceType(row, colTemp).isEmpty()) {
          currPlayerCount++;
          if (currPlayerCount == winningSize) {
            winningPlayers.replace(currPlayerNumber, true);
          }
        } else {
          currPlayerCount = 1;
          currPlayerNumber = currBoard.getPieceType(row, colTemp).getPlayerNumber();
        }
        row++;
        colTemp++;
      }
      col--;
    }
  }

  private void checkBottomLeftToUpperRightDiagonalTopLeftSideOfBoard(BoardStructure currBoard,
      Map winningPlayers) {
    int row = 0;
    while (row < currBoard.getRows()) {
      int col = 0;
      int rowTemp = row;
      int currPlayerNumber = currBoard.getPieceType(row, col).getPlayerNumber();
      int currPlayerCount = 0;
      while (rowTemp >= 0) {
        if (currPlayerNumber == currBoard.getPieceType(rowTemp, col).getPlayerNumber() &&
            !currBoard.getPieceType(rowTemp, col).isEmpty()) {
          currPlayerCount++;
          if (currPlayerCount == winningSize) {
            winningPlayers.replace(currPlayerNumber, true);
          }
        } else {
          currPlayerCount = 1;
          currPlayerNumber = currBoard.getPieceType(rowTemp, col).getPlayerNumber();
        }
        rowTemp--;
        col++;
      }
      row++;
    }
  }

  private void checkBottomLeftToUpperRightDiagonalBottomRightSideOfBoard(BoardStructure currBoard,
      Map winningPlayers) {
    int col = 1;

    while (col < currBoard.getCols()) {
      int colTemp = col;
      int row = currBoard.getCols() - 1;
      int currPlayerNumber = currBoard.getPieceType(row, colTemp).getPlayerNumber();
      int currPlayerCount = 0;
      while (colTemp <= currBoard.getCols() - 1) {
        if (currPlayerNumber == currBoard.getPieceType(row, colTemp).getPlayerNumber() &&
            !currBoard.getPieceType(row, colTemp).isEmpty()) {
          currPlayerCount++;
          if (currPlayerCount == winningSize) {
            winningPlayers.replace(currPlayerNumber, true);
          }
        } else {
          currPlayerCount = 1;
          currPlayerNumber = currBoard.getPieceType(row, colTemp).getPlayerNumber();
        }
        row--;
        colTemp++;
      }
      col++;
    }
  }

}

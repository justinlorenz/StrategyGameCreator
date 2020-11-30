package ooga.model.gameBuildingBlocks.winConditions;

import java.util.HashMap;
import java.util.Map;
import ooga.model.BoardStructure;
import ooga.model.WinStatus;

/**
 * WinCondition building block that checks to see if either player has
 * no more pieces left. If a player has no more pieces left, then the
 * other player has won the game.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class NoMorePiecesWin implements WinCondition {

  public static final int PLAYER_ONE_NUMBER = 1;
  public static final int PLAYER_TWO_NUMBER = 2;

  /**
   * Retrieves the WinStatus for the game based on satisfying WinCondition
   *
   * @param currBoard BoardStructure to determine if WinCondition has been satisfied on
   * @return WinStatus of the current game
   */
  @Override
  public WinStatus getWinStatus(BoardStructure currBoard) {
    Map<Integer, Integer> playerCounts = new HashMap<>();
    playerCounts.put(PLAYER_ONE_NUMBER, 0);
    playerCounts.put(PLAYER_TWO_NUMBER, 0);
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (!currBoard.getPieceType(i, j).isEmpty()) {
          playerCounts.put(currBoard.getPieceType(i, j).getPlayerNumber(),
              playerCounts.getOrDefault(currBoard.getPieceType(i, j).getPlayerNumber(), 0) + 1);
        }
      }
    }
    if (playerCounts.get(PLAYER_ONE_NUMBER) == 0 && playerCounts.get(PLAYER_TWO_NUMBER) == 0) {
      return new WinStatus();
    } else if (playerCounts.get(PLAYER_ONE_NUMBER) == 0) {
      return new WinStatus(true, PLAYER_TWO_NUMBER);
    } else if (playerCounts.get(PLAYER_TWO_NUMBER) == 0) {
      return new WinStatus(true, PLAYER_ONE_NUMBER);
    } else {
      return new WinStatus();
    }
  }
}

package ooga.model;

/**
 * Class for containing WinStatus information
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class WinStatus {

  private final boolean gameWon;
  private final int winningPlayer;

  /**
   * WinStatus constructor
   *
   * @param gameStatus Boolean for if game is won
   * @param winningPlayerNumber Associated player number for winner
   */
  public WinStatus(boolean gameStatus, int winningPlayerNumber) {
    gameWon = gameStatus;
    winningPlayer = winningPlayerNumber;
  }

  /**
   * WinStatus constructor for if game isn't won
   */
  public WinStatus() {
    gameWon = false;
    winningPlayer = 0;
  }

  /**
   * Retrieves if game is won
   *
   * @return Boolean for if game is won
   */
  public boolean isGameWon() {
    return gameWon;
  }

  /**
   * Retrieves the player number of winner
   *
   * @return Associated player number for winner
   */
  public int getWinningPlayer() {
    return winningPlayer;
  }
}


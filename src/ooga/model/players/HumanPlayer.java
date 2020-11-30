package ooga.model.players;

import ooga.model.BoardStructure;
import ooga.model.GameMoveHandler;

/**
 * Class for human players

 */
public class HumanPlayer implements Player {

  private final GameMoveHandler gameMoveHandler;
  private final int playerNumber;
  private final String playerName;

  /**
   * HumanPlayer constructor
   *
   * @param gameMoveHandler gameMoveHandler which handles moves
   * @param playerName playerName of human
   * @param playerNumber number associated with player
   */
  public HumanPlayer(GameMoveHandler gameMoveHandler, String playerName, int playerNumber) {
    this.gameMoveHandler = gameMoveHandler;
    this.playerNumber = playerNumber;
    this.playerName = playerName;
  }

  /**
   * Makes move by only specifying destination
   *
   * @param destinationI Destination I coordinate for move on piece
   * @param destinationJ Destination J coordinate for move on piece
   * @param currBoard Board structure that move will take place on
   * @return True if move occurred, false if move did not happen.
   */
  @Override
  public boolean makeMove(int destinationI, int destinationJ, BoardStructure currBoard) {
    return gameMoveHandler.makeGameMove(destinationI, destinationJ, playerNumber);
    // Have interface to front end which communicates that it needs player input
  }

  /**
   * Makes move by specifies piece location and destination location
   *
   * @param pieceI Piece I coordinate
   * @param pieceJ Piece J coordinate
   * @param destinationI Destination I coordinate for move on piece
   * @param destinationJ Destination J coordinate for move on piece
   * @param currBoard Board structure that move will take place on
   * @return True if move occurred, false if move did not happen.
   */
  @Override
  public boolean makeMove(int pieceI, int pieceJ, int destinationI, int destinationJ,
      BoardStructure currBoard) {
    return gameMoveHandler.makeGameMove(pieceI, pieceJ, destinationI, destinationJ, playerNumber);
  }

  /**
   * Abstract method to check if player is human
   *
   * @return Returns true
   */
  @Override
  public boolean isHuman() {
    return true;
  }

  /**
   * Retrieves name of the player
   *
   * @return Player name
   */
  @Override
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Retrieves the associated number to player
   *
   * @return Player's associated number
   */
  @Override
  public int getPlayerNumber() {
    return playerNumber;
  }

}

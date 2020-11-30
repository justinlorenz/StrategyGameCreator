package ooga.model.players;

import ooga.model.BoardStructure;

/**
 * Interface for Player
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public interface Player {

  /**
   * Makes move by only specifying destination
   *
   * @param destinationI Destination I coordinate for move on piece
   * @param destinationJ Destination J coordinate for move on piece
   * @param currBoard Board structure that move will take place on
   * @return True if move occurred, false if move did not happen.
   */
  boolean makeMove(int destinationI, int destinationJ, BoardStructure currBoard);

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
  boolean makeMove(int pieceI, int pieceJ, int destinationI, int destinationJ,
      BoardStructure currBoard);

  /**
   * Method to check if player is human
   *
   * @return Boolean if player is human
   */
  boolean isHuman();

  /**
   * Retrieves name of the player
   *
   * @return Player name
   */
  String getPlayerName();

  /**
   * Retrieves the associated number to player
   *
   * @return Player's associated number
   */
  int getPlayerNumber();

}

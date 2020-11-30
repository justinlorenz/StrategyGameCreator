package ooga.model;

import ooga.controller.PieceData;
import ooga.controller.PossibleMoveList;


/**
 * Interface for GameEngine
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public interface GameEngine {

  /**
   * Method to handle user input on boardStructure
   *
   * @param i I coordinate that user inputted
   * @param j J coordinate that user inputted
   */
  void gotUserInput(int i, int j);

  /**
   * Retrieves the associated number of the current player's whose turn it is
   *
   * @return The current player's turn associated number
   */
  int getCurrPlayerTurn();

  /**
   * Retrieves PieceData at specified coordinates
   *
   * @param i I coordinate to retrieve PieceData from
   * @param j J coordinate to retrieve PieceData from
   * @return PieceData at specified coordinates
   */
  PieceData getPieceData(int i, int j);

  /**
   * Retrieves PieceData of the clicked Piece
   *
   * @return PieceData of currently clicked Piece
   */
  PieceData getClickedPieceData();

  /**
   * Retrieves the current GameStatus of game
   *
   * @return Return current GameStatus
   */
  GameStatus getGameStatus();

  /**
   * Method ends current players turn
   */
  void endCurrPlayerTurn();

  /**
   * @return Returns boolean if turns are ended manually
   */
  boolean doesUserEndTurn();

  /**
   * Gets gridSize of game
   *
   * @return Size of the grid of game
   */
  int getGridSize();

  /**
   * Retrieves PieceData of the clicked Piece
   *
   * @return PieceData of currently clicked Piece
   */
  PossibleMoveList getClickedPieceMoves();

  /**
   * Method plays AI turn
   *
   * @return Returns true if turn was played properly
   */
  boolean playAITurn();

}

package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import ooga.model.BoardStructure;

/**
 Interface for BoardMoveUpdate building block. GameEngine uses dependency injection
 with BoardMoveUpdate Interface to allow for different implementations of building blocks.
 */
public interface BoardMoveUpdate {

  /**
   Updates the board using specified coordinates
   *
   @param currBoard BoardStructure of the board to be updated
   @param playerNumber Number associated with player making the move
   @param pieceI I coordinate of Piece to be moved
   @param pieceJ J coordinate of Piece to be moved
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   */
  void updateBoard(BoardStructure currBoard, int playerNumber, int pieceI, int pieceJ,
      int destinationI, int destinationJ);

}

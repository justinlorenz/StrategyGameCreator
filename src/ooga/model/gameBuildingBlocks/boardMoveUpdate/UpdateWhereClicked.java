package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import ooga.model.BoardStructure;
import ooga.model.pieces.NormalPiece;

/**
 * BoardMoveUpdate building block that places current player's piece on the clicked
 * destination coordinates.

 */
public class UpdateWhereClicked implements BoardMoveUpdate {

  /**
   * Updates the board using specified coordinates
   *
   * @param currBoard BoardStructure of the board to be updated
   * @param playerNumber Number associated with player making the move
   * @param pieceI I coordinate of Piece to be moved
   * @param pieceJ J coordinate of Piece to be moved
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   */
  @Override
  public void updateBoard(BoardStructure currBoard, int playerNumber, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    currBoard.setCellState(destinationI, destinationJ,
        new NormalPiece(playerNumber, "NormalPiece", destinationI, destinationJ));
  }
}

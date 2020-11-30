package ooga.model.gameBuildingBlocks.validMoveChecks;

import ooga.model.BoardStructure;

/**
 * ValidMoveCheck building block that checks to see if the piece move
 * made is connected to other non empty pieces next to it.
 */
public class ConnectedCellCheck implements ValidMoveCheck {

  /**
   * Determines if the move is valid
   *
   * @param currBoard BoardStructure to check validity of move for
   * @param pieceI I coordinate of piece to be moved
   * @param pieceJ K coordinate of piece to be moved
   * @param destinationI I coordinate for move destination
   * @param destinationJ J coordinate for move destination
   * @return boolean if the given move passes this valid check
   */
  @Override
  public boolean isValidMove(BoardStructure currBoard, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    if (outOfBounds.isValidMove(currBoard, pieceI, pieceJ, destinationI - 1, destinationJ) &&
        !currBoard.getPieceType(destinationI - 1, destinationJ).isEmpty()) {
      return true;
    } else if (outOfBounds.isValidMove(currBoard, pieceI, pieceJ, destinationI + 1, destinationJ) &&
        !currBoard.getPieceType(destinationI + 1, destinationJ).isEmpty()) {
      return true;
    } else if (outOfBounds.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ - 1) &&
        !currBoard.getPieceType(destinationI, destinationJ - 1).isEmpty()) {
      return true;
    }
    return outOfBounds.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ + 1) &&
        !currBoard.getPieceType(destinationI, destinationJ + 1).isEmpty();
  }
}

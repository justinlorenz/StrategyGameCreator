package ooga.model.gameBuildingBlocks.validMoveChecks;

import ooga.model.BoardStructure;

/**
 * ValidMoveCheck building block that checks to see if the piece move
 * made outflanks an opponent piece Diagonally Upwards, Diagonally Downwards,
 * Row, or by Column.
 */
public class OutflankCheck implements ValidMoveCheck {

  int playerNumber;

  public OutflankCheck(int playerNumber) {
    this.playerNumber = playerNumber;
  }

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
    ValidMoveCheck outflankCol = new OutflankColCheck(playerNumber);
    ValidMoveCheck outflankRow = new OutflankRowCheck(playerNumber);
    ValidMoveCheck outflankDiagonalUp = new OutflankDiagonalDownCheck(playerNumber);
    ValidMoveCheck outflankDiagonalDown = new OutflankDiagonalUpCheck(playerNumber);
    return (outflankCol.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ) ||
        outflankRow.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ) ||
        outflankDiagonalUp.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ) ||
        outflankDiagonalDown.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ));
  }
}

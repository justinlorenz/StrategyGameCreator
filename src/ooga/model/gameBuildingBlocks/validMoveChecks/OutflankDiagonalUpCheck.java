package ooga.model.gameBuildingBlocks.validMoveChecks;

import static ooga.model.GameMoveHandler.NON_USED_PARAMETER;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.NEGATIVE_DIRECTION;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.POSITIVE_DIRECTION;

import ooga.model.BoardStructure;

/**
 * ValidMoveCheck building block that checks to see if the piece move
 * made outflanks an opponent piece Diagonally Upwards
 */
public class OutflankDiagonalUpCheck implements ValidMoveCheck {

  int playerNumber;

  public OutflankDiagonalUpCheck(int playerNumber) {
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
    return ((transverseDiagonal(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION,
        NEGATIVE_DIRECTION) &&
        !sameTeamDiagonal(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION,
            NEGATIVE_DIRECTION)) ||
        transverseDiagonal(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION,
            POSITIVE_DIRECTION) &&
            !sameTeamDiagonal(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION,
                POSITIVE_DIRECTION));
  }

  /**
   * Checks if piece is outflanked Diagonally Downwards
   *
   * @param currBoard BoardStructure to check for outflank on
   * @param pieceI I coordinate of the piece
   * @param pieceJ J coordinate of the piece
   * @param destinationI I coordinate of the destination
   * @param destinationJ J coordinate of the destination
   * @return Boolean if the outflank occurs
   */
  public boolean isOutflankedDiagonalDown(BoardStructure currBoard, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    return ((transverseDiagonal(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION,
        NEGATIVE_DIRECTION) &&
        transverseDiagonal(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION,
            POSITIVE_DIRECTION)));
  }


  private boolean transverseDiagonal(BoardStructure currBoard, int startingI, int startingJ,
      int iDirection, int jDirection) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    int currCol = startingJ + jDirection;
    int currRow = startingI + iDirection;
    while (
        outOfBounds.isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, currRow, currCol)
            &&
            !currBoard.getPieceType(currRow, currCol).isEmpty()) {
      if (currBoard.getPieceType(currRow, currCol).getPlayerNumber() == playerNumber &&
          !currBoard.getPieceType(currRow, currCol).isEmpty()) {
        return true;
      }
      currRow += iDirection;
      currCol += jDirection;
    }
    return false;
  }

  private boolean sameTeamDiagonal(BoardStructure currBoard, int startingI, int startingJ,
      int iDirection, int jDirection) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    return outOfBounds
        .isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, startingI + iDirection,
            startingJ + jDirection) &&
        currBoard.getPieceType(startingI + iDirection, startingJ + jDirection).getPlayerNumber()
            == playerNumber;
  }
}

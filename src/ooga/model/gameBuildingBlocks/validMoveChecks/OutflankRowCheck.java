package ooga.model.gameBuildingBlocks.validMoveChecks;

import static ooga.model.GameMoveHandler.NON_USED_PARAMETER;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.NEGATIVE_DIRECTION;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.POSITIVE_DIRECTION;

import ooga.model.BoardStructure;

/**
 * ValidMoveCheck building block that checks to see if the piece move
 * made outflanks an opponent piece within a row
 */
public class OutflankRowCheck implements ValidMoveCheck {

  int playerNumber;

  public OutflankRowCheck(int playerNumber) {
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
    return ((transverseRow(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION) &&
        !sameTeamRow(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION)) ||
        transverseRow(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION) &&
            !sameTeamRow(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION));
  }

  /**
   * Checks if piece is outflanked on a row.
   *
   * @param currBoard BoardStructure to check for outflank on
   * @param pieceI I coordinate of the piece
   * @param pieceJ J coordinate of the piece
   * @param destinationI I coordinate of the destination
   * @param destinationJ J coordinate of the destination
   * @return Boolean if the outflank occurs
   */
  public boolean isOutflankedRow(BoardStructure currBoard, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    return (transverseRow(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION) &&
        transverseRow(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION));
  }


  private boolean transverseRow(BoardStructure currBoard, int startingI, int startingJ,
      int direction) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    int currCol = startingJ + direction;
    while (outOfBounds
        .isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, startingI, currCol) &&
        !currBoard.getPieceType(startingI, currCol).isEmpty()) {
      if (currBoard.getPieceType(startingI, currCol).getPlayerNumber() == playerNumber &&
          !currBoard.getPieceType(startingI, currCol).isEmpty()) {
        return true;
      }
      currCol += direction;
    }
    return false;
  }

  private boolean sameTeamRow(BoardStructure currBoard, int startingI, int startingJ,
      int direction) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    return outOfBounds.isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, startingI,
        startingJ + direction) &&
        currBoard.getPieceType(startingI, startingJ + direction).getPlayerNumber()
            == playerNumber;
  }
}

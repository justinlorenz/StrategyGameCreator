package ooga.model.gameBuildingBlocks.validMoveChecks;

import static ooga.model.GameMoveHandler.NON_USED_PARAMETER;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.NEGATIVE_DIRECTION;
import static ooga.model.gameBuildingBlocks.boardMoveUpdate.UpdateFlipOutflanked.POSITIVE_DIRECTION;

import ooga.model.BoardStructure;

public class OutflankColCheck implements ValidMoveCheck {

  int playerNumber;

  /**
   * ValidMoveCheck building block that checks to see if the piece move
   * made outflanks an opponent piece within a column.
   */
  public OutflankColCheck(int playerNumber) {
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
    return ((transverseCol(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION) &&
        !sameTeamCol(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION)) ||
        transverseCol(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION) &&
            !sameTeamCol(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION));
  }

  /**
   * Checks if piece is outflanked on a column
   *
   * @param currBoard BoardStructure to check for outflank on
   * @param pieceI I coordinate of the piece
   * @param pieceJ J coordinate of the piece
   * @param destinationI I coordinate of the destination
   * @param destinationJ J coordinate of the destination
   * @return Boolean if the outflank occurs
   */
  public boolean isOutflankedCol(BoardStructure currBoard, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    return (transverseCol(currBoard, destinationI, destinationJ, POSITIVE_DIRECTION) &&
        transverseCol(currBoard, destinationI, destinationJ, NEGATIVE_DIRECTION));
  }

  private boolean transverseCol(BoardStructure currBoard, int startingI, int startingJ,
      int direction) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    int currRow = startingI + direction;
    while (outOfBounds
        .isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, currRow, startingJ) &&
        !currBoard.getPieceType(currRow, startingJ).isEmpty()) {
      if (currBoard.getPieceType(currRow, startingJ).getPlayerNumber() == playerNumber &&
          !currBoard.getPieceType(currRow, startingJ).isEmpty()) {
        return true;
      }
      currRow += direction;
    }
    return false;
  }

  private boolean sameTeamCol(BoardStructure currBoard, int startingI, int startingJ,
      int direction) {
    ValidMoveCheck outOfBounds = new OutOfBoundsCheck();
    return outOfBounds
        .isValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, startingI + direction,
            startingJ) &&
        currBoard.getPieceType(startingI + direction, startingJ).getPlayerNumber()
            == playerNumber;
  }
}

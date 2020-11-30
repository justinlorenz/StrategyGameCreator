package ooga.model.gameBuildingBlocks.validMoveChecks;

import ooga.model.BoardStructure;

/**
 * ValidMoveCheck building block that checks to see if the destination coordinates
 * belong to the same team as the piece coordinates.
 */
public class SameTeamCheck implements ValidMoveCheck {

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
    return (currBoard.getPieceType(pieceI, pieceJ).getPlayerNumber() == currBoard
        .getPieceType(destinationI, destinationJ).getPlayerNumber());
  }
}

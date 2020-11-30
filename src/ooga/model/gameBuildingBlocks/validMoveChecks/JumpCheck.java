package ooga.model.gameBuildingBlocks.validMoveChecks;

import ooga.model.BoardStructure;
import ooga.model.pieces.Piece;

/**
 * ValidMoveCheck building block that checks to see if the piece move
 * jumps over an opponent's piece.
 */
public class JumpCheck implements ValidMoveCheck {

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
    Piece currPiece = currBoard.getPieceType(pieceI, pieceJ);
    Piece jumpedPiece = currBoard
        .getPieceType((pieceI + destinationI) / 2, (pieceJ + destinationJ) / 2);
    return (currPiece.getPlayerNumber() != jumpedPiece.getPlayerNumber() && !jumpedPiece.isEmpty());
  }
}

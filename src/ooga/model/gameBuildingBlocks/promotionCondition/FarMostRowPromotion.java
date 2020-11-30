package ooga.model.gameBuildingBlocks.promotionCondition;

import ooga.model.BoardStructure;
import ooga.model.pieces.Piece;

/**
 * Promotion Condition building block which promotes pieces when they reach the far most row away
 * from their own team's side.
 *
 * 
 * 
 * 
 */
public class FarMostRowPromotion implements PromotionCondition {

  /**
   * Promotes piece at given coordinates to a special piece if it satisfies promotion condition
   *
   * @param currBoard BoardStructure to check for promotion
   * @param pieceI I coordinate of piece to check for promotion
   * @param pieceJ J coordinate of piece to check for promotion
   */
  @Override
  public void piecePromotionUpdate(BoardStructure currBoard, int pieceI, int pieceJ) {
    Piece currPiece = currBoard.getPieceType(pieceI, pieceJ);
    if (currPiece.isSpecial()) {
      return;
    }
    int pieceDirection = currPiece.getDirection().get(0);
    int promotionRow;
    if (pieceDirection < 0) {
      promotionRow = 0;
    } else {
      promotionRow = currBoard.getRows() - 1;
    }
    if (pieceI == promotionRow) {
      currBoard.getPieceType(pieceI, pieceJ).makeSpecialPiece();
    }
  }
}

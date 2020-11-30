package ooga.model.gameBuildingBlocks.promotionCondition;

import ooga.model.BoardStructure;

/**
 * Promotion Condition building block which promotes pieces when they reach the first column.
 *
 * 
 * 
 * 
 */
public class FirstColumnPromotion implements PromotionCondition {

  /**
   * Promotes piece at given coordinates to a special piece if it satisfies promotion condition
   *
   * @param currBoard BoardStructure to check for promotion
   * @param pieceI I coordinate of piece to check for promotion
   * @param pieceJ J coordinate of piece to check for promotion
   */
  @Override
  public void piecePromotionUpdate(BoardStructure currBoard, int pieceI, int pieceJ) {
    int promotionCol = 0;
    if (pieceJ == promotionCol) {
      currBoard.getPieceType(pieceI, pieceJ).makeSpecialPiece();
    }
  }
}

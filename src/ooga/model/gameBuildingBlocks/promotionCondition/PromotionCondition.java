package ooga.model.gameBuildingBlocks.promotionCondition;

import ooga.model.BoardStructure;

/**
 * Interface for PromotionCondition building block. GameEngine uses dependency injection
 * with PromotionCondition Interface to allow for different implementations of building block.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public interface PromotionCondition {

  /**
   * Promotes piece at given coordinates to a special piece if it satisfies promotion condition
   *
   * @param currBoard BoardStructure to check for promotion
   * @param pieceI I coordinate of piece to check for promotion
   * @param pieceJ J coordinate of piece to check for promotion
   */
  void piecePromotionUpdate(BoardStructure currBoard, int pieceI, int pieceJ);

}

package ooga.model.gameBuildingBlocks.promotionCondition;

import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class for creating PromotionCondition building blocks
 *
 * 
 * 
 * 
 */
public class PromotionConditionFactory {

  /**
   * Creates a new instance of a PromotionCondition
   *
   * @param promotionCondition Class name of PieceMove building block
   * @return Constructed PieceMove
   */
  public PromotionCondition getPromotionCondition(String promotionCondition) {
    try {
      return (PromotionCondition) Class
          .forName("ooga.model.gameBuildingBlocks.promotionCondition." + promotionCondition)
          .getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ClassDoesNotExistException(promotionCondition, e);
    }
  }
}

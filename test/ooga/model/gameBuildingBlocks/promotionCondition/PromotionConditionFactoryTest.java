package ooga.model.gameBuildingBlocks.promotionCondition;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class PromotionConditionFactoryTest {

  private static final String BAD_PROMOTION_CONDITION_UPDATE = "Bad Promotion Condition Update";
  private static final String FAR_MOST_ROW_PROMOTION = "FarMostRowPromotion";
  private static final String FIRST_COLUMN_PROMOTION= "FirstColumnPromotion";
  private static final String LAST_COLUMN_PROMOTION= "LastColumnPromotion";

  @Test
  void badPromotionConditionCreationTest() {
    PromotionConditionFactory promotionConditionFactory = new PromotionConditionFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> promotionConditionFactory.getPromotionCondition(BAD_PROMOTION_CONDITION_UPDATE));
  }

  @Test
  void farMostRowPromotionCreationTest() {
    PromotionConditionFactory promotionConditionFactory = new PromotionConditionFactory();
    promotionConditionFactory.getPromotionCondition(FAR_MOST_ROW_PROMOTION);
  }

  @Test
  void firstColumnPromotionCreationTest() {
    PromotionConditionFactory promotionConditionFactory = new PromotionConditionFactory();
    promotionConditionFactory.getPromotionCondition(FIRST_COLUMN_PROMOTION);
  }

  @Test
  void lastColumnPromotionCreationTest() {
    PromotionConditionFactory promotionConditionFactory = new PromotionConditionFactory();
    promotionConditionFactory.getPromotionCondition(LAST_COLUMN_PROMOTION);
  }

}
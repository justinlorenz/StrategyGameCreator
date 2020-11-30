package ooga.model.gameBuildingBlocks.validMoveChecks;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class ValidMoveCheckFactoryTest {

  private static final String BAD_VALID_MOVE = "Bad Valid Move";
  private static final String EMPTY_CELL_CHECK = "EmptyCellCheck";
  private static final String EMPTY_COLUMN_CHECK= "EmptyColumnCheck";
  private static final String EMPTY_ROW_CHECK= "EmptyRowCheck";
  private static final String JUMP_CHECK= "JumpCheck";
  private static final String OUT_OF_BOUNDS_CHECK= "OutOfBoundsCheck";
  private static final String SAME_TEAM_CHECK= "SameTeamCheck";

  @Test
  void badValidMoveCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> validMoveCheckFactory.getValidMoveCheck(BAD_VALID_MOVE));
  }

  @Test
  void emptyCellCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(EMPTY_CELL_CHECK);
  }

  @Test
  void emptyColumnCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(EMPTY_COLUMN_CHECK);
  }

  @Test
  void emptyRowCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(EMPTY_ROW_CHECK);
  }

  @Test
  void jumpCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(JUMP_CHECK);
  }

  @Test
  void outOfBoundsCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(OUT_OF_BOUNDS_CHECK);
  }

  @Test
  void sameTeamCheckCreationTest() {
    ValidMoveCheckFactory validMoveCheckFactory = new ValidMoveCheckFactory();
    validMoveCheckFactory.getValidMoveCheck(SAME_TEAM_CHECK);
  }

}
package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class BoardMoveUpdateFactoryTest {
  private static final String BAD_BOARD_MOVE_UPDATE = "Bad Board Move Update";
  private static final String UPDATE_BOTTOM_ROW = "UpdateBottomRow";
  private static final String UPDATE_LAST_COLUMN= "UpdateLastColumn";
  private static final String UPDATE_WHERE_CLICKED= "UpdateWhereClicked";
  private static final String UPDATE_WHERE_JUMPED= "UpdateWhereJumped";
  private static final String UPDATE_WHERE_LEFT_AND_CLICKED= "UpdateWhereLeftAndClicked";

  @Test
  void badBoardMoveUpdateCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> boardMoveUpdateFactory.getBoardMoveUpdate(BAD_BOARD_MOVE_UPDATE));
  }

  @Test
  void updateBottomRowCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    boardMoveUpdateFactory.getBoardMoveUpdate(UPDATE_BOTTOM_ROW);
  }

  @Test
  void updateLastColumnCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    boardMoveUpdateFactory.getBoardMoveUpdate(UPDATE_LAST_COLUMN);
  }

  @Test
  void updateWhereClickedCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    boardMoveUpdateFactory.getBoardMoveUpdate(UPDATE_WHERE_CLICKED);
  }

  @Test
  void updateWhereJumpedCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    boardMoveUpdateFactory.getBoardMoveUpdate(UPDATE_WHERE_JUMPED);
  }

  @Test
  void updateWhereLeftAndClickedCreationTest() {
    BoardMoveUpdateFactory boardMoveUpdateFactory = new BoardMoveUpdateFactory();
    boardMoveUpdateFactory.getBoardMoveUpdate(UPDATE_WHERE_LEFT_AND_CLICKED);
  }
}
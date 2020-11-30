package ooga.model.gameBuildingBlocks.winConditions;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class WinConditionFactoryTest {

  private static final String BAD_WIN_CONDITION = "Bad win condition";
  private static final String COLUMN_WIN = "ColumnWin";
  private static final String DIAGONAL_WIN= "DiagonalWin";
  private static final String MOST_PIECES_WIN= "MostPiecesWin";
  private static final String NO_MORE_PIECES_WIN= "NoMorePiecesWin";
  private static final String ROW_WIN= "RowWin";

  @Test
  void badWinConditionNoParameterCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> winConditionFactory.getWinCondition(BAD_WIN_CONDITION));
  }

  @Test
  void badWinConditionWithOneParameterCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> winConditionFactory.getWinCondition(BAD_WIN_CONDITION, 1));
  }

  @Test
  void columnWinCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    winConditionFactory.getWinCondition(COLUMN_WIN, 1);
  }

  @Test
  void diagonalWinCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    winConditionFactory.getWinCondition(DIAGONAL_WIN, 1);
  }

  @Test
  void mostPiecesWinCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    winConditionFactory.getWinCondition(MOST_PIECES_WIN);
  }

  @Test
  void noMorePiecesWinCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    winConditionFactory.getWinCondition(NO_MORE_PIECES_WIN);
  }

  @Test
  void rowWinCreationTest() {
    WinConditionFactory winConditionFactory = new WinConditionFactory();
    winConditionFactory.getWinCondition(ROW_WIN, 1);
  }

}
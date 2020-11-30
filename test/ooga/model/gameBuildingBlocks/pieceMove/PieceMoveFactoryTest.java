package ooga.model.gameBuildingBlocks.pieceMove;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class PieceMoveFactoryTest {

  private static final String ANYWHERE_EMPTY_CELL_MOVE = "AnywhereEmptyCellMove";
  private static final String ANYWHERE_EMPTY_COLUMN_MOVE = "AnywhereEmptyColumnMove";
  private static final String ANYWHERE_EMPTY_ROW_MOVE= "AnywhereEmptyRowMove";
  private static final String DIAGONAL_LEFT_MOVE= "DiagonalLeftMove";
  private static final String DIAGONAL_RIGHT_MOVE= "DiagonalRightMove";
  private static final String JUMP_DIAGONAL_LEFT_MOVE= "JumpDiagonalLeftMove";
  private static final String JUMP_DIAGONAL_RIGHT_MOVE= "JumpDiagonalRightMove";
  private static final String OMNIDIRECTIONAL_MOVE= "OmnidirectionalMove";

  private static final String BAD_PIECE_MOVE = "Bad Piece Move";

  @Test
  void badPieceMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> pieceMoveFactory.getPieceMove(BAD_PIECE_MOVE));
  }

  @Test
  void anywhereEmptyCellMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(ANYWHERE_EMPTY_CELL_MOVE);
  }

  @Test
  void anywhereEmptyColumnMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(ANYWHERE_EMPTY_COLUMN_MOVE);
  }

  @Test
  void anywhereEmptyRowMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(ANYWHERE_EMPTY_ROW_MOVE);
  }

  @Test
  void diagonalLeftMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(DIAGONAL_LEFT_MOVE);
  }

  @Test
  void jumpDiagonalLeftMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(JUMP_DIAGONAL_LEFT_MOVE);
  }

  @Test
  void jumpDiagonalRightMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(JUMP_DIAGONAL_RIGHT_MOVE);
  }

  @Test
  void OmnidirectionalMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(OMNIDIRECTIONAL_MOVE);
  }

  @Test
  void diagonalRightMoveCreationTest() {
    PieceMoveFactory pieceMoveFactory = new PieceMoveFactory();
    pieceMoveFactory.getPieceMove(DIAGONAL_RIGHT_MOVE);
  }


}
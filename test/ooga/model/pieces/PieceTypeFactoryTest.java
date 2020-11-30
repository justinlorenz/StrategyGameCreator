package ooga.model.pieces;

import static org.junit.jupiter.api.Assertions.*;

import ooga.exception.ClassDoesNotExistException;
import org.junit.jupiter.api.Test;

class PieceTypeFactoryTest {

  private static final String BAD_PIECE_TYPE = "Bad piece type";
  private static final String EMPTY_PIECE = "Empty";
  private static final String NORMAL_PIECE= "NormalPiece";

  @Test
  void badPieceTypeCreationTest() {
    PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
    assertThrows(ClassDoesNotExistException.class, ()-> pieceTypeFactory.getPieceType(BAD_PIECE_TYPE,0,0,0));
  }

  @Test
  void emptyPieceCreationTest() {
    PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
    pieceTypeFactory.getPieceType(EMPTY_PIECE, 0,0,0);
  }

  @Test
  void normalPieceCreationTest() {
    PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
    pieceTypeFactory.getPieceType(NORMAL_PIECE, 0,0,0);
  }

}
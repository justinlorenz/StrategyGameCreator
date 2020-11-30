package ooga.model;

import static org.junit.jupiter.api.Assertions.*;

import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;

import org.junit.jupiter.api.Test;

class BoardStructureTest {

  private static final int GRID_SIZE = 3;
  private static final String PIECE_ID = "Piece";

  @Test
  void getRowsTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(GRID_SIZE, boardStructure.getRows());
  }

  @Test
  void getColsTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(GRID_SIZE, boardStructure.getCols());
  }

  @Test
  void getPieceTypeTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(new NormalPiece(1, PIECE_ID,0,0), boardStructure.getPieceType(0,0));
  }

  @Test
  void getPieceIdTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(new NormalPiece(1, PIECE_ID,0,0).getPieceId(), boardStructure.getPieceId(0,0));
  }

  @Test
  void getPiecePlayerNumberTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(new NormalPiece(1, PIECE_ID,0,0).getPlayerNumber(), boardStructure.getPlayerNumber(0,0));
  }

  @Test
  void setCellStateTest() {
    BoardStructure boardStructure = new BoardStructure(createStateArray());
    assertEquals(new NormalPiece(1, PIECE_ID,0,0), boardStructure.getPieceType(0,0));
    boardStructure.setCellState(0,0,new EmptyPiece(0,0));
    assertEquals(new EmptyPiece(0,0), boardStructure.getPieceType(0,0));
  }

  private PieceBoardStructure createStateArray() {
    PieceBoardStructure pieceBoardStructure = new PieceBoardStructure(GRID_SIZE);
    for (int i = 0; i < pieceBoardStructure.getGridSize(); i++) {
      for (int j = 0; j < pieceBoardStructure.getGridSize(); j++) {
        pieceBoardStructure.addNewPiece(new NormalPiece(1, PIECE_ID,i,j), i, j);
      }
    }
    return pieceBoardStructure;
  }

}
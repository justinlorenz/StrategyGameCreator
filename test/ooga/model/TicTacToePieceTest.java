package ooga.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class TicTacToePieceTest {



  private static final int PLAYER1 = 1;
  private static final int PLAYER2 = 1;

  private static final int TEST_ROWS = 3;
  private static final int TEST_COLS = 3;
  private static final int TEST_GRID_SIZE = 9;

  private static final String PIECE_ID = "Piece";


//  @Test
//  void getPossibleMovesOnEmptyGridTest() {
//    TicTacToePiece piece = new TicTacToePiece(PLAYER1, PIECE_ID,0,0);
//
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new EmptyPiece(0,0));
//
//    assertEquals(TEST_GRID_SIZE, piece.getPossibleMoves(boardStructure).size());
//
//  }
//
//  @Test
//  void getPossibleMovesOneFreeCellTest() {
//    TicTacToePiece piece = new TicTacToePiece(PLAYER1, PIECE_ID,0,0);
//
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new TicTacToePiece(PLAYER1,PIECE_ID,0,0));
//    when(boardStructure.getPieceType(0, 0)).thenReturn(new EmptyPiece(0,0));
//
//    assertEquals(1, piece.getPossibleMoves(boardStructure).size());
//    assertEquals(0, piece.getPossibleMoves(boardStructure).get(0).getI());
//    assertEquals(0, piece.getPossibleMoves(boardStructure).get(0).getJ());
//
//  }


}
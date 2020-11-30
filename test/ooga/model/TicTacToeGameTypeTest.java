//package ooga.model;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import ooga.model.Pieces.EmptyPiece;
//import org.junit.jupiter.api.Test;
//
//class TicTacToeGameTypeTest {
//
//  private static final int PLAYER1 = 1;
//  private static final int PLAYER2 = 2;
//
//  private static final int TEST_ROWS = 3;
//  private static final int TEST_COLS = 3;
//  private static final int TEST_GRID_SIZE = 9;
//
//  private static final String PIECE_ID = "Piece";
//  private static final String TIC_TAC_TOE = "TicTacToe";
//
//  @Test
//  void isDrawTrueTest() {
//
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new TicTacToePiece(PLAYER1,PIECE_ID,0,0));
//    assertTrue(ticTacToeGameType.isDraw());
//  }
//
//  @Test
//  void isDrawFalseTest() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new TicTacToePiece(PLAYER1,PIECE_ID,0,0));
//    when(boardStructure.getPieceType(0, 0)).thenReturn(new EmptyPiece(0,0));
//    assertFalse(ticTacToeGameType.isDraw());
//  }
//
//  @Test
//  void makeValidMoveTest1() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new TicTacToePiece(PLAYER1,PIECE_ID,0,0));
//    when(boardStructure.getPieceType(0, 0)).thenReturn(new EmptyPiece(0,0));
//    assertTrue(ticTacToeGameType.makeGameMove(0,0, PLAYER1));
//  }
//
//  @Test
//  void makeValidMoveTest2() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new TicTacToePiece(PLAYER2,PIECE_ID,0,0));
//    when(boardStructure.getPieceType(0, 0)).thenReturn(new EmptyPiece(0,0));
//    assertTrue(ticTacToeGameType.makeGameMove(0,0, PLAYER1));
//  }
//
//  @Test
//  void makeInvalidMoveTest() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new EmptyPiece(0,0));
//    when(boardStructure.getPieceType(0, 0)).thenReturn(new TicTacToePiece(PLAYER1, PIECE_ID,0,0));
//    assertFalse(ticTacToeGameType.makeGameMove(0,0, PLAYER2));
//  }
//
//  @Test
//  void diagonalVictoryTest() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new EmptyPiece(0,0));
//    for(int i = 0; i<= TEST_ROWS; i++) {
//      when(boardStructure.getPieceType(i, i)).thenReturn(new TicTacToePiece(PLAYER2, PIECE_ID,0,0));
//    }
//    assertTrue(ticTacToeGameType.isGameWon());
//  }
//
//  @Test
//  void colVictoryTest() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new EmptyPiece(0,0));
//    for(int i = 0; i<= TEST_COLS; i++) {
//      when(boardStructure.getPieceType(0, i)).thenReturn(new TicTacToePiece(PLAYER2, PIECE_ID,0,0));
//    }
//    assertTrue(ticTacToeGameType.isGameWon());
//  }
//
//  @Test
//  void rowVictoryTest() {
//    BoardStructure boardStructure = mock(BoardStructure.class);
//    TicTacToeGameType ticTacToeGameType = new TicTacToeGameType(TIC_TAC_TOE, boardStructure);
//    when(boardStructure.getRows()).thenReturn(TEST_ROWS);
//    when(boardStructure.getCols()).thenReturn(TEST_COLS);
//    when(boardStructure.getPieceType(anyInt(), anyInt())).thenReturn(new EmptyPiece(0,0));
//    for(int i = 0; i<= TEST_COLS; i++) {
//      when(boardStructure.getPieceType(i, 0)).thenReturn(new TicTacToePiece(PLAYER2, PIECE_ID,0,0));
//    }
//    assertTrue(ticTacToeGameType.isGameWon());
//  }
//
//}
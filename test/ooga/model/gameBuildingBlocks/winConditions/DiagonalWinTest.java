package ooga.model.gameBuildingBlocks.winConditions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class DiagonalWinTest {

  @Test
  void gamePlayer1Won3x3DiagonalTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "NormalPiece 1"
    )));
    DiagonalWin diagonalWin = new DiagonalWin(3);
    assertTrue(diagonalWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(1, diagonalWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gamePlayer2Won3x3DiagonalTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "NormalPiece 2",
        "Empty", "NormalPiece 2", "Empty",
        "NormalPiece 2", "Empty", "Empty"
    )));
    DiagonalWin diagonalWin = new DiagonalWin(3);
    assertTrue(diagonalWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, diagonalWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gamePlayerWon4InARowDiagonalTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "NormalPiece 2", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    DiagonalWin digaonalWin = new DiagonalWin(4);
    assertTrue(digaonalWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, digaonalWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gameNoWin5InARowDiagonalTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "NormalPiece 2", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    DiagonalWin diagonalWin = new DiagonalWin(5);
    assertFalse(diagonalWin.getWinStatus(boardStructure).isGameWon());
  }

  @Test
  void gameNoPlayerWon3x3DiagonalTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 2", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "NormalPiece 2"
    )));
    DiagonalWin diagonalWin = new DiagonalWin(3);
    assertFalse(diagonalWin.getWinStatus(boardStructure).isGameWon());
  }

  private PieceBoardStructure createPieceBoardStructure(int size, List<String> piecesInfo) {
    PieceBoardStructure pieceBoardStructure = new PieceBoardStructure(size);
    PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
    int piecesIndex = 0;
    for(int i = 0; i<pieceBoardStructure.getGridSize(); i++) {
      for (int j = 0; j < pieceBoardStructure.getGridSize(); j++) {
        String[] pieceInfo = piecesInfo.get(piecesIndex).split(" ");
        String pieceType = pieceInfo[0];
        int playerNumber = 0;
        if(pieceInfo.length>1) {
          playerNumber = Integer.parseInt(pieceInfo[1]);
        }
        pieceBoardStructure
            .addNewPiece(pieceTypeFactory.getPieceType(pieceType, playerNumber, 0, 0), i, j);
        piecesIndex++;
      }
    }
    return pieceBoardStructure;
  }

}
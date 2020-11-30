package ooga.model.gameBuildingBlocks.winConditions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class NoMorePiecesWinTest {

  @Test
  void playerOneWinsTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    NoMorePiecesWin noMorePiecesWin = new NoMorePiecesWin();
    assertTrue(noMorePiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(1, noMorePiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void playerTwoWinsTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    NoMorePiecesWin noMorePiecesWin = new NoMorePiecesWin();
    assertTrue(noMorePiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, noMorePiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void noPiecesLeftOnBoardDrawTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    NoMorePiecesWin noMorePiecesWin = new NoMorePiecesWin();
    assertFalse(noMorePiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(0, noMorePiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void bothPlayersHavePiecesDrawTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2",
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    NoMorePiecesWin noMorePiecesWin = new NoMorePiecesWin();
    assertFalse(noMorePiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(0, noMorePiecesWin.getWinStatus(boardStructure).getWinningPlayer());
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
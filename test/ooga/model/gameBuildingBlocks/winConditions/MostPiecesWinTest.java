package ooga.model.gameBuildingBlocks.winConditions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class MostPiecesWinTest {

  @Test
  void playerOneWinsTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1",
        "NormalPiece 2", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty"
    )));
    MostPiecesWin mostPiecesWin = new MostPiecesWin();
    assertTrue(mostPiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(1, mostPiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void playerTwoWinsTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "NormalPiece 1", "Empty",
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2",
        "Empty", "Empty", "Empty"
    )));
    MostPiecesWin mostPiecesWin = new MostPiecesWin();
    assertTrue(mostPiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, mostPiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void drawTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1",
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2",
        "Empty", "Empty", "Empty"
    )));
    MostPiecesWin mostPiecesWin = new MostPiecesWin();
    assertFalse(mostPiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(0, mostPiecesWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void onlyPlayer1PiecesOnBoardTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    MostPiecesWin mostPiecesWin = new MostPiecesWin();
    assertTrue(mostPiecesWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(1, mostPiecesWin.getWinStatus(boardStructure).getWinningPlayer());
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
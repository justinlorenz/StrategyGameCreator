package ooga.model.gameBuildingBlocks.winConditions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class RowWinTest {


  @Test
  void gamePlayer1Won3x3RowTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    RowWin rowWin = new RowWin(3);
    assertTrue(rowWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(1, rowWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gamePlayer2Won3x3RowTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2"
    )));
    RowWin rowWin = new RowWin(3);
    assertTrue(rowWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, rowWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gamePlayerWon4InARowColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    RowWin rowWin = new RowWin(4);
    assertTrue(rowWin.getWinStatus(boardStructure).isGameWon());
    assertEquals(2, rowWin.getWinStatus(boardStructure).getWinningPlayer());
  }

  @Test
  void gameNoWin5InARowColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    RowWin rowWin = new RowWin(5);
    assertFalse(rowWin.getWinStatus(boardStructure).isGameWon());
  }

  @Test
  void gameNoPlayerWon3x3ColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "NormalPiece 1", "NormalPiece 2"
    )));
    RowWin rowWin = new RowWin(3);
    assertFalse(rowWin.getWinStatus(boardStructure).isGameWon());
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
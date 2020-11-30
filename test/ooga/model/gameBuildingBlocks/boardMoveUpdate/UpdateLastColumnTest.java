package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class UpdateLastColumnTest {

  @Test
  void placePlayer1PieceOnEmptyRowRightMostColClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateLastColumn updateLastColumn = new UpdateLastColumn();
    updateLastColumn.updateBoard(boardStructure,1,0,0, 0,5);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,5), boardStructure.getPieceType(0,5));
  }

  @Test
  void placePlayer2PieceOnEmptyRowRightMostColClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateLastColumn updateLastColumn = new UpdateLastColumn();
    updateLastColumn.updateBoard(boardStructure,2,0,0, 0,5);
    assertEquals(new NormalPiece(2, "NormalPiece", 0,5), boardStructure.getPieceType(0,5));
  }

  @Test
  void placePieceOnRowWithPiecesClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2", "NormalPiece 2",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateLastColumn updateLastColumn = new UpdateLastColumn();
    updateLastColumn.updateBoard(boardStructure,2,0,0, 0,0);
    for(int i = 1; i < boardStructure.getCols(); i++) {
      for(int j= 0; j < boardStructure.getRows(); j++) {
        assertEquals(new EmptyPiece(0,0), boardStructure.getPieceType(i,j));
      }
    }
  }

  @Test
  void placePlayer1PieceOnEmptyLeftRightMostColClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateLastColumn updateLastColumn = new UpdateLastColumn();
    updateLastColumn.updateBoard(boardStructure,1,0,0, 0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,5), boardStructure.getPieceType(0,5));
  }



  @Test
  void placePieceOn3x3ColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateLastColumn updateLastColumn = new UpdateLastColumn();
    updateLastColumn.updateBoard(boardStructure,1,0,0, 0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,2), boardStructure.getPieceType(0,2));
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
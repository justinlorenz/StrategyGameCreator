package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class UpdateBottomRowTest {

  @Test
  void placePlayer1PieceOnEmptyColumnBottomMostRowClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,1,0,0, 5,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 5,0), boardStructure.getPieceType(5,0));
  }

  @Test
  void placePlayer2PieceOnEmptyColumnBottomMostRowClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,2,0,0, 5,0);
    assertEquals(new NormalPiece(2, "NormalPiece", 5,0), boardStructure.getPieceType(5,0));
  }

  @Test
  void placePieceOnEmptyColumnTopMostRowClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,1,0,0, 0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 5,0), boardStructure.getPieceType(5,0));
  }

  @Test
  void placePieceOnColumnWithPiecesClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty","Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 2", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 2", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 2", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,1,0,0, 0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 2,0), boardStructure.getPieceType(2,0));
  }

  @Test
  void placePieceOnFullColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(6, List.of(
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty","Empty",
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,1,0,0, 0,0);
    for(int i = 0; i < boardStructure.getCols(); i++) {
      for(int j= 1; j < boardStructure.getRows(); j++) {
        assertEquals(new EmptyPiece(0,0), boardStructure.getPieceType(i,j));
      }
    }
  }

  @Test
  void placePieceOn3x3ColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateBottomRow updateBottomRow = new UpdateBottomRow();
    updateBottomRow.updateBoard(boardStructure,1,0,0, 0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 2,0), boardStructure.getPieceType(2,0));
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
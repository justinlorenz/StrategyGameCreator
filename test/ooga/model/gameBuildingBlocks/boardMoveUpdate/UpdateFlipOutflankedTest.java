package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class UpdateFlipOutflankedTest {

  @Test
  void player1OutFlanksSinglePlayer2PieceOnColumnClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 2,2);
    assertEquals(new NormalPiece(1, "NormalPiece", 1,2), boardStructure.getPieceType(1,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
  }

  @Test
  void player2OutFlanksSinglePlayer1PieceOnColumnClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,2,0,0, 2,2);
    assertEquals(new NormalPiece(2, "NormalPiece", 1,2), boardStructure.getPieceType(1,2));
    assertEquals(new NormalPiece(2, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
  }

  @Test
  void player1OutFlanksMultiplePlayer2PieceOnColumnClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 1,2);
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 2,2);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,2), boardStructure.getPieceType(0,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,2), boardStructure.getPieceType(1,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 3,2), boardStructure.getPieceType(3,2));
  }

  @Test
  void player1OutFlanksSinglePlayer2PieceOnRightDiagonalClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 2,2);
    assertEquals(new NormalPiece(1, "NormalPiece", 1,3), boardStructure.getPieceType(1,3));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 3,1), boardStructure.getPieceType(3,1));
  }

  @Test
  void player1OutFlanksSinglePlayer2PieceOnLeftDiagonalClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty", "Empty", "Empty",
        "Empty", "Empty", "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 2,2);
    assertEquals(new NormalPiece(1, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 3,3), boardStructure.getPieceType(3,3));
  }

  @Test
  void player1OutFlanksSinglePlayer2PieceOnRowClicked() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "NormalPiece 2", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateFlipOutflanked updateFlipOutflanked = new UpdateFlipOutflanked();
    updateFlipOutflanked.updateBoard(boardStructure,1,0,0, 2,2);
    assertEquals(new NormalPiece(1, "NormalPiece", 2,1), boardStructure.getPieceType(2,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,2), boardStructure.getPieceType(2,2));
    assertEquals(new NormalPiece(1, "NormalPiece", 2,3), boardStructure.getPieceType(2,3));
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
package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import ooga.model.pieces.SpecialPiece;
import org.junit.jupiter.api.Test;

class UpdateWhereLeftAndClickedTest {

  @Test
  void player1PieceMoved() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereLeftAndClicked updateWhereLeftAndClicked = new UpdateWhereLeftAndClicked();
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,0), boardStructure.getPieceType(1,0));
    updateWhereLeftAndClicked.updateBoard(boardStructure,1, 1,0,1,1);
    assertEquals(new EmptyPiece( 0,1), boardStructure.getPieceType(0,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
  }

  @Test
  void player2PieceMoved() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereLeftAndClicked updateWhereLeftAndClicked = new UpdateWhereLeftAndClicked();
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
    assertEquals(new NormalPiece(2, "NormalPiece", 1,0), boardStructure.getPieceType(1,0));
    updateWhereLeftAndClicked.updateBoard(boardStructure,2, 1,0,1,1);
    assertEquals(new EmptyPiece( 0,1), boardStructure.getPieceType(0,1));
    assertEquals(new NormalPiece(2, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
  }

  @Test
  void player1PieceMoved2Spots() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereLeftAndClicked updateWhereLeftAndClicked = new UpdateWhereLeftAndClicked();
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,0), boardStructure.getPieceType(1,0));
    updateWhereLeftAndClicked.updateBoard(boardStructure,1, 1,0,1,2);
    assertEquals(new EmptyPiece( 0,1), boardStructure.getPieceType(0,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,2), boardStructure.getPieceType(1,2));
  }

  @Test
  void player1PieceClickedAtSameSpot() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereLeftAndClicked updateWhereLeftAndClicked = new UpdateWhereLeftAndClicked();
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
    assertEquals(new NormalPiece(1, "NormalPiece", 1,0), boardStructure.getPieceType(1,0));
    updateWhereLeftAndClicked.updateBoard(boardStructure,1, 1,0,1,0);
    assertEquals(new EmptyPiece( 1,0), boardStructure.getPieceType(1,0));
  }

  @Test
  void player1SpecialPieceMoved() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "SpecialPiece 1", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateWhereLeftAndClicked updateWhereLeftAndClicked = new UpdateWhereLeftAndClicked();
    assertEquals(new SpecialPiece( 1,"SpecialPiece", 0,0), boardStructure.getPieceType(0,0));
    assertEquals(new EmptyPiece(0,1), boardStructure.getPieceType(0,1));
    updateWhereLeftAndClicked.updateBoard(boardStructure,1, 0,0,0,1);
    assertEquals(new EmptyPiece( 0,0), boardStructure.getPieceType(0,0));
    assertEquals(new SpecialPiece( 1,"SpecialPiece", 0,1), boardStructure.getPieceType(0,1));
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
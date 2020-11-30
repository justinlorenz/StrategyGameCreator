package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class UpdateWhereClickedTest {

  @Test
  void player1ClicksEmptyCell() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereClicked updateWhereClicked = new UpdateWhereClicked();
    updateWhereClicked.updateBoard(boardStructure, 1, 0,0,0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,0), boardStructure.getPieceType(0,0));
  }

  @Test
  void player2ClicksEmptyCell() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereClicked updateWhereClicked = new UpdateWhereClicked();
    updateWhereClicked.updateBoard(boardStructure, 2, 0,0,0,0);
    assertEquals(new NormalPiece(2, "NormalPiece", 0,0), boardStructure.getPieceType(0,0));
  }

  @Test
  void player1ClicksEmptyCell5x5() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateWhereClicked updateWhereClicked = new UpdateWhereClicked();
    updateWhereClicked.updateBoard(boardStructure, 1, 0,0,0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,0), boardStructure.getPieceType(0,0));
  }

  @Test
  void player1ClicksPlayer2Cell5x5() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(5, List.of(
        "NormalPiece 2", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty", "Empty", "Empty"
    )));
    UpdateWhereClicked updateWhereClicked = new UpdateWhereClicked();
    assertEquals(new NormalPiece(2, "NormalPiece", 0,0), boardStructure.getPieceType(0,0));
    updateWhereClicked.updateBoard(boardStructure, 1, 0,0,0,0);
    assertEquals(new NormalPiece(1, "NormalPiece", 0,0), boardStructure.getPieceType(0,0));
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
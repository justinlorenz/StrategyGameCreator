package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

class UpdateWhereJumpedTest {

  @Test
  void player1JumpsOverPlayer2Diagonal() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereJumped updateWhereJumped = new UpdateWhereJumped();
    assertEquals(new NormalPiece(2, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
    updateWhereJumped.updateBoard(boardStructure, 1,0,0,2,2);
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
  }

  @Test
  void player2JumpsOverPlayer1Diagonal() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 2", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereJumped updateWhereJumped = new UpdateWhereJumped();
    assertEquals(new NormalPiece(1, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
    updateWhereJumped.updateBoard(boardStructure, 1,0,0,2,2);
    assertEquals(new EmptyPiece( 1,1), boardStructure.getPieceType(1,1));
  }

  @Test
  void Player1JumpsOverPlayer2SideWays() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 2", "NormalPiece 1",
        "Empty", "Empty", "Empty"
    )));
    UpdateWhereJumped updateWhereJumped = new UpdateWhereJumped();
    assertEquals(new NormalPiece(2, "NormalPiece", 1,1), boardStructure.getPieceType(1,1));
    updateWhereJumped.updateBoard(boardStructure, 1,1,2,1,0);
    assertEquals(new EmptyPiece(1,1), boardStructure.getPieceType(1,1));
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
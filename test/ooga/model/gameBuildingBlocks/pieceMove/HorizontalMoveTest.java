package ooga.model.gameBuildingBlocks.pieceMove;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.PossibleMove;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class HorizontalMoveTest {
  @Test
  void HorizontalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty"
    )));
    PieceMove pieceMove = new HorizontalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,2,1,1);
    assertTrue(validMoves.contains(new PossibleMove(2,0)));
  }


  @Test
  void InvalidHorizontalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "Empty",
        "NormalPiece 1", "NormalPiece 1", "Empty"
    )));
    PieceMove pieceMove = new HorizontalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,2,1,1);
    assertTrue(!validMoves.contains(new PossibleMove(2,0)));
    assertTrue(!validMoves.contains(new PossibleMove(2,2)));
  }

  @Test
  void OtherTeamHorizontalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty"
    )));
    PieceMove pieceMove = new HorizontalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,1,1,2);
    assertTrue(validMoves.contains(new PossibleMove(1,2)));
    assertTrue(!validMoves.contains(new PossibleMove(1,0)));
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
            .addNewPiece(pieceTypeFactory.getPieceType(pieceType, playerNumber, i, j), i, j);
        piecesIndex++;
      }
    }
    return pieceBoardStructure;
  }
}

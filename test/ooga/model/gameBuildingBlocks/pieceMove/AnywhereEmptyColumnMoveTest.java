package ooga.model.gameBuildingBlocks.pieceMove;

import static ooga.model.gameBuildingBlocks.pieceMove.PieceMove.NON_USED_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.PossibleMove;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class AnywhereEmptyColumnMoveTest {
  @Test
  void AnywhereEmptyColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "NormalPiece 1", "Empty"
    )));
    PieceMove pieceMove = new AnywhereEmptyColumnMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,NON_USED_PARAMETER,NON_USED_PARAMETER,1);
    assertTrue(validMoves.contains(new PossibleMove(0,0)));
    assertTrue(validMoves.contains(new PossibleMove(1,0)));
    assertTrue(validMoves.contains(new PossibleMove(2,0)));
  }


  @Test
  void AnywhereNonEmptyColumnTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "NormalPiece 1", "Empty"
    )));
    PieceMove pieceMove = new AnywhereEmptyColumnMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,NON_USED_PARAMETER,NON_USED_PARAMETER,1);
    assertTrue(!validMoves.contains(new PossibleMove(0,1)));
    assertTrue(!validMoves.contains(new PossibleMove(1,1)));
    assertTrue(!validMoves.contains(new PossibleMove(2,1)));
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

package ooga.model.gameBuildingBlocks.pieceMove;

import static ooga.model.gameBuildingBlocks.pieceMove.PieceMove.NON_USED_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.PossibleMove;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class AnywhereEmptyCellMoveTest {
  @Test
  void AnywhereEmptyCellTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty"
    )));
    PieceMove pieceMove = new AnywhereEmptyCellMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,NON_USED_PARAMETER,NON_USED_PARAMETER,1);
    assertTrue(validMoves.contains(new PossibleMove(0,0)));
  }


  @Test
  void NotConnectedCellTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty"
    )));
    PieceMove pieceMove = new AnywhereConnectedPieceMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,NON_USED_PARAMETER,NON_USED_PARAMETER,1);
    assertTrue(!validMoves.contains(new PossibleMove(1,1)));
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

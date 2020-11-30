package ooga.model.gameBuildingBlocks.validMoveChecks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class OutflankRowCheckTest {
  @Test
  void OutflankRowTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "NormalPiece 2", "Empty",
        "NormalPiece 1", "NormalPiece 2", "NormalPiece 1"
    )));
    ValidMoveCheck outflankRowCheck = new OutflankRowCheck(1);
    assertTrue(outflankRowCheck.isValidMove(boardStructure,0,0,2,0));
  }

  @Test
  void NoOutflankCheckTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "NormalPiece 2", "Empty",
        "NormalPiece 1", "Empty", "NormalPiece 1"
    )));
    ValidMoveCheck outflankRowCheck = new OutflankRowCheck(1);
    assertTrue(!outflankRowCheck.isValidMove(boardStructure,0,0,2,0));
  }

  @Test
  void NoOutflankSameTeamCheckTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "NormalPiece 2", "NormalPiece 2", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    ValidMoveCheck outflankRowCheck = new OutflankRowCheck(1);
    assertTrue(!outflankRowCheck.isValidMove(boardStructure,0,0,2,0));
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

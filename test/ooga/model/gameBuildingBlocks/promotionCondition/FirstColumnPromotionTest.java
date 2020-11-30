package ooga.model.gameBuildingBlocks.promotionCondition;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class FirstColumnPromotionTest {
  @Test
  void FirstColPromotionTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "NormalPiece 1",
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    PromotionCondition promotionCondition = new FirstColumnPromotion();
    promotionCondition.piecePromotionUpdate(boardStructure,0,0);
    promotionCondition.piecePromotionUpdate(boardStructure,2,0);
    assertTrue(boardStructure.getPieceType(0,0).isSpecial());
    assertTrue(boardStructure.getPieceType(2,0).isSpecial());
  }

  @Test
  void MultipleTeamFirstColPromotionTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "NormalPiece 1",
        "NormalPiece 2", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    PromotionCondition promotionCondition = new FirstColumnPromotion();
    promotionCondition.piecePromotionUpdate(boardStructure,0,0);
    promotionCondition.piecePromotionUpdate(boardStructure,1,0);
    promotionCondition.piecePromotionUpdate(boardStructure,2,0);
    assertTrue(boardStructure.getPieceType(0,0).isSpecial());
    assertTrue(boardStructure.getPieceType(1,0).isSpecial());
    assertTrue(boardStructure.getPieceType(2,0).isSpecial());
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

package ooga.model.gameBuildingBlocks.validMoveChecks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class EmptyColumnCheckTest {
  @Test
  void EmptyColumnCheckTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    ValidMoveCheck emptyColumnCheck = new EmptyColumnCheck();
    assertTrue(emptyColumnCheck.isValidMove(boardStructure,0,0,0,0));
  }

  @Test
  void NonEmptyColumnCheckTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "Empty",
        "NormalPiece 1", "Empty", "Empty",
        "NormalPiece 1", "NormalPiece 1", "NormalPiece 1"
    )));
    ValidMoveCheck emptyCellCheck = new EmptyCellCheck();
    assertTrue(!emptyCellCheck.isValidMove(boardStructure,0,0,0,0));
  }


  @Test
  void VaryingTeamNonEmptyColumnCheckTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "SpecialPiece 2", "Empty", "Empty",
        "NormalPiece 2", "Empty", "Empty",
        "SpecialPiece 1", "Empty", "NormalPiece 1"
    )));
    ValidMoveCheck emptyCellCheck = new EmptyCellCheck();
    assertTrue(!emptyCellCheck.isValidMove(boardStructure,0,0,0,0));
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

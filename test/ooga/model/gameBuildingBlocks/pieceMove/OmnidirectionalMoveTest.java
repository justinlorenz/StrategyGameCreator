package ooga.model.gameBuildingBlocks.pieceMove;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PieceBoardStructure;
import ooga.model.PossibleMove;
import ooga.model.pieces.PieceTypeFactory;
import org.junit.jupiter.api.Test;

public class OmnidirectionalMoveTest {
  @Test
  void OmnidirectionalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "Empty", "Empty", "Empty"
    )));
    PieceMove pieceMove = new OmnidirectionalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,1,1,1);
    assertTrue(validMoves.contains(new PossibleMove(0,0)));
    assertTrue(validMoves.contains(new PossibleMove(0,2)));
    assertTrue(validMoves.contains(new PossibleMove(2,0)));
    assertTrue(validMoves.contains(new PossibleMove(2,2)));
  }

  @Test
  void BlockedOmnidirectionalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 2", "Empty", "NormalPiece 2",
        "Empty", "NormalPiece 1", "Empty",
        "NormalPiece 2", "Empty", "NormalPiece 2"
    )));
    PieceMove pieceMove = new OmnidirectionalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,1,1,1);
    assertTrue(!validMoves.contains(new PossibleMove(0,0)));
    assertTrue(!validMoves.contains(new PossibleMove(0,2)));
    assertTrue(!validMoves.contains(new PossibleMove(2,0)));
    assertTrue(!validMoves.contains(new PossibleMove(2,2)));
  }

  @Test
  void JumpOmnidirectionalMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "NormalPiece 1", "Empty", "Empty",
        "Empty", "NormalPiece 2", "Empty",
        "Empty", "Empty", "Empty"
    )));
    PieceMove pieceMove = new OmnidirectionalMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,0,0,1);
    assertTrue(validMoves.contains(new PossibleMove(2,2)));
  }

  @Test
  void NoJumpDiagonalRightMoveTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "Empty", "Empty",
        "NormalPiece 1", "Empty", "NormalPiece 1"
    )));
    PieceMove pieceMove = new JumpDiagonalRightMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,2,0,1);
    assertTrue(!validMoves.contains(new PossibleMove(0,2)));
  }

  @Test
  void NoJumpDiagonalRightMoveSameTeatTest() {
    BoardStructure boardStructure = new BoardStructure(createPieceBoardStructure(3, List.of(
        "Empty", "Empty", "Empty",
        "Empty", "NormalPiece 1", "Empty",
        "NormalPiece 1", "Empty", "NormalPiece 1"
    )));
    PieceMove pieceMove = new JumpDiagonalRightMove();
    List<PossibleMove> validMoves = pieceMove.getValidMoves(boardStructure,2,0,1);
    assertTrue(!validMoves.contains(new PossibleMove(0,2)));
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

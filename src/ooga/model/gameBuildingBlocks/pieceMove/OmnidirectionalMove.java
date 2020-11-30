package ooga.model.gameBuildingBlocks.pieceMove;

import java.util.ArrayList;
import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PossibleMove;
import ooga.model.pieces.Piece;

/**
 * PieceMove building block which gets all moves that allow piece to move and jump diagonally in upwards
 * and downwards direction.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class OmnidirectionalMove extends PieceMove {

  /**
   * Gets all valid moves for given piece coordinates on boardStructure
   *
   * @param currBoard BoardStructure of the board to be updated
   * @param pieceI I coordinate of Piece to be moved
   * @param pieceJ J coordinate of Piece to be moved
   * @param playerNum Player number making the move
   * @return List of all PossibleMoves that can be played for given piece coordinates
   */
  @Override
  public List<PossibleMove> getValidMoves(BoardStructure currBoard, int pieceI, int pieceJ,
      int playerNum) {
    Piece originalPiece = currBoard.getPieceType(pieceI, pieceJ);
    if (originalPiece.getPlayerNumber() == 1) {
      currBoard.getPieceType(pieceI, pieceJ).addNewDirection(1);
    } else {
      currBoard.getPieceType(pieceI, pieceJ).addNewDirection(-1);
    }
    List<PieceMove> allPieceMoves = List
        .of(new DiagonalLeftMove(), new DiagonalRightMove(), new JumpDiagonalLeftMove(),
            new JumpDiagonalRightMove());
    List<PossibleMove> omnidirectionalMoves = new ArrayList<>();
    for (PieceMove pieceMove : allPieceMoves) {
      omnidirectionalMoves.addAll(pieceMove.getValidMoves(currBoard, pieceI, pieceJ, playerNum));
    }
    currBoard.setPieceType(originalPiece);
    return omnidirectionalMoves;
  }

}

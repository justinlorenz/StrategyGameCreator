package ooga.model.gameBuildingBlocks.pieceMove;

import java.util.ArrayList;
import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PossibleMove;
import ooga.model.gameBuildingBlocks.validMoveChecks.EmptyCellCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.JumpCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutOfBoundsCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.ValidMoveCheck;
import ooga.model.pieces.Piece;

/**
 * PieceMove building block which gets all moves that allow piece to jump over vertically
 * in the players direction.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class JumpVerticalMove extends PieceMove {

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
    Piece currPiece = currBoard.getPieceType(pieceI, pieceJ);
    List<Integer> directionList = currPiece.getDirection();
    List<PossibleMove> possibleMoves = new ArrayList<>();
    for (int direction : directionList) {
      PossibleMove move = new PossibleMove(pieceI + direction * 2, pieceJ);
      List<ValidMoveCheck> validMoveChecks = List
          .of(new OutOfBoundsCheck(), new EmptyCellCheck(), new JumpCheck());
      if (checkValidMove(currBoard, pieceI, pieceJ, move.getI(), move.getJ(), validMoveChecks)) {
        possibleMoves.add(move);
      }
    }
    return possibleMoves;
  }
}

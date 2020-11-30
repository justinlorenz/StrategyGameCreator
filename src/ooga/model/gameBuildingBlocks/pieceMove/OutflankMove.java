package ooga.model.gameBuildingBlocks.pieceMove;

import java.util.ArrayList;
import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PossibleMove;
import ooga.model.gameBuildingBlocks.validMoveChecks.ConnectedCellCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.EmptyCellCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutflankCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.ValidMoveCheck;

/**
 * PieceMove building block which gets all moves that allow current player's piece
 * to outflank their opponent's piece.

 */
public class OutflankMove extends PieceMove {

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
    List<PossibleMove> possibleMoves = new ArrayList<>();
    List<ValidMoveCheck> validMoveChecks = List
        .of(new EmptyCellCheck(), new ConnectedCellCheck(), new OutflankCheck(playerNum));
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (checkValidMove(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, i, j,
            validMoveChecks)) {
          possibleMoves.add(new PossibleMove(i, j));
        }
      }
    }
    return possibleMoves;
  }
}

package ooga.model.gameBuildingBlocks.pieceMove;

import java.util.List;
import ooga.model.BoardStructure;
import ooga.model.PossibleMove;
import ooga.model.gameBuildingBlocks.validMoveChecks.ValidMoveCheck;

/**
 * Abstract class for PieceMove building block. GameEngine uses dependency injection with
 * PieceMove abstract lass to allow for different subclasses of PieceMove building block.
 * Abstract class is used as all subclasses have same logic of determining if move is valid, but
 * require retrieval of valid moves to be different. Abstract class allows for logic for determining
 * if move is valid to be implemented only once and prevents duplication over all the subclasses.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public abstract class PieceMove {

  public static final int NON_USED_PARAMETER = 0;

  /**
   * Checks if given move is a valid move on BoardStructure
   *
   * @param currBoard BoardStructure of the board to be updated
   * @param pieceI I coordinate of Piece to be moved
   * @param pieceJ J coordinate of Piece to be moved
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   * @param validMoveChecks List of constraints that move must satisfy to be valid
   * @return boolean if given move is valid
   */
  public boolean checkValidMove(BoardStructure currBoard, int pieceI, int pieceJ,
      int destinationI, int destinationJ, List<ValidMoveCheck> validMoveChecks) {
    for (ValidMoveCheck moveCheck : validMoveChecks) {
      if (!moveCheck.isValidMove(currBoard, pieceI, pieceJ, destinationI, destinationJ)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets all valid moves for given piece coordinates on boardStructure
   *
   * @param currBoard BoardStructure of the board to be updated
   * @param pieceI I coordinate of Piece to be moved
   * @param pieceJ J coordinate of Piece to be moved
   * @param playerNum Player number making the move
   * @return List of all PossibleMoves that can be played for given piece coordinates
   */
  public abstract List<PossibleMove> getValidMoves(BoardStructure currBoard, int pieceI, int pieceJ,
      int playerNum);

}

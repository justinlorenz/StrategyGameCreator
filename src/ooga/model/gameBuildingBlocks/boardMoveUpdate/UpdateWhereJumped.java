package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import ooga.model.BoardStructure;

/**
 * BoardMoveUpdate building block that updates board to be empty where piece
 * has just jumped over.
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class UpdateWhereJumped implements BoardMoveUpdate {

  public static final int ONE_CELL_AWAY = 1;

  /**
   * Updates the board using specified coordinates
   *
   * @param currBoard BoardStructure of the board to be updated
   * @param playerNumber Number associated with player making the move
   * @param pieceI I coordinate of Piece to be moved
   * @param pieceJ J coordinate of Piece to be moved
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   */
  @Override
  public void updateBoard(BoardStructure currBoard, int playerNumber, int pieceI, int pieceJ,
      int destinationI, int destinationJ) {
    if (isAJump(pieceI, pieceJ, destinationI, destinationJ) && (pieceI + destinationI) % 2 == 0) {
      currBoard.setEmptyCell((pieceI + destinationI) / 2, (pieceJ + destinationJ) / 2);
    }
  }

  private boolean isAJump(int pieceI, int pieceJ, int destinationI, int destinationJ) {
    return ((Math.abs(pieceI - destinationI) > ONE_CELL_AWAY) || (Math.abs(pieceJ - destinationJ)
        > ONE_CELL_AWAY));
  }
}

package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import static ooga.model.GameMoveHandler.NON_USED_PARAMETER;

import ooga.model.BoardStructure;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutflankColCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutflankDiagonalDownCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutflankDiagonalUpCheck;
import ooga.model.gameBuildingBlocks.validMoveChecks.OutflankRowCheck;
import ooga.model.pieces.NormalPiece;

/**
 * BoardMoveUpdate building block that flips current opponent's piece if
 * it is outflanked from current player's pieces.

 */
public class UpdateFlipOutflanked implements BoardMoveUpdate {

  public static final int POSITIVE_DIRECTION = 1;
  public static final int NEGATIVE_DIRECTION = -1;

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
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (checkViablePiece(i, j, destinationI, destinationJ)
            && currBoard.getPieceType(i, j).getPlayerNumber() != playerNumber &&
            !currBoard.getPieceType(i, j).isEmpty()) {
          if (checkOutflanked(currBoard, playerNumber, i, j)) {
            currBoard.setPieceType(new NormalPiece(playerNumber, "NormalPiece", i, j));
          }
        }
      }
    }
  }

  private boolean checkViablePiece(int i, int j, int destinationI, int destinationJ) {
    return (i == destinationI || j == destinationJ || (Math.abs(i - j) == Math
        .abs(destinationI - destinationJ))
        || (i + j) == (destinationI + destinationJ));
  }


  private boolean checkOutflanked(BoardStructure currBoard, int playerNumber, int i, int j) {
    OutflankRowCheck rowCheck = new OutflankRowCheck(playerNumber);
    OutflankColCheck colCheck = new OutflankColCheck(playerNumber);
    OutflankDiagonalDownCheck diagonalUpCheck = new OutflankDiagonalDownCheck(playerNumber);
    OutflankDiagonalUpCheck diagonalDownCheck = new OutflankDiagonalUpCheck(playerNumber);
    return (rowCheck.isOutflankedRow(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, i, j) ||
        colCheck.isOutflankedCol(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, i, j) ||
        diagonalDownCheck
            .isOutflankedDiagonalDown(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, i, j) ||
        diagonalUpCheck
            .isOutflankedDiagonalUp(currBoard, NON_USED_PARAMETER, NON_USED_PARAMETER, i, j));
  }
}

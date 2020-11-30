package ooga.model.gameBuildingBlocks.pieceMove;

import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class for creating PieceMove building blocks

 */
public class PieceMoveFactory {

  /**
   * Creates a new instance of a PieceMove
   *
   * @param pieceMove Class name of PieceMove building block
   * @return Constructed PieceMove
   */
  public PieceMove getPieceMove(String pieceMove) {
    try {
      return (PieceMove) Class.forName("ooga.model.gameBuildingBlocks.pieceMove." + pieceMove)
          .getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ClassDoesNotExistException(pieceMove, e);
    }
  }
}

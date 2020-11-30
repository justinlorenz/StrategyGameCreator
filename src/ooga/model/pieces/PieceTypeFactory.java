package ooga.model.pieces;

import java.lang.reflect.InvocationTargetException;
import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class to create Pieces
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class PieceTypeFactory {

  /**
   * Creates new Piece based on specified arguments
   *
   * @param pieceType Piece type to create
   * @param playerNumber Player number associated to the piece
   * @param xCor X coordinate of created piece
   * @param yCor Y coordinate of created piece
   * @return New constructed piece
   */
  public Piece getPieceType(String pieceType, int playerNumber, int xCor, int yCor) {
    try {
      if (pieceType.equals("Empty")) {
        return new EmptyPiece(xCor, yCor);
      }
      return (Piece) Class.forName("ooga.model.pieces." + pieceType)
          .getDeclaredConstructor(int.class, String.class, int.class, int.class)
          .newInstance(playerNumber, pieceType, xCor, yCor);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalStateException | InvocationTargetException | ClassNotFoundException e) {
      throw new ClassDoesNotExistException(pieceType, e);
    }
  }
}

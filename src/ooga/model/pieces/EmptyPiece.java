package ooga.model.pieces;

/**
 * State to represent empty

 */
public class EmptyPiece extends Piece {

  public static final String EMPTY_PIECE_ID = "Empty";
  public static final int EMPTY_PLAYER_NUM = 0;

  /**
   * Empty Piece constructor
   *
   * @param xCor x coordinate of empty piece
   * @param yCor y coordinate of empty piece
   */
  public EmptyPiece(int xCor, int yCor) {
    super(EMPTY_PLAYER_NUM, EMPTY_PIECE_ID, xCor, yCor);
  }

}

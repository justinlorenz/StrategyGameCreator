package ooga.model.pieces;

/**
 * State to represent a normal piece

 */
public class NormalPiece extends Piece {

  /**
   * Normal Piece constructor
   *
   * @param playerNumber player number who owns the piece
   * @param pieceId ID of the piece
   * @param i The i coordinate of empty piece
   * @param j The j coordinate of empty piece
   */
  public NormalPiece(int playerNumber, String pieceId, int i, int j) {
    super(playerNumber, pieceId, i, j);
  }

}

package ooga.model.pieces;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for Pieces
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public abstract class Piece {

  private final int playerNumber;
  private String pieceId;
  private int i, j;
  private final List<Integer> directionList;
  private boolean isSpecial;

  /**
   * Piece constructor
   *
   * @param playerNumber player number who owns the piece
   * @param pieceId ID of the piece
   * @param i The i coordinate of empty piece
   * @param j The j coordinate of empty piece
   */
  public Piece(int playerNumber, String pieceId, int i, int j) {
    directionList = new ArrayList<>();
    this.pieceId = pieceId;
    this.playerNumber = playerNumber;
    this.i = i;
    this.j = j;
    isSpecial = false;
    if (playerNumber == 1) {
      directionList.add(-1);
    } else {
      directionList.add(1);
    }
  }

  /**
   * Checks if piece is empty type
   *
   * @return Boolean if the piece is empty
   */
  public boolean isEmpty() {
    Piece emptyPiece = new EmptyPiece(0, 0);
    return this.equals(emptyPiece);
  }

  /**
   * Makes the piece a special piece
   */
  public void makeSpecialPiece() {
    isSpecial = !isSpecial;
    pieceId = "SpecialPiece";
  }

  /**
   * Checks if the piece is special type
   *
   * @return Boolean if piece is special
   */
  public boolean isSpecial() {
    return isSpecial;
  }

  /**
   * Retrieves player number of piece
   *
   * @return The associated player number of the current piece
   */
  public int getPlayerNumber() {
    return playerNumber;
  }

  /**
   * Retrieves id of current piece
   *
   * @return ID of the current piece
   */
  public String getPieceId() {
    return pieceId;
  }

  /**
   * Retrieves current piece I coordinate
   *
   * @return I coordinate of piece
   */
  public int getI() {
    return i;
  }

  /**
   * Retrieves current piece J coordinate
   *
   * @return J coordinate of piece
   */
  public int getJ() {
    return j;
  }

  /**
   * Sets coordinate of current piece
   *
   * @param i I coordinate that piece will be set to
   * @param j J coordinate that piece will be set to
   */
  public void setCoordinate(int i, int j) {
    this.i = i;
    this.j = j;
  }

  /**
   * Checks if pieces have the same ID and are associated with same player number
   *
   * @param o Object to compare if piece is equal to
   * @return boolean if piece is the same ID and same playerNumber association
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Piece)) {
      return false;
    }
    Piece p = (Piece) o;
    return (this.playerNumber == p.getPlayerNumber() && this.pieceId.equals(p.getPieceId()));
  }


  /**
   * Retrieves the piece's direction that it can move in
   *
   * @return Piece's direction
   */
  public List<Integer> getDirection() {
    return directionList;
  }

  /**
   * Adds directions that piece can go in
   *
   * @param direction Direction that piece will be able to go to
   */
  public void addNewDirection(int direction) {
    directionList.add(direction);
  }

}

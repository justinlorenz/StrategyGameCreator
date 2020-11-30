package ooga.model;

/**
 * Class represents a move
 */
public class PossibleMove {

  private final int i;
  private final int j;

  /**
   * Constructor for PossibleMove
   *
   * @param i I coordinate for move
   * @param j J coordinate for move
   */
  public PossibleMove(int i, int j) {
    this.i = i;
    this.j = j;
  }

  /**
   * Compares if two Piece moves are the same
   *
   * @param o Object to compare
   * @return True if compared PieceMove is the same move
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof PossibleMove)) {
      return false;
    }
    PossibleMove p = (PossibleMove) o;
    return (this.i == p.getI() && this.j == p.getJ());
  }

  /**
   * Gets I coordinate of move
   *
   * @return I coordinate of move
   */
  public int getI() {
    return i;
  }

  /**
   * Gets J coordinate of move
   *
   * @return J coordinate of move
   */
  public int getJ() {
    return j;
  }

  /**
   * Determines if the move is in the bounds of the grid
   *
   * @param gridSize Size of the grid
   * @return Boolean if move is in bounds of grid
   */
  protected boolean isInBounds(int gridSize) {
    return (getI() >= 0 && getJ() >= 0 && getI() < gridSize && getJ() < gridSize);
  }
}


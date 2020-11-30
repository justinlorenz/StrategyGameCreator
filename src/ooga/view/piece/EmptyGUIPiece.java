package ooga.view.piece;

/**
 * Subclass for an empty piece
 *
 * @author JasonDong
 */
public class EmptyGUIPiece extends GUIPiece {

  public EmptyGUIPiece(double gridWidth, double gridHeight, double xCoordinate,
      double yCoordinate) {
    super(gridWidth, gridHeight, xCoordinate, yCoordinate);
  }

  @Override
  public void highlightOnClick() {

  }

  @Override
  public void resetColor() {

  }
}

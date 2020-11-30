package ooga.view.piece;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Superclass for the pieces to display on the board, overlayed on top of the Tiles
 *
 *
 */
public abstract class GUIPiece extends ImageView {

  public GUIPiece(double gridWidth, double gridHeight, double xCoordinate, double yCoordinate,
      Image image) {
    super(image);
    setFitWidth(gridWidth);
    setFitHeight(gridHeight);
    setX(xCoordinate);
    setY(yCoordinate);
  }

  public GUIPiece(double gridWidth, double gridHeight, double xCoordinate, double yCoordinate) {
    setFitWidth(gridWidth);
    setFitHeight(gridHeight);
    setX(xCoordinate);
    setY(yCoordinate);
  }

  /**
   * Abstract method to allow pieces to be highlighted on click
   */
  public abstract void highlightOnClick();

  /**
   * Reset the color of the piece
   */
  public abstract void resetColor();

}

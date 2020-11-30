package ooga.view.piece;

import javafx.scene.image.Image;

/**
 * Subclass used for pieces that can be moved around with play, such as checkers
 *
 * 
 */

public class MoveableGUIPiece extends GUIPiece {

  private final Boolean isClicked = true;
  private final String imageFile;

  public MoveableGUIPiece(double gridWidth, double gridHeight, double xCoordinate,
      double yCoordinate,
      Image image, String imageFile) {
    super(gridWidth, gridHeight, xCoordinate, yCoordinate, image);
    this.imageFile = imageFile;
  }

  /**
   * highlight the piece on a click
   */
  @Override
  public void highlightOnClick() {
    String[] fileName = imageFile.split("\\.");
    String clickedFile = String.format("%sClicked.%s", fileName[0], fileName[1]);
    setImage(new Image(clickedFile));
  }

  /**
   * Reset the color of the piece
   */
  @Override
  public void resetColor() {
    setImage(new Image(imageFile));
  }

}
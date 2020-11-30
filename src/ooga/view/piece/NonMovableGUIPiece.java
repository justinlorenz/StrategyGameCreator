package ooga.view.piece;

import javafx.scene.image.Image;

/**
 * Subclass used for pieces that cannot be moved, such as pieces for TicTacToe, ConnectFour, and
 * Othello
 *
 * @author JasonDong
 */
public class NonMovableGUIPiece extends GUIPiece {


  public NonMovableGUIPiece(double gridWidth, double gridHeight, double xCoordinate,
      double yCoordinate, Image image) {
    super(gridWidth, gridHeight, xCoordinate, yCoordinate, image);
  }

  @Override
  public void highlightOnClick() {

  }

  @Override
  public void resetColor() {

  }
}

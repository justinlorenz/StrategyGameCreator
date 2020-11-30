package ooga.view.tile;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Subclass used for displaying a Tile for games that have only one grid type
 */
public class MonochromeTile extends Tile {

  public MonochromeTile(double x, double y, double xCoord, double yCoord) {
    super(x, y, xCoord, yCoord);
    handleMouseHover();
  }

  /**
   * Change the color of the grid on a mouse hover
   */
  public void handleMouseHover() {
    setOnMouseEntered(e -> setFill(new ImagePattern(new Image("woodSquareHover.png"))));
    setOnMouseExited(e -> setFill(new ImagePattern(new Image("woodSquare.png"))));
  }

}

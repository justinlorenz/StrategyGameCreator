package ooga.view.tile;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ooga.view.piece.GUIPiece;

/**
 * Superclass used for tiles that are displayed on the GridPane
 *
 * @author JasonDong
 */

public abstract class Tile extends Rectangle {

  public GUIPiece GUIPiece;
  private final double xCoord;
  private final double yCoord;

  public Tile(boolean light, double x, double y, double xCoord, double yCoord) {
    setWidth(x);
    setHeight(y);
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    setLayoutX(xCoord);
    setLayoutY(yCoord);
    setFill(light ? new ImagePattern(new Image("lightSquare.png"))
        : new ImagePattern(new Image("darkSquare.png")));
  }

  public Tile(double x, double y, double xCoord, double yCoord) {
    setWidth(x);
    setHeight(y);
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    setLayoutX(xCoord);
    setLayoutY(yCoord);
    setFill(new ImagePattern(new Image("woodSquare.png")));
  }

  /**
   * Get the xCoordinate of the tile
   *
   * @return xCoord
   */
  public double getXCoord() {
    return xCoord;
  }

  /**
   * Get the yCoordinate of the tile
   *
   * @return yCoord
   */
  public double getYCoord() {
    return yCoord;
  }

}

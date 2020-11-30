package ooga.view.guiComponentFactory;

import java.util.function.Consumer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ooga.exception.BadDataException;

/**
 * Factory used to create profile pictures on the pane that the user can pick
 *
 * @author JasonDong
 */
public class GUIProfileFactory {

  /**
   * Create the rectangle with the profile picture
   *
   * @param imageName, the name of the image to show
   * @param width,     the width of the profile picture
   * @param height,    the height of the profile picture
   * @param xCoord,    the xCoordinate to place the profile picture
   * @param yCoord,    the yCoordinate to place the profile picture
   * @param profRect,  the Consumer used to add functionality to clicking on the rectangle
   * @return rectangle, the profile picture to display
   */
  public Rectangle makeProfilePicture(String imageName, double width, double height, double xCoord,
      double yCoord, Consumer<Rectangle> profRect) {

    try {
      Rectangle rectangle = new Rectangle(width, height);
      rectangle.setLayoutX(xCoord);
      rectangle.setLayoutY(yCoord);
      String formattedURL = String.format("%s.jpg", imageName);
      rectangle.setFill(new ImagePattern(new Image(formattedURL)));
      rectangle.setId(imageName);
      rectangle.setId(imageName);

      String formattedHover = String.format("%sHover.jpg", imageName);
      rectangle
          .setOnMouseEntered(e -> rectangle.setFill(new ImagePattern(new Image(formattedHover))));
      rectangle.setOnMouseClicked(e -> {
        rectangle.setFill(new ImagePattern(new Image(formattedHover)));
        rectangle
            .setOnMouseExited(ev -> rectangle.setFill(new ImagePattern(new Image(formattedHover))));
        profRect.accept(rectangle);
      });
      rectangle.setOnMouseExited(e -> rectangle.setFill(new ImagePattern(new Image(formattedURL))));
      return rectangle;

    } catch (Exception e) {
      throw new BadDataException("Invalid profile string", e.getCause());
    }
  }

}

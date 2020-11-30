package ooga.view.guiComponentFactory;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.exception.BadDataException;

/**
 * Factory used to create chat messages to place on the scene
 */

public class GUIChatMessageFactory {

  private static final int HBOX_SPACING = 50;
  private static final int VBOX_SPACING = 5;
  private static final int PROFILE_WIDTH = 30;
  private static final int PROFILE_HEIGHT = 30;

  /**
   * Method to create the HBox of the chat message, which contains the user name, profile picture,
   * and message
   *
   * @param name,            the name of the user
   * @param profilePic,      the profile picture the commenter is using
   * @param message,         the message to send
   * @param isCurrentPlayer, boolean used to know what side to place the profile picture and name on
   *                         to signify that it's the same user who's commenting
   * @return HBox, the HBox of the chat message, which contains the user name, profile picture, and
   * message
   */
  public HBox makeChatMessage(String name, String profilePic, String message,
      Boolean isCurrentPlayer) {
    try {
      HBox hbox = new HBox(HBOX_SPACING);
      VBox vBox = new VBox(VBOX_SPACING);

      Text nameText = new Text(name);
      nameText.getStyleClass().add("NameText");

      Rectangle profile = new Rectangle();
      String formattedURL = String.format("%s.jpg", profilePic);
      profile.setFill(new ImagePattern(new Image(formattedURL)));
      profile.setWidth(PROFILE_WIDTH);
      profile.setHeight(PROFILE_HEIGHT);

      vBox.getChildren().addAll(profile, nameText);
      vBox.setAlignment(Pos.CENTER);

      Text messageText = new Text(message);
      messageText.getStyleClass().add("MessageText");

      Region r = new Region();
      HBox.setHgrow(r, Priority.ALWAYS);
      if (isCurrentPlayer) {
        hbox.getChildren().addAll(vBox, messageText, r);
      } else {
        hbox.getChildren().addAll(r, messageText, vBox);
      }
      hbox.getStyleClass().add("ChatMessage");
      return hbox;

    } catch (Exception e) {
      throw new BadDataException("Invalid profile message", e.getCause());
    }
  }
}

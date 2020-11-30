package ooga.view.mainGUI;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Set up player 2's profile picture, name, and update with status of the current player's turn
 *
 * @author JasonDong
 */

public class MainGUIPlayer2Window extends VBox {

  private final String currTheme;
  private final String profilePic2;
  private final String player2Name;
  private final ResourceBundle myResources;
  private Rectangle profilePic;
  private final int PROFILE_WIDTH = 100;
  private final int PROFILE_HEIGHT = 100;
  private static final int TOP_INSET = 100;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 100;
  private static final int LEFT_INSET = 10;

  public MainGUIPlayer2Window(double windowWidth, double stageHeight, ResourceBundle myResources,
      String currTheme, String profilePic2, String player2Name) {
    this.currTheme = currTheme;
    this.myResources = myResources;
    this.profilePic2 = profilePic2;
    this.player2Name = player2Name;
    setMaxWidth(windowWidth);
    setHeight(stageHeight);
    setUpWindow();
    getStylesheets().add("guiStyles.css");
  }

  /**
   * Helper method to set up the window
   */
  private void setUpWindow() {
    setAlignment(Pos.CENTER);
    setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    String themePath = String.format("Player2Theme%s", currTheme);
    getStyleClass().add(themePath);
    setUpProfile();
  }

  /**
   * Set player 1's profile picture to the original image
   */
  public void setPlayer1Turn() {
    String formattedURL = String.format("%s.jpg", profilePic2);
    profilePic.setFill(new ImagePattern(new Image(formattedURL)));
  }

  /**
   * Set player 2's profile picture to the hover image
   */
  public void setPlayer2Turn() {
    String formattedURL = String.format("%sHover.jpg", profilePic2);
    profilePic.setFill(new ImagePattern(new Image(formattedURL)));
  }

  /**
   * Set up the profile picture
   */
  private void setUpProfile() {
    Region r = new Region();
    setVgrow(r, Priority.ALWAYS);
    profilePic = styleRectangle();
    Text name = new Text(player2Name);
    name.getStyleClass().add("ProfileText");
    getChildren().addAll(name, profilePic, r);

  }

  /**
   * Set up the rectangle and add styling to it
   *
   * @return rectangle, the profile picture to show
   */
  private Rectangle styleRectangle() {
    Rectangle rectangle = new Rectangle();
    rectangle.setWidth(PROFILE_WIDTH);
    rectangle.setHeight(PROFILE_HEIGHT);
    String formattedURL = String.format("%s.jpg", profilePic2);
    Image img = new Image(formattedURL);
    rectangle.setFill(new ImagePattern(img));
    return rectangle;
  }
}

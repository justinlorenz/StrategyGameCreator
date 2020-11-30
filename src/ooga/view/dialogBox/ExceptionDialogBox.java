package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Subclass for setting up the DialogBox for displaying exceptions
 *
 * 
 */


public class ExceptionDialogBox extends DialogBox {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 400;
  private final String property;
  private final GUIButtonFactory guiButtonFactory;
  private final String message;
  private static final int TOP_INSET = 100;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 100;
  private static final int LEFT_INSET = 10;
  private final ResourceBundle exceptionBundle;

  public ExceptionDialogBox(ResourceBundle myResources, String property, String message) {
    super(myResources);
    this.exceptionBundle = ResourceBundle
        .getBundle(String.format("%sexceptions", myResources.getBaseBundleName()));
    this.property = property;
    this.message = message;
    guiButtonFactory = new GUIButtonFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setWidth(WIDTH);
    setHeight(HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
    setResizable(false);
    show();
  }

  /**
   * Set up the scene to place on the stage
   *
   * @param myResources, the ResourceBundle for the languages
   * @return myScene, the scene to place on the stage
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    VBox myVbox = new VBox(VBOX_SPACING);
    myVbox.setAlignment(Pos.CENTER);
    myVbox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    setMessage(myVbox);
    makeButton(myVbox);
    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("ExceptionStyles");
    return myScene;

  }

  /**
   * Set the message to display
   *
   * @param vbox, the vbox for the exception and button
   */
  private void setMessage(VBox vbox) {
    Text text = new Text(exceptionBundle.getString(property) + "\n" + message);
    text.getStyleClass().add("ExceptionText");
    text.setId("ExceptionText");
    vbox.getChildren().add(text);
  }

  /**
   * Make the close button
   *
   * @param vBox, the vbox for the close button
   */
  private void makeButton(VBox vBox) {
    Button closeButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "CloseButton",
            e -> this.close());
    closeButton.setDisable(false);
    vBox.getChildren().add(closeButton);
  }
}

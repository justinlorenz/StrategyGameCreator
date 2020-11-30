package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.model.GameStatus;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Subclass for setting up the DialogBox for showing the winner that won, or the message for a tie
 *
 * 
 */

public class WinDialogBox extends DialogBox {

  private final String playerName;
  private Button guiCloseButton;
  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;
  private final GUIButtonFactory guiButtonFactory;
  private final GameStatus gameStatus;
  private static final int TOP_INSET = 100;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 100;
  private static final int LEFT_INSET = 10;

  public WinDialogBox(ResourceBundle myResources, String playerName, GameStatus gameStatus) {
    super(myResources);
    this.playerName = playerName;
    this.gameStatus = gameStatus;
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
   * @param myResources
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
    myVbox.getStyleClass().add("WinLoseTieStyles");
    return myScene;

  }

  /**
   * Set up the win message for the person who won, or a tie message
   *
   * @param myVBox
   */
  private void setMessage(VBox myVBox) {
    Text text = new Text();
    text.getStyleClass().add("WinLoseTieText");
    text.setId("WinLoseTieText");
    String winnerText = String
        .format("%s %s", playerName, getMyResources().getString("WinnerMessage"));
    String drawText = getMyResources().getString("DrawMessage");
    switch (gameStatus) {
      case WON -> text.setText(winnerText);
      case DRAW -> text.setText(drawText);

    }
    myVBox.getChildren().add(text);
  }

  /**
   * Make the close button to place on the DialogBox
   *
   * @param myVbox
   */
  private void makeButton(VBox myVbox) {
    guiCloseButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "CloseButton",
            e -> closeButtonPressed());
    guiCloseButton.setDisable(false);
    myVbox.getChildren().add(guiCloseButton);
  }

  /**
   * Functionality for the close button to close the stage
   */
  public void closeButtonPressed() {
    close();
  }
}

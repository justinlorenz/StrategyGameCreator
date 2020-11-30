package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.controller.GameController;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Subclass for setting up the DialogBox for allowing the user to save their game
 *
 * @author JasonDong
 */

public class SaveGameDialogBox extends DialogBox {

  private final GameController gameController;
  private Button saveAndCloseButton;
  private static final int HBOX_SPACING = 10;
  TextField gameName;
  TextField textFieldAuthor;
  TextField textFieldDescription;
  private final GUIButtonFactory guiButtonFactory;
  private static final int TOP_INSET = 75;
  private static final int RIGHT_INSET = 0;
  private static final int BOTTOM_INSET = 75;
  private static final int LEFT_INSET = 0;
  private static final int TEXT_WIDTH = 200;

  public SaveGameDialogBox(ResourceBundle myResources, GameController gameController) {
    super(myResources);
    this.gameController = gameController;
    guiButtonFactory = new GUIButtonFactory();

  }

  /**
   * Initialize the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("SaveGameDialogBoxTitle"));
    setWidth(STAGE_WIDTH);
    setHeight(STAGE_HEIGHT);
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
    setUpVbox(myVbox);
    setUpButton(myVbox);
    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("PlayerChooserStyles");
    return myScene;

  }

  /**
   * Set up the VBox to display the textfields and buttons on the scene
   *
   * @param vBox
   */
  private void setUpVbox(VBox vBox) {
    HBox gameHBox = makeHBoxForText("EnterGameName");
    HBox authorHBox = makeHBoxForText("EnterAuthorName");
    HBox descriptionHBox = makeHBoxForText("EnterGameDescription");

    gameName = setUpTextField();
    gameName.setId("GameNameTextField");
    textFieldAuthor = setUpTextField();
    textFieldAuthor.setId("AuthorTextField");
    textFieldDescription = setUpTextField();
    textFieldDescription.setId("DescriptionTextField");

    gameHBox.getChildren().add(gameName);
    authorHBox.getChildren().add(textFieldAuthor);
    descriptionHBox.getChildren().add(textFieldDescription);

    vBox.getChildren().addAll(gameHBox, authorHBox, descriptionHBox);
  }

  /**
   * Make the HBox for aligning the description to display for the TextField and the Textfield
   * itself
   *
   * @param property
   * @return
   */
  private HBox makeHBoxForText(String property) {
    HBox hBox = new HBox(HBOX_SPACING);
    hBox.setAlignment(Pos.CENTER);

    Text text = new Text(getMyResources().getString(property));
    text.getStyleClass().add("my-text");
    hBox.getChildren().add(text);

    return hBox;
  }

  /**
   * Helper method to set up the textfield
   *
   * @return textField, the textfield to put on the scene
   */
  private TextField setUpTextField() {
    TextField textField = new TextField();
    textField.setMaxWidth(TEXT_WIDTH);
    return textField;
  }

  /**
   * Set up the button for saving and closing
   *
   * @param vBox
   */
  private void setUpButton(VBox vBox) {
    saveAndCloseButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "SaveAndCloseButton",
            e -> saveAndCloseClicked());
    saveAndCloseButton.setDisable(false);
    vBox.getChildren().add(saveAndCloseButton);
  }

  /**
   * Functionality for the save and close button
   */
  public void saveAndCloseClicked() {
    gameController
        .saveGame(gameName.getText(), textFieldAuthor.getText(), textFieldDescription.getText());
    close();
  }
}

package ooga.view.mainGUI;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ooga.controller.GameController;
import ooga.view.dialogBox.ChatProfileDialogBox;
import ooga.view.dialogBox.CreateLoadDialogBox;
import ooga.view.dialogBox.SaveGameDialogBox;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Set up the bottom window for the MainGUI with the appropriate buttons and background
 *
 * 
 */
public class MainGUIBottomWindow extends HBox {

  private final int HBOX_SPACING = 10;
  private final String currTheme;
  private final ResourceBundle myResources;
  private final GameController gameController;
  private final MainGUI mainGUI;
  private SaveGameDialogBox saveGameDialogBox;
  private CreateLoadDialogBox createLoadDialogBox;
  private final GUIButtonFactory guiButtonFactory;
  private static final int BUTTON_WIDTH = 150;
  private static final int BUTTON_HEIGHT = 50;
  private static final int TOP_INSET = 0;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 0;
  private static final int LEFT_INSET = 10;

  public MainGUIBottomWindow(double windowHeight, double stageWidth, ResourceBundle myResources,
      String currTheme, GameController gameController, MainGUI mainGUI) {
    this.currTheme = currTheme;
    this.myResources = myResources;
    this.gameController = gameController;
    this.mainGUI = mainGUI;
    setMinHeight(windowHeight);
    setWidth(stageWidth);
    guiButtonFactory = new GUIButtonFactory();
    setUpWindow();
    getStylesheets().add("guiStyles.css");
  }

  /**
   * Helper to set up the window
   */
  private void setUpWindow() {
    setAlignment(Pos.CENTER);
    setSpacing(HBOX_SPACING);
    setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    getStyleClass().add("BottomBanner");
    setUpButtons();
  }

  /**
   * Helper to set up the buttons on the scene
   */
  private void setUpButtons() {
    Button guiBackButton = guiButtonFactory
        .makeButton(myResources, BUTTON_HEIGHT, BUTTON_WIDTH, "BackButton",
            e -> backButtonClicked());
    Button guiResetButton = guiButtonFactory
        .makeButton(myResources, BUTTON_HEIGHT, BUTTON_WIDTH, "ResetButton",
            e -> resetButtonClicked());
    Button guiSaveButton = guiButtonFactory
        .makeButton(myResources, BUTTON_HEIGHT, BUTTON_WIDTH, "SaveButton",
            e -> saveGameButtonClicked());
    Button chatButton = guiButtonFactory
        .makeButton(myResources, BUTTON_HEIGHT, BUTTON_WIDTH, "ChatRoomButton",
            e -> chatRoomButtonClicked());
    guiBackButton.setDisable(false);
    guiResetButton.setDisable(false);
    guiSaveButton.setDisable(false);
    chatButton.setDisable(false);
    getChildren().addAll(guiBackButton, guiResetButton, guiSaveButton, chatButton);
  }

  /**
   * Functionality for when the save button is clicked, launch Save Dialog Box
   */
  public void saveGameButtonClicked() {
    saveGameDialogBox = new SaveGameDialogBox(myResources, gameController);
    saveGameDialogBox.initializeStage();
  }

  /**
   * Functionality for when the chat room button is clicked, launch Chatroom Dialog Box
   */
  public void chatRoomButtonClicked() {
    ChatProfileDialogBox chatProfileDialogBox = new ChatProfileDialogBox(myResources);
    chatProfileDialogBox.initializeStage();
  }

  /**
   * Functionality for when the back button is pressed, close the GUI and launch the CreateLoad
   * Dialog Box
   */
  public void backButtonClicked() {
    mainGUI.stopAnimation();
    mainGUI.close();
    createLoadDialogBox = new CreateLoadDialogBox(myResources);
    createLoadDialogBox.initializeStage();
  }

  /**
   * Reset the grid when the reset button is clicked
   */
  public void resetButtonClicked() {
    mainGUI.clearGrid();
  }

}

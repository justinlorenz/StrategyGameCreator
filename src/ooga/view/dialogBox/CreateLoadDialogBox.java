package ooga.view.dialogBox;

import java.io.File;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ooga.controller.GameController;
import ooga.exception.BadFileException;
import ooga.view.guiComboBox.GUILanguageComboBox;
import ooga.view.guiComponentFactory.GUIButtonFactory;
import ooga.view.mainGUI.MainGUI;

/**
 * Subclass for setting up the DialogBox for allowing the user to pick a language and then either
 * create a new game or load in a game Also allows the user to access the chat box or leaderboard
 *
 * @author JasonDong
 */

public class CreateLoadDialogBox extends DialogBox {

  private ComboBox<String> languageComboBox = new ComboBox();
  private Button loadButton;
  private Button createButton;
  private Button chatButton;
  private Button leaderboardButton;
  private Button gameInfoButton;
  private final GameController gameController;
  private MainGUI mainGUI;
  private ChooseGameDialogBox chooseGameDialogBox;
  private final GUIButtonFactory guiButtonFactory;
  private static final int TOP_INSET = 50;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 10;
  private static final int LEFT_INSET = 10;

  public CreateLoadDialogBox(ResourceBundle myResources) {
    super(myResources);
    gameController = new GameController();
    guiButtonFactory = new GUIButtonFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("GamePickerDialogBoxTitle"));
    setWidth(STAGE_WIDTH);
    setHeight(STAGE_HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
    setResizable(false);
    show();
  }

  /**
   * Set up the scene
   *
   * @param myResources
   * @return the scene to put on the stage
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    VBox myVbox = new VBox(VBOX_SPACING);
    myVbox.setAlignment(Pos.CENTER);
    setUpLogo(myVbox);
    setUpComboBox(myVbox);
    initializeButtons(myVbox);
    myVbox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));

    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("GamePickerStyles");
    return myScene;
  }

  /**
   * Make the buttons to place on the scene
   *
   * @param myVbox, the main VBOX for GUI elements on the scene
   */
  private void initializeButtons(VBox myVbox) {
    createButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "CreateButton",
            e -> createGameButtonPressed());
    loadButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LoadButton",
            e -> loadGameButtonPressed());
    chatButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "ChatRoomButton",
            e -> chatRoomButtonPressed());
    leaderboardButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LeaderboardButton",
            e -> leaderboardButtonPressed());
    gameInfoButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "GameInfoButton",
            e -> gameInfoButtonPressed());
    HBox buttonAlign = new HBox(createButton, loadButton);
    HBox optionalsButtonAlighn = new HBox(chatButton, leaderboardButton);
    buttonAlign.setAlignment(Pos.CENTER);
    buttonAlign.setSpacing(VBOX_SPACING);
    optionalsButtonAlighn.setAlignment(Pos.CENTER);
    optionalsButtonAlighn.setSpacing(VBOX_SPACING);
    myVbox.getChildren().addAll(buttonAlign, optionalsButtonAlighn, gameInfoButton);
  }

  /**
   * Helper method to launch the game info screen when button is pressed
   */
  private void gameInfoButtonPressed() {
    GameInfoDialogBox gameInfoDialogBox = new GameInfoDialogBox(getMyResources());
    gameInfoDialogBox.initializeStage();
  }

  /**
   * Helper method to launch the leaderboard when button is pressed
   */
  private void leaderboardButtonPressed() {
    LeaderboardDialogBox leaderboardDialogBox = new LeaderboardDialogBox(getMyResources());
    leaderboardDialogBox.initializeStage();
  }

  /**
   * Helper method to set up the logo
   *
   * @param myVbox
   */
  private void setUpLogo(VBox myVbox) {
    ImageView logo = new ImageView("strategyLogo.png");
    ImageView credits = new ImageView("teamCredits.png");
    myVbox.getChildren().addAll(logo, credits);
  }

  /**
   * Helper method to set up the combobox for selecting languages
   *
   * @param myVbox
   */
  private void setUpComboBox(VBox myVbox) {
    languageComboBox = new GUILanguageComboBox(getMyResources(), this);
    myVbox.getChildren().add(languageComboBox);
  }

  /**
   * On selecting a language, set the language for the button appropriately and enable all buttons
   */
  public void enableButtons() {
    createButton
        .setText(ResourceBundle.getBundle(languageComboBox.getValue()).getString("CreateButton"));
    loadButton
        .setText(ResourceBundle.getBundle(languageComboBox.getValue()).getString("LoadButton"));
    chatButton
        .setText(ResourceBundle.getBundle(languageComboBox.getValue()).getString("ChatRoomButton"));
    leaderboardButton.setText(
        ResourceBundle.getBundle(languageComboBox.getValue()).getString("LeaderboardButton"));
    gameInfoButton
        .setText(ResourceBundle.getBundle(languageComboBox.getValue()).getString("GameInfoButton"));
    createButton.setDisable(false);
    loadButton.setDisable(false);
    chatButton.setDisable(false);
    leaderboardButton.setDisable(false);
    gameInfoButton.setDisable(false);
  }

  /**
   * Functionality for launching the next dialog box on clicking of the create button
   */
  public void createGameButtonPressed() {
    chooseGameDialogBox = new ChooseGameDialogBox(
        ResourceBundle.getBundle(languageComboBox.getValue()), gameController);
    chooseGameDialogBox.initializeStage();
    close();
  }

  /**
   * Functionality to allow the user to load in a game configuration file on clicking of the load
   * button
   */
  public void loadGameButtonPressed() {
    FileChooser fileChooser = new FileChooser();
    String currentPath = Paths.get("./data/SavedGames").toAbsolutePath().normalize().toString();
    fileChooser.setInitialDirectory(new File(currentPath));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Game Files", "*.game")
    );
    try {
      File file = fileChooser.showOpenDialog(this);
      gameController.loadInGame(file);
      mainGUI = new MainGUI(gameController, ResourceBundle.getBundle(languageComboBox.getValue()));
      mainGUI.initializeStage();
      mainGUI.updateBoard();
      close();
    } catch (BadFileException e) {
      ExceptionDialogBox exceptionDialogBox = new ExceptionDialogBox(
          ResourceBundle.getBundle(languageComboBox.getValue()), e.getPropertyKey(),
          e.getMessage());
      exceptionDialogBox.initializeStage();
    } catch (Exception e) {
      ExceptionDialogBox exceptionDialogBox = new ExceptionDialogBox(
          ResourceBundle.getBundle(languageComboBox.getValue()), "BadGameFileException",
          e.getMessage());
      exceptionDialogBox.initializeStage();
    }
  }

  /**
   * Functionality to close the current window and launch the chat room on clicking of the chat room
   * button
   */
  public void chatRoomButtonPressed() {
    close();
    ChatProfileDialogBox chatProfileDialogBox = new ChatProfileDialogBox(getMyResources());
    chatProfileDialogBox.initializeStage();
  }
}

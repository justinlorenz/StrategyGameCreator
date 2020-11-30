package ooga.view.dialogBox;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import ooga.controller.GameController;
import ooga.exception.BadFileException;
import ooga.exception.MissingPropertyException;
import ooga.view.guiComponentFactory.GUIButtonFactory;
import ooga.view.guiComponentFactory.GUIProfileFactory;
import ooga.view.guiComponentFactory.GUITextFieldFactory;
import ooga.view.mainGUI.MainGUI;

/**
 * Subclass for setting up the DialogBox for allowing the user to pick a name and profile picture
 *
 * @author JasonDong
 */


public class PlayerChooserDialogBox extends DialogBox {

  private final GameController myGameController;
  private Button singlePlayerButton;
  private Button multiPlayerButton;
  private Button startGameButton;
  private TextField player1Name;
  private TextField player2Name;
  private static final int HBOX_SPACING = 10;
  private static final int VBOX_SPACING = 10;
  private static final int TEXTFIELD_WIDTH = 200;
  private final GUIButtonFactory guiButtonFactory;
  private final GUITextFieldFactory guiTextFieldFactory;
  private final GUIProfileFactory guiProfileFactory;
  private MainGUI mainGUI;
  private final List<String> profilePictures = List
      .of("AmongUsRed", "AmongUsGray", "BowserJr", "KingDedede", "Lucas", "Mario", "MetaKnight",
          "Sonic", "Yoshi");
  private final List<String> selectedProfilePictures = new ArrayList<>();
  private Pane profilePane;
  private VBox player1VBox;
  private VBox player2VBox;
  private Pane profilePane1;
  private Pane profilePane2;
  private static final int TOP_INSET = 75;
  private static final int RIGHT_INSET = 0;
  private static final int BOTTOM_INSET = 50;
  private static final int LEFT_INSET = 0;
  private static final int VBOX_MIN_HEIGHT = 100;
  private static final int HBOX_SPACING_2 = 50;
  private static final int INDIVIDUAL_PROFILE_WIDTH = 70;
  private static final int INDIVIDUAL_PROFILE_HEIGHT = 70;
  private static final int PROFILE_OFFSET = 5;
  private static final int NUM_PROFILE_ROWS = 3;
  private static final int NUM_PROFILE_COLUMNS = 3;
  private static final int NUM_PLAYERS = 2;


  public PlayerChooserDialogBox(ResourceBundle myResources, GameController myGameController) {
    super(myResources);
    this.myGameController = myGameController;
    guiButtonFactory = new GUIButtonFactory();
    guiTextFieldFactory = new GUITextFieldFactory();
    guiProfileFactory = new GUIProfileFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("PlayerChooserDialogBoxTitle"));
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
    myVbox.setAlignment(Pos.TOP_CENTER);
    myVbox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    setUpVbox(myVbox);
    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("PlayerChooserStyles");
    return myScene;
  }

  /**
   * Set up the main VBox for setting the GUI elements on the scene
   *
   * @param myVbox
   */
  private void setUpVbox(VBox myVbox) {
    HBox topHBox = new HBox(HBOX_SPACING_2);
    topHBox.setAlignment(Pos.CENTER);
    singlePlayerButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "SinglePlayerButton",
            e -> applySinglePlayer());
    multiPlayerButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "MultiPlayerButton",
            e -> applyMultiplayer());
    singlePlayerButton.setDisable(false);
    multiPlayerButton.setDisable(false);
    topHBox.getChildren().addAll(singlePlayerButton, multiPlayerButton);

    player1VBox = new VBox(VBOX_SPACING);
    player2VBox = new VBox(VBOX_SPACING);
    player1VBox.setAlignment(Pos.CENTER);
    player2VBox.setAlignment(Pos.CENTER);
    player1VBox.setMinHeight(VBOX_MIN_HEIGHT);
    player2VBox.setMinHeight(VBOX_MIN_HEIGHT);

    player1VBox.setAlignment(Pos.CENTER);
    player2VBox.setAlignment(Pos.CENTER);
    HBox hBoxCol = new HBox(HBOX_SPACING);
    hBoxCol.setAlignment(Pos.CENTER);
    hBoxCol.getChildren().addAll(player1VBox, player2VBox);

    startGameButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "StartGameButton",
            e -> startGame());

    myVbox.getChildren().addAll(topHBox, hBoxCol, startGameButton);
  }

  /**
   * Make the profile selection grid for allowing the user to pick a profile picture
   *
   * @return Pane, a pane with the profile pictures on it
   */
  private Pane makeProfileSelectionGrid() {
    Pane profilePane = new Pane();
    double individualProfileWidth = INDIVIDUAL_PROFILE_WIDTH;
    double individualProfileHeight = INDIVIDUAL_PROFILE_HEIGHT;

    int profileIndx = 0;
    double yCoord = 0;
    for (int i = 0; i < NUM_PROFILE_ROWS; i++) {
      double xCoord = 0;
      for (int j = 0; j < NUM_PROFILE_COLUMNS; j++) {

        Consumer<Rectangle> profRect = e -> addProfilePic(e);
        Rectangle rect = guiProfileFactory
            .makeProfilePicture(profilePictures.get(profileIndx), individualProfileWidth,
                individualProfileHeight, xCoord, yCoord, profRect);
        profilePane.getChildren().add(rect);

        xCoord += individualProfileWidth + PROFILE_OFFSET;
        profileIndx++;
      }
      yCoord += individualProfileHeight + PROFILE_OFFSET;
    }

    return profilePane;
  }

  /**
   * Add the profile picture to a list of profile pictures
   *
   * @param rectangle
   */
  public void addProfilePic(Rectangle rectangle) {
    selectedProfilePictures.add(rectangle.getId());
    if (selectedProfilePictures.size() >= NUM_PLAYERS) {
      startGameButton.setDisable(false);
    }
  }

  /**
   * Apply singleplayer after the user clicks on the button
   */
  public void applySinglePlayer() {
    player1VBox.getChildren().removeAll(player1VBox.getChildren());
    player2VBox.getChildren().removeAll(player2VBox.getChildren());
    player1Name = guiTextFieldFactory
        .makeTextField(getMyResources(), "Player1TextFieldDefault", TEXTFIELD_WIDTH);
    player1Name.setId("Player1Name");
    player2Name = guiTextFieldFactory
        .makeTextField(getMyResources(), "Player2TextFieldDefault", TEXTFIELD_WIDTH);
    player2Name.setId("Player2Name");
    profilePane1 = makeProfileSelectionGrid();
    profilePane2 = makeProfileSelectionGrid();
    Button customProfile1Button = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LoadCustomProfile",
            e -> addCustomProfilePic1());
    Button customProfile2Button = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LoadCustomProfile",
            e -> addCustomProfilePic2());
    customProfile1Button.setDisable(false);
    customProfile2Button.setDisable(false);
    player1VBox.getChildren().addAll(player1Name, profilePane1, customProfile1Button);
    player2VBox.getChildren().addAll(player2Name, profilePane2, customProfile2Button);
    player2Name.setText("AI");
    player1Name.setDisable(false);
    myGameController.setSinglePlayer(true);
  }

  /**
   * Applying multiplayer after the user clicks on the multiplayer button
   */
  public void applyMultiplayer() {
    player1VBox.getChildren().removeAll(player1VBox.getChildren());
    player2VBox.getChildren().removeAll(player2VBox.getChildren());
    player1Name = guiTextFieldFactory
        .makeTextField(getMyResources(), "Player1TextFieldDefault", TEXTFIELD_WIDTH);
    player1Name.setId("Player1Name");
    player2Name = guiTextFieldFactory
        .makeTextField(getMyResources(), "Player2TextFieldDefault", TEXTFIELD_WIDTH);
    player2Name.setId("Player2Name");
    player1Name.setDisable(false);
    player2Name.setDisable(false);
    myGameController.setSinglePlayer(false);
    profilePane1 = makeProfileSelectionGrid();
    profilePane2 = makeProfileSelectionGrid();
    Button customProfile1Button = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LoadCustomProfile",
            e -> addCustomProfilePic1());
    Button customProfile2Button = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "LoadCustomProfile",
            e -> addCustomProfilePic2());
    player1VBox.getChildren().addAll(player1Name, profilePane1, customProfile1Button);
    player2VBox.getChildren().addAll(player2Name, profilePane2, customProfile2Button);
    customProfile1Button.setDisable(false);
    customProfile2Button.setDisable(false);
  }

  /**
   * Allow the user to pick their own custom profile picture and add it to the list
   */
  private void addCustomProfilePic1() {
    FileChooser fileChooser = new FileChooser();
    String currentPath = Paths.get("./resources").toAbsolutePath().normalize().toString();
    fileChooser.setInitialDirectory(new File(currentPath));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.jpg")
    );

    File file = fileChooser.showOpenDialog(this);
    if(file!=null) {
      String strFile = file.toString();
      String imagePath = strFile.substring(strFile.lastIndexOf("\\") + 1).replace(".jpg", "");
      selectedProfilePictures.add(0, imagePath);
      if (selectedProfilePictures.size() >= NUM_PLAYERS) {
        startGameButton.setDisable(false);
      }
    }
  }

  /**
   * Allow the user to pick a second profile picture and add it to the list
   */
  private void addCustomProfilePic2() {
    FileChooser fileChooser = new FileChooser();
    String currentPath = Paths.get("./resources").toAbsolutePath().normalize().toString();
    fileChooser.setInitialDirectory(new File(currentPath));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.jpg")
    );

    File file = fileChooser.showOpenDialog(this);
    String strFile = file.toString();
    String imagePath = strFile.substring(strFile.lastIndexOf("\\") + 1).replace(".jpg", "");
    selectedProfilePictures.add(1, imagePath);
    if (selectedProfilePictures.size() >= NUM_PLAYERS) {
      startGameButton.setDisable(false);
    }
  }

  /**
   * Start the game and launch the main gui. Send to the controller the parameters the user has
   * picked
   */
  public void startGame() {
    myGameController.addPlayerName(player1Name.getText());
    myGameController.addPlayerName(player2Name.getText());

    for (String pic : selectedProfilePictures) {
      myGameController.addProfilePic(pic);
    }

    mainGUI = new MainGUI(myGameController, getMyResources());
    try {
      myGameController.createNewGame();
      mainGUI.initializeStage();
      close();
    } catch (BadFileException e) {
      ExceptionDialogBox exceptionDialogBox = new ExceptionDialogBox(getMyResources(),
          e.getPropertyKey(), e.getMessage());
      exceptionDialogBox.initializeStage();
    } catch (Exception e) {
      ExceptionDialogBox exceptionDialogBox = new ExceptionDialogBox(getMyResources(),
          "BadGameFileException", e.getMessage());
      exceptionDialogBox.initializeStage();
    }
  }
}


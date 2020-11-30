package ooga.view.dialogBox;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import ooga.view.guiComponentFactory.GUIButtonFactory;
import ooga.view.guiComponentFactory.GUIProfileFactory;
import ooga.view.guiComponentFactory.GUITextFieldFactory;

/**
 * Subclass for setting up the DialogBox for allowing the user to pick a chat name and profile
 * picture
 *
 * @author JasonDong
 */
public class ChatProfileDialogBox extends DialogBox {

  private final GUITextFieldFactory guiTextFieldFactory;
  private TextField nameText;
  private final List<String> profilePictures = List
      .of("AmongUsRed", "AmongUsGray", "BowserJr", "KingDedede", "Lucas", "Mario", "MetaKnight",
          "Sonic", "Yoshi");
  private Button joinButton;
  private final GUIButtonFactory guiButtonFactory;
  private String selectedProfilePic;
  private final GUIProfileFactory guiProfileFactory;
  private static final int TOP_INSET = 100;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 100;
  private static final int LEFT_INSET = 10;
  private static final int INDIVIDUAL_PROFILE_WIDTH = 70;
  private static final int INDIVIDUAL_PROFILE_HEIGHT = 70;
  private static final int PROFILE_OFFSET = 5;
  private static final int NUM_PROFILE_ROWS = 3;
  private static final int NUM_PROFILE_COLUMNS = 3;
  private static final int NAME_TEXT_MAX_WIDTH = 200;
  private static final int PROFILE_GRID_X = 220;

  public ChatProfileDialogBox(ResourceBundle myResources) {
    super(myResources);
    guiTextFieldFactory = new GUITextFieldFactory();
    guiButtonFactory = new GUIButtonFactory();
    guiProfileFactory = new GUIProfileFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("ChatProfileDialogBoxTitle"));
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
   * @return myscene, the scene to place on the stage
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    VBox myVbox = new VBox(VBOX_SPACING);
    myVbox.setAlignment(Pos.CENTER);
    myVbox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));

    nameText = guiTextFieldFactory.makeTextField(myResources, "ChatRoomName", NAME_TEXT_MAX_WIDTH);
    nameText.setDisable(false);
    myVbox.getChildren().add(nameText);
    makeProfileSelectionGrid(myVbox);
    joinButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "JoinButton",
            e -> joinButtonClicked());
    myVbox.getChildren().add(joinButton);

    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("GamePickerStyles");
    return myScene;

  }

  /**
   * Make the selection grid to allow the user to pick their profile picture
   *
   * @param vBox
   */
  private void makeProfileSelectionGrid(VBox vBox) {
    Pane profilePane = new Pane();
    profilePane.setTranslateX(PROFILE_GRID_X);
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

    vBox.getChildren().add(profilePane);
  }

  /**
   * Add the profile picture and enable join button
   *
   * @param rectangle
   */
  private void addProfilePic(Rectangle rectangle) {
    selectedProfilePic = rectangle.getId();
    joinButton.setDisable(false);
  }

  /**
   * Functionality after clicking on the join button
   */
  public void joinButtonClicked() {
    ChatRoomDialogBox chatRoomDialogBox = new ChatRoomDialogBox(getMyResources(),
        selectedProfilePic, nameText.getText());
    chatRoomDialogBox.initializeStage();
    close();
  }

}

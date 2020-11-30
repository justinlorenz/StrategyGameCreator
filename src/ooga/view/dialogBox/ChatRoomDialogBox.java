package ooga.view.dialogBox;

import java.io.File;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ooga.exception.SavingFileException;
import ooga.exception.SavingGameFileException;
import ooga.model.data.PropertiesHandler;
import ooga.view.guiComponentFactory.GUIButtonFactory;
import ooga.view.guiComponentFactory.GUIChatMessageFactory;
import ooga.view.guiComponentFactory.GUITextFieldFactory;

/**
 * Subclass for setting up the DialogBox for allowing the user to chat and interact with others
 * socially
 *
 * @author JasonDong
 */

public class ChatRoomDialogBox extends DialogBox {

  private static final int CHAT_WIDTH = 600;
  private static final int CHAT_HEIGHT = 600;
  private static final int BUTTON_HEIGHT = 30;
  private static final int BUTTON_WIDTH = 100;
  private static final int HBOX_SPACING = 20;
  private static final int HBOX_HEIGHT = 70;
  private static final int VBOX_SPACING = 10;
  private static final int TOP_INSET = 30;
  private static final int RIGHT_INSET = 30;
  private static final int BOTTOM_INSET = 0;
  private static final int LEFT_INSET = 30;
  private static final int MIN_WIDTH = 200;
  private static final int PROFILE_WIDTH = 30;
  private static final int PROFILE_HEIGHT = 30;
  private final GUIButtonFactory guiButtonFactory;
  private final GUIChatMessageFactory guiChatMessageFactory;
  private final String name;
  private final String profilePic;
  private BorderPane root;
  private HBox messageBox;
  private TextField messageTextField;
  private final GUITextFieldFactory guiTextFieldFactory;
  private Button sendButton;
  private Rectangle profile;
  private VBox chatVBox;
  private int chatMessageNum = 0;

  public ChatRoomDialogBox(ResourceBundle myResources, String profilePic, String name) {
    super(myResources);
    this.profilePic = profilePic;
    this.name = name;
    guiButtonFactory = new GUIButtonFactory();
    guiChatMessageFactory = new GUIChatMessageFactory();
    guiTextFieldFactory = new GUITextFieldFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setWidth(CHAT_WIDTH);
    setHeight(CHAT_HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
    show();
  }

  /**
   * Set up the scene to place on the stage, with the root being a borderpane to display
   *
   * @param myResources
   * @return myScene, the scene to place on the stage
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    root = setUpBorderPane();

    Scene myScene = new Scene(root);
    myScene.getStylesheets().add("guiStyles.css");
    root.getStyleClass().add("ChatBoxStyles");
    return myScene;
  }

  /**
   * Set up the borderpane for the scene, utilizing the center and bottom areas
   *
   * @return borderPane, the main root
   */
  private BorderPane setUpBorderPane() {
    BorderPane borderPane = new BorderPane();
    chatVBox = new VBox(VBOX_SPACING);
    chatVBox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    initializePastMessages();
    messageBox = makeMessageBox();
    ScrollPane scrollPane = new ScrollPane(chatVBox);
    borderPane.setCenter(scrollPane);
    borderPane.setBottom(messageBox);

    return borderPane;
  }

  /**
   * Make the HBox and components within it to allow the user to type and send messages
   *
   * @return
   */
  private HBox makeMessageBox() {
    HBox mHBox = new HBox(HBOX_SPACING);
    mHBox.setMinHeight(HBOX_HEIGHT);
    profile = new Rectangle();
    String formattedURL = String.format("%s.jpg", profilePic);
    profile.setFill(new ImagePattern(new Image(formattedURL)));
    profile.setWidth(PROFILE_WIDTH);
    profile.setHeight(PROFILE_HEIGHT);
    messageTextField = guiTextFieldFactory
        .makeTextField(getMyResources(), "EnterSendMessage", MIN_WIDTH);
    messageTextField.setDisable(false);
    sendButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "SendButton", e -> addMessage());
    sendButton.setDisable(false);
    mHBox.getChildren().addAll(profile, messageTextField, sendButton);
    mHBox.setAlignment(Pos.CENTER);

    return mHBox;
  }

  /**
   * Initialize the previous chat messages that had been sent and display them first on the scene
   */
  private void initializePastMessages() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File("data/chatLog/ChatLog.chat");
    propertiesHandler.createProperties(file);

    for (int i = 0; i < propertiesHandler.getKeySize(); i++) {
      String[] messageInfo = propertiesHandler.getGameProperty(String.format("Message%d", i))
          .split("`");
      HBox message = guiChatMessageFactory
          .makeChatMessage(messageInfo[0], messageInfo[1], messageInfo[2],
              messageInfo[0].equals(name));
      chatVBox.getChildren().add(message);
      chatMessageNum++;
    }
  }

  /**
   * Add message to the VBox of rolling messages
   */
  private void addMessage() {
    HBox message = guiChatMessageFactory
        .makeChatMessage(name, profilePic, messageTextField.getText(), true);
    addMessageToLog(messageTextField.getText());
    messageTextField.clear();

    chatVBox.getChildren().add(message);
  }

  /**
   * Add message to the log
   *
   * @param message, the message to add to the chat log
   */
  private void addMessageToLog(String message) {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File("data/chatLog/ChatLog.chat");
    propertiesHandler.createProperties(file);
    try {
      String filePath = "data/chatLog/ChatLog.chat";
      propertiesHandler.putProperty(String.format("Message%d", chatMessageNum),
          String.format("%s`%s`%s", name, profilePic, message));
      propertiesHandler.save(filePath);
      chatMessageNum++;
    } catch (SavingFileException e) {
      throw new SavingGameFileException("Error saving the game", e);
    }
  }
}

package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Subclass for setting up the DialogBox for allowing the user to see the game rules
 *
 * @author JasonDong
 */

public class GameInfoDialogBox extends DialogBox {

  private final GUIButtonFactory guiButtonFactory;
  private static final int HBOX_SPACING = 50;
  private static final int IMAGE_WIDTH = 100;
  private static final int IMAGE_HEIGHT = 100;
  private static final int VBOX_TOP_PADDING = 30;
  private static final int VBOX_BOTTOM_PADDING = 0;
  private static final int VBOX_LEFT_PADDING = 0;
  private static final int VBOX_RIGHT_PADDING = 0;

  public GameInfoDialogBox(ResourceBundle myResources) {
    super(myResources);
    guiButtonFactory = new GUIButtonFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("GameInfoDialogBoxTitle"));
    setWidth(STAGE_WIDTH);
    setHeight(STAGE_HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
    setResizable(false);
    show();
  }

  /**
   * Set up the scene to display on the stage
   *
   * @param myResources
   * @return myScene, the scene to place on the stage
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    VBox myVBox = new VBox(VBOX_SPACING);
    HBox gameHBox = new HBox(HBOX_SPACING);
    VBox firstColVBox = new VBox();
    VBox secondColVBox = new VBox();
    myVBox.setAlignment(Pos.CENTER);
    gameHBox.setAlignment(Pos.CENTER);
    firstColVBox.setAlignment(Pos.CENTER);
    secondColVBox.setAlignment(Pos.CENTER);
    setUpFirstVBox(firstColVBox);
    setUpSecondVBox(secondColVBox);
    firstColVBox.setPadding(
        new Insets(VBOX_TOP_PADDING, VBOX_RIGHT_PADDING, VBOX_BOTTOM_PADDING, VBOX_LEFT_PADDING));
    secondColVBox.setPadding(
        new Insets(VBOX_TOP_PADDING, VBOX_RIGHT_PADDING, VBOX_BOTTOM_PADDING, VBOX_LEFT_PADDING));

    Scene myScene = new Scene(myVBox);
    myScene.getStylesheets().add("guiStyles.css");
    myVBox.getStyleClass().add("GamePickerStyles");

    Button closeButton = guiButtonFactory
        .makeButton(myResources, BUTTON_HEIGHT, BUTTON_WIDTH, "CloseButton", e -> this.close());
    closeButton.setDisable(false);
    gameHBox.getChildren().addAll(firstColVBox, secondColVBox);
    myVBox.getChildren().addAll(gameHBox, closeButton);
    return myScene;
  }

  /**
   * Set up the first VBox column for TicTacToe and Othello
   *
   * @param vbox
   */
  private void setUpFirstVBox(VBox vbox) {
    Text tictactoeText = titleTextMaker(getMyResources().getString("TicTacToeTitle"));
    ImageView ticTacToeImage = makeImage("gameLogos/TicTacToeLogo.png");
    Text tictactoeDescrip = descripTextMaker("TicTacToeDescription");

    Text othelloText = titleTextMaker(getMyResources().getString("OthelloTitle"));
    ImageView othelloImage = makeImage("gameLogos/OthelloLogo.png");
    Text othelloDescrip = descripTextMaker("OthelloDescription");

    vbox.getChildren()
        .addAll(tictactoeText, ticTacToeImage, tictactoeDescrip, othelloText, othelloImage,
            othelloDescrip);
  }

  /**
   * Set up the second VBox for connect four and checkers
   *
   * @param vBox
   */
  private void setUpSecondVBox(VBox vBox) {
    Text connectFourText = titleTextMaker(getMyResources().getString("ConnectFourTitle"));
    ImageView connectFourImage = makeImage("gameLogos/ConnectFourLogo.png");
    Text connectFourDescrip = descripTextMaker("ConnectFourDescription");

    Text checkersText = titleTextMaker(getMyResources().getString("CheckersTitle"));
    ImageView checkersImage = makeImage("gameLogos/CheckersLogo.png");
    Text checkersDescrip = descripTextMaker("CheckersDescription");

    vBox.getChildren()
        .addAll(connectFourText, connectFourImage, connectFourDescrip, checkersText, checkersImage,
            checkersDescrip);


  }

  /**
   * Helper method to make text for each game Title
   *
   * @param property, the property to lookup in the .properties file
   * @return text, the title text
   */
  private Text titleTextMaker(String property) {
    Text text = new Text(property);
    text.getStyleClass().add("GameTitleText");
    text.setId(property);

    return text;
  }

  /**
   * Helper method to make the description for each game
   *
   * @param property, the property to lookup in the .properties file
   * @return text, the description text
   */
  private Text descripTextMaker(String property) {
    Text text = new Text(getMyResources().getString(property));
    text.getStyleClass().add("GameDescriptionText");
    text.setId(property);

    return text;
  }

  /**
   * Helper method to get the imageview of the images that represent each game
   *
   * @param URL, the URL to the image
   * @return imageView, the image of the logo
   */
  private ImageView makeImage(String URL) {
    ImageView imageView = new ImageView(new Image(URL));
    imageView.setFitWidth(IMAGE_WIDTH);
    imageView.setFitHeight(IMAGE_HEIGHT);
    imageView.setId(URL);
    return imageView;
  }
}

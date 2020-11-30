package ooga.view.dialogBox;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.model.data.PropertiesHandler;
import ooga.view.guiComponentFactory.GUIButtonFactory;
import ooga.view.guiComponentFactory.GUILeaderboardEntryFactory;

public class LeaderboardDialogBox extends DialogBox {

  /**
   * Subclass for setting up the DialogBox for allowing the user to see the current leaderboard
   *
   * @author JasonDong
   */

  private final GUIButtonFactory guiButtonFactory;
  private final GUILeaderboardEntryFactory guiLeaderboardEntryFactory;
  private static final int LEADER_STAGE_WIDTH = 400;
  private static final int LEADER_STAGE_HEIGHT = 700;
  private static final int TOP_INSET = 0;
  private static final int RIGHT_INSET = 30;
  private static final int BOTTOM_INSET = 0;
  private static final int LEFT_INSET = 30;
  private static final int LOGO_FIT_HEIGHT = 150;
  private static final int LOGO_FIT_WIDTH = 150;
  private static final int NUM_RANKS_TO_DISPLAY = 9;
  private VBox leaderboardVBox;
  private BorderPane root;
  private Button closeButton;
  private final HashMap<String, Integer> ranksMap = new HashMap<>();
  private HashMap<String, Integer> sortedRanksMap = new LinkedHashMap<>();

  public LeaderboardDialogBox(ResourceBundle myResources) {
    super(myResources);
    guiButtonFactory = new GUIButtonFactory();
    guiLeaderboardEntryFactory = new GUILeaderboardEntryFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setWidth(LEADER_STAGE_WIDTH);
    setHeight(LEADER_STAGE_HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
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
    root = setUpBorderPane();

    Scene myScene = new Scene(root);
    myScene.getStylesheets().add("guiStyles.css");
    root.getStyleClass().add("LeaderboardStyles");
    return myScene;

  }

  /**
   * Set up the borderpane and components to be placed within it
   *
   * @return borderpane
   */
  private BorderPane setUpBorderPane() {
    BorderPane borderPane = new BorderPane();
    setUpLogo(borderPane);
    setUpCloseButton(borderPane);
    setUpLeaderBoard(borderPane);

    return borderPane;
  }

  /**
   * Set up the main leaderboard
   *
   * @param borderPane
   */
  private void setUpLeaderBoard(BorderPane borderPane) {
    leaderboardVBox = new VBox(VBOX_SPACING);
    leaderboardVBox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    leaderboardVBox.setAlignment(Pos.TOP_CENTER);

    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File("data/leaderboardLog/leaderboard.lblog");
    propertiesHandler.createProperties(file);

    for (int i = 1; i < propertiesHandler.getKeySize() + 1; i++) {
      String[] playerInfo = propertiesHandler.getGameProperty(String.format("Player%d", i))
          .split(",");
      ranksMap.putIfAbsent(playerInfo[0], Integer.parseInt(playerInfo[1]));
    }
    sortRanksMap();
    int currRank = 1;
    for (Map.Entry<String, Integer> entry : sortedRanksMap.entrySet()) {
      String key = entry.getKey();
      int score = entry.getValue();
      if (currRank <= NUM_RANKS_TO_DISPLAY) {
        HBox leaderboardEntry = guiLeaderboardEntryFactory
            .makeLeaderboardEntry(currRank, key, score);
        leaderboardVBox.getChildren().add(leaderboardEntry);
        currRank++;
      }
    }
    borderPane.setCenter(leaderboardVBox);
  }

  /**
   * Helper method to sort the values of the map based on number of wins (values)
   */
  private void sortRanksMap() {
    sortedRanksMap = new LinkedHashMap<>();
    ranksMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> sortedRanksMap.put(x.getKey(), x.getValue()));
  }

  /**
   * Set up the leaderboard logo
   *
   * @param borderPane
   */
  private void setUpLogo(BorderPane borderPane) {
    ImageView logo = new ImageView("leaderboardLogo.png");
    logo.setFitHeight(LOGO_FIT_HEIGHT);
    logo.setFitWidth(LOGO_FIT_WIDTH);
    HBox imageHBOX = new HBox(logo);
    imageHBOX.setAlignment(Pos.CENTER);
    borderPane.setTop(imageHBOX);
  }

  /**
   * Set up the button to close to dialog box
   *
   * @param borderPane
   */
  private void setUpCloseButton(BorderPane borderPane) {
    closeButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "CloseButton",
            e -> this.close());
    closeButton.setDisable(false);
    HBox buttonHBox = new HBox(closeButton);
    buttonHBox.setAlignment(Pos.CENTER);
    borderPane.setBottom(buttonHBox);
  }
}

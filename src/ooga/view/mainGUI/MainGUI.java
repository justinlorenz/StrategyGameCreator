package ooga.view.mainGUI;

import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.exception.BadDataException;
import ooga.model.LeaderboardWriter;
import ooga.view.dialogBox.WinDialogBox;
import ooga.view.gridPane.RectangularGridPane;

/**
 * Main GUI used to display the main game board, along with profiles and names of the people playing
 * and the turn
 * <p>
 * This class is responsible for displaying all submodules of the GUI, and interfacing with the
 * controller in determining the game status, as well as handling the animation and step functions
 * for updating the board and AI.
 *
 *
 */

public class MainGUI extends Stage {

  public static final int STAGE_WIDTH = 710;
  public static final int STAGE_HEIGHT = 640;
  public static final int WINDOW_WIDTH = 125;
  public static final int WINDOW_BOTTOM_HEIGHT = 100;
  public static final int WINDOW_TOP_HEIGHT = 50;
  public static final double FRAMES_PER_SECOND = 60;
  public double SECOND_DELAY = 1 / FRAMES_PER_SECOND;
  private final ResourceBundle myResources;
  private final GameController gameController;
  private BorderPane root;
  private RectangularGridPane rectangularGridPane;
  private Timeline animation;
  private MainGUIPlayer1Window mainGUIPlayer1Window;
  private MainGUIPlayer2Window mainGUIPlayer2Window;
  private final Map<String, String> gridTypesForGames = Map
      .of("TicTacToe", "Monochrome", "Othello", "Monochrome",
          "ConnectFour", "Monochrome", "Checkers", "Polychrome", "CheckersMod", "Polychrome");

  public MainGUI(GameController gameController, ResourceBundle myResources) {
    this.gameController = gameController;
    this.myResources = myResources;
  }

  /**
   * Use reflection to make the grid based on the game the user wants to pick
   */
  private void makeGridPane() {
    try {
      Class<? extends RectangularGridPane> clazz = (Class<? extends RectangularGridPane>) Class
          .forName(String.format("ooga.view.gridPane.%sRectangularGridPane",
              gridTypesForGames.get(gameController.getCurrentGame())));
      rectangularGridPane = clazz.getDeclaredConstructor(String.class, GameController.class)
          .newInstance(gameController.getCurrentGame(), gameController);
    } catch (Exception e) {
      throw new BadDataException("Invalid Grid", e.getCause());
    }
    rectangularGridPane.initializeGrid(gameController.getGridSize(), gameController.getGridSize());
  }

  /**
   * Set up the stage
   */
  public void initializeStage() {
    setWidth(STAGE_WIDTH);
    setHeight(STAGE_HEIGHT);
    Scene myScene = setUpScene();
    setScene(myScene);
    setResizable(false);
    show();

    animation = new Timeline();
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /**
   * Set up the step function to check if the game is won or if the AI should play its turn
   *
   * @param elapsedTime, the step interval
   */
  public void step(double elapsedTime) {
    switch (gameController.getGameStatus()) {
      case WON, DRAW -> {
        animation.stop();
        WinDialogBox winDialogBox = new WinDialogBox(myResources,
            gameController.getGameStatus().getPlayerList().get(0).getPlayerName(),
            gameController.getGameStatus());
        winDialogBox.initializeStage();

        LeaderboardWriter leaderboardWriter = new LeaderboardWriter(
            gameController.getGameStatus().getPlayerList().get(0).getPlayerName());
        leaderboardWriter.writeToLeaderBoard();
      }
    }
    switch (gameController.getCurrPlayerTurn()) {
      case 1 -> {
        mainGUIPlayer1Window.setPlayer1Turn();
        mainGUIPlayer2Window.setPlayer1Turn();
      }

      case 2 -> {
        mainGUIPlayer2Window.setPlayer2Turn();
        mainGUIPlayer1Window.setPlayer2Turn();
      }
    }
    if (gameController.playAITurn()) {
      gameController.endTheCurrPlayerTurn();
      updateBoard();
    }
  }

  /**
   * Set up the scene
   *
   * @return scene, the scene to display on the stage
   */
  public Scene setUpScene() {
    makeGridPane();
    root = makeBorderPane();
    root.getStyleClass().add("MainGuiStyles");
    Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
    scene.getStylesheets().add("guiStyles.css");
    return scene;
  }

  /**
   * Set up the borderpane, initialize and utilize all aspects of the borderpane: both player's
   * statuses, the logo on top, and the buttons to display on the bottom
   * <p>
   * Also display the grid in the center
   *
   * @return borderpane, the root to place on the scene
   */
  private BorderPane makeBorderPane() {
    BorderPane myBorderPane = new BorderPane();

    mainGUIPlayer1Window = new MainGUIPlayer1Window(WINDOW_WIDTH, STAGE_HEIGHT, myResources,
        gameController.getCurrentTheme(), gameController.getProfilePic(0),
        gameController.getPlayerName(0));
    mainGUIPlayer2Window = new MainGUIPlayer2Window(WINDOW_WIDTH, STAGE_HEIGHT, myResources,
        gameController.getCurrentTheme(), gameController.getProfilePic(1),
        gameController.getPlayerName(1));
    MainGUITopWindow mainGUITopWindow = new MainGUITopWindow(WINDOW_TOP_HEIGHT, STAGE_WIDTH,
        myResources, gameController);
    MainGUIBottomWindow mainGUIBottomWindow = new MainGUIBottomWindow(WINDOW_BOTTOM_HEIGHT,
        STAGE_WIDTH, myResources, gameController.getCurrentTheme(), gameController, this);

    myBorderPane.setLeft(mainGUIPlayer1Window);
    myBorderPane.setRight(mainGUIPlayer2Window);
    myBorderPane.setBottom(mainGUIBottomWindow);
    myBorderPane.setTop(mainGUITopWindow);
    myBorderPane.setCenter(rectangularGridPane);

    return myBorderPane;
  }

  /**
   * Stop the animation
   */
  public void stopAnimation() {
    animation.stop();
  }

  /**
   * Clear the grid
   */
  public void clearGrid() {
    stopAnimation();
    gameController.resetGame();
    rectangularGridPane.resetBoard();
    rectangularGridPane.updateNewBoard();
    animation.play();
  }

  /**
   * Update the gameboard
   */
  public void updateBoard() {
    rectangularGridPane.updateNewBoard();
  }

}

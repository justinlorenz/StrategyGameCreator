package ooga.view.gridPane;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ooga.controller.GameController;
import ooga.view.tile.Tile;

/**
 * Superclass for different grids to display on the scene for the user to play a game
 *
 * @author JasonDong
 */

public abstract class RectangularGridPane extends Pane {

  private final String currGame;
  public static final int GRID_WIDTH = 460;
  public static final int GRID_HEIGHT = 460;
  public static final int BUTTON_GROUP_PLAYER1_X = -100;
  public static final int BUTTON_GROUP_PLAYER1_Y = 400;
  public static final int BUTTON_GROUP_PLAYER2_X = 470;
  public static final int BUTTON_GROUP_PLAYER2_Y = 400;
  public Tile[][] myTiles;
  private final GameController gameController;
  private final ResourceBundle myResources;
  private static final int BUTTON_WIDTH = 80;
  private static final int BUTTON_HEIGHT = 50;

  public RectangularGridPane(String currGame, GameController gameController) {
    this.currGame = currGame;
    this.gameController = gameController;
    myResources = ResourceBundle.getBundle("ENGLISH");
  }

  /**
   * Abstract method to allow each grid to initialize their own grids
   *
   * @param rows,    the rows of the grid
   * @param columns, the colums of the grid
   */
  public abstract void initializeGrid(int rows, int columns);

  /**
   * Abstract method to allow each grid to reset their grids
   */
  public abstract void resetBoard();

  /**
   * Abstract method to allow each grid to update their board accordingly
   */
  public abstract void updateNewBoard();

  /**
   * Return to current GameController
   *
   * @return gameController
   */
  protected GameController getGameController() {
    return gameController;
  }

  /**
   * Protected method for subclasses to make buttons different from that in the factory
   *
   * @param property
   * @return button, the button to place on the grid
   */
  protected Button makeButton(String property) {
    Button myButton = new Button();
    myButton.setText(myResources.getString(property));
    myButton.getStylesheets().add("guiStyles.css");
    myButton.getStyleClass().add(property);
    myButton.setId(property);
    myButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    myButton.setOnMousePressed(e -> {
      myButton.getStyleClass().add("EndTurnButtonClicked");
    });
    return myButton;

  }
}

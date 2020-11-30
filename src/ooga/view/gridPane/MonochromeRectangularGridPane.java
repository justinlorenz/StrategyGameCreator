package ooga.view.gridPane;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import ooga.controller.GameController;
import ooga.view.piece.NonMovableGUIPiece;
import ooga.view.tile.MonochromeTile;
import ooga.view.tile.Tile;

/**
 * Subclass for grids that only have one kind of tile color, such as TicTacToe, Othello, and Connect
 * Four
 *
 * 
 */
public class MonochromeRectangularGridPane extends RectangularGridPane {

  private double individualTileWidth;
  private double individualTileHeight;
  private final Group tileGroup = new Group();
  private final Group pieceGroup = new Group();
  private final Group buttonGroup = new Group();
  private Button endTurnButton;
  private final ResourceBundle myResources;
  private Boolean player1TurnButton = true;

  public MonochromeRectangularGridPane(String currGame, GameController gameController) {
    super(currGame, gameController);
    myResources = ResourceBundle.getBundle("ENGLISH");
  }

  /**
   * Set up the grid to display on the scene and place pieces on
   *
   * @param rows,    the rows of the grid
   * @param columns, the colums of the grid
   */
  @Override
  public void initializeGrid(int rows, int columns) {
    configureEndTurnButton();
    getChildren().addAll(tileGroup, pieceGroup, buttonGroup);
    pieceGroup.setId("PieceGroup");
    myTiles = new Tile[rows][columns];
    individualTileWidth = GRID_WIDTH / columns;
    individualTileHeight = GRID_HEIGHT / rows;
    double yCoord = 0;
    for (int i = 0; i < rows; i++) {
      double xCoord = 0;
      for (int j = 0; j < columns; j++) {
        MonochromeTile monochromeTile = new MonochromeTile(individualTileWidth,
            individualTileHeight, xCoord, yCoord);
        myTiles[i][j] = monochromeTile;
        tileGroup.getChildren().add(monochromeTile);
        monochromeTile.setOnMouseClicked(e -> detectedUserInput(monochromeTile));
        monochromeTile.setId("Monochrome" + i + j);
        xCoord += individualTileWidth;
      }
      yCoord += individualTileHeight;
    }
    updateNewBoard();
  }

  /**
   * Method called when a user input is detected, in this case a tile click
   *
   * @param monochromeTile
   */
  private void detectedUserInput(MonochromeTile monochromeTile) {
    int xCoord = 0;
    int yCoord = 0;
    for (int row = 0; row < myTiles.length; row++) {
      for (int col = 0; col < myTiles[row].length; col++) {
        if (myTiles[row][col] == monochromeTile) {
          xCoord = row;
          yCoord = col;
        }
      }
    }
    getGameController().userInputDetected(xCoord, yCoord);
    updateNewBoard();
  }

  /**
   * Method to configure the end turn button if needed for games that need it
   */
  private void configureEndTurnButton() {
    endTurnButton = makeButton("EndTurnButton");
    if (getGameController().doesPlayerEndTurn()) {
      buttonGroup.getChildren().add(endTurnButton);
    }
    if (getGameController().getCurrPlayerTurn() == 1) {
      buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER1_X);
      buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER1_Y);
    } else {
      buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER2_X);
      buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER2_Y);
      player1TurnButton = !player1TurnButton;
    }
    endTurnButton.setOnMouseReleased(e -> {
      if (player1TurnButton) {
        buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER2_X);
        buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER2_Y);
      } else {
        buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER1_X);
        buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER1_Y);
      }
      player1TurnButton = !player1TurnButton;
    });
  }

  /**
   * Update the current board based on the backend update, display pieces
   */
  @Override
  public void updateNewBoard() {
    resetBoard();
    for (int row = 0; row < myTiles.length; row++) {
      for (int col = 0; col < myTiles[row].length; col++) {
        String pieceType = getGameController().getPieceData(row, col).getPieceId();
        int playerNumber = getGameController().getPieceData(row, col).getPlayerNumber();
        String pieceKey = "";
        String imageFile = "";
        if (!pieceType.equals("Empty")) {
          pieceKey = String.format("Player%d%s", playerNumber, pieceType);
          imageFile = getGameController().getProperty(pieceKey);
          NonMovableGUIPiece nonMovablePiece = new NonMovableGUIPiece(individualTileWidth,
              individualTileHeight,
              myTiles[row][col].getXCoord(), myTiles[row][col].getYCoord(), new Image(imageFile));
          nonMovablePiece.setId("Piece" + row + col);
          pieceGroup.getChildren().add(nonMovablePiece);
        }
      }
    }
  }


  /**
   * Reset the board
   */
  @Override
  public void resetBoard() {
    pieceGroup.getChildren().clear();
  }
}

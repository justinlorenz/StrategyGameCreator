package ooga.view.gridPane;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import ooga.controller.GameController;
import ooga.controller.PieceData;
import ooga.controller.PossibleMoveList;
import ooga.model.PossibleMove;
import ooga.view.piece.EmptyGUIPiece;
import ooga.view.piece.GUIPiece;
import ooga.view.piece.MoveableGUIPiece;
import ooga.view.tile.PolychromeTile;
import ooga.view.tile.Tile;

/**
 * Subclass for grids that have two types of colors and pieces that are always on the grid, such as
 * Checkers
 */
public class PolychromeRectangularGridPane extends RectangularGridPane {

  private double individualTileWidth;
  private double individualTileHeight;
  private final Group tileGroup = new Group();
  private final Group pieceGroup = new Group();
  private final Group buttonGroup = new Group();
  private Button endTurnButton;
  private GUIPiece[][] myGUIPieces;
  //private Optional<MoveableGUIPiece>[][] myGUIPieces;
  private final ResourceBundle myResources;
  private int rows;
  private int columns;
  private PossibleMoveList possibleMoveList = new PossibleMoveList();

  public PolychromeRectangularGridPane(String currGame,
      GameController gameController) {
    super(currGame, gameController);
    myResources = ResourceBundle.getBundle("ENGLISH");
  }

  /**
   * Set up the grid with the proper number of rows and columns, then update the board
   *
   * @param rows,    the rows of the grid
   * @param columns, the colums of the grid
   */
  @Override
  public void initializeGrid(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    configureEndTurnButton();
    getChildren().addAll(tileGroup, pieceGroup, buttonGroup);
    setUpTiles(rows, columns);

    updateNewBoard();
  }

  /**
   * Set up the tiles, alternating between light and dark squares
   *
   * @param rows,    the number of rows for the grid
   * @param columns, the number of columns for the grid
   */
  private void setUpTiles(int rows, int columns) {
    myTiles = new Tile[rows][columns];
    tileGroup.setId("TileGroup");
    individualTileWidth = GRID_WIDTH / columns;
    individualTileHeight = GRID_HEIGHT / rows;
    double yCoord = 0;
    for (int i = 0; i < rows; i++) {
      double xCoord = 0;
      for (int j = 0; j < columns; j++) {
        PolychromeTile polychromeTile = new PolychromeTile((i + j) % 2 == 0, individualTileWidth,
            individualTileHeight, xCoord, yCoord);
        myTiles[i][j] = polychromeTile;
        tileGroup.getChildren().add(polychromeTile);
        polychromeTile.setOnMouseClicked(e -> detectedTileClick(polychromeTile));
        polychromeTile.setId("Polychrome" + i + j);
        xCoord += individualTileWidth;
      }
      yCoord += individualTileHeight;
    }
  }

  /**
   * Set up the end turn button next the grid
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
    }
    endTurnButton.setOnMouseReleased(e -> {
      getGameController().endTheCurrPlayerTurn();
      updateNewBoard();
    });
  }

  /**
   * Send to the backend what piece the user clicks and let it decide if the move is valid
   *
   * @param moveablePiece
   */
  private void detectedPieceClick(MoveableGUIPiece moveablePiece) {
    int xCoord = 0;
    int yCoord = 0;
    for (int row = 0; row < myGUIPieces.length; row++) {
      for (int col = 0; col < myGUIPieces[row].length; col++) {
        if (myGUIPieces[row][col] == moveablePiece) {
          xCoord = row;
          yCoord = col;
        }
      }
    }
    getGameController().userInputDetected(xCoord, yCoord);
    resetPieceColors();

    PieceData pieceData = getGameController().getClickedPiece();
    if (!(myGUIPieces[pieceData.getI()][pieceData.getJ()] instanceof EmptyGUIPiece) && (!pieceData
        .getPieceId().equals("Empty"))) {
      myGUIPieces[pieceData.getI()][pieceData.getJ()].highlightOnClick();
    }
    updateBoardWithPossibleMoves();
  }

  /**
   * Highlight on the board the possible moves that the user can make
   */
  private void updateBoardWithPossibleMoves() {
    for (PossibleMove possibleMove : possibleMoveList.getPossibleMoves()) {
      myTiles[possibleMove.getI()][possibleMove.getJ()].setFill(
          (possibleMove.getI() + possibleMove.getJ()) % 2 == 0 ? new ImagePattern(
              new Image("lightSquare.png")) : new ImagePattern(new Image("darkSquare.png")));
    }
    possibleMoveList = getGameController().getClickedPieceMoves();
    for (PossibleMove possibleMove : possibleMoveList.getPossibleMoves()) {
      myTiles[possibleMove.getI()][possibleMove.getJ()]
          .setFill(new ImagePattern(new Image("clickedTile.png")));
    }
  }

  /**
   * Reset all the pieces back to their initial colors after a piece is clicked
   */
  private void resetPieceColors() {
    for (int row = 0; row < myGUIPieces.length; row++) {
      for (int col = 0; col < myGUIPieces[row].length; col++) {
        if (!(myGUIPieces[row][col] instanceof EmptyGUIPiece)) {
          myGUIPieces[row][col].resetColor();
        }
      }
    }
  }

  /**
   * After the user clicks on a piece and then a tile, send to the backend to let it decide of the
   * move is valid
   *
   * @param polychromeTile
   */
  private void detectedTileClick(PolychromeTile polychromeTile) {
    int xCoord = 0;
    int yCoord = 0;
    for (int row = 0; row < myTiles.length; row++) {
      for (int col = 0; col < myTiles[row].length; col++) {
        if (myTiles[row][col] == polychromeTile) {
          xCoord = row;
          yCoord = col;
        }
      }
    }
    getGameController().userInputDetected(xCoord, yCoord);
    for (PossibleMove possibleMove : possibleMoveList.getPossibleMoves()) {
      myTiles[possibleMove.getI()][possibleMove.getJ()].setFill(
          (possibleMove.getI() + possibleMove.getJ()) % 2 == 0 ? new ImagePattern(
              new Image("lightSquare.png")) : new ImagePattern(new Image("darkSquare.png")));
    }
    updateNewBoard();
  }

  /**
   * Update the board after a move to display pieces correctly on the game board
   */
  @Override
  public void updateNewBoard() {
    resetBoard();
    myGUIPieces = new GUIPiece[myTiles.length][myTiles.length];
    pieceGroup.setId("PieceGroup");
    for (int row = 0; row < myTiles.length; row++) {
      for (int col = 0; col < myTiles[row].length; col++) {

        String pieceType = getGameController().getPieceData(row, col).getPieceId();
        int playerNum = getGameController().getPieceData(row, col).getPlayerNumber();
        String pieceKey = "";
        String imageFile = "";
        if (!pieceType.equals("Empty")) {
          pieceKey = String.format("Player%d%s", playerNum, pieceType);
          imageFile = getGameController().getProperty(pieceKey);
          MoveableGUIPiece moveablePiece = new MoveableGUIPiece(individualTileWidth,
              individualTileHeight, myTiles[row][col].getXCoord(), myTiles[row][col].getYCoord(),
              new Image(imageFile), imageFile);
          myGUIPieces[row][col] = moveablePiece;
          moveablePiece.setId("Piece" + row + col);
          moveablePiece.setOnMouseReleased(e -> detectedPieceClick(moveablePiece));
          pieceGroup.getChildren().add(moveablePiece);
        } else {
          EmptyGUIPiece emptyGUIPiece = new EmptyGUIPiece(individualTileWidth, individualTileHeight,
              myTiles[row][col].getXCoord(), myTiles[row][col].getYCoord());
          myGUIPieces[row][col] = emptyGUIPiece;
        }
      }
    }
    placeEndTurnButton();
  }

  /**
   * Clear the pieces that are in the scene
   */
  @Override
  public void resetBoard() {
    pieceGroup.getChildren().clear();
  }

  /**
   * Place the end turn button based on the current player's turn
   */
  public void placeEndTurnButton() {
    if (getGameController().getCurrPlayerTurn() == 2) {
      buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER2_X);
      buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER2_Y);
    } else {
      buttonGroup.setLayoutX(BUTTON_GROUP_PLAYER1_X);
      buttonGroup.setLayoutY(BUTTON_GROUP_PLAYER1_Y);
    }
  }
}

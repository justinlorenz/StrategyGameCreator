package ooga.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ooga.model.GameEngine;
import ooga.model.GameStatus;
import ooga.model.data.GridReader.CsvReader;
import ooga.model.data.GridWriter.CsvWriter;
import ooga.model.data.PropertiesHandler;
import ooga.model.data.PropertiesManager;
import ooga.model.data.gameReader.GameFileReader;
import ooga.model.data.gameReader.GameReader;
import ooga.model.data.gameWriter.GameWriter;

/**
 * Main GameController, responsible for connecting frontend and backend, responsible for creating a
 * game, loading a game, parsing CSV and .game data initiate backend classes. Includes setters and
 * getters for the frontend.
 */
public class GameController implements PieceDataRetriever {

  private final PropertiesManager propertiesManager;
  private GameEngine myGame;
  private GameFileReader dataReader;
  private String currTheme;
  private String currGame;
  private Boolean isSinglePlayer = true;
  private final List<String> playerNames = new ArrayList<>();
  private final List<String> profilePics = new ArrayList<>();
  private static final String PLAYER_1_IMAGE = "Player1Image";
  private static final String PLAYER_2_IMAGE = "Player2Image";
  private static final String PLAYER_1_NAME = "Player1Name";
  private static final String PLAYER_2_NAME = "Player2Name";
  private static final String GAMETYPE = "GameType";
  private static final String THEME = "Theme";
  private static final String HUMAN = "Human";
  private static final String AI = "AI";
  private static final String CURRENT_PLAYER_INDEX = "CurrentPlayerIndex";
  private int gridSize;

  public GameController() {
    propertiesManager = new PropertiesHandler();
  }

  /**
   * Load in a previously saved game
   *
   * @param gameFile, the File to be loaded
   */

  public void loadInGame(File gameFile) {
    dataReader = new GameReader(propertiesManager, new CsvReader()); // Only reads csvs here
    myGame = dataReader.loadInGame(gameFile);
    gridSize = myGame.getGridSize();
    currGame = dataReader.getGameProperty(GAMETYPE);
    currTheme = dataReader.getGameProperty(THEME);

    profilePics.add(dataReader.getGameProperty(PLAYER_1_IMAGE));
    profilePics.add(dataReader.getGameProperty(PLAYER_2_IMAGE));
    playerNames.add(dataReader.getGameProperty(PLAYER_1_NAME));
    playerNames.add(dataReader.getGameProperty(PLAYER_2_NAME));
  }

  /**
   * Create a new game, called from the frontend when the user uses the dialog boxes to pick the
   * specifics for their game
   */
  public void createNewGame() {
    dataReader = new GameReader(propertiesManager, new CsvReader());
    String p1 = HUMAN;
    String p2;
    if (isSinglePlayer) {
      p2 = AI;
    } else {
      p2 = HUMAN;
    }
    List<String> playerTypes = List.of(p1, p2);
    myGame = dataReader
        .loadDefaultGameFromProperties(currGame, gridSize, playerNames, playerTypes, profilePics,
            currTheme);
  }

  /**
   * Save the game
   *
   * @param gameName,    the filename to be saved as
   * @param author,      the author of the saved file
   * @param description, the description for saving the game
   */
  public void saveGame(String gameName, String author, String description) {
    GameWriter gameWriter = new GameWriter(new CsvWriter());
    propertiesManager
        .putProperty(CURRENT_PLAYER_INDEX, Integer.toString(myGame.getCurrPlayerTurn()));
    gameWriter.saveGameFile(propertiesManager, gridSize, this, gameName, author, description);
  }

  /**
   * Play the AI's turn in a singleplayer game
   *
   * @return myGame.playAITurn, the method called for the AI to pick a move
   */
  public boolean playAITurn() {
    return myGame.playAITurn();
  }

  /**
   * Get the possible moves for the piece selected
   *
   * @return PossibleMovesList, a list of possible moves
   */
  public PossibleMoveList getClickedPieceMoves() {
    return myGame.getClickedPieceMoves();
  }

  /**
   * Method called when the user clicks on a piece or tile
   *
   * @param i, the xCoordinate that was clicked
   * @param j, the yCoordinate that was clicked
   */
  public void userInputDetected(int i, int j) {
    myGame.gotUserInput(i, j);
  }

  /**
   * Reset the game
   */
  public void resetGame() {
    myGame = dataReader.getCurrGame();
  }

  /**
   * Add the player's name to the List of names
   *
   * @param name, the name of the player
   */
  public void addPlayerName(String name) {
    playerNames.add(name);
  }

  /**
   * Add the player's chosen profile picture to a list of profile picture
   *
   * @param profilePic, the string for the profile picture
   */
  public void addProfilePic(String profilePic) {
    profilePics.add(profilePic);
  }

  /**
   * Setter for the game the user picked
   *
   * @param currGame, the game picked from the dialog box
   */
  public void setCurrentGame(String currGame) {
    this.currGame = currGame;
  }

  /**
   * Setter for the theme that the user picked
   *
   * @param currTheme, the theme that the user picked from the dialog box
   */
  public void setCurrentTheme(String currTheme) {
    this.currTheme = currTheme;
  }

  /**
   * Setter if the game is singleplayer for multiplayer
   *
   * @param isSinglePlayer, boolean for singleplayer or not
   */
  public void setSinglePlayer(Boolean isSinglePlayer) {
    this.isSinglePlayer = isSinglePlayer;
  }

  /**
   * Setter for the current grid size
   *
   * @param gridSize, the grid size picked from the dialogbox
   */
  public void setCurrentGridSize(int gridSize) {
    this.gridSize = gridSize;
  }

  /**
   * End the current player's turn
   */
  public void endTheCurrPlayerTurn() {
    myGame.endCurrPlayerTurn();
  }

  /**
   * Boolean for if the player ended their turn
   */
  public boolean doesPlayerEndTurn() {
    return myGame.doesUserEndTurn();
  }

  /**
   * Get the clicked piece data from the game
   *
   * @return
   */
  public PieceData getClickedPiece() {
    return myGame.getClickedPieceData();
  }

  /**
   * Get the piece data, takes in coordinates
   *
   * @param i, the xCoordinate of the piece
   * @param j, the yCoordinate of the piece
   * @return
   */
    public PieceData getPieceData(int i, int j) {
    return myGame.getPieceData(i, j);
  }

  /**
   * Get the GameStatus of the game
   *
   * @return GameStatus, the gamestatus such as a win or tie, or ongoing game
   */
  public GameStatus getGameStatus() {
    return myGame.getGameStatus();
  }

  /**
   * Get the value associated with the key property
   *
   * @param key, the key to look for in the .game file
   * @return String of the value associated with the key
   */
  public String getProperty(String key) {
    return propertiesManager.getGameProperty(key);
  }

  /**
   * Get the player's name based on the index
   *
   * @param playerIndex, the index of the player's name
   * @return String with the player's name
   */
  public String getPlayerName(int playerIndex) {
    return playerNames.get(playerIndex);
  }

  /**
   * Get the player's profile picture based in the index
   *
   * @param profileIndex, the index of the profile picture
   * @return String with the player's profile picture
   */
  public String getProfilePic(int profileIndex) {
    return profilePics.get(profileIndex);
  }

  /**
   * Get the current game
   *
   * @return String of the current game
   */
  public String getCurrentGame() {
    return currGame;
  }

  /**
   * Get the current theme
   *
   * @return String of the current theme
   */
  public String getCurrentTheme() {
    return currTheme;
  }

  /**
   * Get the current player's turn
   *
   * @return Integer of the current player's turn
   */
  public int getCurrPlayerTurn() {
    return myGame.getCurrPlayerTurn();
  }

  /**
   * Get the grid size of the game
   *
   * @return Integer of the current grid size
   */
  public int getGridSize() {
    return gridSize;
  }
}

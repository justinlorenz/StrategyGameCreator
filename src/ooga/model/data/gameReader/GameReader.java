package ooga.model.data.gameReader;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ooga.exception.BadDataException;
import ooga.exception.BadFileException;
import ooga.exception.BadGameFileException;
import ooga.exception.MissingPropertyException;
import ooga.model.BoardStructure;
import ooga.model.Game;
import ooga.model.GameEngine;
import ooga.model.GameMoveHandler;
import ooga.model.PieceBoardStructure;
import ooga.model.SpecialPieceHandler;
import ooga.model.data.GridReader.GridReader;
import ooga.model.data.PropertiesManager;
import ooga.model.gameBuildingBlocks.boardMoveUpdate.BoardMoveUpdate;
import ooga.model.gameBuildingBlocks.boardMoveUpdate.BoardMoveUpdateFactory;
import ooga.model.gameBuildingBlocks.pieceMove.PieceMove;
import ooga.model.gameBuildingBlocks.pieceMove.PieceMoveFactory;
import ooga.model.gameBuildingBlocks.promotionCondition.PromotionCondition;
import ooga.model.gameBuildingBlocks.promotionCondition.PromotionConditionFactory;
import ooga.model.gameBuildingBlocks.winConditions.WinCondition;
import ooga.model.gameBuildingBlocks.winConditions.WinConditionFactory;
import ooga.model.players.Player;
import ooga.model.players.PlayerTypeFactory;

/**
 * Class used to read .game files to read Game Files
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class GameReader implements GameFileReader {

  private static final String GAME_TYPE = "GameType";
  private static final String CURRENT_PLAYER_INDEX = "CurrentPlayerIndex";
  private static final String PIECES_ARE_MOVED = "PiecesAreMoved";
  private static final String WIN_ON_END = "WinOnEnd";
  private static final String USER_ENDS_TURN = "UserEndsTurn";
  private static final String WIN_CONDITIONS = "WinConditions";
  private static final String BOARD_MOVE_UPDATE = "BoardMoveUpdate";
  private static final String PIECE_MOVE = "PieceMove";
  private static final String SPECIAL_PIECE = "SpecialPiece";
  private static final String SPECIAL_MOVE = "SpecialMove";
  private static final String PROMOTION_CONDITION = "PromotionCondition";
  private static final String INITIAL_CONFIGURATION = "InitialConfiguration";
  private static final String PLAYER = "Player";
  private static final String NAME = "Name";
  private static final String THEME = "Theme";

  private final PropertiesManager propertiesManager;
  private PlayerTypeFactory playerTypeFactory;
  private final GridReader gridReader;

  /**
   * Constructor for GameReader
   *
   * @param propertiesManager propertiesManager which encapsulates properties of file
   * @param gridReader gridReader which reads the grid data
   */
  public GameReader(PropertiesManager propertiesManager, GridReader gridReader) {
    this.propertiesManager = propertiesManager;
    this.gridReader = gridReader;
  }

  /**
   * Loads in game
   *
   * @param gameFile File of loaded game
   * @return GameEngine of the loaded game
   */
  public GameEngine loadInGame(File gameFile) {
    try {
      propertiesManager.createProperties(gameFile);
      return loadGameFromProperties(propertiesManager);
    } catch (BadFileException e) {
      throw e;
    } catch (Exception e) {
      throw new BadGameFileException(e);
    }
  }

  private GameEngine loadGameFromProperties(PropertiesManager propertiesManager) {
    String gameType = propertiesManager.getGameProperty(GAME_TYPE);
    PieceBoardStructure gamePieces = gridReader.readInGameStates(propertiesManager);
    BoardStructure currBoard = new BoardStructure(gamePieces);
    int currentPlayerIndex =
        parseIntFromString(propertiesManager.getGameProperty(CURRENT_PLAYER_INDEX),
            CURRENT_PLAYER_INDEX) - 1;
    GameMoveHandler gameMoveHandler = retrieveGameMoveHandler(currBoard, propertiesManager);
    List<Player> playerList = createGamePlayers(gameMoveHandler);
    boolean winOnEnd = parseBooleanFromString(propertiesManager.getGameProperty(WIN_ON_END),
        WIN_ON_END);
    boolean userEndsTurn = parseBooleanFromString(propertiesManager.getGameProperty(USER_ENDS_TURN),
        USER_ENDS_TURN);
    validateImages(propertiesManager);
    return new Game(currBoard, playerList, currentPlayerIndex, gameMoveHandler,
        getWinConditions(propertiesManager), winOnEnd, userEndsTurn);
  }

  private void validateImages(PropertiesManager propertiesManager) {
    validateImage(propertiesManager.getGameProperty("Player1NormalPiece"));
    validateImage(propertiesManager.getGameProperty("Player2NormalPiece"));
  }

  private List<WinCondition> getWinConditions(PropertiesManager propertiesManager) {
    String[] winConditionsString = propertiesManager.getGameProperty(WIN_CONDITIONS).split(",");
    List<WinCondition> winConditions = new ArrayList<>();
    for (String winConditionString : winConditionsString) {
      String[] winCondition = winConditionString.split(" ");
      if (winCondition.length > 1) {
        winConditions.add(new WinConditionFactory().getWinCondition(winCondition[0],
            parseIntFromString(winCondition[1], WIN_CONDITIONS)));
      } else {
        winConditions.add(new WinConditionFactory().getWinCondition(winCondition[0]));
      }
    }
    return winConditions;
  }

  private GameMoveHandler retrieveGameMoveHandler(BoardStructure currBoard,
      PropertiesManager propertiesManager) {
    List<BoardMoveUpdate> boardMoveUpdates = getBoardMoveUpdates(propertiesManager);
    List<PieceMove> normalPieceMoves = getPieceMoves(propertiesManager);
    SpecialPieceHandler specialPieceHandler = retrieveSpecialPieceHandler(propertiesManager);
    boolean piecesAreMoved = parseBooleanFromString(
        propertiesManager.getGameProperty(PIECES_ARE_MOVED), PIECES_ARE_MOVED);
    return new GameMoveHandler(currBoard, boardMoveUpdates, normalPieceMoves, specialPieceHandler,
        piecesAreMoved);
  }

  private SpecialPieceHandler retrieveSpecialPieceHandler(PropertiesManager propertiesManager) {
    boolean hasSpecialPiece = parseBooleanFromString(
        propertiesManager.getGameProperty(SPECIAL_PIECE), SPECIAL_PIECE);
    List<PieceMove> specialPieceMoves = new ArrayList<>();
    List<PromotionCondition> promotionConditions = new ArrayList<>();
    if (hasSpecialPiece) {
      String[] specialPieceMovesString = propertiesManager.getGameProperty(SPECIAL_MOVE).split(",");
      for (String specialPieceMove : specialPieceMovesString) {
        specialPieceMoves.add(new PieceMoveFactory().getPieceMove(specialPieceMove));
      }
      String[] promotionConditionsString = propertiesManager.getGameProperty(PROMOTION_CONDITION)
          .split(",");
      for (String promotionCondition : promotionConditionsString) {
        promotionConditions
            .add(new PromotionConditionFactory().getPromotionCondition(promotionCondition));
      }
      validateImage(propertiesManager.getGameProperty("Player1SpecialPiece"));
      validateImage(propertiesManager.getGameProperty("Player2SpecialPiece"));
    }
    return new SpecialPieceHandler(hasSpecialPiece, specialPieceMoves, promotionConditions);
  }

  private List<PieceMove> getPieceMoves(PropertiesManager propertiesManager) {
    List<PieceMove> normalPieceMoves = new ArrayList<>();
    String[] normalPiecesMovesString = propertiesManager.getGameProperty(PIECE_MOVE).split(",");
    for (String normalPieces : normalPiecesMovesString) {
      normalPieceMoves.add(new PieceMoveFactory().getPieceMove(normalPieces));
    }
    return normalPieceMoves;
  }

  private List<BoardMoveUpdate> getBoardMoveUpdates(PropertiesManager propertiesManager) {
    String[] boardMoveUpdatesString = propertiesManager.getGameProperty(BOARD_MOVE_UPDATE)
        .split(",");
    List<BoardMoveUpdate> boardMoveUpdates = new ArrayList<>();
    for (String boardMoveUpdateString : boardMoveUpdatesString) {
      boardMoveUpdates.add(new BoardMoveUpdateFactory().getBoardMoveUpdate(boardMoveUpdateString));
    }
    return boardMoveUpdates;
  }

  /**
   * Load in default of game
   *
   * @param gameFile GameType that is being loaded for default
   * @param gridSize GridSize of the game
   * @param playerNames Names of the players
   * @param playerTypes Types of the players
   * @param playerImagePaths Image paths for players
   * @param theme Theme used in game
   * @return GameEngine of the default loaded game
   */
  public GameEngine loadDefaultGameFromProperties(String gameFile, int gridSize,
      List<String> playerNames, List<String> playerTypes,
      List<String> playerImagePaths, String theme) {
    try {
      File file = new File("data/defaultGrid/" + gameFile + ".game");
      propertiesManager.createProperties(file);
      String gameType = propertiesManager.getGameProperty("GameType");
      propertiesManager.updateProperty(INITIAL_CONFIGURATION,
          "data/defaultGrid/" + gameType + gridSize + "x" + gridSize + ".csv");

      for (int i = 1; i <= 2; i++) {
        propertiesManager.updateProperty(PLAYER + i + NAME, playerNames.get(i - 1));
        propertiesManager.updateProperty(PLAYER + i, playerTypes.get(i - 1));
        propertiesManager.updateProperty(PLAYER + i + "Image", playerImagePaths.get(i - 1));
      }
      propertiesManager.updateProperty(THEME, theme);
      return loadGameFromProperties(propertiesManager);
    } catch (BadFileException e) {
      throw e;
    } catch (Exception e) {
      throw new BadGameFileException(e);
    }
  }

  /**
   * Uses reflection to create a new list of players in the game
   */
  private List<Player> createGamePlayers(GameMoveHandler gameMoveHandler) {
    playerTypeFactory = new PlayerTypeFactory();

    List<Player> playerList = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      int playerNumber = i + 1;
      String playerType = propertiesManager.getGameProperty(PLAYER + playerNumber);
      String playerName = propertiesManager.getGameProperty(PLAYER + playerNumber + NAME);
      playerList.add(
          playerTypeFactory.addNewPlayer(playerType, playerName, playerNumber, gameMoveHandler));
    }
    return playerList;
  }

  /**
   * Gets game property with key.
   *
   * @param key key to retrieve property of
   * @return property associated with key
   */
  public String getGameProperty(String key) {
    return propertiesManager.getGameProperty(key);
  }

  /** Gets the GameEngine that has been read
   *
   * @return current GameEngine that was read
   */
  public GameEngine getCurrGame() {
    return loadGameFromProperties(propertiesManager);
  }

  private void validateImage(String imagePath) {
    URL url = getClass().getClassLoader().getResource(imagePath);
    if(url==null){
      throw new BadDataException(imagePath);
    }
  }

  private int parseIntFromString(String num, String property) {
    try {
      return Integer.parseInt(num);
    } catch (Exception e) {
      throw new BadDataException(property, e);
    }
  }

  private boolean parseBooleanFromString(String bool, String property) {
    if (bool.toLowerCase().equals("true")) {
      return true;
    } else if (bool.toLowerCase().equals("false")) {
      return false;
    } else {
      throw new BadDataException(property);
    }
  }

}

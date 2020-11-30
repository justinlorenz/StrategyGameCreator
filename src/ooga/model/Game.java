package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.controller.PieceData;
import ooga.controller.PossibleMoveList;
import ooga.model.gameBuildingBlocks.winConditions.WinCondition;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.Piece;
import ooga.model.players.Player;

/**
 * Game class which implements GameEngine
 */
public class Game implements GameEngine {

  public static final int NON_USED_PARAMETER = 0;

  private final BoardStructure currBoard;
  private final GameMoveHandler gameMoveHandler;
  private final List<Player> playerList;
  private Player currPlayer;
  private Piece clickedPiece;
  private GameStatus gameStatus = GameStatus.PLAYING;
  private final boolean userEndsTurn;
  private final List<WinCondition> winConditions;
  private final boolean winOnEnd;

  /**
   * Constructor for Game
   *
   * @param currentBoard BoardStructure Game is played on
   * @param playerList List of Players
   * @param currPlayerIndex Index of which player will start
   * @param gameMoveHandler GameMoveHandler which contains how moves are done
   * @param winConditions WinConditions which contains how games are won
   * @param winOnEnd Boolean which determines if games are only won when no more moves can be played
   * @param userEndsTurn Boolean which determines if users manually end their turn.
   */
  public Game(BoardStructure currentBoard, List<Player> playerList, int currPlayerIndex,
      GameMoveHandler gameMoveHandler, List<WinCondition> winConditions, boolean winOnEnd,
      boolean userEndsTurn) {
    this.currBoard = currentBoard;
    this.playerList = playerList;
    this.gameMoveHandler = gameMoveHandler;
    this.currPlayer = playerList.get(currPlayerIndex);
    this.winConditions = winConditions;
    this.winOnEnd = winOnEnd;
    this.userEndsTurn = userEndsTurn;
    this.clickedPiece = new EmptyPiece(0, 0);
  }

  private void updateGameStatus() {
    if (!winOnEnd) {
      findIfGameWon();
      if (gameStatus != GameStatus.WON && !gameMoveHandler.validPieceMoveExists(currPlayer)) {
        gameStatus = GameStatus.DRAW;
        gameStatus.setPlayerList(playerList);
      }
    } else if (!gameMoveHandler.validPieceMoveExists(currPlayer)) {
      findIfGameWon();
      if (gameStatus != GameStatus.WON) {
        gameStatus = GameStatus.DRAW;
        gameStatus.setPlayerList(playerList);
      }
    }
  }

  private void findIfGameWon() {
    List<WinStatus> gameStatuses = new ArrayList<>();
    for (WinCondition winCondition : winConditions) {
      gameStatuses.add(winCondition.getWinStatus(currBoard));
    }
    for (WinStatus winStatus : gameStatuses) {
      if (winStatus.isGameWon()) {
        gameStatus = GameStatus.WON;
        gameStatus.setPlayerList(List.of(findPlayerFromNumber(winStatus.getWinningPlayer())));
      }
    }
  }

  /**
   * Method to handle user input on boardStructure
   *
   * @param i I coordinate that user inputted
   * @param j J coordinate that user inputted
   */
  public void gotUserInput(int i, int j) {
    if (currPlayer.isHuman()) {
      if (!gameMoveHandler.arePiecesMoved() && currPlayer.makeMove(i, j, currBoard)) {
        checkIfUpdateTurn();
        updateGameStatus();
        clickedPiece = new EmptyPiece(0, 0);
      } else if (gameMoveHandler.arePiecesMoved() && updateClickedPiece(i, j) && currPlayer
          .makeMove(clickedPiece.getI(), clickedPiece.getJ(), i, j, currBoard)) {
        checkIfUpdateTurn();
        updateGameStatus();
        clickedPiece = new EmptyPiece(0, 0);
      }
    }
  }

  /**
   * Method plays AI turn
   *
   * @return Returns true if turn was played properly
   */
  public boolean playAITurn() {
    if (!currPlayer.isHuman()) {
      if (!gameMoveHandler.arePiecesMoved()) {
        currPlayer.makeMove(0, 0, currBoard);
      } else {
        currPlayer.makeMove(0, 0, 0, 0, currBoard);
      }
      if (!userEndsTurn) {
        updatePlayerTurn();
      }
      updateGameStatus();

      return true;
    }
    return false;
  }

  private void updatePlayerTurn() {
    int currPlayerIndex = playerList.indexOf(currPlayer);
    currPlayerIndex++;
    if (currPlayerIndex >= playerList.size()) {
      currPlayer = playerList.get(0);
    } else {
      currPlayer = playerList.get(currPlayerIndex);
    }
    clickedPiece = new EmptyPiece(0, 0);
  }

  private void checkIfUpdateTurn() {
    if (!userEndsTurn) {
      updatePlayerTurn();
    }
  }

  /**
   * Method ends current players turn
   */
  public void endCurrPlayerTurn() { //button is clicked
    if (userEndsTurn) {
      updatePlayerTurn();
    }
  }

  /**
   * Retrieves the associated number of the current player's whose turn it is
   *
   * @return The current player's turn associated number
   */
  public int getCurrPlayerTurn() {
    return currPlayer.getPlayerNumber();
  }

  /**
   * Retrieves PieceData at specified coordinates
   *
   * @param i I coordinate to retrieve PieceData from
   * @param j J coordinate to retrieve PieceData from
   * @return PieceData at specified coordinates
   */
  public PieceData getPieceData(int i, int j) {
    return new PieceData(currBoard.getPieceId(i, j), currBoard.getPlayerNumber(i, j), i, j);
  }


  /**
   * Retrieves PieceData of the clicked Piece
   *
   * @return PieceData of currently clicked Piece
   */
  public PieceData getClickedPieceData() {
    return new PieceData(clickedPiece.getPieceId(), clickedPiece.getPlayerNumber(),
        clickedPiece.getI(), clickedPiece.getJ());
  }


  /**
   * Retrieves the current GameStatus of game
   *
   * @return Return current GameStatus
   */
  public GameStatus getGameStatus() {
    return gameStatus;
  }

  /**
   * Retrieves player based on associated playerNumber
   *
   * @param playerNumber playerNumber to retrieve Player for
   * @return Player with associated playerNumber
   */
  private Player findPlayerFromNumber(int playerNumber) {
    return playerList.get(playerNumber - 1);
  }

  /**
   * Gets gridSize of game
   *
   * @return Size of the grid of game
   */
  public int getGridSize() {
    return currBoard.getCols();
  }

  /**
   * Returns a list of all possible moves for the clicked piece to be displayed on the UI
   */
  @Override
  public PossibleMoveList getClickedPieceMoves() {
    PossibleMoveList possibleMoveList = new PossibleMoveList();
    if (!clickedPiece.isEmpty()) {
      possibleMoveList.addPossibleMoves(gameMoveHandler
          .getPossibleMoves(currBoard, clickedPiece.getI(), clickedPiece.getJ(),
              clickedPiece.getPlayerNumber()));
    }
    return possibleMoveList;
  }

  /**
   * @return Returns boolean if turns are ended manually
   */
  public boolean doesUserEndTurn() {
    return userEndsTurn;
  }

  private boolean updateClickedPiece(int i, int j) {
    if (currBoard.getPieceType(i, j).equals(clickedPiece)
        && clickedPiece.getI() == i && clickedPiece.getJ() == j) {
      clickedPiece = new EmptyPiece(0, 0);
      return false;
    } else if (
        clickedPiece.isEmpty() && currBoard.getPlayerNumber(i, j) == currPlayer.getPlayerNumber() ||
            !clickedPiece.isEmpty() && clickedPiece.getPlayerNumber() == currBoard
                .getPlayerNumber(i, j)) {
      this.clickedPiece = currBoard.getPieceType(i, j);
      return false;
    } else {
      return !clickedPiece.isEmpty() && currBoard.getPieceType(i, j).isEmpty();
    }
  }


}

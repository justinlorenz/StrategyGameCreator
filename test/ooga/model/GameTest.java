package ooga.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import ooga.model.gameBuildingBlocks.winConditions.WinCondition;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import ooga.model.players.Player;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void userClicksValidMovePiecesAreNotMoved() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.WON, game.getGameStatus());
    assertEquals("Empty", game.getClickedPieceData().getPieceId());
    assertEquals(2, game.getCurrPlayerTurn());
  }

  @Test
  void userClicksInvalidMovePiecesAreNotMoved() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(false);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
    assertEquals("Empty", game.getClickedPieceData().getPieceId());
    assertEquals(1, game.getCurrPlayerTurn());
  }

  @Test
  void userClicksEmptySpaceValidMovePiecesAreMoved() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));
    when(boardStructure.getPlayerNumber(anyInt(),anyInt())).thenReturn(1);
    when(boardStructure.getPieceType(anyInt(),anyInt())).thenReturn(new NormalPiece(1,"Normal",0,0));
    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
    assertEquals("Normal", game.getClickedPieceData().getPieceId());
    assertEquals(1,game.getClickedPieceData().getPlayerNumber());
    assertEquals(1, game.getCurrPlayerTurn());
  }

  @Test
  void userClicksSameOwnPieceValidMovePiecesAreMoved() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));
    when(boardStructure.getPlayerNumber(anyInt(),anyInt())).thenReturn(1);
    when(boardStructure.getPieceType(0,0)).thenReturn(new NormalPiece(1,"Normal",0,0));
    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
    assertEquals("Empty", game.getClickedPieceData().getPieceId());
    assertEquals(0,game.getClickedPieceData().getPlayerNumber());
    assertEquals(1, game.getCurrPlayerTurn());
  }

  @Test
  void userClicksDifferentOwnCellWhileHoldingClickedPieceTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));
    when(boardStructure.getPlayerNumber(anyInt(),anyInt())).thenReturn(1);
    when(boardStructure.getPieceType(0,0)).thenReturn(new NormalPiece(1,"Normal",0,0));
    when(boardStructure.getPieceType(0,1)).thenReturn(new NormalPiece(1,"Normal",0,1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    game.gotUserInput(0,1);
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
    assertEquals("Normal", game.getClickedPieceData().getPieceId());
    assertEquals(0,game.getClickedPieceData().getI());
    assertEquals(1,game.getClickedPieceData().getJ());
    assertEquals(1,game.getClickedPieceData().getPlayerNumber());
    assertEquals(1, game.getCurrPlayerTurn());
  }

  @Test
  void userClicksNewEmptyCellWhileHoldingClickedPieceTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));
    when(boardStructure.getPlayerNumber(0,0)).thenReturn(1);
    when(boardStructure.getPieceType(0,0)).thenReturn(new NormalPiece(1,"Normal",0,0));
    when(boardStructure.getPieceType(0,1)).thenReturn(new EmptyPiece(0,0));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    game.gotUserInput(0,1);
    assertEquals("Empty", game.getClickedPieceData().getPieceId());
    assertEquals(2, game.getCurrPlayerTurn());
    assertEquals(GameStatus.WON, game.getGameStatus());
  }

  @Test
  void backToPlayer1Test(){
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(1).isHuman()).thenReturn(true);
    when(playerList.get(1).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));

    Game game = new Game(boardStructure, playerList, 1, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.WON, game.getGameStatus());
    assertEquals("Empty", game.getClickedPieceData().getPieceId());
    assertEquals(1, game.getCurrPlayerTurn());

  }

  @Test
  void endPlayerTurnTest(){
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));

    Game userEndsTurnGame = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, true);
    userEndsTurnGame.endCurrPlayerTurn();
    assertEquals(2, userEndsTurnGame.getCurrPlayerTurn());

    Game regularGame = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    regularGame.endCurrPlayerTurn();
    assertEquals(1, regularGame.getCurrPlayerTurn());
  }

  @Test
  void notWinOnEndDrawTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(false, 0));
    when(gameMoveHandler.validPieceMoveExists(any())).thenReturn(false);

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.DRAW, game.getGameStatus());
  }

  @Test
  void winOnEndDrawTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(false, 0));
    when(gameMoveHandler.validPieceMoveExists(any())).thenReturn(false);

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, true, false);
    game.gotUserInput(0,0);
    assertEquals(GameStatus.DRAW, game.getGameStatus());
  }

  @Test
  void getGridSizeTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(boardStructure.getCols()).thenReturn(3);
    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    assertEquals(3, game.getGridSize());
  }

  @Test
  void playAITurnAsHumanTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    assertFalse(game.playAITurn());
    assertEquals(1, game.getCurrPlayerTurn());
  }

  @Test
  void playAITurnNotPiecesAreMovedTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(false);
    when(gameMoveHandler.validPieceMoveExists(any())).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(false);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(false, 1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    assertTrue(game.playAITurn());
    assertEquals(2, game.getCurrPlayerTurn());
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
  }

  @Test
  void playAITurnPiecesAreMovedTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).isHuman()).thenReturn(false);
    when(gameMoveHandler.validPieceMoveExists(any())).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(false, 1));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    assertTrue(game.playAITurn());
    verify(playerList.get(0),times(1)).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any());
    assertEquals(2, game.getCurrPlayerTurn());
    assertEquals(GameStatus.PLAYING, game.getGameStatus());
  }

  @Test
  void getPieceDataTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(boardStructure.getPieceId(0,0)).thenReturn("PieceIDTest");
    when(boardStructure.getPlayerNumber(0,0)).thenReturn(1);

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    assertEquals("PieceIDTest", game.getPieceData(0,0).getPieceId());
    assertEquals(1, game.getPieceData(0,0).getPlayerNumber());
  }

  @Test
  void getClickedPieceMovesTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(gameMoveHandler.getPossibleMoves(any(),anyInt(),anyInt(),anyInt())).thenReturn(List.of(new PossibleMove(0,0)));
    when(player2.getPlayerNumber()).thenReturn(2);
    when(playerList.get(0).getPlayerNumber()).thenReturn(1);
    when(playerList.get(0).isHuman()).thenReturn(true);
    when(playerList.get(0).makeMove(anyInt(),anyInt(),anyInt(),anyInt(),any())).thenReturn(true);
    when(gameMoveHandler.arePiecesMoved()).thenReturn(true);
    when(winCondition.getWinStatus(any())).thenReturn(new WinStatus(true, 1));
    when(boardStructure.getPlayerNumber(0,0)).thenReturn(1);
    when(boardStructure.getPieceType(0,0)).thenReturn(new NormalPiece(1,"Normal",0,0));

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, false);
    game.gotUserInput(0,0);
    assertEquals(1, game.getClickedPieceMoves().getNumOfPossibleMoves());

  }

  @Test
  void doesUserEndTurnTest() {
    BoardStructure boardStructure = mock(BoardStructure.class);
    Player player2 = mock(Player.class);
    List<Player> playerList = List.of(mock(Player.class),player2);
    WinCondition winCondition = mock(WinCondition.class);
    List<WinCondition> winConditions = List.of(winCondition);
    GameMoveHandler gameMoveHandler = mock(GameMoveHandler.class);
    when(boardStructure.getCols()).thenReturn(3);

    Game game = new Game(boardStructure, playerList, 0, gameMoveHandler,
        winConditions, false, true);
    assertEquals(true, game.doesUserEndTurn());
  }

}
package ooga.model;

import java.util.List;
import ooga.model.players.Player;

/**
 * Enum for the GameStatus a game can be in
 */
public enum GameStatus {

  WON,
  DRAW,
  PLAYING;

  private List<Player> playerList;

  /**
   * Sets players to be associated with the GameStatus
   *
   * @param playerList List of players to be associated with GameStatus with
   */
  public void setPlayerList(List<Player> playerList) {
    this.playerList = playerList;
  }

  /**
   * Retrieves the list of Players associated with GameStatus
   *
   * @return List of players associated with GameStatus
   */
  public List<Player> getPlayerList() {
    return playerList;
  }

}

package ooga.model.players;

import java.lang.reflect.InvocationTargetException;
import ooga.exception.ClassDoesNotExistException;
import ooga.model.GameMoveHandler;

/**
 * Factory class to create Players
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class PlayerTypeFactory {

  /**
   * Creates new Player based on specified arguments
   *
   * @param playerType Type of player
   * @param playerName Name of the player
   * @param playerNumber Number associated with player
   * @param gameMoveHandler GameMoveHandler passed to player
   * @return New created player
   */
  public Player addNewPlayer(String playerType, String playerName, int playerNumber,
      GameMoveHandler gameMoveHandler) {
    try {
      //TODO: When we change different AI's send easy AI versus hard
      if (playerName.equals(("AI"))) {
        return new RandomAI(gameMoveHandler, playerNumber);
      }
      Class currentGameTypeClass = Class.forName("ooga.model.players." + playerType + "Player");
      return (Player) currentGameTypeClass
          .getDeclaredConstructor(GameMoveHandler.class, String.class, int.class)
          .newInstance(gameMoveHandler, playerName, playerNumber);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalStateException | InvocationTargetException | ClassNotFoundException e) {
      throw new ClassDoesNotExistException(playerType, e);
    }
  }
}

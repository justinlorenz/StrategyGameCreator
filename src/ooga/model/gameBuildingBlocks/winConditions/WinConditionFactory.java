package ooga.model.gameBuildingBlocks.winConditions;

import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class for creating WinCondition building blocks
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class WinConditionFactory {

  /**
   * Generates a WinCondition that doesn't require any parameters
   *
   * @param winCondition String of the Class name of the WinCondition
   * @return Constructed WinCondition
   */
  public WinCondition getWinCondition(String winCondition) {
    try {
      return (WinCondition) Class
          .forName("ooga.model.gameBuildingBlocks.winConditions." + winCondition)
          .getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ClassDoesNotExistException(winCondition, e);
    }
  }

  /**
   * Generates a WinCondition that requires an integer parameter
   *
   * @param winCondition String of the Class name of the WinCondition
   * @return Constructed WinCondition
   */
  public WinCondition getWinCondition(String winCondition, int winningSize) {
    try {
      return (WinCondition) Class
          .forName("ooga.model.gameBuildingBlocks.winConditions." + winCondition)
          .getDeclaredConstructor(int.class).newInstance(winningSize);
    } catch (Exception e) {
      throw new ClassDoesNotExistException(winCondition, e);
    }
  }
}

package ooga.model.gameBuildingBlocks.validMoveChecks;

import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class for creating ValidMoveCheck building blocks
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class ValidMoveCheckFactory {

  /**
   * Creates a new instance of a ValidMoveCheck
   *
   * @param validMoveCheck Class name of PieceMove building block
   * @return Constructed PieceMove
   */
  public ValidMoveCheck getValidMoveCheck(String validMoveCheck) {
    try {
      return (ValidMoveCheck) Class
          .forName("ooga.model.gameBuildingBlocks.validMoveChecks." + validMoveCheck)
          .getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ClassDoesNotExistException(validMoveCheck, e);
    }
  }
}

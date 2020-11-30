package ooga.model.gameBuildingBlocks.boardMoveUpdate;

import ooga.exception.ClassDoesNotExistException;

/**
 * Factory class for creating BoardMoveUpdate building blocks
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class BoardMoveUpdateFactory {

  /**
   * Creates a new instance of a BoardMoveUpdate
   *
   * @param boardMoveUpdate Class name of boardMoveUpdate building block
   * @return Constructed BoardMoveUpdate
   */
  public BoardMoveUpdate getBoardMoveUpdate(String boardMoveUpdate) {
    try {
      return (BoardMoveUpdate) Class
          .forName("ooga.model.gameBuildingBlocks.boardMoveUpdate." + boardMoveUpdate)
          .getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new ClassDoesNotExistException(boardMoveUpdate, e);
    }
  }
}

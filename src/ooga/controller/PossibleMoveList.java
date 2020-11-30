package ooga.controller;

import java.util.ArrayList;
import java.util.List;
import ooga.model.PossibleMove;

/**
 * Class created to hold an encapsulated data structure of possible moves that can be sent
 * to the controller and then to the view for the ability to display possible moves on the board
 *
 * @author Justin Lorenz
 */
public class PossibleMoveList {

  List<PossibleMove> possibleMoves = new ArrayList<>();

  /**
   * Adds new possible moves to the current possible moves data structure that is stored in this class
   * @param possibleMoves - All possible moves that are sent in from the GameMoveHandler
   */
  public void addPossibleMoves(List<PossibleMove> possibleMoves) {
    this.possibleMoves.addAll(possibleMoves);
  }

  /**
   * Getter that returns possible moves left in the game
   * @return - Number of possible moves that are currently stored in the possible moves data structure
   */
  public int getNumOfPossibleMoves() {
    return possibleMoves.size();
  }

  /**
   * Getter to retrieve the possible moves that are currently stored in the data structure
   * @return - Current data structure that the PossibleMoveList class implements
   */
  public List<PossibleMove> getPossibleMoves() {
    return possibleMoves;
  }
}

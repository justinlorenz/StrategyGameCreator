package ooga.controller;

/*
 * Class created to hold data about specific pieces in the model,
 * so that this data could be passed to the controller and then do the view
 */
public class PieceData {

  private final String pieceId;
  private final int playerNumber;
  private final int i;
  private final int j;

  /**
   * Creates a piece data object with specific piece data being sent in
   * @param pieceId - The String id of the piece
   * @param playerNumber - The player number associated with the current piece
   * @param i - The i coordinate of the current piece
   * @param j - The j coordinate of the current piece
   */
  public PieceData(String pieceId, int playerNumber, int i, int j) {
    this.pieceId = pieceId; //pieceId == empty if not wanted to be highlighted
    this.playerNumber = playerNumber;
    this.i = i;
    this.j = j;
  }

  /**
   * Getter to access the pieceId
   * @return The piece String title
   */
  public String getPieceId() {
    return pieceId;
  }

  /**
   * Getter to retrieve the piece player number
   * @return The piece player number
   */
  public int getPlayerNumber() {
    return playerNumber;
  }


  /**
   * Getter to retrieve the piece i coordinate
   * @return The i coordinate
   */
  public int getI() {
    return i;
  }

  /**
   * Getter to retrieve the piece j coordinate
   * @return The j coordinate
   */
  public int getJ() {
    return j;
  }
}

package ooga.model;

import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.Piece;

/**
 * Encapsulated class which contains the board content
 */
public class BoardStructure {

  private final Piece[][] currBoardStructure;
  private final int rows;
  private final int cols;

  /**
   * Constructor for BoardStructure
   *
   * @param stateArray Encapsulated data of Pieces
   */
  public BoardStructure(PieceBoardStructure stateArray) {
    rows = stateArray.getGridSize();
    cols = stateArray.getGridSize();
    currBoardStructure = new Piece[rows][cols];
    initializeBoard(stateArray);
  }

  /**
   * Retrieves number of rows of board
   *
   * @return number of rows of current board
   */
  public int getRows() {
    return rows;
  }

  /**
   * Retrieves number of columns of board
   *
   * @return number of columns of current board
   */
  public int getCols() {
    return cols;
  }


  private void initializeBoard(PieceBoardStructure stateArray) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        currBoardStructure[i][j] = stateArray.getPiece(i, j);
      }
    }
  }

  /**
   * Gets piece type at given i and j coordinate of board
   *
   * @param i - row number
   * @param j -  column number
   * @return cell in place i,j of current board structure
   */
  public Piece getPieceType(int i, int j) {
    return currBoardStructure[i][j];
  }


  /**
   * Gets pieceId at given i and j coordinate of board
   *
   * @param i i coordinate to retrieve PieceID
   * @param j j coordinate to retrieve PieceID
   * @return pieceId at specified coordinates
   */
  protected String getPieceId(int i, int j) {
    return currBoardStructure[i][j].getPieceId();
  }

  /**
   * Gets playerNumber at given i and j coordinate of board
   *
   * @param i i coordinate to retrieve PieceID
   * @param j j coordinate to retrieve PieceID
   * @return associated playerNumber for piece at specified coordinates
   */
  protected int getPlayerNumber(int i, int j) {
    return currBoardStructure[i][j].getPlayerNumber();
  }

  /**
   * Sets board at specified coordinates to empty
   *
   * @param i I coordinate
   * @param j J coordinate
   */
  public void setEmptyCell(int i, int j) {
    currBoardStructure[i][j] = new EmptyPiece(0, 0);
  }

  /**
   * Sets board at specified coordinates to specified Piece
   *
   * @param i I coordinate
   * @param j J coordinate
   * @param newPiece Piece to set at coordinates
   */
  public void setCellState(int i, int j, Piece newPiece) {
    currBoardStructure[i][j] = newPiece;
  }

  /**
   * Sets board at piece coordinates to equal that piece
   *
   * @param piece Piece to be put set into board
   */
  public void setPieceType(Piece piece) {
    currBoardStructure[piece.getI()][piece.getJ()] = piece;
  }

}

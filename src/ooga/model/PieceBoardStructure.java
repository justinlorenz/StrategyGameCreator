package ooga.model;

import ooga.model.pieces.Piece;

/**
 * Encapsulated class which contains PieceBoard Data for ``
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class PieceBoardStructure {

  private final Piece[][] pieceGrid;

  /**
   * Constructor for PieceBoardStructure
   *
   * @param size Size of the PieceData
   */
  public PieceBoardStructure(int size) {
    pieceGrid = new Piece[size][size];
  }

  /**
   * Adds new Piece at specified coordinates
   *
   * @param piece Piece that is added
   * @param i I coordinate to add Piece into
   * @param j J coordinate to add Piece into
   */
  public void addNewPiece(Piece piece, int i, int j) {
    pieceGrid[i][j] = piece;
  }

  /**
   * Gets piece at coordinates
   *
   * @param pieceI I coordinate to retrieve piece for
   * @param pieceJ J coordinate to retrieve piece for
   * @return The piece at specified coordinates
   */
  public Piece getPiece(int pieceI, int pieceJ) {
    return pieceGrid[pieceI][pieceJ];
  }

  /**
   * Gets size of the grid
   *
   * @return Size of the grid
   */
  public int getGridSize() {
    return pieceGrid.length;
  }
}

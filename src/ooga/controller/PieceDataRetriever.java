package ooga.controller;

/**
 * Interface created to be implemented by the controller so that the controller can be passed
 * to data with only the getPieceData method being available
 *
 */
public interface PieceDataRetriever {

  /**
   * Getter to access the PieceData of a requested piece
   * @param i - i coordinate of the requested piece
   * @param j - j coordinate of the requested piece
   * @return - PieceData object holding all current data about requested piece
   */
  PieceData getPieceData(int i, int j);

}

package ooga.model.players;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ooga.model.BoardStructure;
import ooga.model.GameMoveHandler;
import ooga.model.PossibleMove;
import ooga.model.pieces.Piece;

/**
 * Class for RandomAI player
 *
 * @author Jerry Fang
 */
public class RandomAI implements Player {

  private final GameMoveHandler gameMoveHandler;
  private final int playerNumber;
  private Piece clickedPiece;

  /**
   * Constructor for RandomAI
   *
   * @param gameMoveHandler gameMoveHandler which handles moves
   * @param playerNumber associated number for AI player
   */
  public RandomAI(GameMoveHandler gameMoveHandler, int playerNumber) {
    this.gameMoveHandler = gameMoveHandler;
    this.playerNumber = playerNumber;
  }

  /**
   * Makes move by only specifying destination
   *
   * @param destinationI Destination I coordinate for move on piece
   * @param destinationJ Destination J coordinate for move on piece
   * @param currBoard Board structure that move will take place on
   * @return True if move occurred, false if move did not happen.
   */
  @Override
  public boolean makeMove(int destinationI, int destinationJ, BoardStructure currBoard) {
    List<PossibleMove> myPossibleMoves = gameMoveHandler
        .getPossibleMoves(currBoard, GameMoveHandler.NON_USED_PARAMETER,
            GameMoveHandler.NON_USED_PARAMETER, playerNumber);
    Random rand = new Random();
    PossibleMove myMove = myPossibleMoves.get(rand.nextInt(myPossibleMoves.size()));
    return gameMoveHandler.makeGameMove(myMove.getI(), myMove.getJ(), playerNumber);
  }

  /**
   * Makes move by specifies piece location and destination location
   *
   * @param pieceI Piece I coordinate
   * @param pieceJ Piece J coordinate
   * @param destinationI Destination I coordinate for move on piece
   * @param destinationJ Destination J coordinate for move on piece
   * @param currBoard Board structure that move will take place on
   * @return True if move occurred, false if move did not happen.
   */
  //currently restricted to only making one move (no special moves)
  @Override
  public boolean makeMove(int pieceI, int pieceJ, int destinationI, int destinationJ,
      BoardStructure currBoard) {
    Piece myPiece = updateClickedPiece(currBoard);
    List<PossibleMove> myPossibleMoves = gameMoveHandler
        .getPossibleMoves(currBoard, myPiece.getI(), myPiece.getJ(), playerNumber);
    Random rand = new Random();
    PossibleMove myMove = myPossibleMoves.get(rand.nextInt(myPossibleMoves.size()));
    return gameMoveHandler
        .makeGameMove(myPiece.getI(), myPiece.getJ(), myMove.getI(), myMove.getJ(), playerNumber);
  }

  /**
   * Method to check if player is human
   *
   * @return Boolean if player is human
   */
  @Override
  public boolean isHuman() {
    return false;
  }

  /**
   * Retrieves name of the player
   *
   * @return Player name
   */
  @Override
  public String getPlayerName() {
    return "AI";
  }

  /**
   * Retrieves the associated number to player
   *
   * @return Player's associated number
   */
  @Override
  public int getPlayerNumber() {
    return playerNumber;
  }

  private Piece updateClickedPiece(BoardStructure currBoard) {
    List<Piece> myPieces = new ArrayList<>();
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (currBoard.getPieceType(i, j).getPlayerNumber() == this.playerNumber) {
          List<PossibleMove> myPossibleMoves = gameMoveHandler
              .getPossibleMoves(currBoard, currBoard.getPieceType(i, j).getI(),
                  currBoard.getPieceType(i, j).getJ(), playerNumber);
          if (myPossibleMoves.size() > 0) {
            myPieces.add(currBoard.getPieceType(i, j));
          }
        }
      }
    }
    Random randomPieceGenerator = new Random();
    Piece myPiece = myPieces.get(randomPieceGenerator.nextInt(myPieces.size()));
    return myPiece;
  }
}

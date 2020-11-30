package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.model.gameBuildingBlocks.boardMoveUpdate.BoardMoveUpdate;
import ooga.model.gameBuildingBlocks.pieceMove.PieceMove;
import ooga.model.players.Player;

/**
 * Class which handles GameMoves
 */
public class GameMoveHandler {

  public static final int NON_USED_PARAMETER = 0;
  private final BoardStructure currBoard;
  private final List<BoardMoveUpdate> boardMoveUpdates;
  private final List<PieceMove> normalPieceMoves;
  private final SpecialPieceHandler specialPieceHandler;
  private final boolean piecesAreMoved;

  /**
   * Constructor for GameMoveHandler
   *
   * @param currBoard BoardStructure where moves are being played on
   * @param boardMoveUpdates List of BoardMoveUpdates that are being played
   * @param normalPieceMoves List of PieceMoves for normal pieces
   * @param specialPieceHandler List of PieceMoves for special pieces
   * @param piecesAreMoved Boolean for if game has pieces moved
   */
  public GameMoveHandler(BoardStructure currBoard, List<BoardMoveUpdate> boardMoveUpdates,
      List<PieceMove> normalPieceMoves,
      SpecialPieceHandler specialPieceHandler, boolean piecesAreMoved) {
    this.currBoard = currBoard;
    this.boardMoveUpdates = boardMoveUpdates;
    this.normalPieceMoves = normalPieceMoves;
    this.specialPieceHandler = specialPieceHandler;
    this.piecesAreMoved = piecesAreMoved;
  }

  /**
   * Retrieves if game has pieces moved
   *
   * @return Boolean if game has pieces moved
   */
  public boolean arePiecesMoved() {
    return piecesAreMoved;
  }

  /**
   * Makes move with only destination specified
   *
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   * @param playerNumber Player number that is making the move
   * @return Returns true if move is able to be done
   */
  public boolean makeGameMove(int destinationI, int destinationJ, int playerNumber) {
    List<PossibleMove> possibleMoves = getPossibleMoves(currBoard, NON_USED_PARAMETER,
        NON_USED_PARAMETER, playerNumber);
    if (possibleMoves.contains(new PossibleMove(destinationI, destinationJ))) {
      updateTheBoardAfterMove(NON_USED_PARAMETER, NON_USED_PARAMETER, destinationI, destinationJ,
          playerNumber);
      specialPieceHandler.promotePieceIfNeeded(currBoard, destinationI, destinationJ);
      return true;
    }
    return false;
  }

  /**
   * Makes move with only destination specified
   *
   * @param pieceI I coordinate of piece
   * @param pieceJ J coordinate of piece
   * @param destinationI I coordinate of destination
   * @param destinationJ J coordinate of destination
   * @param playerNumber Player number that is making the move
   * @return Returns true if move is able to be done
   */
  public boolean makeGameMove(int pieceI, int pieceJ, int destinationI, int destinationJ,
      int playerNumber) {
    List<PossibleMove> possibleMoves = getPossibleMoves(currBoard, pieceI, pieceJ, playerNumber);
    if (possibleMoves.contains(new PossibleMove(destinationI, destinationJ))) {
      updateTheBoardAfterMove(pieceI, pieceJ, destinationI, destinationJ, playerNumber);
      specialPieceHandler.promotePieceIfNeeded(currBoard, destinationI, destinationJ);
      return true;
    }
    return false;
  }

  private void updateTheBoardAfterMove(int pieceI, int pieceJ, int destinationI, int destinationJ,
      int playerNumber) {
    for (BoardMoveUpdate boardMoveUpdate : boardMoveUpdates) {
      boardMoveUpdate
          .updateBoard(currBoard, playerNumber, pieceI, pieceJ, destinationI, destinationJ);
    }
  }

  /**
   * Gets all the possible moves given Piece coordinates
   *
   * @param currBoard BoardStructure to retrieve possible moves from
   * @param pieceI I coordinate of piece
   * @param pieceJ J coordinate of piece
   * @param playerNum PlayerNumber to retrieve possible moves for
   * @return List of all possible moves
   */
  public List<PossibleMove> getPossibleMoves(BoardStructure currBoard, int pieceI, int pieceJ,
      int playerNum) {
    List<PossibleMove> validPieceMoves = new ArrayList<>();
    if (currBoard.getPieceType(pieceI, pieceJ).isSpecial()) {
      validPieceMoves
          .addAll(specialPieceHandler.getSpecialPieceMoves(currBoard, pieceI, pieceJ, playerNum));
    }
    for (PieceMove pieceMove : normalPieceMoves) {
      validPieceMoves.addAll(pieceMove.getValidMoves(currBoard, pieceI, pieceJ, playerNum));
    }
    return validPieceMoves;
  }

  /**
   * @param currPlayer Player to see if validPieceMove exists for
   * @return True if validPieceMove exists, false if none exist
   */
  public boolean validPieceMoveExists(Player currPlayer) {
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        if (!currBoard.getPieceType(i, j).isEmpty()
            && getPossibleMoves(currBoard, i, j, currPlayer.getPlayerNumber()).size() > 0) {
          return true;
        }
      }
    }
    return false;
  }
}

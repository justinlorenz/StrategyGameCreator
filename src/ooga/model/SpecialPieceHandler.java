package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.model.gameBuildingBlocks.pieceMove.PieceMove;
import ooga.model.gameBuildingBlocks.promotionCondition.PromotionCondition;

/**
 * Class that handles Special Pieces
 */
public class SpecialPieceHandler {

  private final List<PieceMove> specialPieceMoves;
  private final List<PromotionCondition> promotionConditions;
  private final boolean hasSpecialPiece;

  /**
   * SpecialPieceHandler constructor
   *
   * @param hasSpecialPiece Boolean determining if game has special piece
   * @param specialPieceMoves List of PieceMoves for Special Piece
   * @param promotionConditions List of PromotionConditions for piece to become special piece
   */
  public SpecialPieceHandler(boolean hasSpecialPiece, List<PieceMove> specialPieceMoves,
      List<PromotionCondition> promotionConditions) {
    this.hasSpecialPiece = hasSpecialPiece;
    this.specialPieceMoves = specialPieceMoves;
    this.promotionConditions = promotionConditions;
  }

  /**
   * Promotes the piece if it promotion condition is satisfied
   *
   * @param currBoard BoardStructure to check if promotion is satisfied on
   * @param pieceI I coordinate to check for piece promotion
   * @param pieceJ J coordinate to check for piece promotion
   */
  public void promotePieceIfNeeded(BoardStructure currBoard, int pieceI, int pieceJ) {
    if (hasSpecialPiece) {
      for (PromotionCondition promotionCondition : promotionConditions) {
        promotionCondition.piecePromotionUpdate(currBoard, pieceI, pieceJ);
      }
    }
  }

  /**
   * Retrieves the pieceMoves for special piece
   *
   * @param currBoard BoardStructure to retrieve moves of special piece for
   * @param pieceI I coordinate of special piece
   * @param pieceJ J coordinate of special piece
   * @param playerNum Player number associated with special piece
   * @return List of all special moves at given special piece
   */
  public List<PossibleMove> getSpecialPieceMoves(BoardStructure currBoard, int pieceI, int pieceJ,
      int playerNum) {
    List<PossibleMove> specialMoves = new ArrayList<>();
    for (PieceMove pieceMove : specialPieceMoves) {
      specialMoves.addAll(pieceMove.getValidMoves(currBoard, pieceI, pieceJ, playerNum));
    }
    return specialMoves;
  }

}

package ooga.view.guiComponentFactory;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Factory used to create an entry to place on the leaderboard
 *
 * @author JasonDong
 */
public class GUILeaderboardEntryFactory {

  private static final int HBOX_SPACING = 50;
  private static final int PROFILE_WIDTH = 30;
  private static final int PROFILE_HEIGHT = 30;

  /**
   * Method to create the leaderboard entry, complete with the rank, the users name, and their
   * score
   *
   * @param leaderboardRank, the ranking of the person on the leaderboard
   * @param name,            the name of the person
   * @param score,           the score of the person
   * @return Hbox, the HBOx of the leaderboard entry, complete with the rank, the users name, and
   * their score
   */
  public HBox makeLeaderboardEntry(int leaderboardRank, String name, int score) {

    HBox hBox = new HBox(HBOX_SPACING);
    Rectangle rankRect = new Rectangle();
    String formattedURL = String.format("leaderboard/number%d.png", leaderboardRank);
    rankRect.setFill(new ImagePattern(new Image(formattedURL)));
    rankRect.setWidth(PROFILE_WIDTH);
    rankRect.setHeight(PROFILE_HEIGHT);

    String fullText = String.format("%s - Score: %d", name, score);
    Text leaderText = new Text(fullText);
    leaderText.getStyleClass().add("LeaderboardText");

    hBox.getChildren().addAll(rankRect, leaderText);

    return hBox;
  }

}

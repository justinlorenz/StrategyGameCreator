package ooga.view.dialogBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class GameInfoDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private GameInfoDialogBox gameInfoDialogBox;
  private Button closeButton;
  private Text titleText;
  private Text descriptionText;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameInfoDialogBox = new GameInfoDialogBox(myResources);
    myScene = gameInfoDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    closeButton = lookup("#CloseButton").query();
    titleText = lookup("#Tic-Tac-Toe").query();
    descriptionText = lookup("#TicTacToeDescription").query();
  }

  @Test
  void testInitialConfiguration() {
    assertFalse(closeButton.isDisabled());
    assertEquals("Tic-Tac-Toe", titleText.getText());
    assertEquals("2 players take turns marking the\n"
        + " spaces in a grid. The player who\n"
        + " succeeds in placing three\n"
        + "(or a custom number) of their\n"
        + " marks in a horizontal, vertical\n"
        + ", or diagonal row is the winner.\n", descriptionText.getText());
    sleep(1, TimeUnit.SECONDS);
  }

}
package ooga.view.dialogBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.GameStatus;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class LeaderboardDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private LeaderboardDialogBox leaderboardDialogBox;
  private Button closeButton;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(400);
    myStage.setHeight(700);
    myResources = ResourceBundle.getBundle("ENGLISH");
    leaderboardDialogBox = new LeaderboardDialogBox(myResources);
    myScene = leaderboardDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    closeButton = lookup("#CloseButton").query();
    leaderboardDialogBox.initializeStage();
  }

  @Test
  void testInitialConfig() {
    sleep(1, TimeUnit.SECONDS);
    assertFalse(closeButton.isDisabled());
    clickOn(closeButton);
  }

}
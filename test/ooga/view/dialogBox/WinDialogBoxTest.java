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

class WinDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private WinDialogBox winDialogBox;
  private Button closeButton;
  private Text winText;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(400);
    myStage.setHeight(400);
    myResources = ResourceBundle.getBundle("ENGLISH");
    winDialogBox = new WinDialogBox(myResources, "Jason", GameStatus.WON);
    myScene = winDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    closeButton = lookup("#CloseButton").query();
    winText = lookup("#WinLoseTieText").query();
    winDialogBox.initializeStage();
  }

  @Test
  void testInitialConfiguration() {
    sleep(1, TimeUnit.SECONDS);
    assertFalse(closeButton.isDisabled());
    clickOn(closeButton);
  }

  @Test
  void testMessage() {
    assertEquals(winText.getText(), "Jason is the WINNER!");
  }

}
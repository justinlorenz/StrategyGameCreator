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

class ExceptionDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private ExceptionDialogBox exceptionDialogBox;
  private Button closeButton;
  private Text exceptionText;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(400);
    myStage.setHeight(400);
    myResources = ResourceBundle.getBundle("ENGLISH");
    exceptionDialogBox = new ExceptionDialogBox(myResources, "BadGameFileException", "Missing Property");
    myScene = exceptionDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    closeButton = lookup("#CloseButton").query();
    exceptionText = lookup("#ExceptionText").query();
    exceptionDialogBox.initializeStage();
  }

  @Test
  void testInitialConfig() {
    assertFalse(closeButton.isDisabled());
    sleep(1, TimeUnit.SECONDS);
    assertEquals("The loaded game file is broken\n"
        + "Missing Property", exceptionText.getText());
    clickOn(closeButton);
  }
}
package ooga.view.dialogBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.GameStatus;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ChatProfileDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private ChatProfileDialogBox chatProfileDialogBox;
  private Button joinButton;
  private TextField nameText;
  private Rectangle profilePic;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    chatProfileDialogBox = new ChatProfileDialogBox(myResources);
    myScene = chatProfileDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    joinButton = lookup("#JoinButton").query();
    nameText = lookup("#ChatRoomName").query();
    profilePic = lookup("#Lucas").query();
    chatProfileDialogBox.initializeStage();
  }

  @Test
  void testInitialConfiguration() {
    assertTrue(joinButton.isDisabled());
    assertEquals("", nameText.getText());
  }

  @Test
  void testProfilePicking() {
    assertTrue(joinButton.isDisabled());
    nameText.setText("Jason");
    clickOn(profilePic);
    sleep(1, TimeUnit.SECONDS);
    clickOn(joinButton);
  }
}
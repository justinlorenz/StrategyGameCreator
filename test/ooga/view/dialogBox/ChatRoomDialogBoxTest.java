package ooga.view.dialogBox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ChatRoomDialogBoxTest extends DukeApplicationTest {
  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private ChatRoomDialogBox chatRoomDialogBox;
  private Button sendbutton;
  private TextField sendMessage;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(400);
    myStage.setHeight(400);
    myResources = ResourceBundle.getBundle("ENGLISH");
    chatRoomDialogBox = new ChatRoomDialogBox(myResources, "Lucas", "Jason");
    myScene = chatRoomDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();
    chatRoomDialogBox.initializeStage();

    sendbutton = lookup("#SendButton").query();
    sendMessage = lookup("#EnterSendMessage").query();
  }

  @Test
  void testInitialConfig() {
    sleep(1, TimeUnit.SECONDS);
    assertFalse(sendbutton.isDisabled());
    assertFalse(sendMessage.isDisabled());
  }

  @Test
  void testSendMessage() {
    sendMessage.setText("Hey Everyone");
    //clickOn(sendbutton);
    sleep(1, TimeUnit.SECONDS);
  }

}
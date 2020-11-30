package ooga.view.dialogBox;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.controller.GameController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerChooserDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private PlayerChooserDialogBox playerChooserDialogBox;
  private GameController gameController;
  private Button singlePlayerButton;
  private Button multiPlayerButton;
  private Button startGameButton;
  private Button customProfileButton;
  private TextField player1Name;
  private TextField player2Name;
  private Rectangle profile1;
  private Rectangle profile2;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameController = new GameController();
    playerChooserDialogBox = new PlayerChooserDialogBox(myResources, gameController);
    myScene = playerChooserDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    singlePlayerButton = lookup("#SinglePlayerButton").query();
    multiPlayerButton = lookup("#MultiPlayerButton").query();
    startGameButton = lookup("#StartGameButton").query();

  }

  @Test
  void testInitialConfiguration(){
    sleep(1, TimeUnit.SECONDS);
    assertFalse(singlePlayerButton.isDisabled());
    assertFalse(multiPlayerButton.isDisabled());
    assertTrue(startGameButton.isDisabled());
  }


  @Test
  void testSinglePlayer(){
    clickOn(singlePlayerButton);
    player1Name = lookup("#Player1Name").query();
    player2Name = lookup("#Player2Name").query();
    player1Name.setText("Jason");
    sleep(1, TimeUnit.SECONDS);
    assertTrue(startGameButton.isDisabled());
  }

  @Test
  void testMultiPlayer(){
    clickOn(multiPlayerButton);
    player1Name = lookup("#Player1Name").query();
    player2Name = lookup("#Player2Name").query();
    player1Name.setText("Justin");
    player2Name.setText("Loten");
    sleep(1, TimeUnit.SECONDS);
    assertTrue(startGameButton.isDisabled());
  }

  @Test
  void testClickingOnProfile(){
    clickOn(multiPlayerButton);
    profile1 = lookup("#AmongUsRed").query();
    profile2 = lookup("#MetaKnight").query();
    clickOn(profile1);
    clickOn(profile2);
    sleep(1, TimeUnit.SECONDS);
    assertFalse(startGameButton.isDisabled());
    clickOn(startGameButton);
  }

  @Test
  void testCustomProfile() {
    clickOn(multiPlayerButton);
    customProfileButton = lookup("#LoadCustomProfile").query();
    clickOn(customProfileButton);
  }
}

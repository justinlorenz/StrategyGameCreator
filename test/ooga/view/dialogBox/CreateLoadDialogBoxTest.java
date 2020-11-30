package ooga.view.dialogBox;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ooga.view.dialogBox.CreateLoadDialogBox;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class CreateLoadDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private CreateLoadDialogBox createLoadDialogBox;
  private Button createButton;
  private Button loadButton;
  private Button chatButton;
  private Button leaderboardButton;
  private ComboBox<String> languageComboBox;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    createLoadDialogBox = new CreateLoadDialogBox(myResources);
    myScene = createLoadDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    createButton = lookup("#CreateButton").query();
    loadButton = lookup("#LoadButton").query();
    chatButton = lookup("#ChatRoomButton").query();
    leaderboardButton = lookup("#LeaderboardButton").query();
    languageComboBox = lookup("#LanguageComboBox").query();
  }

  @Test
  void testInitialConfiguration(){
    sleep(1, TimeUnit.SECONDS);
    assertTrue(createButton.isDisabled());
    assertTrue(loadButton.isDisabled());
    assertTrue(chatButton.isDisabled());
    assertTrue(leaderboardButton.isDisabled());
  }

  @Test
  void testComboBoxSizeAndValues(){
    final String language1 = "ENGLISH";
    final String language2 = "SPANISH";
    final String language3 = "PIGLATIN";
    List<String> languageComboBoxItems = languageComboBox.getItems();
    assertEquals(3, languageComboBoxItems.size());
    assertEquals(language1, languageComboBoxItems.get(0));
    assertEquals(language2, languageComboBoxItems.get(1));
    assertEquals(language3, languageComboBoxItems.get(2));
  }

  @Test
  void testButtonsEnabled(){
    sleep(1, TimeUnit.SECONDS);
    final String language1 = "ENGLISH";
    select(languageComboBox, language1);
    sleep(1, TimeUnit.SECONDS);
    assertFalse(createButton.isDisabled());
    assertFalse(loadButton.isDisabled());
    assertFalse(chatButton.isDisabled());
    assertFalse(leaderboardButton.isDisabled());
  }

  @Test
  void clickCreateButton(){
    sleep(1, TimeUnit.SECONDS);
    final String language1 = "ENGLISH";
    select(languageComboBox, language1);
    clickOn(createButton);
    sleep(1, TimeUnit.SECONDS);
  }


  @Test
  void clickLoadButton(){
    sleep(1, TimeUnit.SECONDS);
    final String language1 = "ENGLISH";
    select(languageComboBox, language1);
    clickOn(loadButton);
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void clickChatButton() {
    sleep(1, TimeUnit.SECONDS);
    final String language1 = "English";
    select(languageComboBox, language1);
    clickOn(chatButton);
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void clickLeaderboardButton() {
    sleep(1, TimeUnit.SECONDS);
    final String language1 = "English";
    select(languageComboBox, language1);
    clickOn(leaderboardButton);
    sleep(1, TimeUnit.SECONDS);
  }

}

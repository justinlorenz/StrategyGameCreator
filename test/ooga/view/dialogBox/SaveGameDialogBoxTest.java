package ooga.view.dialogBox;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.controller.GameController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class SaveGameDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private SaveGameDialogBox saveGameDialogBox;
  private GameController gameController;
  private Button saveAndCloseButton;
  private TextField fileNameTextField;
  private TextField authorNameTextField;
  private TextField descriptionTextField;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameController = new GameController();
    saveGameDialogBox = new SaveGameDialogBox(myResources, gameController);
    myScene = saveGameDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();
    saveGameDialogBox.initializeStage();

    saveAndCloseButton = lookup("#SaveAndCloseButton").query();
    fileNameTextField = lookup("#GameNameTextField").query();
    authorNameTextField = lookup("#AuthorTextField").query();
    descriptionTextField = lookup("#DescriptionTextField").query();
  }

  @Test
  void testInitialConfiguration(){
    sleep(1, TimeUnit.SECONDS);
    assertFalse(saveAndCloseButton.isDisabled());
    assertFalse(fileNameTextField.isDisabled());
    assertFalse(authorNameTextField.isDisabled());
    assertFalse(descriptionTextField.isDisabled());
  }

  @Test
  void testTypingIntoTextField(){
    fileNameTextField.setText("TICTAC");
    authorNameTextField.setText("JuJaJeLo");
    descriptionTextField.setText("Playing TicTacToe with AI!");
    sleep(1, TimeUnit.SECONDS);

    //clickOn(saveAndCloseButton);

  }

}

package ooga.view.dialogBox;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.view.dialogBox.ChooseGameDialogBox;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ChooseGameDialogBoxTest extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private ChooseGameDialogBox chooseGameDialogBox;
  private GameController gameController;
  private Button continueButton;
  private ComboBox<String> gameComboBox;
  private ComboBox<String> gridSizeComboBox;
  private ComboBox<String> themeComboBox;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameController = new GameController();
    chooseGameDialogBox = new ChooseGameDialogBox(myResources, gameController);
    myScene = chooseGameDialogBox.setUpScene(myResources);
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    continueButton = lookup("#ContinueButton").query();
    gameComboBox = lookup("#GameChooserComboBox").query();
    gridSizeComboBox = lookup("#GridSizeComboBox").query();
    themeComboBox = lookup("#ThemeChooserComboBox").query();
  }

  @Test
  void testInitialConfiguration(){
    sleep(1, TimeUnit.SECONDS);
    assertFalse(gameComboBox.isDisabled());
    assertTrue(continueButton.isDisabled());
    assertTrue(themeComboBox.isDisabled());
    assertTrue(gridSizeComboBox.isDisabled());
  }

  @Test
  void testComboBoxElements(){
    final String game1 = "TicTacToe";
    final String game2 = "Checkers";
    final String game3 = "Othello";
    final String game4 = "ConnectFour";

    List<String> gameComboBoxItems = gameComboBox.getItems();
    assertEquals(game1, gameComboBoxItems.get(0));
    assertEquals(game2, gameComboBoxItems.get(1));
    assertEquals(game3, gameComboBoxItems.get(2));
    assertEquals(game4, gameComboBoxItems.get(3));
    assertEquals(5, gameComboBoxItems.size());

    select(gameComboBox, game1);
    final String grid1 = "3x3";
    final String grid2 = "5x5";
    final String grid3 = "8x8";
    final String grid4 = "10x10";


    List<String> gridSizeComboBoxItems = gridSizeComboBox.getItems();
    assertEquals(grid1, gridSizeComboBoxItems.get(0));
    assertEquals(grid2, gridSizeComboBoxItems.get(1));
    assertEquals(grid3, gridSizeComboBoxItems.get(2));
    assertEquals(grid4, gridSizeComboBoxItems.get(3));

    final String theme1 = "Duke";
    final String theme2 = "UNC";
    final String theme3 = "Christmas";

    List<String> themeComboBoxItems = themeComboBox.getItems();
    assertEquals(theme1, themeComboBoxItems.get(0));
    assertEquals(theme2, themeComboBoxItems.get(1));
    assertEquals(theme3, themeComboBoxItems.get(2));
  }

  @Test
  void testEnablingElementsAfterClickingComboBoxes(){
    final String game1 = "TicTacToe";
    final String grid1 = "3x3";
    final String theme1 = "Duke";

    select(gameComboBox, game1);
    //assertFalse(gridSizeComboBox.isDisabled());
    assertTrue(themeComboBox.isDisabled());

    select(gridSizeComboBox, grid1);
    assertFalse(gameComboBox.isDisabled());
    assertFalse(themeComboBox.isDisabled());

    select(themeComboBox, theme1);
    assertFalse(gridSizeComboBox.isDisabled());
    assertFalse(gameComboBox.isDisabled());

    clickOn(continueButton);
  }
}

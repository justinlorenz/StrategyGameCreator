package ooga.view.mainGUI;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.model.pieces.Piece;
import ooga.view.mainGUI.MainGUI;
import ooga.view.piece.NonMovableGUIPiece;
import ooga.view.tile.MonochromeTile;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class MainGUITestIntegrationTicTacToe extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private GameController gameController;
  private MainGUI mainGUI;
  private Button saveButton;
  private Button resetButton;
  private Button backButton;
  private Button chatRoomButton;
  private Group pieceGroup;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameController = new GameController();
    gameController.setCurrentGame("TicTacToe");
    gameController.setCurrentTheme("Duke");
    gameController.addPlayerName("Jason1");
    gameController.addPlayerName("Jason2");
    gameController.setCurrentGridSize(3);
    gameController.addProfilePic("AmongUsRed");
    gameController.addProfilePic("AmongUsGray");
    gameController.setSinglePlayer(false);
    gameController.createNewGame();

    mainGUI = new MainGUI(gameController, myResources);

    myScene = mainGUI.setUpScene();
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();
    mainGUI.initializeStage();

    backButton = lookup("#BackButton").query();
    resetButton = lookup("#ResetButton").query();
    saveButton = lookup("#SaveButton").query();
    chatRoomButton = lookup("#ChatRoomButton").query();

  }

  @Test
  void testButtons(){
    sleep(1, TimeUnit.SECONDS);
    assertFalse(backButton.isDisabled());
    assertFalse(resetButton.isDisabled());
    assertFalse(saveButton.isDisabled());
    assertFalse(chatRoomButton.isDisabled());
  }

  @Test
  void testTileClick(){
    MonochromeTile monochromeTile00, monochromeTile01, monochromeTile02;
    MonochromeTile monochromeTile10, monochromeTile11, monochromeTile12;
    MonochromeTile monochromeTile20, monochromeTile21, monochromeTile22;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    monochromeTile02 = lookup("#Monochrome02").query();
    monochromeTile10 = lookup("#Monochrome10").query();
    monochromeTile11 = lookup("#Monochrome11").query();
    monochromeTile12 = lookup("#Monochrome12").query();
    monochromeTile20 = lookup("#Monochrome20").query();

    sleep(1, TimeUnit.SECONDS);
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    clickOn(monochromeTile02);
    clickOn(monochromeTile10);
    clickOn(monochromeTile11);
    clickOn(monochromeTile12);
    clickOn(monochromeTile20);

    pieceGroup = lookup("#PieceGroup").query();

    NonMovableGUIPiece piece01 = lookup("#Piece01").query();
    NonMovableGUIPiece piece02 = lookup("#Piece02").query();
    NonMovableGUIPiece piece10 = lookup("#Piece10").query();
    NonMovableGUIPiece piece11 = lookup("#Piece11").query();
    NonMovableGUIPiece piece12 = lookup("#Piece12").query();
    Text winText = lookup("#WinLoseTieText").query();
    assertEquals("Jason1 is the WINNER!", winText.getText());
    sleep(1, TimeUnit.SECONDS);

  }

  @Test
  void testResetButton() {
    MonochromeTile monochromeTile00, monochromeTile01, monochromeTile02;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    monochromeTile02 = lookup("#Monochrome02").query();

    sleep(1, TimeUnit.SECONDS);
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    clickOn(monochromeTile02);
    NonMovableGUIPiece piece00 = lookup("#Piece00").query();
    pieceGroup = lookup("#PieceGroup").query();
    clickOn(resetButton);
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    clickOn(monochromeTile02);
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void testBackButton() {
    MonochromeTile monochromeTile00, monochromeTile01, monochromeTile02;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    monochromeTile02 = lookup("#Monochrome02").query();

    sleep(1, TimeUnit.SECONDS);
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    clickOn(monochromeTile02);
    clickOn(backButton);
    sleep(1, TimeUnit.SECONDS);
    Button loadButton = lookup("#LoadButton").query();
    assertTrue(loadButton.isDisabled());
  }

  @Test
  void testSaveButton() {
    MonochromeTile monochromeTile00, monochromeTile01, monochromeTile02;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    monochromeTile02 = lookup("#Monochrome02").query();

    sleep(1, TimeUnit.SECONDS);
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    clickOn(monochromeTile02);
    clickOn(saveButton);
    sleep(1, TimeUnit.SECONDS);

    Button saveAndCloseButton = lookup("#SaveAndCloseButton").query();
    TextField fileNameTextField = lookup("#GameNameTextField").query();
    TextField authorNameTextField = lookup("#AuthorTextField").query();
    TextField descriptionTextField = lookup("#DescriptionTextField").query();

    fileNameTextField.setText("TICTAC");
    authorNameTextField.setText("JuJaJeLo");
    descriptionTextField.setText("Playing TicTacToe with AI!");
    assertFalse(saveAndCloseButton.isDisabled());

  }

  @Test
  void testChatroomButton() {
    sleep(1, TimeUnit.SECONDS);
    clickOn(chatRoomButton);
    sleep(1, TimeUnit.SECONDS);
    Button joinButton = lookup("#JoinButton").query();
    Rectangle rectangle = lookup("#Lucas").query();
    clickOn(rectangle);
    assertFalse(joinButton.isDisabled());
    clickOn(joinButton);
  }

  @Test
  void testTicTacToeDraw() {
    MonochromeTile monochromeTile00, monochromeTile01, monochromeTile02;
    MonochromeTile monochromeTile10, monochromeTile11, monochromeTile12;
    MonochromeTile monochromeTile20, monochromeTile21, monochromeTile22;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    monochromeTile02 = lookup("#Monochrome02").query();
    monochromeTile10 = lookup("#Monochrome10").query();
    monochromeTile11 = lookup("#Monochrome11").query();
    monochromeTile12 = lookup("#Monochrome12").query();
    monochromeTile20 = lookup("#Monochrome20").query();
    monochromeTile21 = lookup("#Monochrome21").query();
    monochromeTile22 = lookup("#Monochrome22").query();

    sleep(1, TimeUnit.SECONDS);
    clickOn(monochromeTile11);
    clickOn(monochromeTile02);
    clickOn(monochromeTile01);
    clickOn(monochromeTile00);
    clickOn(monochromeTile12);
    clickOn(monochromeTile10);
    clickOn(monochromeTile22);
    clickOn(monochromeTile21);
    clickOn(monochromeTile20);

    NonMovableGUIPiece piece00 = lookup("#Piece00").query();
    NonMovableGUIPiece piece01 = lookup("#Piece01").query();
    NonMovableGUIPiece piece02 = lookup("#Piece02").query();
    NonMovableGUIPiece piece10 = lookup("#Piece10").query();
    NonMovableGUIPiece piece11 = lookup("#Piece11").query();
    NonMovableGUIPiece piece12 = lookup("#Piece12").query();
    NonMovableGUIPiece piece20 = lookup("#Piece20").query();
    NonMovableGUIPiece piece21 = lookup("#Piece21").query();
    NonMovableGUIPiece piece22 = lookup("#Piece22").query();
    pieceGroup = lookup("#PieceGroup").query();
    Text winText = lookup("#WinLoseTieText").query();
    assertEquals("There was a DRAW!", winText.getText());
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void testExpectedImageFile() {
    MonochromeTile monochromeTile00, monochromeTile01;
    monochromeTile00 = lookup("#Monochrome00").query();
    monochromeTile01 = lookup("#Monochrome01").query();
    clickOn(monochromeTile00);
    clickOn(monochromeTile01);
    NonMovableGUIPiece piece00 = lookup("#Piece00").query();
    NonMovableGUIPiece piece01 = lookup("#Piece01").query();
    assertTrue(piece00.getImage().getUrl().contains("TicTacToePlayer1.png"));
    assertTrue(piece01.getImage().getUrl().contains("TicTacToePlayer2.png"));
  }
}


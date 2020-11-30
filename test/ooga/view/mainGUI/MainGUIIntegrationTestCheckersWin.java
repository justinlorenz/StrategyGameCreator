package ooga.view.mainGUI;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.controller.GameController;
import ooga.model.Game;
import ooga.view.piece.MoveableGUIPiece;
import ooga.view.tile.PolychromeTile;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class MainGUIIntegrationTestCheckersWin extends DukeApplicationTest {

  private Stage myStage;
  private Scene myScene;
  private ResourceBundle myResources;
  private GameController gameController;
  private MainGUI mainGUI;
  private Button saveButton;
  private Button resetButton;
  private Button backButton;
  private Button chatRoomButton;
  private Button endTurnButton;
  private Group pieceGroup;
  private Group tileGroup;

  @Override
  public void start(Stage myStage) {
    this.myStage = myStage;
    myStage.setWidth(710);
    myStage.setHeight(640);
    myResources = ResourceBundle.getBundle("ENGLISH");
    gameController = new GameController();
    Path path = Paths.get("./data/savedGames/CheckersLastMove.game");
    File filePath = new File(path.toAbsolutePath().normalize().toString());
    gameController.loadInGame(filePath);

    mainGUI = new MainGUI(gameController, myResources);

    myScene = mainGUI.setUpScene();
    myScene.getStylesheets().add("guiStyles.css");
    myStage.setScene(myScene);
    myStage.show();

    backButton = lookup("#BackButton").query();
    resetButton = lookup("#ResetButton").query();
    saveButton = lookup("#SaveButton").query();
    chatRoomButton = lookup("#ChatRoomButton").query();
    endTurnButton = lookup("#EndTurnButton").query();

  }
  @Test
  void testButtons() {
    sleep(1, TimeUnit.SECONDS);
    assertFalse(backButton.isDisabled());
    assertFalse(resetButton.isDisabled());
    assertFalse(saveButton.isDisabled());
    assertFalse(chatRoomButton.isDisabled());
  }

  @Test
  void testPlayer2Wins() {
    tileGroup = lookup("#TileGroup").query();
    pieceGroup = lookup("#PieceGroup").query();
    MoveableGUIPiece piece14 = lookup("#Piece14").query();
    PolychromeTile polychromeTile32 = lookup("#Polychrome32").query();
    assertTrue(tileGroup.getChildren().contains(polychromeTile32));
    clickOn(piece14);
    clickOn(polychromeTile32);
    sleep(1, TimeUnit.SECONDS);
  }

}

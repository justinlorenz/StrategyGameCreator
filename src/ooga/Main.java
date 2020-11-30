package ooga;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.dialogBox.CreateLoadDialogBox;

/**
 * Strategy Games - Collection of strategy based grid-type games. Play against an opponent or a CPU.
 * This collection includes Checkers, Tic-Tac-Toe, Connect-4, and Othello

 *
 */
public class Main extends Application {

  public static final String DEFAULT_LANGUAGE = "ENGLISH";


  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    CreateLoadDialogBox createLoadDialogBox = new CreateLoadDialogBox(
        ResourceBundle.getBundle(DEFAULT_LANGUAGE));
    createLoadDialogBox.initializeStage();
  }
}

package ooga.view.mainGUI;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import ooga.controller.GameController;

/**
 * Initialize elements to place on the top window of the Main GUI
 *
 * @author JasonDong
 */

public class MainGUITopWindow extends HBox {

  private final ResourceBundle myResources;
  private final GameController gameController;
  private static final int TOP_INSET = 0;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 0;
  private static final int LEFT_INSET = 10;

  public MainGUITopWindow(double windowHeight, double stageWidth, ResourceBundle myResources,
      GameController gameController) {
    this.myResources = myResources;
    this.gameController = gameController;
    setMinHeight(windowHeight);
    setWidth(stageWidth);
    setUpWindow();
    getStylesheets().add("guiStyles.css");
  }

  /**
   * Helper method to set up the window
   */
  private void setUpWindow() {
    setAlignment(Pos.CENTER);
    setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));
    getStyleClass().add("TopBanner");
  }

}

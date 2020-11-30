package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Superclass for all Dialog Boxes shown on screen to the user for selecting game configuration
 * parameters and windows for Chat and Leaderboard
 *
 *
 */
public abstract class DialogBox extends Stage {

  public static final int STAGE_WIDTH = 710;
  public static final int STAGE_HEIGHT = 640;
  public static final int VBOX_SPACING = 20;
  public static final int BUTTON_HEIGHT = 50;
  public static final int BUTTON_WIDTH = 100;
  private final ResourceBundle myResources;

  public DialogBox(ResourceBundle myResources) {
    this.myResources = myResources;
  }

  /**
   * Abstract method to allow each dialog box to set up their stages, including dimensions and
   * modality
   */
  public abstract void initializeStage();

  /**
   * Set up the scene on the stage
   *
   * @param myResources
   * @return the scene to set on the stage
   */
  public abstract Scene setUpScene(ResourceBundle myResources);

  /**
   * Retrieve the current ResourceBundle for the language the user selected
   *
   * @return
   */
  protected ResourceBundle getMyResources() {
    return myResources;
  }
}

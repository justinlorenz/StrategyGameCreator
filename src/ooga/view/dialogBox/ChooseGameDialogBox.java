package ooga.view.dialogBox;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ooga.controller.GameController;
import ooga.view.guiComboBox.GUIGameComboBox;
import ooga.view.guiComboBox.GUIGridSizeComboBox;
import ooga.view.guiComboBox.GUIStringComboBox;
import ooga.view.guiComboBox.GUIThemeStyleComboBox;
import ooga.view.guiComponentFactory.GUIButtonFactory;

/**
 * Subclass for setting up the DialogBox for allowing the user to pick a game, a grid size, and a
 * theme
 *
 * @author JasonDong
 */

public class ChooseGameDialogBox extends DialogBox {

  private final GameController myGameController;
  private GUIStringComboBox guiGameComboBox;
  private GUIGridSizeComboBox guiGridSizeComboBox;
  private GUIStringComboBox guiThemeComboBox;
  private Button guiContinueButton;
  private PlayerChooserDialogBox playerChooserDialogBox;
  private final GUIButtonFactory guiButtonFactory;
  private static final int TOP_INSET = 100;
  private static final int RIGHT_INSET = 10;
  private static final int BOTTOM_INSET = 100;
  private static final int LEFT_INSET = 10;

  public ChooseGameDialogBox(ResourceBundle myResources, GameController myGameController) {
    super(myResources);
    this.myGameController = myGameController;
    guiButtonFactory = new GUIButtonFactory();
  }

  /**
   * Set up the stage
   */
  @Override
  public void initializeStage() {
    setTitle(getMyResources().getString("ChooseGameDialogBoxTitle"));
    setWidth(STAGE_WIDTH);
    setHeight(STAGE_HEIGHT);
    Scene scene = setUpScene(getMyResources());
    setScene(scene);
    setResizable(false);
    show();
  }

  /**
   * Set up the scene to place onto the stage
   *
   * @param myResources
   * @return scene, the scene to place
   */
  @Override
  public Scene setUpScene(ResourceBundle myResources) {
    VBox myVbox = new VBox(VBOX_SPACING);
    myVbox.setAlignment(Pos.CENTER);
    setUpLogo(myVbox);
    setUpComboBoxes(myVbox);
    setUpButton(myVbox);
    myVbox.setPadding(new Insets(TOP_INSET, RIGHT_INSET, BOTTOM_INSET, LEFT_INSET));

    Scene myScene = new Scene(myVbox);
    myScene.getStylesheets().add("guiStyles.css");
    myVbox.getStyleClass().add("ChooseGameStyles");
    return myScene;

  }

  /**
   * Helper method to set up the logo for the Dialog Box
   *
   * @param myVbox, the main vbox to set the GUI elements on
   */
  private void setUpLogo(VBox myVbox) {
    Region r = new Region();
    VBox.setVgrow(r, Priority.ALWAYS);
    ImageView logo = new ImageView("strategyLogo.png");
    ImageView credits = new ImageView("teamCredits.png");
    myVbox.getChildren().addAll(logo, credits, r);
  }

  /**
   * Set up the 3 comboboxes for game, grid size, and theme
   *
   * @param myVbox
   */
  private void setUpComboBoxes(VBox myVbox) {
    guiGameComboBox = new GUIGameComboBox(getMyResources(), this);
    guiGridSizeComboBox = new GUIGridSizeComboBox(getMyResources(), this);
    guiThemeComboBox = new GUIThemeStyleComboBox(getMyResources(), this);
    myVbox.getChildren().addAll(guiGameComboBox, guiGridSizeComboBox, guiThemeComboBox);
  }

  /**
   * Set up the continue button to move onto the next dialog box
   *
   * @param myVbox
   */
  private void setUpButton(VBox myVbox) {
    guiContinueButton = guiButtonFactory
        .makeButton(getMyResources(), BUTTON_HEIGHT, BUTTON_WIDTH, "ContinueButton",
            e -> createChoosePlayersDialogBox());
    myVbox.getChildren().add(guiContinueButton);
  }

  /**
   * Enable the combobox for choosing theme
   */
  public void enableThemeCB() {
    guiThemeComboBox.setDisable(false);
  }

  /**
   * Enable the combobox for choosing a grid size
   */
  public void enableGridSizeCB() {
    guiGridSizeComboBox.addElements(guiGameComboBox.getValue());
    guiGridSizeComboBox.setDisable(false);
  }

  /**
   * Enable the continue button to move onto the next dialog box
   */
  public void enableContinueButton() {
    guiContinueButton.setDisable(false);
  }


  /**
   * Set up the next combobox, send relevant information like game and dimensions to the
   * GameController
   */
  public void createChoosePlayersDialogBox() {
    myGameController.setCurrentGame(guiGameComboBox.getValue());
    myGameController.setCurrentTheme(guiThemeComboBox.getValue());

    String[] dimensions = guiGridSizeComboBox.getValue().split("x");
    myGameController.setCurrentGridSize(Integer.parseInt(dimensions[0]));

    playerChooserDialogBox = new PlayerChooserDialogBox(getMyResources(), myGameController);
    playerChooserDialogBox.initializeStage();
    close();
  }
}

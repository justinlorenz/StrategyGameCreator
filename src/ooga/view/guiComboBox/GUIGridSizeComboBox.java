package ooga.view.guiComboBox;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import ooga.view.dialogBox.ChooseGameDialogBox;

/**
 * Subclass for the ComboBox responsible for letting the user pick a Grid size
 *
 *
 */


public class GUIGridSizeComboBox extends GUIStringComboBox {

  private final ChooseGameDialogBox chooseGameDialogBox;
  private final Map<String, List<String>> gridSizes = Map
      .of("TicTacToe", List.of("3x3", "5x5", "8x8", "10x10"), "ConnectFour",
          List.of("6x6", "7x7", "8x8", "10x10"), "Othello", List.of("8x8", "10x10", "12x12"),
          "Checkers", List.of("8x8", "10x10", "12x12"), "CheckersMod",
          List.of("8x8", "10x10", "12x12"));

  public GUIGridSizeComboBox(ResourceBundle myResources, ChooseGameDialogBox chooseGameDialogBox) {
    super(myResources);
    this.chooseGameDialogBox = chooseGameDialogBox;
    addElements();
    setStyles();
    setID();
    setOnAction(e -> handleMouseInput(e));
    setDisable(true);
  }

  /**
   * Add the elements the combobox
   */
  @Override
  public void addElements() {
    setValue(getMyResources().getString("ChooseGridSizeDialogBoxTitle"));
  }

  /**
   * Set the styles of the combobox
   */
  @Override
  public void setStyles() {
    getStyleClass().add("ComboBox");
  }

  /**
   * Set the ID of the combobox
   */
  @Override
  public void setID() {
    setId("GridSizeComboBox");
  }

  /**
   * Handle the mouse input
   *
   * @param event
   */
  @Override
  public void handleMouseInput(ActionEvent event) {
    chooseGameDialogBox.enableThemeCB();
  }

  /**
   * Add the elements to the combobox based on what game is picked
   *
   * @param currGame
   */
  public void addElements(String currGame) {
    getItems().clear();
    List<String> possibleGridSizes = gridSizes.get(currGame);
    getItems().addAll(possibleGridSizes);
  }
}

package ooga.view.guiComboBox;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import ooga.view.Games;
import ooga.view.dialogBox.ChooseGameDialogBox;

/**
 * Subclass for the ComboBox responsible for letting the user pick a game
 */


public class GUIGameComboBox extends GUIStringComboBox {

  private final ChooseGameDialogBox chooseGameDialogBox;

  public GUIGameComboBox(ResourceBundle myResources, ChooseGameDialogBox chooseGameDialogBox) {
    super(myResources);
    this.chooseGameDialogBox = chooseGameDialogBox;
    addElements();
    setStyles();
    setID();
    setOnAction(e -> handleMouseInput(e));
  }

  /**
   * Add the games to the combobox
   */
  @Override
  public void addElements() {
    for (Games game : Games.values()) {
      getItems().add(getMyResources().getString(game.toString()));
    }
    setValue(getMyResources().getString("SelectGame"));
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
    setId("GameChooserComboBox");
  }

  /**
   * Handle the mouse input, enable the next combobox in the grid
   *
   * @param event
   */
  @Override
  public void handleMouseInput(ActionEvent event) {
    chooseGameDialogBox.enableGridSizeCB();
  }
}

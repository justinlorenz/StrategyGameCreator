package ooga.view.guiComboBox;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import ooga.view.Themes;
import ooga.view.dialogBox.ChooseGameDialogBox;

/**
 * Subclass for the ComboBox responsible for letting the user pick a theme
 *
 * @author JasonDong
 */
public class GUIThemeStyleComboBox extends GUIStringComboBox {

  private final ChooseGameDialogBox chooseGameDialogBox;

  public GUIThemeStyleComboBox(ResourceBundle myResources,
      ChooseGameDialogBox chooseGameDialogBox) {
    super(myResources);
    this.chooseGameDialogBox = chooseGameDialogBox;
    addElements();
    setStyles();
    setID();
    setOnAction(e -> handleMouseInput(e));
    setDisable(true);
  }

  /**
   * Add themes to the combobox
   */
  @Override
  public void addElements() {
    for (Themes themes : Themes.values()) {
      getItems().add(getMyResources().getString(themes.toString()));
    }
    setValue(getMyResources().getString("SelectTheme"));
  }

  /**
   * Set the style of the combobox
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
    setId("ThemeChooserComboBox");
  }

  /**
   * Handle the mouse input
   *
   * @param event, the method to call on click
   */
  @Override
  public void handleMouseInput(ActionEvent event) {
    chooseGameDialogBox.enableContinueButton();
  }
}

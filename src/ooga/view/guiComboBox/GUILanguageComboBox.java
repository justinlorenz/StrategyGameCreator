package ooga.view.guiComboBox;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import ooga.view.Languages;
import ooga.view.dialogBox.CreateLoadDialogBox;

/**
 * Subclass for the ComboBox responsible for letting the user pick a language
 *
 * @author JasonDong
 */


public class GUILanguageComboBox extends GUIStringComboBox {

  private final CreateLoadDialogBox createLoadDialogBox;

  public GUILanguageComboBox(ResourceBundle myResources, CreateLoadDialogBox createLoadDialogBox) {
    super(myResources);
    this.createLoadDialogBox = createLoadDialogBox;
    addElements();
    setStyles();
    setID();
    setOnAction(e -> handleMouseInput(e));
  }

  /**
   * Add the languages to the combobox
   */
  @Override
  public void addElements() {
    for (Languages language : Languages.values()) {
      getItems().add(language.toString());
    }
    setValue(getMyResources().getString("SelectLanguage"));
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
    setId("LanguageComboBox");
  }

  /**
   * Enable the buttons in Dialog Box once the user selects an element of the combobox
   *
   * @param event
   */
  @Override
  public void handleMouseInput(ActionEvent event) {
    createLoadDialogBox.enableButtons();
  }
}

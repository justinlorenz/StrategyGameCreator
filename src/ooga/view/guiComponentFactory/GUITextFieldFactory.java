package ooga.view.guiComponentFactory;

import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import ooga.exception.BadDataException;

/**
 * Factory used for all textfields used within the GUI, such as entering a name or when saving a
 * file
 */

public class GUITextFieldFactory {

  private ResourceBundle myResources;

  /**
   * Create the textfield
   *
   * @param myResources, the current language that the user has picked
   * @param property,    the property to use in the resource bundle
   * @param maxWidth,    the width of the textfield
   * @return TextField, the textfield to use in the GUI
   */
  public TextField makeTextField(ResourceBundle myResources, String property, int maxWidth) {
    this.myResources = myResources;
    try {
      TextField textField = new TextField();
      textField.setPromptText(myResources.getString(property));
      textField.setMaxWidth(maxWidth);
      textField.setDisable(true);
      textField.setId(property);

      return textField;
    } catch (Exception e) {
      throw new BadDataException("Invalid property string", e.getCause());
    }
  }

}

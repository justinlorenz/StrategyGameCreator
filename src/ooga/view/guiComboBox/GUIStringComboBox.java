package ooga.view.guiComboBox;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

/**
 * Superclass for all ComboBoxes needed for the GUI
 *
 * @author JasonDong
 */

public abstract class GUIStringComboBox extends ComboBox<String> {

  private final ResourceBundle myResources;

  public GUIStringComboBox(ResourceBundle myResources) {
    this.myResources = myResources;
    getStylesheets().add("guiStyles.css");
  }

  /**
   * Abstract method to add elements to the combobox
   */
  public abstract void addElements();

  /**
   * Abstract method to set specific styles to the combobox
   */
  public abstract void setStyles();

  /**
   * Abstract method to allow each combobox to set their own ID
   */
  public abstract void setID();

  /**
   * Abstract method for each combobox to respond to mouse inputs
   *
   * @param event
   */
  public abstract void handleMouseInput(ActionEvent event);

  /**
   * Protected method to return the current ResourceBundle
   *
   * @return myResources, the current language bundle
   */
  protected ResourceBundle getMyResources() {
    return myResources;
  }

}

package ooga.view.guiComponentFactory;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ooga.exception.BadDataException;

/**
 * Factory used to make Buttons to place on the scenes in the GUI
 *
 * @author JasonDong
 */
public class GUIButtonFactory {

  private ResourceBundle myResources;

  /**
   * Method to make the button
   *
   * @param myResources,  the current language that was picked
   * @param buttonHeight, the height of the button
   * @param buttonWidth,  the width of the button
   * @param property,     the property to look for in the Resource Bundle
   * @param handler,      the event to execute on a mouse click
   * @return button, the button to place on the scene
   */
  public Button makeButton(ResourceBundle myResources, double buttonHeight, double buttonWidth,
      String property, EventHandler<ActionEvent> handler) {
    try {
      this.myResources = myResources;
      Button myButton = new Button();
      myButton.getStylesheets().add("guiStyles.css");
      myButton.getStyleClass().add("MainButton");
      myButton.setMinSize(buttonWidth, buttonHeight);
      myButton.setId(property);
      myButton.setText(myResources.getString(property));
      myButton.setOnAction(handler);
      myButton.setDisable(true);
      myButton.setOnMousePressed(e -> myButton.getStyleClass().add("MainButtonClicked"));

      return myButton;

    } catch (Exception e) {
      throw new BadDataException("Invalid button string", e.getCause());
    }

  }

}
